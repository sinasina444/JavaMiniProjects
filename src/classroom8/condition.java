package classroom8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class condition {
	private Lock lock;
	private Condition listNotEmpty;
	private List<Integer> list;
	public condition(){
		lock = new ReentrantLock();
		listNotEmpty = lock.newCondition();
		list = new ArrayList<Integer>();
	}
	
	public void checkEmpty(){
		lock.lock();
		//listNotEmpty.notify();
		try{
			while(list.isEmpty()){
				listNotEmpty.await();//当前锁解了，线程进入等待，直到被其他线程唤醒。同时唤醒和这个lock有关的其他condition
			}
		}catch(InterruptedException exc){
			exc.getMessage();
		}finally{	
			
		}
			lock.unlock();
		}
	}
}
