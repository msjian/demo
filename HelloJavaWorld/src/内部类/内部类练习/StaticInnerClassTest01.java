package �ڲ���.�ڲ�����ϰ;

class OuterClass{
	
	private static int a=8;
	
	public static class StaticInnerClass{
		
		public void test(){
			System.out.println(a);
		}
	}
}
public class StaticInnerClassTest01 {

	public static void main(String[] args) {
		OuterClass.StaticInnerClass staticClass=new OuterClass.StaticInnerClass();
		staticClass.test();
	}

}
