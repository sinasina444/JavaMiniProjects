package Homework8;

import java.util.List;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<LambdaJobs> joblist = new ArrayList<LambdaJobs>();
		LambdaJobs job1 = new LambdaJobs(1,3);
		LambdaJobs job2 = new LambdaJobs(2,6);
		LambdaJobs job3 = new LambdaJobs(5,7);
		LambdaJobs job4 = new LambdaJobs(3,4);
		LambdaJobs job5 = new LambdaJobs(4,5);
		LambdaJobs job6 = new LambdaJobs(4,6);
		joblist.add(job1);
		joblist.add(job2);
		joblist.add(job3);
		joblist.add(job4);
		joblist.add(job5);
		joblist.add(job6);
		LambdaScheduler scheduler = new LambdaScheduler();
		List<LambdaJobs> schedulelist = scheduler.schedule(joblist);
		for(LambdaJobs job:joblist){
			System.out.println(job.getStartTime());
		}
		for(LambdaJobs job:schedulelist){
			System.out.println("start time:"+job.getStartTime()+";finish time:"+job.getFinishTime());
		}
		
		//test problem 2
		LambdaJobs job11 = new LambdaJobs(1,3,10d);
		LambdaJobs job12 = new LambdaJobs(2,4,15d);
		LambdaJobs job13 = new LambdaJobs(2,6,20d);
		LambdaJobs job14 = new LambdaJobs(4,6,6d);
		LambdaJobs job15 = new LambdaJobs(5,6,1d);
		LambdaJobs job16 = new LambdaJobs(3,5,10.5);
		List<LambdaJobs> joblist2 = new ArrayList<>();
		joblist2.add(job11);
		joblist2.add(job12);
		joblist2.add(job13);
		joblist2.add(job14);
		joblist2.add(job15);
		joblist2.add(job16);
		LambdaWeightedScheduler weightscheduler = new LambdaWeightedScheduler();
		List<LambdaJobs> weightlist = weightscheduler.schedule(joblist2);
		System.out.println("--------------------------");
		for(LambdaJobs job:weightlist){
			System.out.println("start time:"+job.getStartTime()+";finish time:"+job.getFinishTime());
		}
		
	}

}
