package JAVA��������;

public interface IEat {
	public void eat();
	public void sleep();
	//defaultĿ����Ϊ�˽���ӿڵ��޸������е�ʵ�ֲ����ݵ�����
	public default void eatApple(){System.out.println("�����ƻ����");}
	public static  void eatOrange(){System.out.println("�����������");}
	public static void main(String[] args) {
		IEat.eatOrange();//static������ͬ�ļ��б�����
	}
}
