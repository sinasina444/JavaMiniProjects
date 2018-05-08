package classroom8;

public class Time {
	//解决方法： private volatile long epoch;
	//private atomic long epoch;?
	//issue: 32位还是64位；32位： 32位：会把long 分成两个32位的数，也就没有atomic性，也就是会有race condition
	//32位系统下在setEpoch会分成几步，就会出错的可能
	//要找什么操作才是atomic的operation
	private long  epoch;	//suffer
	
	public Time(long epoch){	//not suffer
		this.epoch = epoch;
	}
	
	public long getEpoch(){	//not suffer race condition
		return epoch;
	}
	
	public void setEpoch(long epoch){ //not suffer race condition
		this.epoch = epoch;//32位系统：not atomic  64位：atomic
	}
}

//get epic time--> set to concurrent time-->get epic time
