package JAVA��������;

/*public class Animal implements IEat{
	public String name=null;
	public void eat(){System.out.println("�����ˮ����");}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IEat an=new Animal();
		an.eatApple();
		//an.eatOrange();
		//error,staticֻ����ͬԴ�ļ��б�����
		an.eat();
	}
}
*/
//����������ڲ��ṩ�ӿڷ���ʵ�ֵ������ʵ�ֽӿ�
public abstract class Animal implements IEat{
	public String name=null;
	public void eat(){System.out.println("�����ˮ����");}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}

