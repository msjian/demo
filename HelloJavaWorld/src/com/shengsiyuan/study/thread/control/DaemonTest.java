package com.shengsiyuan.study.thread.control;

/**
 * ���ǰ̨�̶߳���������̨�̻߳��Զ�����
 * ��̨�̴߳��������߳�Ĭ���Ǻ�̨�߳�
 * @author zhaohe
 *
 */
public class DaemonTest extends Thread{
	public static void main(String[] args) {
		DaemonTest t=new DaemonTest();
		System.out.println(t.isDaemon());
		t.setDaemon(true);//���óɺ�̨�߳�
		t.start();
		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
		//����ִ�е��ô���ǰ̨�߳̽���
		//��̨�߳�Ҳ��֮����
	}
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println(getName()+" "+i);
		}
	}

}
