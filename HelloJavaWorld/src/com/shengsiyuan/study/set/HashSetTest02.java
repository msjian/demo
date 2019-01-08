package com.shengsiyuan.study.set;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest02 {

	public static void main(String[] args) {
		HashSet<Student> set=new HashSet<Student>();
		set.add(new Student("a"));
		set.add(new Student("a"));
		set.add(new Student("b"));
		//System.out.println(set);
		for(Iterator it=set.iterator();it.hasNext();){
			System.out.println(it.next());
		}
	}

}
class Student{
	String name;
	public Student(String name){
		this.name=name;
	}
	//�򼯺������Ԫ���ǣ���Ҫ�ж�name�Ƿ���ȣ�����ֻҪ����name�Ĺ�ϣֵ����
	public int hashCode(){
		return this.name.hashCode();
	}
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		if(null!=obj&&obj instanceof Student){
			if(this.name.equals(((Student)obj).name)){
				return true;
			}
		}
		return false;
	}
}
