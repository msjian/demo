package com.shengsiyuan.study.thread;

public class DemoSample {

	private int number;
	public synchronized void increase(){
		//һ��Ҫ��whileѭ����ifֻ���ж�һ�Σ�ֻ������2���߳�
		while(0!=number){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		number++;
		System.out.println(number);
		notify();
	}
	public synchronized void decrease(){
		//һ��Ҫ��whileѭ����ifֻ���ж�һ�Σ�ֻ������2���߳�
		while(0==number){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		number--;
		System.out.println(number);
		notify();
	}
	

}
