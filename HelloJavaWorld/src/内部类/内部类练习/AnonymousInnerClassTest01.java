package �ڲ���.�ڲ�����ϰ;

import java.util.Date;

//ʹ�������ڲ���ʱ�����Ǳ����Ǽ̳�һ�������ʵ��һ���ӿڣ��������߲��ɼ�ã�ͬʱҲֻ�ܼ̳�һ�������ʵ��һ���ӿ�
//�����ڲ��಻���ǳ���ģ�������Ҫʵ�ּ̳е������ʵ�ֵĽӿڵ����г��󷽷�
class AnonymousInnerClass{
	
	@SuppressWarnings("deprecation")
	public String get(Date date){
		return date.toLocaleString();
	}
}
public class AnonymousInnerClassTest01 {

	public static void main(String[] args) {
		AnonymousInnerClass a=new AnonymousInnerClass();
		//a.get(new Date());
		String str=a.get(new Date(){
			public String toLocaleString(){
				return "hello"+super.toString();
			}
		});
		System.out.println(str);

	}
}
