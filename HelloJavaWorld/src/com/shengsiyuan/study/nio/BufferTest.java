package com.shengsiyuan.study.nio;

import java.nio.CharBuffer;

public class BufferTest {
	public static void show(CharBuffer buff){
		System.out.println("capacity: "+buff.capacity());
		System.out.println("limit: "+buff.limit());
		System.out.println("position: "+buff.position());
	}
	public static void main(String[] args) {
		//������ͨbuffer���ɱ��ͣ�������buffer�Ļ����ϴ���buffer(buff.allocate(capacity))���ɱ���
		CharBuffer buff = CharBuffer.allocate(8);
		BufferTest.show(buff);
		//
		buff.put('a');
		buff.put('b');
		buff.put('a');
		BufferTest.show(buff);
		//Ϊ��buffer��ȡ������׼��
		buff.flip();
		BufferTest.show(buff);
		//Ϊ��buffer��װ��������׼��
		buff.clear();
		BufferTest.show(buff);
		//System.out.println(buff.get(2));
		buff.put('c');
		System.out.println(buff.get(3));
		BufferTest.show(buff);
		
		
	}
}
