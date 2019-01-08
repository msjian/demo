package com.shengsiyuan.study.thread.pool;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * ��ӡ0~300����ֵ
 * @author zhaohe
 *
 */
public class ForkJoinPoolTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		ForkJoinPool pool=new ForkJoinPool();
		//�ύ�ɷֽ��Printtask����
		pool.submit(new PrintTask(0, 300));
		pool.awaitTermination(2, TimeUnit.SECONDS);
		//�ر��̳߳�
		pool.shutdown();
		*/
		int arr[]=new int[100];
		Random rand=new Random();
		int total=0;
		long start=System.currentTimeMillis();
		for(int i=0,len=arr.length;i<len;i++){
			int tmp=rand.nextInt(20);
			arr[i]=tmp;
			total+=arr[i];
		}
		long end=System.currentTimeMillis();
		System.out.println(total+" ��ʱ "+(end-start));
		//����ͨ�ó�
		start=System.currentTimeMillis();
		ForkJoinPool pool=ForkJoinPool.commonPool();
		Future<Integer> future=pool.submit(new CallTask(arr,0,arr.length));
		end=System.currentTimeMillis();
		System.out.println(" ��ʱ "+(end-start));
		System.out.println(future.get());
		pool.shutdown();
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
class CallTask extends RecursiveTask<Integer>{
	//ÿ��"С����"����ۼ�20����
	private static final int THRESHOLD=20;
	private int arr[];
	private int start;
	private int end;
	public CallTask(int[] arr,int start, int end) {
		super();
		this.arr=arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum=0;
		if(end-start<THRESHOLD){
			for(int i=start;i<end;i++){
				sum+=arr[i];
			}
			return sum;
		}else{
			int middle=(start+end)/2;
			CallTask left=new CallTask(arr,start, middle);
			CallTask right=new CallTask(arr,middle, end);
			//ִ��2��С����
			left.fork();
			right.fork();
			return left.join()+right.join();
		}
		
	}
	
}
class PrintTask extends RecursiveAction{
	//ÿ��"С����"���ֻ��ӡ50����
	private static final int THRESHOLD=50;
	private int start;
	private int end;
	public PrintTask(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected void compute() {
		if(end-start<THRESHOLD){
			for(int i=start;i<end;i++){
				System.out.println(Thread.currentThread().getName()+"��iֵ�� "+i);
			}
		}else{
			int middle=(start+end)/2;
			PrintTask left=new PrintTask(start, middle);
			PrintTask right=new PrintTask(middle, end);
			//ִ��2��С����
			left.fork();
			right.fork();
		}
		
	}
	
}

