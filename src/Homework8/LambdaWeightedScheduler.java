package Homework8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LambdaWeightedScheduler {
	//timeList maintains every time stamp from time 0 to the last job's finishTime
	private List<Integer> timeList;
	private List<LambdaJobs> jobScheduledList;
	private int theFinalTime;
	//maintain a hash map for every final time map to the maximum total value
	private HashMap<Integer,Double> timeToValueMap;
	//maintain a hash map for every final time map to a list of scheduled jobs
	private HashMap<Integer,List<LambdaJobs> > timeToScheduleMap;
	
	public LambdaWeightedScheduler(){
		timeList = new ArrayList<Integer>();
		jobScheduledList = new ArrayList<>();
		theFinalTime = 0;
		timeToValueMap = new HashMap<Integer,Double>();
		timeToScheduleMap = new HashMap<>();
	}
	
	public List<LambdaJobs> schedule(List<LambdaJobs> jobList) {
		if(jobList == null) {
			return null;
		}
		//sort jobList by the finishTime of jobs
		Collections.sort(jobList);
		//initial timeList,timeTOScheduleMap and timeToValueMap 
		timeToValueMap.put(0, 0.0);
		timeList.add(0);
		//timeToScheduleMap.put(0,new ArrayList<LambdaJobs>());

		for(LambdaJobs job:jobList) {
			if(job.getStartTime() >= theFinalTime) {
				addJobInSchedule(job);
			}else{
				checkJobInSchedule(job);
			}
		}
		return jobScheduledList;
	}
	
	private void addJobInSchedule(LambdaJobs job) {
		if(job==null){
			return;
		}
		//this job has not conflict with the previous jobs and could be added in list
		//update timeList and jobScheduledList
		if(!timeList.contains(job.getFinishTime())) {
			timeList.add(job.getFinishTime());
		}
		jobScheduledList.add(job);
		//update timeToValueMap,first find out the previous maximum value,and add it to 
		//the job's value and add the time-value to the hash map
		Double totalValue = timeToValueMap.get(theFinalTime) + job.getCost();
		timeToValueMap.put(job.getFinishTime(), totalValue);
		//update theFinalTime at last
		theFinalTime = job.getFinishTime();
		//update the timeToScheduleMap
		timeToScheduleMap.put(theFinalTime, new ArrayList<LambdaJobs>(jobScheduledList));
	}
	
	private void checkJobInSchedule(LambdaJobs job){
		if(job==null){
			return;
		}
		//need to compare two case:1.this job is included;2.this job is not included
		Double valueWithoutJob = timeToValueMap.get(theFinalTime);
		Double previousValue = 0d;
		int jobStartTime = job.getStartTime();
		//find the time zone where the start time is between two previous time stamp
		//and the time stamp just less than the start time
		int theLastFinalTime = 0;
		for(int i=0;i<timeList.size();i++) {
			//notice this cannot be larger than or equal
			if(timeList.get(i) > jobStartTime) {
				if(i <= 1){
					previousValue = 0d;
					theLastFinalTime = 0;
				}else{
					//the previous max value is the max value before the start time of job
					previousValue = timeToValueMap.get(timeList.get(i-1));
					theLastFinalTime = timeList.get(i-1);
				}
				break;
			}
		}
		//compare two cases
		Double valueWithJob = previousValue+job.getCost();
		compareJob(valueWithJob,valueWithoutJob,theLastFinalTime,job);
	}
	
	private void compareJob(double valueWithJob, double valueWithoutJob,int theLastFinalTime,LambdaJobs job){
		if(job==null){
			return;
		}
		if(valueWithJob>valueWithoutJob) {
			//renew the jobScheduledList which contains current job
			if(theLastFinalTime == 0) {
				jobScheduledList = new ArrayList<LambdaJobs>();
				jobScheduledList.add(job);
			}else{
				jobScheduledList = timeToScheduleMap.get(theLastFinalTime);
				jobScheduledList.add(job);
			}
			timeToScheduleMap.put(job.getFinishTime(), new ArrayList<LambdaJobs>(jobScheduledList));
			//update timeList,timeToValueMap and the FinalTime
			if(!timeList.contains(job.getFinishTime())){
				timeList.add(job.getFinishTime());
			}
			timeToValueMap.put(job.getFinishTime(), valueWithJob);
			theFinalTime = job.getFinishTime();
		}
	}
}
