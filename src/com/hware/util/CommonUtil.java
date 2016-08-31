package com.hware.util;

public class CommonUtil {

	// 字符串转十六进制字节数据
	public static byte[] hexStringToBytes(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}
	private static int toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	// 字节数组转字符串
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	// 单个字节转字符
	public static String byteToHexString(byte src) {
		StringBuilder stringBuilder = new StringBuilder("");
		int v = src & 0xFF;
		String hv = Integer.toHexString(v);
		if (hv.length() < 2) {
			stringBuilder.append(0);
		}
		stringBuilder.append(hv);
		return stringBuilder.toString();
	}

	// 将十六进制转字符串
	public static String toHexString(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	//解析体测仪返回的信息，解析完的数据    例子：66#longling#0#1989#Beijing#26#185#
	public static String getInfo(String str){
		String str1 = CommonUtil.toHexString(str.toString().substring(18, str.length() - 5)).replace(CommonUtil.toHexString("00"), "");
		return str1;
	}

	//将个人信息转16进制并且封包
	static int num = 0;
	static int total = 0;
	public static StringBuffer toStringHex(String s) {
		StringBuffer bu = new StringBuffer();
		char[] ch = s.toCharArray();
		bu.append("AABB032100000000");
		bu.append(Integer.toHexString(2 * (ch.length + 6)));
		System.out.println("数据包长度：" + 2 * (ch.length + 6));
		for (int i = 0; i < ch.length; i++) {

			String st = Integer.toHexString(ch[i]);
			num += (int) ch[i];
			if (st.toCharArray().length == 1) {
				bu.append("000" + st);
			} else {
				bu.append("00" + st);
			}
		}
		String str = Integer.toHexString((num + 2 * (ch.length + 6) + 36));
		System.out.println("校验位：" + str.substring(str.length() - 2, str.length()));
		bu.append(str.substring(str.length() - 2, str.length()) + "CCDD");
		num=0;
		return bu;

	}

	/*public static void main(String[] args) {
		StringBuffer bt = CommonUtil.toStringHex("10#wang#0#1993#Beijing#23#174#");
//		String str = CommonUtil.toHexString(bt.toString().substring(18, bt.length() - 5));
		System.out.println(CommonUtil.getInfo(bt.toString()));
		System.out.println(bt.toString());
		String[] array = CommonUtil.getInfo(bt.toString()).split("#");
		for (int i = 0; i <array.length ; i++) {
			System.out.print(i);
			System.out.println("");
			System.out.print(array[i]);
		}
	}*/
	
}
