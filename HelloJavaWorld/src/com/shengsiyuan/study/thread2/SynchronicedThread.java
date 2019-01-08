package com.shengsiyuan.study.thread2;
//ͬ���߳�
public class SynchronicedThread {
	/**
	 * ����Ԥ��100Ԫ��ÿ�����̴�10�Σ�ÿ��10Ԫ����100Ԫ���������û��ͬ�����ƵĻ����������200Ԫ��������ݻᱻ���ǵ���
	 * ������ͬ�����ƺ󣬽���֮������ݲ������ᱻ���ǣ�����������˻��Ĵ����Ϊ300Ԫ��������200Ԫ��
	 * ͬ�������һ�����̴�Ǯ����һ������ȡͬ����Ǯ����ô�����Ӧ����100Ԫ���䡣
	 * @author zhaohe
	 *
	 */
	//1.�����ڲ���
	class Bank{
		volatile private int account=100;
		public int getAccount(){
			return account;
		}
		//ͬ������,synchronized��volatileֻ����һ������
		/*public synchronized void saveByMethod(int money){
			account+=money;
			}*/
		//ͬ������
		public  void saveByMethod(int money){
			account+=money;
		}
		//ͬ�������
		public void saveByBlock(int money){
			synchronized(this){
				account+=money;
			}
		}
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
				//bank.saveByMethod(10);//������򣬲��ظ�
				bank.saveByBlock(10);//������򣬿����ظ�
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
		SynchronicedThread st=new SynchronicedThread();
		st.useThread();
	}
}
