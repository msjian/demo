package com.shengsiyuan.study.thread2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.management.monitor.Monitor;

//ͬ���߳�
public class SynchronizedThreadReentrantLock {
	//1.�����ڲ���
	class Bank{
		 private int account=100;
		 private Lock lock=new ReentrantLock();
		public int getAccount(){
			return account;
		}
		//ͬ������,synchronized��volatileֻ����һ������
		/*public synchronized void saveByMethod(int money){
			account+=money;
			}*/
		//ͬ������
		public void saveByMethod(int money){
			lock.lock();
			try{
			account+=money;
			}finally{
				lock.unlock();
			}
		}
		/*//ͬ�������
		public void saveByBlock(int money){
			synchronized(this){
				account+=money;
			}
		}*/
	}
	//2.�߳���
	class NewThread implements Runnable{
		private Bank bank;
		public NewThread(Bank bank){
			this.bank=bank;
		}
		@Override
		public void run() {
			for(int i=0;i<10;i++){
				bank.saveByMethod(10);//������򣬲��ظ�
				//bank.saveByBlock(10);//������򣬿����ظ�
				System.out.println(i+"�˻����Ϊ��"+bank.getAccount());
			}
		}
	}
	//3.�����̣߳������ڲ���
	public void useThread(){
		Bank bank=new Bank();
		NewThread new_thread=new NewThread(bank);
		System.out.println("�߳�1");
		//new_thread.start();//error�������淽ʽstart
		Thread thread1=new Thread(new_thread);
		thread1.start();
		System.out.println("�߳�2");
		//new_thread.start();//error�������淽ʽstart
		Thread thread2=new Thread(new_thread);
		thread2.start();		
	}
	public static void main(String[] args) {
		SynchronizedThreadReentrantLock st=new SynchronizedThreadReentrantLock();
		st.useThread();
	}
}
