package com.shengsiyuan.study.thread;

public class ThreadTest03 {

	public static void main(String[] args) {
		Runnable r=new HelloThread();
		Thread r1=new Thread(r);
		//r=new HelloThread();
		Thread r2=new Thread(r);
		
		r1.start();
		r2.start();
	}

}
class HelloThread implements Runnable{
	int i;//r1,r2����ͬһ�������i
	@Override
	public void run() {
		//int i=0;//r1,r2������һ���ֲ������Ŀ���������Ӱ��
		while(true){
			System.out.println("number:"+i++);
			try{
				Thread.sleep((long) (Math.random()*1000));
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(i==50){
				break;
			}
		}
	}
}
