package com.shengsiyuan.study.reflect;

import java.lang.reflect.Method;

public class AccessPrivateMethod {

	public static void main(String[] args) throws Exception {
		Private pri=new Private();
		
		Class<?> classType=pri.getClass();
		
		Method method=classType.getDeclaredMethod("sayHello", new Class[]{String.class});
		/**
		 * ���ڷ���private�����������java.lang.IllegalAccessException
		 * ����취��ѹ��Java�ķ��ʿ��ƻ��ƣ�������´���
		 */
		method.setAccessible(true);
		
		String result=(String) method.invoke(pri, new Object[]{"Jerry"});
		
		System.out.println(result);
		
	}

}
class Private{
	private String sayHello(String str){
		return "Hello:"+str;
	}
}