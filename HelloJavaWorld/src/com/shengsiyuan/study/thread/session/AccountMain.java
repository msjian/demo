package com.shengsiyuan.study.thread.session;

public class AccountMain {
	/**
	 * ����������������Ϊ�ܴ�Ǯ300�Σ�ȡǮֻ����100�Σ����Գ��ֳ���������������������
	 * @param args
	 */
	public static void main(String[] args) {
		Account act=new  Account();
		new DrawThread(act, 100, "ȡǮ��").start();
		new DepositThread(act, 100, "��Ǯ�߼�").start();
		new DepositThread(act, 100, "��Ǯ����").start();
		new DepositThread(act, 100, "��Ǯ�߱�").start();
		
	}
}
