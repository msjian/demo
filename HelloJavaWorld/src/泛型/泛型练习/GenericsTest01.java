package ����.������ϰ;

/**
 * ����ʵ������������������߼��ϵ����࣬�������������������ϲ������ڣ�
 * List<Circle>����List<Shape>�������ͣ�������Canvas�����ж��� void draw(List<Shape> shapes)�Ǵ���ģ�
 * ���Ըĳ�ͨ�������ʽvoid draw(List<?> shapes)�����ǳ����÷��������Ըĳɱ����Ƶķ���ͨ���void draw(List<? extends Shape> shapes)�������Ա�ʾ
 * List<Circle>��List<Rectangle>�ĸ���
 * @author zhaohe
 *
 * @param <T>
 * @param <E>
 */
public class GenericsTest01<T,E> 
{
	
	private T typeName;
	public T getTypeName() {
		return typeName;
	}


	public void setTypeName(T typeName) {
		this.typeName = typeName;
	}


	public E getTypeName2() {
		return typeName2;
	}


	public void setTypeName2(E typeName2) {
		this.typeName2 = typeName2;
	}


	private E typeName2;

	
	public static void main(String[] args) {
		GenericsTest01<Boolean,Integer> gene=new GenericsTest01<Boolean,Integer>();
		gene.setTypeName(false);
		gene.setTypeName2(7);
		System.out.println(gene.getTypeName());
		System.out.println(gene.getTypeName2());
		
	}
	
}
