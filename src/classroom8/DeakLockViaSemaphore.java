package classroom8;

import java.util.concurrent.Semaphore;

public class DeakLockViaSemaphore {
	private final Semaphore binarySemaphore;
	
	public DeakLockViaSemaphore(){
		this.binarySemaphore = new Semaphore(1);
	}
	
	/* if
	 * public DeakLockViaSemaphore(Semophore binarySemaphore){
		this.binarySemaphore = new Semaphore(1);
	}  在其他同样传入了这个binarySemaphore的地方，可能有线程调用了.release()
		Semaphore binarySemaphore = new Semaphore(1);
		DeakLockViaSemaphore(binarySemaphore);
		binarySemaphore.release();//这里就被释放了，那么里面那个acuqire后面的代码就不具有原始性
	 * 
	 */
	
	public void mutualExclusion(){
		//问题是并不知道哪个线程aquire了这个锁
		binarySemaphore.acquire();//线程获取了这个锁，the only thread which has acquired lock
		binarySemaphore.release();
	}
	//注意一下2，3会造成死锁
	public void mutualExclusion2(){
		//问题是并不知道哪个线程aquire了这个锁
		binarySemaphore.acquire();//线程获取了这个锁，the only thread which has acquired lock
		//invoke();
		binarySemaphore.release();
	}
	
	public void mutualExclusion3(){
		//问题是并不知道哪个线程aquire了这个锁
		binarySemaphore.acquire();//线程获取了这个锁，the only thread which has acquired lock
		//invoke();
		binarySemaphore.release();
	}
	
	public void mutualExclusion4(){
		//问题是并不知道哪个线程aquire了这个锁
		binarySemaphore.acquire();//线程获取了这个锁，the only thread which has acquired lock
		//throw new RuntimeException()://这样后面就不会release;
			//所以release要写在finally里面
		binarySemaphore.release();
	}
	
	//正确写法
	public void mutualExclusion5(){
		binarySemaphore.acquire();//线程获取了这个锁，the only thread which has acquired lock
		try{
		//throw new RuntimeException();//这样后面就不会release;
			//所以release要写在finally里面
		}catch(InterruptedException ie){//突然中断的错误，就是interruptedExcption,比如ctrl+c?
			//要写以下代码，重要！homework 必写
			Thread.currentThread().interrupt();//这是正确处理interruptedExcption的方法
			throw new RuntimeException(ie);
		}try{
			throw new RuntimeException();
		}
		finally{
		}
			binarySemaphore.release();
		}
	}
}

