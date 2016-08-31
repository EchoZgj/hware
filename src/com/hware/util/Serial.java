package com.hware.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.TooManyListenersException;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
public class Serial implements SerialPortEventListener,Runnable  {
    public static CommPortIdentifier portId; // 串口通信管理类
    public static Enumeration<?> ports;
    public static List<String> portList = new ArrayList<String>();
    public static InputStream inputStream; // 从串口来的输入流
    public static OutputStream outputStream;// 向串口输出的流
	public static SerialPort serialPort; // 串口的引用
	public static StringBuffer sb = new StringBuffer();
	public static Serial serial= null;
	// 0,正常 1 断开了连接
	public static int disconnect=0;

	private Serial() {
	}
	public void run() {
		System.out.println("读取返回信息：");
        receiveMeg();
		System.out.println(serial.sb.toString());

	}

	//扫描本机全部的串口
	public  List scanPorts(){
		ports = CommPortIdentifier.getPortIdentifiers();
		while(ports.hasMoreElements()){
			portId = (CommPortIdentifier) ports.nextElement();
			if(portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
				String name = portId.getName();
				if(!portList.contains(name)) {
					portList.add(name);
				}
			}
		}
		for (int i = 0; i < portList.size(); i++) {
			System.out.println("可用的串口有："+portList.get(i));
		}
		return portList;
	}



	//给一个具体的串口名，然后启动该串口
	public  String openSerialPort(String comm){

			try {
	//			System.out.println("选择你需要打开的串口");
	//			Scanner sc = new Scanner(System.in);
	//			String comm = sc.next();
				portId = CommPortIdentifier.getPortIdentifier(comm);
				serialPort = (SerialPort) portId.open("CompositionAnalysis", 2000);// 打开串口名字为myapp,延迟为2毫秒
			} catch (PortInUseException e) {
				System.out.println("串口已占用！");
				//serial.closeSerialPort();
				throw new RuntimeException("串口已占用！");
			} catch (NoSuchPortException e) {
				// TODO Auto-generated catch block
				System.out.println("未识别到端口！");
				e.printStackTrace();
				throw new RuntimeException("未识别到端口！");
			}
			try {
				inputStream = serialPort.getInputStream();
				outputStream = serialPort.getOutputStream();
			} catch (IOException e) {
				throw new RuntimeException("IO异常！");
			}
			try {
				serialPort.addEventListener(this); // 给当前串口天加一个监听器
			} catch (TooManyListenersException e) {
				throw new RuntimeException("初始化监听失败！");
			}
			serialPort.notifyOnDataAvailable(true); // 当有数据时通知
			try {
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, // 设置串口读写参数
						SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			} catch (UnsupportedCommOperationException e) {
				throw new RuntimeException("串口初始化失败");
			}
			if(serialPort!=null){
				return "success";
			}else{
				return "fail";
			}

		/*}*/
	}


	//关闭串口
	public  void closeSerialPort() {
		try {
			if(outputStream != null)
				outputStream.close();
			if(serialPort != null)
				serialPort.close();
			serialPort = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Serial getSerialInstance(){
		if(serial==null){
			serial = new Serial();
		}
		return serial;
	}

	public static int isConnect(){
		return disconnect;
	}


	//事件， 主要功能是，检测串口是否有可用数据返回，并且赋值给StringBuffer的sb对象
	@Override
	public void serialEvent(SerialPortEvent event) {
		System.out.println(event.getEventType());
		// TODO Auto-generated method stub
		switch (event.getEventType()) {

			case SerialPortEvent.BI:
				System.out.println("通信中断--------------------------------------------");
				break;
			case SerialPortEvent.OE:
			case SerialPortEvent.FE:
			case SerialPortEvent.PE:
			case SerialPortEvent.CD:
			case SerialPortEvent.CTS:
			case SerialPortEvent.DSR:
			case SerialPortEvent.RI:
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
				break;
			case SerialPortEvent.DATA_AVAILABLE://当有可用数据时读取数据,并且给串口返回数据
				byte[] readBuffer = new byte[12];
				try {
					while (inputStream.available() > 0) {
						int numBytes = inputStream.read(readBuffer);
						for (int i = 0; i < numBytes; i++) {
							sb.append(CommonUtil.byteToHexString(readBuffer[i]));
						}
						disconnect=0;
					}

				} catch (IOException e) {
					disconnect=1;
					e.printStackTrace();
					System.out.println(e.getMessage());

				}
				break;
		}
	}

	//向体测仪发送固定的指令，比如：主控，检测状态，开始测试，接收各种数据的命令
	public void sendMeg(String str){
		System.out.println("开始发送指令：");
		try {
			outputStream.write(CommonUtil.hexStringToBytes(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//new Throwable(RuntimeException e);
		}
	}
	//发送个人信息 （自动转16进制并且封包）
	public void sendInfo(String str){
		try {
			System.out.println(CommonUtil.toStringHex(str).toString().toUpperCase());
			outputStream.write(CommonUtil.hexStringToBytes(CommonUtil.toStringHex(str).toString().toUpperCase()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//用来接收  体测仪返回的数据
	public static String receiveMeg(){
		try {
			return serial.sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			int len = serial.sb.length();
			serial.sb.delete(0, len);
		}
		return serial.sb.toString();

	}
}

