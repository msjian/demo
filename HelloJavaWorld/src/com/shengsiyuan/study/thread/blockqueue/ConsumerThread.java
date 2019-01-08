package com.shengsiyuan.study.thread.blockqueue;

import java.util.concurrent.BlockingQueue;

public class ConsumerThread extends Thread {
	@Override
	public void run() {
		while(true){
			System.out.println(getName()+"��������׼�����Ѽ���Ԫ��");
			try{
				Thread.sleep(200);
				bq.take();//��������Ѿ��գ�����
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(getName()+"�������"+bq);
		}
		
	}
	private BlockingQueue<String> bq;

	public ConsumerThread(BlockingQueue<String> bq) {
		super();
		this.bq = bq;
	}
}
