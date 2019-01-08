package com.shengsiyuan.study.thread.blockqueue;

import java.util.concurrent.BlockingQueue;

public class ProducerThread extends Thread {
	@Override
	public void run() {
		String[] strArr=new String[]{
				"JAVA","Struts","Spring"
		};
		for(int i=0;i<100;i++){
			System.out.println(getName()+"������׼����������Ԫ��");
			try{
				Thread.sleep(200);
				bq.put(strArr[i%3]);//��������Ѿ���������
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(getName()+"�������"+bq);
		}
		
	}
	private BlockingQueue<String> bq;

	public ProducerThread(BlockingQueue<String> bq) {
		super();
		this.bq = bq;
	}
}
