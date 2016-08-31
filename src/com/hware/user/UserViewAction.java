package com.hware.user;

import com.hware.core.domain.Testdata;
import com.hware.core.domain.User;
import com.hware.core.service.IResultService;
import com.hware.core.service.ITestDataService;
import com.hware.core.service.IUserService;
import com.hware.util.*;
import com.sun.javafx.sg.PGShape;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Administrator on 2016/7/28.
 */
@Controller
public class UserViewAction {

    @Autowired
    IUserService userService;

    @Autowired
    IResultService resultService;

    @Autowired
    ITestDataService testDataService;

    Serial serial = Serial.getSerialInstance();


    @RequestMapping({"/admin/user.htm"})
    public ModelAndView advert_invoke(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/index");
        User user = userService.getObjById(Long.valueOf(0));
        return mv;
    }


    /**
     * 1、打开主页面，显示可以连接的串口
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getIndex.htm")
    public ModelAndView getIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("machineConnection");
        List<String> portList = serial.scanPorts();
        System.out.println("进去");
        if (portList.isEmpty()) {
            model.addObject("info", "没有扫描到任何串口！请检查");
        } else {
            model.addObject("portList", portList);
        }
        return model;
    }

    @RequestMapping("/json/getIndex.htm")
    public void jsongetIndex(HttpServletRequest request, HttpServletResponse response)  {
        response.addHeader("Access-Control-Allow-Origin", "*");

        List<String> portList = serial.scanPorts();
        try {
            response.setContentType("text/plain");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(JSONArray.fromObject(portList));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 1、点击连接串口，然后会启动该串口
     * 2、如果失败会返回到主页面 重新选择串口
     *
     * @param comm
     * @return
     */
    @RequestMapping("/openSerical.htm")
    public ModelAndView openSerial(HttpServletRequest request, HttpServletResponse response, String port) {
        ModelAndView model = new ModelAndView();
        String info = serial.openSerialPort(port);

        if (info.equals("success")) {

            serial.sendMeg(Instructions.concorl);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg = serial.receiveMeg();

            System.out.println("主控相应" + msg);

            model.setViewName("index");
        } else if (info.equals("fail")) {
            model.setViewName("redirect:/getIndex");
        }
        return model;
    }

