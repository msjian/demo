package ö�ٽӿ�ʵ�ּ�����;

interface Calculate{
	int eval(int a,int b);
}
public enum EnumInterface implements Calculate{
	ADD("�ӷ�"){
		@Override
		public int eval(int a,int b) {
			return a+b;
		}
	},
	MINUS("����"){
		@Override
		public int eval(int a,int b) {
			return a-b;
		}
	};
	private final String name;
	private EnumInterface(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public static void main(String[] args) {
		EnumInterface.ADD.eval(1,2);
	}
	
}
