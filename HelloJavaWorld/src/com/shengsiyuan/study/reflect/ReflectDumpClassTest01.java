package com.shengsiyuan.study.reflect;

import java.lang.reflect.Method;

public class ReflectDumpClassTest01 {

	public static void main(String[] args) throws Exception {
		//?������extands Object
		//���������Ӧ��Class����ʽһ
		Class<?> classType=Class.forName("java.lang.String");
		//���������Ӧ��Class����ʽ��
		Class<?> classType1=ReflectDumpClassTest01.class;
		//��÷���
		Method[] methods=classType.getDeclaredMethods();
		for(Method method:methods){
			System.out.println(method);
		}
		
		
	}
}
