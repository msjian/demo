package com.shengsiyuan.study.reflect.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

public class DynamicProxyVector implements InvocationHandler {

	private Object obj;
	public DynamicProxyVector(Object obj) {

		this.obj=obj;
	}
	
	private static Object factory(Object obj){
		Class<?> classType=obj.getClass();
		//Ϊʲô����new DynamicProxyVector(obj).getClass().getClassLoader();
		return Proxy.newProxyInstance(classType.getClassLoader(),
				classType.getInterfaces(),new DynamicProxyVector(obj));
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("before calling:"+method);
		if(args!=null){
			for(Object obj:args){
				System.out.println(obj);
			}
		}
		Object objRetrun=method.invoke(obj, args);
		System.out.println("after calling"+method);
		return objRetrun;
	}
	public static void main(String[] args) {
		System.out.println("----����ǿ��ת������ΪVectorʵ����List�ӿ�----");
		//����ǿ��ת������ΪVectorʵ����List�ӿ�
		List<String> list=(List<String>) factory(new Vector());
		list.add("New");
		list.add("York");
		System.out.println("------��ӡlist�����toString()����������invoke(),û�в���---------------");
		//��ӡlist�����toString()����������invoke(),û�в���
		System.out.println(list);
		System.out.println("-----ɾ��index=1��,�ٴ�ӡ--------------");
		//ɾ��
		list.remove(1);
		System.out.println(list);
	}

}
