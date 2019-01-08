package com.shengsiyuan.study.thread;
/**
 * ��˳���ִ��0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9
 * @author zhaohe
 *
 */
public class ThreadTest04 {

	public static void main(String[] args) {
		Content content=new Content();
		
		Thread bt1=new BodyThread1(content);
		content=new Content();
		
		Thread bt2=new BodyThread2(content);
		bt1.start();
		bt2.start();
		
	}

}
class Content{
	//��Content��������Ϊֻ��һ����������bt2�Ȳ�ִ��
	public synchronized static void execute1(){
		for(int i=0;i<10;i++){
			try {
				Thread.sleep((long) (Math.random()*500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("hello:"+i);
		}
	}
	//��Content��������Ϊֻ��һ����������bt1�Ȳ�ִ��
	public synchronized static void execute2(){
		for(int i=0;i<10;i++){
			try {
				Thread.sleep((long) (Math.random()*500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("world:"+i);
		}
	}
}
class BodyThread1 extends Thread{
	private Content em;
	public BodyThread1(Content em) {
		this.em=em;
	}
	@Override
	public void run() {
		this.em.execute1();
	}
}
class BodyThread2 extends Thread{
	private Content em;
	public BodyThread2(Content em) {
		this.em=em;
	}
	@Override
	public void run() {
		this.em.execute2();
	}
}