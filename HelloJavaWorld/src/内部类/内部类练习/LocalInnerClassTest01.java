package �ڲ���.�ڲ�����ϰ;


class OuterClass2{
	
	public void doSomething(){
	    int a=9;
		class LocalInnerClass{
			public void test(){
				System.out.println(a);
			}
		}
		new LocalInnerClass().test();
	}
}
public class LocalInnerClassTest01 {

	public static void main(String[] args) {
		OuterClass2 outerClass=new OuterClass2();
		outerClass.doSomething();
	}

}
