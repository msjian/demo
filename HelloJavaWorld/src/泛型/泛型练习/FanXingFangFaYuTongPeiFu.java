package ����.������ϰ;

import java.util.Collection;
import java.util.List;

/**
 * ���������£����ͷ������Ժ�ͨ��������滻���������û��������ϵ��ʹ��ͨ������ɡ�������������Ƚ�ǿ���Ƽ�ʹ�÷��ͷ���
 * @author zhaohe
 *
 */
public class FanXingFangFaYuTongPeiFu<E> {
	//���͹�����
	public <T> FanXingFangFaYuTongPeiFu(T t){}
	static <T, S extends T> void fill(Collection<T> des,Collection<S> src){
		
	}
	//ͨ�������
	static <T> void fill2(Collection<T> des,Collection<? extends T> src){
		
	}
	static <T,S extends T> List<S> fill3(Collection<T> des,Collection<S> src){
		return null;
		
	}
	//ͨ�������
	static <T,S extends T> T copy(Collection<T> des,Collection<S> src){
		T last=null;
		for(S a:src){
			des.add(a);
			last=a;
		}
		return last;
	}
	/**
	 * copy��copy2�������ص�������T������ʵ�ʵĲ���������S ���� ? extends T������ʧȥ�˲��������� 
	 * @param des
	 * @param src
	 * @return
	 */
	static <T> T copy2(Collection<T> des,Collection<? extends T> src){
		T last=null;
		for(T a:src){
			des.add(a);
			last=a;
		}
		return last;
	}
	/**
	 * copy3�������ص�������T��ʵ�ʵĲ�������Ҳ��T��û��ʧȥ����������
	 * @param des
	 * @param src
	 * @return
	 */
	static <T> T copy3(Collection<? super T> des,Collection<T> src){
		T last=null;
		for(T a:src){
			des.add(a);
			last=a;
		}
		return last;
	}
	//copy4��copy3����ͬʱ���ڣ�����֪�����ĸ�����Ϊͨ������޺����޶�����
	static <T> T copy4(Collection<T> des,Collection<? extends T> src){
		T last=null;
		for(T a:src){
			des.add(a);
			last=a;
		}
		return last;
	}
}