    @RequestMapping("/json/openSerical.htm")
    public void jsonOpenSerical(HttpServletRequest request, HttpServletResponse response, String port) {
        response.addHeader("Access-Control-Allow-Origin", "*");

        Map map = new HashMap();

        int i = Serial.isConnect();
        if(i==1){
            map.put("status",2);
            map.put("body","");
            map.put("msg", "串口断开过，应用程序必须重新启动！");
            responseText(response, 1, map);
            return;
        }


        String info = "";
        try {
            info = serial.openSerialPort(port);
            serial.sendMeg(Instructions.concorl);
            Thread.currentThread().sleep(1000);
            String msg = serial.receiveMeg();
            System.out.println("主控相应" + msg);
            if (msg.equals("")) {
                map.put("status", "0");
                map.put("body", "");
                map.put("msg", "主控模式失败！");
                responseText(response, 1, map);
                return;
            } else if (msg.equalsIgnoreCase("aabb8221040000000cb3ccdd")) {
                if (info.equals("success")) {
                    map.put("status", "1");
                    map.put("body", "");
                    map.put("msg", "连接成功！");

                } else if (info.equals("fail")) {
                    map.put("status", "0");
                    map.put("body", "");
                    map.put("msg", "连接失败！");
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            map.put("status", "0");
            map.put("body", "");
            map.put("msg", "主控模式失败！");
            responseText(response, 1, map);
            return;

        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "0");
            map.put("body", "");
            map.put("msg", e.getMessage());

        }

        if (info.equals("fail")) {
            map.put("status", "0");
            map.put("body", "");
            map.put("msg", "连接失败！");
        }


        try {

            PrintWriter writer = response.getWriter();
            writer.print(JSONObject.fromObject(map));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 这是一个ajax请求
     * 点击开始测试，进入该方法，
     * 功能是：
     * 1、接收个人信息，封装成数据包发给体测仪
     * 2、接收体测仪返回状态，并且等待体重测试完毕
     * 3、发送开始测试的命令，并且循环发送检测状态命令
     * 4、接收体测仪返回状态，判断是否检测完毕，
     * 5、发送接收个人信息的命令，接收体测仪返回信息并解析
     * 6、发送接收全身测试的数据，接收体测仪返回信息并解析
     *
     * @param request
     * @param response
     * @param user
     */
    @RequestMapping("/startTest.htm")
    public ModelAndView startTest(HttpServletRequest request, HttpServletResponse response, User user) {
        try {
            serial.sendMeg(Instructions.concorl);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg = serial.receiveMeg();

            System.out.println("主控相应" + msg);


            ModelAndView model = new ModelAndView();
            //定义6个String变量，用于接收体测仪返回的信息
            String hex1 = null;
            String hex2 = null;
            String hex3 = null;
            String hex4 = null;
            String hex5 = null;
            String hex6 = null;
            //将User对象中的信息 拼接成字符串，格式如下：66#longling#0#1989#Beijing#26#185#
            StringBuffer sb = new StringBuffer();
            sb.append(user.getUserNo() + "#");
            sb.append(user.getUserName() + "#");
            sb.append(user.getSex() + "#");
            sb.append("1984#");
            if (user.getAddress() == null || user.getAddress().equals("")) {
                sb.append("*#");
            } else {
                sb.append(user.getAddress() + "#");
            }
            sb.append(user.getAge() + "#");
       /* if(user.getPhone()==null || user.getPhone().equals("")){
            sb.append("*#");
        }else{
            sb.append(user.getPhone()+"#");
        }*/
            sb.append(user.getHeight() + "#");
            //sendInfo方法可以将拼接的字符串自动转成16进制，并且封装好数据包发送给体测仪

            try {
                Boolean run = true;
                while (run) {
                    System.out.println("个人信息：" + sb.toString());
                    serial.sendInfo(sb.toString());
                    Thread.currentThread().sleep(1000);
                    //发送完命令后，线程等待一秒，然后接收体测仪返回的数据
                    hex1 = serial.receiveMeg();
                    System.out.println("hex1=" + hex1);
                    if (hex1.equalsIgnoreCase("AABB8321040000000CB4CCDD")) {
                        run = false;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //判断体测仪返回的信息是否是 ：收信息，开始测量体重
            if (hex1.equalsIgnoreCase("AABB8321040000000CB4CCDD")) {
                boolean flag = true;
                //循环检测状态
                while (flag) {
                    //发送检测状态的命令
                    serial.sendMeg(Instructions.QUERY_STATE);
                    try {
                        Thread.currentThread().sleep(1000);
                        hex2 = serial.receiveMeg();
                        System.out.println("hex2=" + hex2);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        int len = serial.sb.length();
                        serial.sb.delete(0, len);
                    }
                    //判断体测仪返回的信息是否是： 空闲状态
                    //繁忙状态
                    if (hex2.equalsIgnoreCase("AABB8121040000000D02B5CCDD")) {
                        flag = true;
                        //检测完毕
                    } else if (hex2.equalsIgnoreCase("AABB8121040000000D03B6CCDD")) {
                        model.addObject("info", "体重检测完毕");
                        flag = false;
                    } else if (hex2.equalsIgnoreCase("aabb8121040000000d01b4ccdd")) {
                        model = new ModelAndView("redirect:/getIndex.htm");
                        model.addObject("msg", "机器没有接收到命令，请重新连接！");
                        return model;

                       /* serial.sendMeg(Instructions.cancel);
                        Thread.currentThread().sleep(1000);
                        String hex7 =serial.receiveMeg();
                        System.out.println("hex7=" + hex7);*/

                    }
                }
            } else {
                request.setAttribute("info", "个人信息输入异常，请重新输入！");
                throw new RuntimeException("个人信息输入异常，请重新输入！");
                //request.setAttribute("info", "体重检测异常，重新点击开始测试");
            }
            boolean flag1 = true;
            serial.sendMeg(Instructions.START_TEST);
            //开始测试完毕，然后开始  检测状态
            try {

                Thread.currentThread().sleep(1000);
                hex3 = serial.receiveMeg();
                System.out.println("hex3=" + hex3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //判断体测仪返回的数据是否是  ：   开始测试
            if (hex3.equalsIgnoreCase("AABB8421040000000CB5CCDD")) {
                while (flag1) {
                    serial.sendMeg(Instructions.QUERY_STATE);
                    try {
                        Thread.currentThread().sleep(1000);
                        hex4 = serial.receiveMeg();
                        System.out.println("hex4=" + hex4);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //繁忙：正在测试
                    if (hex4.equalsIgnoreCase("AABB8421040000000D02B8CCDD")) {
                        flag1 = true;
                        //测试完毕
                    } else if (hex4.equalsIgnoreCase("AABB8121040000000D05B8CCDD")) {
                        System.out.println("体重测量完毕");
                        flag1 = false;
                    } else if (hex4.equals("")) {
                        model = new ModelAndView("index");
                        return model;
                    }
                }
                //发送接收个人信息的命令
                serial.sendMeg("AABB0521000000000C32CCDD");
                try {
                    Thread.currentThread().sleep(1000);
                    hex5 = serial.receiveMeg();

                    System.out.println("hex5=" + hex5);
                    String usernfo = CommonUtil.getInfo(hex5);
                    String[] array1 = usernfo.split("#");
                    user.setUserNo(Integer.parseInt(array1[0]));
                    user.setUserName(array1[1]);
                    user.setSex(Integer.parseInt(array1[2]));
                    user.setBirthday(array1[3]);
                    user.setAddress(array1[4]);
                    user.setAge(Integer.parseInt(array1[5]));
                    user.setHeight(Double.valueOf(array1[6]));
                    request.setAttribute("user", user);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //向体测仪发送 接收全身测试数据命令
                serial.sendMeg("AABB0621000000000C33CCDD");
                try {

                    Thread.currentThread().sleep(1000);
                    hex6 = serial.receiveMeg();
                    System.out.println("hex6=" + hex6);
                    //封装全身测试数据   等待封装类（），这里要封装的是  全身测试数据（注意：hex6是一个拼接字符串，具体如何拼接，看文档）
                    hex6 = CommonUtil.getInfo(hex6);
                    System.out.println("个人测试信息=" + hex6);
                    Testdata data = new Testdata();
                    String[] array2 = hex6.split("#");
                    data.setUserId(Integer.parseInt(array2[0]));
                   // data.setMeasureDate(array2[1]);
                    data.setAge(Integer.parseInt(array2[2]));
                    if (array2[3].equals("男")) {
                        data.setSex(0);
                    } else {
                        data.setSex(1);
                    }
                    data.setFat(Double.parseDouble(array2[4]));
                    data.setBone(Double.parseDouble(array2[5]));
                    data.setProtein(Double.parseDouble(array2[6]));
                    data.setInterFluid(Double.parseDouble(array2[7]));
                    data.setOutFluid(Double.parseDouble(array2[8]));
                    data.setMuscle(Double.parseDouble(array2[9]));
                    data.setSlimWeight(Double.parseDouble(array2[10]));
                    data.setHeight(Double.parseDouble(array2[11]));
                    data.setWeight(Double.parseDouble(array2[12]));
                    data.setCriteriaWeight(Double.parseDouble(array2[13]));
                    data.setWeightControl(Double.parseDouble(array2[14]));
                    data.setFatControl(Double.parseDouble(array2[15]));
                    data.setMuscleControl(Double.parseDouble(array2[16]));
                    data.setRatioFat(Double.parseDouble(array2[17]));
                    data.setBoneMuscle(Double.parseDouble(array2[18]));
                    data.setBmi(Double.parseDouble(array2[19]));
                    data.setRatioHip(Double.parseDouble(array2[20]));
                    data.setBaseMetabolize(Double.parseDouble(array2[21]));
                    data.setEdemaRatio(Double.parseDouble(array2[22]));
                    data.setInternalFat(Double.parseDouble(array2[23]));
                    data.setSkinFat(Double.parseDouble(array2[24]));
                    data.setBodyAge(Integer.parseInt(array2[25]));
                    data.setScore(Double.parseDouble(array2[26]));
                    testDataService.save(data);
                    //resultService.save(data);
                    System.out.println(data.toString());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("info", "出现异常，重新开始测试");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          /* serial.sendMeg(Instructions.cancel);
            String hex7 =serial.receiveMeg();
            System.out.println("hex7=" + hex7);
*/

            /* serial.sendMeg(Instructions.concorl);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg = serial.receiveMeg();

            System.out.println("主控相应" + msg);*/
        }
        ModelAndView model = new ModelAndView("index");
        return model;

    }

    public static void responseText(HttpServletResponse response, int type, Object o) {
        //type 0 list 1 map

        try {
            response.setContentType("text/plain");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();

            if (type == 0) {
                List l = (List) o;
                writer.print(JSONArray.fromObject(l));
            } else if (type == 1) {
                Map map = (Map) o;
                writer.print(JSONObject.fromObject(map));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/json/startTest.htm")
    public void jsonStartTest(HttpServletRequest request, HttpServletResponse response, User user) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map map = new HashMap();
        //检测前 测试机器状态



        int net = Serial.isConnect();
        if (net == 1) {
            map.put("status", 2);
            map.put("body", "");
            map.put("msg", "串口断开过，请重新启动应用系统！");
            responseText(response, 1, map);
            return;
        }

        if (serial == null) {
            map.put("status", 0);
            map.put("body", "");
            map.put("msg", "未连接人体成分分析仪！");
            responseText(response, 1, map);
            return;
        }

        try {
            serial.sendMeg(Instructions.QUERY_STATE);
            Thread.currentThread().sleep(1000);
            String msg1 = serial.receiveMeg();

            System.out.println("机器状态：============================" + msg1);

            if (msg1.equalsIgnoreCase("aabb8121040000000d01b4ccdd")) {
                System.out.println("机器【空闲】状态");
            } else if (msg1.equalsIgnoreCase("")) {
                map.put("status", "0");
                map.put("body", "");
                map.put("msg", "主控模式失败！");
                responseText(response, 1, map);
                return;
            } else {
                map.put("status", "0");
                map.put("body", "");
                map.put("msg", "机器状态不是空闲状态，请稍后再测！");
                responseText(response, 1, map);
                return;
            }
            System.out.println("机器状态：" + msg1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("body", "");
            map.put("msg", "请检查人体成分分析仪连接状态");
            responseText(response, 1, map);
            return;
        } catch (NullPointerException e) {
            map.put("status", 0);
            map.put("body", "");
            map.put("msg", "连接异常！");
            responseText(response, 1, map);
            return;
        }


        try {

            //定义6个String变量，用于接收体测仪返回的信息
            String hex1 = null;
            String hex2 = null;
            String hex3 = null;
            String hex4 = null;
            String hex5 = null;
            String hex6 = null;

            //将User对象中的信息 拼接成字符串，格式如下：66#longling#0#1989#Beijing#26#185#
            StringBuffer sb = new StringBuffer();
            sb.append(user.getUserNo() + "#");
            sb.append(user.getUserNo()+"#");
            sb.append(user.getSex() + "#");
            sb.append(user.getBirthday() + "#");
            if (user.getAddress() == null || user.getAddress().equals("")) {
                sb.append("*#");
            } else {
                sb.append(user.getAddress() + "#");
            }
            sb.append("*#");
       /* if(user.getPhone()==null || user.getPhone().equals("")){
            sb.append("*#");
        }else{
            sb.append(user.getPhone()+"#");
        }*/
            sb.append(user.getHeight() + "#");
            //sendInfo方法可以将拼接的字符串自动转成16进制，并且封装好数据包发送给体测仪

            try {
                serial.sendInfo(sb.toString());
                Thread.currentThread().sleep(1000);
                //发送完命令后，线程等待一秒，然后接收体测仪返回的数据
                hex1 = serial.receiveMeg();
                System.out.println("hex1=" + hex1);
                if (hex1.equals("")) {
                    map.put("status", 0);
                    map.put("body", "");
                    map.put("msg", "个人信息异常！");
                    responseText(response, 1, map);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                map.put("status", 0);
                map.put("body", "");
                map.put("msg", "个人信息异常！");
                responseText(response, 1, map);
                return;
            }


            /*try {
                Boolean run = true;
                int i = 0;
                while (run) {
                    if (i == 2) {
                        map.put("status", 0);
                        map.put("body", "");
                        map.put("msg", "机器无响应！");
                        responseText(response, 1, map);
                        return;
                    }
                    i++;
                    System.out.println("个人信息：" + sb.toString());
                    serial.sendInfo(sb.toString());
                    Thread.currentThread().sleep(1000);
                    //发送完命令后，线程等待一秒，然后接收体测仪返回的数据
                    hex1 = serial.receiveMeg();

                    System.out.println("hex1=" + hex1);
                    if (hex1.equalsIgnoreCase("aabb8321040000000cb4ccdd")) {
                        run = false;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

                map.put("status", 0);
                map.put("body", "");
                map.put("msg", "个人信息异常！");
                responseText(response, 1, map);
                return;
            }
*/

            /*//判断体测仪返回的信息是否是 ：收信息，开始测量体重
            if (hex1.equalsIgnoreCase("AABB8321040000000CB4CCDD")) {*/
            boolean flag = true;
            int time = 0;
            //循环检测状态
            while (flag) {
                //发送检测状态的命令
                serial.sendMeg(Instructions.QUERY_STATE);
                try {
                    Thread.currentThread().sleep(1000);
                    hex2 = serial.receiveMeg();
                    System.out.println("hex2=" + hex2);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    int len = serial.sb.length();
                    serial.sb.delete(0, len);
                }
                //判断体测仪返回的信息是否是： 空闲状态
                //繁忙状态
                if (hex2.equalsIgnoreCase("AABB8121040000000D02B5CCDD")) {
                    flag = true;
                    //检测完毕
                } else if (hex2.equalsIgnoreCase("AABB8121040000000D03B6CCDD")) {
                    flag = false;
                } else if (hex2.equalsIgnoreCase("aabb8121040000000d01b4ccdd")) {

                    if (time>12){
                        map.put("status",0);
                        map.put("body", "");
                        map.put("msg","测试失败！原因：测试者没站上机器、机器和人体静电没有导通！");
                        responseText(response, 1, map);
                        return;
                    }
                    time++;

                        /*map.put("status",0);
                        map.put("body", "");
                        map.put("msg","测试失败！原因：测试者没站上机器、机器和人体静电没有导通！");
                        responseText(response, 1, map);
                        return;*/
                    //flag=false;

                       /* serial.sendMeg(Instructions.cancel);
                        Thread.currentThread().sleep(1000);
                        String hex7 =serial.receiveMeg();
                        System.out.println("hex7=" + hex7);*/

                }
            }

            boolean flag1 = true;
            serial.sendMeg(Instructions.START_TEST);
            //开始测试完毕，然后开始  检测状态
            try {

                Thread.currentThread().sleep(1000);
                hex3 = serial.receiveMeg();
                System.out.println("hex3=" + hex3);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //判断体测仪返回的数据是否是  ：   开始测试
            if (hex3.equalsIgnoreCase("AABB8421040000000CB5CCDD")) {
                while (flag1) {
                    serial.sendMeg(Instructions.QUERY_STATE);
                    try {
                        Thread.currentThread().sleep(1000);
                        hex4 = serial.receiveMeg();
                        System.out.println("hex4=" + hex4);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //繁忙：正在测试
                    if (hex4.equalsIgnoreCase("AABB8421040000000D02B8CCDD")) {
                        flag1 = true;
                        //测试完毕
                    } else if (hex4.equalsIgnoreCase("AABB8121040000000D05B8CCDD")) {
                        System.out.println("体重测量完毕");
                        flag1 = false;
                    } else if (hex4.equals("")) {
                        map.put("status", 0);
                        map.put("body", "");
                        map.put("msg", "机器没有相应！");
                        responseText(response, 1, map);
                        return;
                    }
                }
                //发送接收个人信息的命令
                serial.sendMeg("AABB0521000000000C32CCDD");
                try {
                    Thread.currentThread().sleep(1000);
                    hex5 = serial.receiveMeg();

                    System.out.println("hex5=" + hex5);
                    String usernfo = CommonUtil.getInfo(hex5);
                    String[] array1 = usernfo.split("#");
                    System.out.println("usernfo="+usernfo);
                    System.out.println(Long.valueOf(array1[0]));
                    User user1 = userService.getBy("userNo",Integer.valueOf(array1[0]));
                    if (user1!=null){
                        user1.setSex(Integer.parseInt(array1[2]));
                        user1.setBirthday(array1[3]);
                        user1.setAddress(array1[4]);
                        user1.setUserName(user.getUserName());
                        // user.setAge(Integer.parseInt(array1[5]));
                        user1.setHeight(Double.valueOf(array1[6]));
                        user1.setMeasureDate(new Date());

                        userService.update(user1);
                        user=user1;
                    }else{
                        //user.setUserNo(Integer.parseInt(array1[0]));
                        //user.setUserName(array1[1]);
                        user.setSex(Integer.parseInt(array1[2]));
                        user.setBirthday(array1[3]);
                        user.setAddress(array1[4]);
                        // user.setAge(Integer.parseInt(array1[5]));
                        user.setHeight(Double.valueOf(array1[6]));
                        user.setMeasureDate(new Date());
                        String uuid = UUID.randomUUID().toString();
                        user.setUuid(uuid);
                        userService.save(user);
                    }


                    request.setAttribute("user", user);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    map.put("status", 0);
                    map.put("body", "");
                    map.put("msg", "个人信息接收异常!");
                    responseText(response, 1, map);
                    return;
                }

                try {
                    //向体测仪发送 接收全身测试数据命令
                    serial.sendMeg("AABB0621000000000C33CCDD");

                    Thread.currentThread().sleep(1000);
                    hex6 = serial.receiveMeg();
                    System.out.println("hex6=" + hex6);
                    //封装全身测试数据   等待封装类（），这里要封装的是  全身测试数据（注意：hex6是一个拼接字符串，具体如何拼接，看文档）
                    hex6 = CommonUtil.getInfo(hex6);
                    System.out.println("个人测试信息=" + hex6);

                    Testdata data = testDataService.getBy("userId",user.getId());
                    boolean save = false;
                    if (data==null){
                        data=new Testdata();
                        save=true;
                    }else{
                        save=false;
                    }

                    //体型判定


                    String[] array2 = hex6.split("#");
                    data.setUserId(Integer.parseInt(array2[0]));
                    data.setMeasureDate(user.getMeasureDate());
                    data.setAge(Integer.parseInt(array2[2]));
                    if (array2[3].equals("男")) {
                        data.setSex(0);
                    } else {
                        data.setSex(1);
                    }

                    data.setFat(Double.parseDouble(array2[4]));
                    data.setBone(Double.parseDouble(array2[5]));
                    data.setProtein(Double.parseDouble(array2[6]));
                    data.setInterFluid(Double.parseDouble(array2[7]));
                    data.setOutFluid(Double.parseDouble(array2[8]));
                    data.setMuscle(Double.parseDouble(array2[9]));
                    data.setSlimWeight(Double.parseDouble(array2[10]));
                    data.setHeight(Double.parseDouble(array2[11]));
                    data.setWeight(Double.parseDouble(array2[12]));
                    data.setCriteriaWeight(Double.parseDouble(array2[13]));
                    data.setWeightControl(Double.parseDouble(array2[14]));
                    data.setFatControl(Double.parseDouble(array2[15]));
                    data.setMuscleControl(Double.parseDouble(array2[16]));
                    data.setRatioFat(Double.parseDouble(array2[17]));
                    data.setBoneMuscle(Double.parseDouble(array2[18]));
                    data.setBmi(Double.parseDouble(array2[19]));
                    data.setRatioHip(Double.parseDouble(array2[20]));
                    data.setBaseMetabolize(Double.parseDouble(array2[21]));
                    data.setEdemaRatio(Double.parseDouble(array2[22]));
                    data.setInternalFat(Double.parseDouble(array2[23]));
                    data.setSkinFat(Double.parseDouble(array2[24]));
                    data.setBodyAge(Integer.parseInt(array2[25]));
                    data.setScore(Double.parseDouble(array2[26]));

                    if(user.getSex()==0){
                        //男性判定
                        if(data.getBmi()<Double.valueOf(18.5)){
                            if (data.getRatioFat()<Double.valueOf(10)){
                                user.setBodyConfirm(7);
                            }else if (Double.valueOf(10)<=data.getRatioFat() && data.getRatioFat()<=Double.valueOf(20)){
                                user.setBodyConfirm(4);
                            }else{
                                user.setBodyConfirm(1);
                            }
                        }else if (Double.valueOf(18.5)<=data.getBmi() && data.getBmi()<=Double.valueOf(24)){
                            if (data.getRatioFat()<Double.valueOf(10)){
                                user.setBodyConfirm(8);
                            }else if (Double.valueOf(10)<=data.getRatioFat() && data.getRatioFat()<=Double.valueOf(20)){
                                user.setBodyConfirm(5);
                            }else{
                                user.setBodyConfirm(2);
                            }
                        }else{
                            if (data.getRatioFat()<Double.valueOf(10)){
                                user.setBodyConfirm(9);
                            }else if (Double.valueOf(10)<=data.getRatioFat() && data.getRatioFat()<=Double.valueOf(20)){
                                user.setBodyConfirm(6);
                            }else{
                                user.setBodyConfirm(3);
                            }
                        }
                    }else{
                        //女性判定

                        if(data.getBmi()<Double.valueOf(18.5)){
                            if (data.getRatioFat()<Double.valueOf(18)){
                                user.setBodyConfirm(7);
                            }else if (Double.valueOf(18)<=data.getRatioFat() && data.getRatioFat()<=Double.valueOf(28)){
                                user.setBodyConfirm(4);
                            }else{
                                user.setBodyConfirm(1);
                            }
                        }else if (Double.valueOf(18.5)<=data.getBmi() && data.getBmi()<=Double.valueOf(24)){
                            if (data.getRatioFat()<Double.valueOf(18)){
                                user.setBodyConfirm(8);
                            }else if (Double.valueOf(18)<=data.getRatioFat() && data.getRatioFat()<=Double.valueOf(28)){
                                user.setBodyConfirm(5);
                            }else{
                                user.setBodyConfirm(2);
                            }
                        }else{
                            if (data.getRatioFat()<Double.valueOf(18)){
                                user.setBodyConfirm(9);
                            }else if (Double.valueOf(18)<=data.getRatioFat() && data.getRatioFat()<=Double.valueOf(28)){
                                user.setBodyConfirm(6);
                            }else{
                                user.setBodyConfirm(3);
                            }
                        }
                    }

                    user.setFlag(0);
                    user.setAge(data.getAge());
                    user.setMeasureDate(new Date());
                    userService.update(user);
                    data.setUserId(user.getId());
                    data.setUuid(user.getUuid());
                    if (save){
                        testDataService.save(data);
                    }else{
                        testDataService.update(data);
                    }

                    //testDataService.save(data);
                    //resultService.save(data);
                    System.out.println(data.toString());
                    map.put("status", 1);
                    Map map1 = new HashMap();
                    map1.put("user", JSONObject.fromObject(user));
                    map1.put("data", JSONObject.fromObject(data));
                    map1.put("time", com.hware.tools.CommUtil.formatTime("yyyy-MM-dd HH:mm:ss",user.getMeasureDate()));
                    map.put("body", JSONObject.fromObject(map1));
                    map.put("msg", "成功！");
                    responseText(response, 1, map);
                    return;

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                    map.put("status", 0);
                    map.put("body", "");
                    map.put("msg", "个人信息接收异常!");
                    responseText(response, 1, map);
                    return;
                }
            } else {

                map.put("status", 0);
                map.put("body", "");
                map.put("msg", "出现异常，重新开始测试");
                responseText(response, 1, map);
                return;
            }
        } catch (Exception e) {

            e.printStackTrace();
            map.put("status", 0);
            map.put("body", "");
            map.put("msg", "出现异常，请重新开始测试");
            responseText(response, 1, map);
            return;
        } finally {
           /*serial.sendMeg(Instructions.cancel);
            String hex7 =serial.receiveMeg();
            System.out.println("hex7=" + hex7);
             serial.sendMeg(Instructions.concorl);
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg = serial.receiveMeg();

            System.out.println("主控相应" + msg);*/
        }

    }


    @RequestMapping("/json/cancelControl.htm")
    public void cancelControl(HttpServletRequest request, HttpServletResponse response) {
        if (serial.disconnect==1){
            Map map = new HashMap();
            map.put("status", 0);
            map.put("body", "");
            map.put("msg", "机器不是【空闲】状态，请稍后再试！");
            responseText(response, 1, map);
            return;
        }
        response.addHeader("Access-Control-Allow-Origin", "*");

        String hex2="";
        try {
            serial.sendMeg(Instructions.QUERY_STATE);
            Thread.currentThread().sleep(1000);
            hex2 = serial.receiveMeg();
            System.out.println("hex2=" + hex2);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            int len = serial.sb.length();
            serial.sb.delete(0, len);
        }
        Boolean flag1= false;
        Map map = new HashMap();

        // 空闲
        if (hex2.equalsIgnoreCase("aabb8121040000000d01b4ccdd")) {

        } else{
            map.put("status", 0);
            map.put("body", "");
            map.put("msg", "机器不是【空闲】状态，请稍后再试！");
            responseText(response, 1, map);
            return;
        }

        try {

            serial.sendMeg(Instructions.cancel);
            Thread.currentThread().sleep(1000);
            String hex7 = serial.receiveMeg();
            System.out.println("hex7=" + hex7);
            map.put("status", 1);
            map.put("body", "");
            map.put("msg", "主控模式已取消！");
            responseText(response, 1, map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serial.closeSerialPort();
            int len = serial.sb.length();
            serial.sb.delete(0, len);
        }
    }






    @RequestMapping("/json/dataInfo.htm")
    public void dataInfo(HttpServletRequest request, HttpServletResponse response,String id) {
        String path="http://yy.yingyanghome.com/hware_server/commentInfoDisplay.htm";
        response.addHeader("Access-Control-Allow-Origin", "*");
        User user = userService.getObjById(Long.valueOf(id));

        StringBody stringBody = null;
        ContentType contentType = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
        stringBody = new StringBody(user.getUuid(), contentType);
        MultipartEntity entity = new MultipartEntity();
        //entity.addPart("img", fileBody);

        entity.addPart("uuid", stringBody);
        try {
            String msg = HttpClientPostMethod.post(path, entity);
            System.out.println(msg);
            JSONObject jsonObject = JSONObject.fromObject(msg);
            if (jsonObject.getInt("status")==1){
                user.setComment(jsonObject.getString("body"));
                userService.update(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        Testdata testdata = testDataService.getBy("userId", Long.valueOf(id));
        Map map = new HashMap();
        map.put("user",JSONObject.fromObject(user));
        map.put("testdata",JSONObject.fromObject(testdata));
        responseText(response,1,map);
    }

    @RequestMapping("/json/dataList.htm")
    public void dataList(HttpServletRequest request, HttpServletResponse response,String currentPage,Long id,String userName) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        int page = 0;
        if(currentPage ==null || currentPage.equals("")){
            page=1;
        }else{
            page=Integer.valueOf(currentPage);
        }
        Map query = new HashMap();
        query.put("id",id);
        query.put("userName",userName);
        PageBean pageBean= userService.getFenDaList(8, page, query);
        List l = pageBean.getList();
        List list = new ArrayList();
        for(int i=0;i<l.size();i++){
            User user = (User) l.get(i);
            Map map = new HashMap();
            map.put("id",user.getId());
            map.put("sex", user.getSex());
            map.put("userName",user.getUserName());
            map.put("birthday",user.getBirthday());
            map.put("bodyConfirm",user.getBodyConfirm());
            map.put("weight",user.getWeight());
            list.add(JSONObject.fromObject(map));
        }

        Map info = new HashMap();
        info.put("objs", JSONArray.fromObject(list));
        info.put("totalPage", pageBean.getTotalPage());
        info.put("pageSize", pageBean.getPageSize());
        info.put("rows", pageBean.getAllRow());
        info.put("currentPage", pageBean.getCurrentPage());
        responseText(response,1,info);
    }

    @RequestMapping("/admin/dataList.htm")
    public ModelAndView dataListV2(HttpServletRequest request, HttpServletResponse response,String currentPage,Long id,String userName) {
        int page = 0;
        if(currentPage ==null || currentPage.equals("")){
            page=1;
        }else{
            page=Integer.valueOf(currentPage);
        }
        Map query = new HashMap();
        if (id!=null) {
            query.put("id", id + "");
        }
        if (userName!=null && !userName.equals("")) {
            query.put("userName", userName);
        }
        PageBean pageBean = userService.getFenDaList(8, page, query);

        ModelAndView mv = new JModelAndView("/searchUser.jsp",request, response);
        CommUtil.savePageBeanModelAndView("", "", "", pageBean, mv);
        mv.addObject("currentPage",currentPage);
        return mv;
    }

    /**
     * 根据id获取评论信息
     * @param id
     * @return
     */
    @RequestMapping("/json/commentInfoDisplay.htm")
    public void commentInfoDisplay(HttpServletRequest request, HttpServletResponse response,Long id,String comment){

        response.addHeader("Access-Control-Allow-Origin", "*");
        JSONObject jsonObject=new JSONObject();
        User user=userService.getObjById(id);
        String oldComment=null;
        if(user!=null){
            oldComment=user.getComment();
        }
        if(!oldComment.equals(comment)){
            user.setComment(comment);
            userService.update(user);
        }
        Map map = new HashMap();
        map.put("user", JSONObject.fromObject(user));
        responseText(response,1,map);
    }

}
