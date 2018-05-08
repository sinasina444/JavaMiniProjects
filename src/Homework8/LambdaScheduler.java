package Homework8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaScheduler {
	
	private List<LambdaJobs> jobScheduledList;
	
	public LambdaScheduler(){
		jobScheduledList = new ArrayList<>();
	}
	List<LambdaJobs> schedule(List<LambdaJobs> jobList){
		if(jobList==null){
			return null;
		}
		//sort jobList by the finishTime of jobs
		Collections.sort(jobList);
		for(LambdaJobs job:jobList) {
			Helper(job);
		}
		return jobScheduledList;
	}
	
	//add new jobs when its startTime is after the previous' fisnishTime
	private void Helper(LambdaJobs job) {
		if(job == null){
			return;
		}
		int finishTime;
		int size = jobScheduledList.size();
		if(size == 0){
			finishTime = 0;
		}else {
			finishTime = jobScheduledList.get(size-1).getFinishTime();
		}
		if(job.getStartTime() >= finishTime) {
			jobScheduledList.add(job);
		}
	}
}
