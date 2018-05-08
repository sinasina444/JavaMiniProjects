package Homework8;

public class LambdaJobs implements Comparable<LambdaJobs> {
	private final int startTime;
	private final int finishTime;
	private final Double cost;
	
	public LambdaJobs(int startTime,int finishTime) {
		this.startTime = startTime;
		this.finishTime = finishTime;
		cost = 0d;	
	}
	
	public LambdaJobs(int startTime,int finishTime,double cost) {
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.cost = cost;
	}
	
	int getStartTime() {
		return startTime;
	}
	
	int getFinishTime() {
		return finishTime;
	}
	
	Double getCost() {
		return cost;
	}
	
	@Override 
	public int compareTo(LambdaJobs jobs) {
		//if both finishTime are the same,then compare the startTime
		int res = this.getFinishTime() - jobs.getFinishTime();
		if(res == 0) {
			res = this.getStartTime() - jobs.getStartTime();
		}
		return res;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o){
			return true;
		}
		if(o == null || this.getClass() != o.getClass()) {
			return false;
		}
		LambdaJobs that = (LambdaJobs)o;
		return that.getStartTime() == this.getStartTime() && 
				that.getFinishTime() == this.getFinishTime() &&
				that.getCost() == this.getCost();
	}
	
	@Override 
	public int hashCode() {
		int hashcode = this.getStartTime();
		hashcode = 37*hashcode+this.getFinishTime();
		hashcode = 37*hashcode+this.getCost().hashCode();
		return hashcode;
	}
}
