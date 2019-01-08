package com.shengsiyuan.study.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectObjectCopy {
	
	public static void main(String[] args) throws Exception {
		ReflectObjectCopy rc=new ReflectObjectCopy();
		Person person=new Person("tom",9);
		person.setId(1L);
		Person copyPerson=(Person) rc.copy(person);
		System.out.println(copyPerson.getId()+","+copyPerson.getName()+","+copyPerson.getAge());
	}

	//�÷���ʵ�ֶ�Person��Ŀ�������
	public Object copy(Object obj) throws Exception{
		Class<?> classType=obj.getClass();
		System.out.println(classType.getName());
		Object objCopy=classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
		//��ö�������г�Ա����
		Field[] fields=classType.getDeclaredFields();
		for(Field field:fields){
			String fieldName=field.getName();//name,id,age
			String firstLetter=fieldName.substring(0, 1).toUpperCase();
			
			String getMethodName="get"+firstLetter+fieldName.substring(1);
			String setMethodName="set"+firstLetter+fieldName.substring(1);
			
			Method getMethod=classType.getMethod(getMethodName, new Class[]{});
			Method setMethod=classType.getMethod(setMethodName, new Class[]{field.getType()});
			
			Object value=getMethod.invoke(obj, new Object[]{});
			setMethod.invoke(objCopy, new Object[]{value});
			
		}
		return objCopy;
		/**
		 * java.lang.reflect.Constructor.newInstance()����
		 * ǰ�����������캯��������public�ģ����������ڴ��ι��췽��
		 */
		//Constructor<?> cons1=classType.getConstructor(new Class[]{});
		//Object obj1=cons1.newInstance(new Object[]{});
		/**
		 * �������д���ȼ������д���
		 * java.lang.Class.newInstance()����
		 * ǰ�����������캯�����޲εģ�
		 */
		//Object obj0=classType.newInstance();
		/**
		 * ���ι���
		 */
		//Constructor<?> cons2=classType.getConstructor(new Class[]{String.class,int.class});
		//Object obj2=cons2.newInstance(new Object[]{"zhaohe",24});
		
	}

}
class Person{
	private Long id;
	private String name;
	private int age;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Person(){}
	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}
}