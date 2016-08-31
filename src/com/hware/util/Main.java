package com.hware.util;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		/*Serial serial = Serial.getSerialInstance();
		Scanner in=new Scanner(System.in);
		while(true){
		System.out.println("ѡ����һ��������(1:�鿴��ʹ�õĶ˿�  2:��������  3:�رն˿�)");
		int key = in.nextInt();
			switch (key) {
			case 1:
				serial.scanPorts();
				serial.openSerialPort();
				break;
				
			case 2:
				while(true){
				System.out.println("������Ҫ����ָ�");
				String str = in.next();
				serial.sendMeg(str);
				Thread readThread = new Thread(serial);
				try {
					readThread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				readThread.start();
				}
			case 3:
				serial.closeSerialPort();
				System.out.println("�رմ��ڳɹ���");
				break;
			default:
				break;
			}
			
		}*/
		Serial serial = Serial.getSerialInstance();
		Scanner in  = new Scanner(System.in);
		serial.scanPorts();
		serial.openSerialPort("com3");
		System.out.println("已经连接。。。");
		
		while(true){
		String str = in.next();
		serial.sendMeg(str);
		Thread readThread = new Thread(serial);
		try {
			readThread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		readThread.start();
		}
			
		}
}
