package com.shengsiyuan.study.reflect.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxySubject implements InvocationHandler {

	/**
	 * private RealSubject realSub;ֻ�ܴ���RealSubject������Object���Դ���������
	 */
	private Object obj;
	public DynamicProxySubject(Object obj) {

		this.obj=obj;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("Pre request!");
		
		System.out.println(args==null);
		method.invoke(obj, args);
		
		System.out.println("Post request!");
		return null;
	}
	/**
	 * ����ʵ��ɫ����֮ǰ�������ɫ���ӹ���
	 */
	private void preRequest(){
		System.out.println("ProxySubject Pre Request!");
	}
	/**
	 * ����ʵ��ɫ����֮�󣬴����ɫ���ӹ���
	 */
	private void postRequest(){
		System.out.println("ProxySubject Post Request!");
	}

}
