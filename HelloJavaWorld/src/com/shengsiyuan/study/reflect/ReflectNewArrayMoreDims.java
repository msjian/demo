package com.shengsiyuan.study.reflect;

import java.lang.reflect.Array;

public class ReflectNewArrayMoreDims {

	public static void main(String[] args) {
		int[] dims=new int[]{3,5,10};
		//�ڶ���������ά�ȣ���������ά���飬ά�ȷֱ��3,5,10
		Object objThree=Array.newInstance(Integer.TYPE, dims);
		
	/*		
 		System.out.println(Integer.TYPE);
		System.out.println(Integer.class);
	*/
		
		Object objTwo=Array.get(objThree, 2);
		
		System.out.println(objTwo instanceof int[][]);
		System.out.println(objTwo.getClass().getComponentType().getName());
		
		Object objOne=Array.get(objTwo, 4);
		
		Array.setInt(objOne, 9, 50);
		
	/*	
	    int result1=Array.getInt(objOne, 9);
		System.out.println(result1);//50
    */		
		int[][][] result=(int[][][]) objThree;
		System.out.println(result[2][4][9]);
		
	}

}
