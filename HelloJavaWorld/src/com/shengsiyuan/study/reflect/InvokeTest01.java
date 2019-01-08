package com.shengsiyuan.study.reflect;

import java.lang.reflect.Method;

public class InvokeTest01 {

	public int add(int a,int b){
		return a+b;
	}
	public String echo(String str){
		return "Hello"+str;
	}
	
	public static void main(String[] args) throws Exception {
		/*InvokeTest01 invoke=new InvokeTest01();
		System.out.println(invoke.add(2, 3));
		System.out.println(invoke.echo("zhaohe"));*/
		//���������Ӧ��Class����
		Class<?> classType=InvokeTest01.class;
		//����Class��������Ӧ��ʵ��
		Object invoke=classType.newInstance();
//		System.out.println(invoke instanceof InvokeTest01);
		//param:����������������Ӧ��Class����
		Method addMethod=classType.getMethod("add", new Class[]{int.class,int.class});
		//param:ʵ����������
		Object objAdd=addMethod.invoke(invoke, new Object[]{2,3} );
		System.out.println(objAdd);
		System.out.println("------------------------------");
		Method echoMethod=classType.getMethod("echo", String.class);
		Object objEcho=echoMethod.invoke(invoke, "zhaohe");
		System.out.println(objEcho);
		
		
	}
}
