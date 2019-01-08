package com.shengsiyuan.study.thread2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//ͬ���߳�
/*���ʹ��ThreadLocal�����������ÿһ��ʹ�øñ������̶߳���øñ����ĸ�����  
����֮���໥����������ÿһ���̶߳����������޸��Լ��ı���������������������̲߳���Ӱ�졣*/ 
public class SynchronizedThreadLocalVariable {
	//1.�����ڲ���
	class Bank{
		private ThreadLocal<Integer> account=new ThreadLocal<Integer>(){
			protected Integer initialValue(){
				return 100;
			}
		};
		public int getAccount(){
			return account.get();
		}
		//ͬ������,synchronized��volatileֻ����һ������
		/*public synchronized void saveByMethod(int money){
			account+=money;
			}*/
		//ͬ������
		public void saveByMethod(int money){
			account.set(account.get()+money);
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
		SynchronizedThreadLocalVariable st=new SynchronizedThreadLocalVariable();
		st.useThread();
	}
}
