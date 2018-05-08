package Classes;



public class UniqueNumberGenerator {
	private long sequence;
	public UniqueNumberGenerator(){
		this.sequence = 0L;
	}
	
	//Time--->
	//ThreadA:next#1
	//ThreadB:next#1 next#2 就错了，因为两个都返回了0
	public long next(){
		//return sequence++;//race condition
		//或者
		long returnResult = sequence;
		sequence = sequence+1;
		return returnResult;
	}
}
