package com.shengsiyuan.study.thread.control;

public class JoinTest extends Thread{
	public JoinTest(String name){
		super(name);
	}
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(this.getName()+" "+i);
		}
	}
	public static void main(String[] args) throws InterruptedException {
		new JoinTest("���߳�").start();
		for(int i=0;i<10;i++){
			if(i==3){
				JoinTest jt=new JoinTest("��join���߳�");
				jt.start();
				//join��ʹ�ñ�join���߳�ִ�н�����Ż�ִ�������߳�
				jt.join();
				//jt.join(1000);
				//jt.join(1000, 10000);
			}
			System.out.println(Thread.currentThread().getName()+" "+i);
		}
	}
}























