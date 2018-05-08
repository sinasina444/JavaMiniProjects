package classroom6;

public class FinallyThrows {
	public static void main(String[] args){
		FinallyThrows gotcha = new FinallyThrows();
		int result = gotcha.finallyGotcha();
		System.out.println(result);
		StringBuffer bf = new StringBuffer();
	}
	
	public int finallyGotcha(){
		try {
			if(true) {
				throw new RuntimeException();
			}
			return 1;
		} catch(RuntimeException ioe) {
			return 2;
		} finally {
			return 3;
		}
	}
}
