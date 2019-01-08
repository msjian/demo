package com.shengsiyuan.study.thread.threadgroup;

public class ThreadGroupTest{
	public static void main(String[] args) {
		ThreadGroup mainGroup=Thread.currentThread().getThreadGroup();
		System.out.println(mainGroup.isDaemon());
		new MyThreadGroup("���߳�����߳�").start();
		ThreadGroup tg=new ThreadGroup("���߳���");
		tg.setDaemon(true);
		MyThreadGroup tt=new MyThreadGroup(tg,"tg����̼߳�");
		tt.start();
		new MyThreadGroup(tg, "tg����߳���").start();
	}
}

class MyThreadGroup extends Thread{
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println(getName()+"�̵߳�i����"+i);
		}
	}

	public MyThreadGroup(ThreadGroup group, String name) {
		super(group, name);
	}
	public MyThreadGroup(String name) {
		super(name);
	} 
	
}
