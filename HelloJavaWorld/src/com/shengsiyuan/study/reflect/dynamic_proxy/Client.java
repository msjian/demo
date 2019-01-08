package com.shengsiyuan.study.reflect.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {

	public static void main(String[] args) throws Exception {
		RealSubject realSub=new RealSubject();
		InvocationHandler h=new DynamicProxySubject(realSub);
		Class<?> classType=h.getClass();
		/**
		 * ��̬�������ʵ��
		 * ��̬��������أ���Ϊ��̬������ʵ����InvocationHandler�ӿڣ�,ʵ����ʵ��Ľӿ�,����
		 * Proxy����getProxyClass�������ص���Proxy��Class��
		 */
		Class<?> proxyClass=Proxy.getProxyClass(classType.getClassLoader(), realSub.getClass().getInterfaces());
		Subject subject0=(Subject) proxyClass.getConstructor(InvocationHandler.class).newInstance(h);
		subject0.request();
		System.out.println(subject0.getClass());//com.sun.proxy.$Proxy0
		
		Subject subject1=(Subject) Proxy.newProxyInstance(classType.getClassLoader(), realSub.getClass().getInterfaces(), h);
		subject1.request();
		
	/**
	 * ���Թ���invoke()�Ĺ����ǣ� 
	 * ��1���õ�Proxy���Class����Proxy.getProxyClass��ʵ������ʵ����Ľӿڣ�
	 * ��2���õ��������ʵ����com.sun.proxy.$Proxy0�������ڼ䶯̬����
	 * ��3�����ô�����ʵ���Ľӿڷ���������Դ���룬����$Proxy0.request()�����е�����h.invoke(this, m3, null)������
	 * 	        �ڱ����У���ΪDynamicProxySubject��invoke()������
	 *     ��thisΪ����ʵ��������󷵻ص�subject��;
	 *     ��m3Ϊ Class.forName("***.RealSubject").getMethod("request",new Class[0]);
	 *     ��argsΪ��������Ϊ�����е�request()����û�в�������ʱ��args���ǵ���null��������DynamicProxySubject.invok()����֤;
	 * �ܽ�������$Proxy0ͨ������InvocationHandler.invok()����ʵ�ֶ�RealSubject�Ĳ�����
	 *		 InvocationHandler.invok()�����ĵ����ڵ���$Proxy0.request()�ӿڷ���ʱִ�С�
	 */
	/* $Proxy0Դ���룬����ΪProxy
	 *  public final String toString() {
			 return (String) super.h.invoke(this, m2, null);
		}
	*/
		
	/*
		To create a proxy for some interface Foo: 

		     InvocationHandler handler = new MyInvocationHandler(...);
		     Class<?> proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(), Foo.class);
		     Foo f = (Foo) proxyClass.getConstructor(InvocationHandler.class).
		                     newInstance(handler);
		 
		or more simply: 
		     Foo f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
		                                          new Class<?>[] { Foo.class },
		                                          handler);
     */

		
	}
}
