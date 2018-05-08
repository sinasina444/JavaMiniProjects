package Homework5;

/*Implement Time Travel!
Implement MadScientist as detailed by its comments
Use anonymous classes to implement TimeTravelCallback when invoked by the MadScientist 
when using her TimeMachine

All implementations of TimeTraveler start with 100 years of time traveling ability
Make an implementation of TimeTraveler which linearly decays (one year of time travel 
results in one year of time travel ability loss)

Make an implementation of TimeTraveler which decays doubly 
(one year of time travel results in two years of time travel ability loss)

Make an implementation of TimeTraveler which exponentially decays with a 
decay constant passed into its constructor; see wiki
*/
public class MadScientist {

	
    private final TimeMachine timeMachine;

    public MadScientist(TimeMachine timeMachine) {
        this.timeMachine = timeMachine;
    }

    public static void main(String[] args) {
        // make a MadScientist / TimeMachine and 3 TimeTraveler implementations
    	TimeMachine myTimeMachine = new TimeMachine();
    	MadScientist  madScientist = new MadScientist(myTimeMachine);
    	
    	class LinearTimeTraveler implements TimeTraveler{
    		LinearTimeTraveler(String name){
    			this.name = name;
    			remainYears = 1.0;
    		}
    		private final String name;
    		private Double remainYears;
    		@Override public String getName(){
    			return name;
    		}
    		@Override public Double getRemainingYearsOfTravel(){
    			return remainYears;
    		}
    		@Override public void adjust(Time unit, int amount, boolean ahead){
    			Double dAmount = new Double(amount);
    			if(unit==Time.Hours){	
    				remainYears-=dAmount/(24*365);   				
    			}else{
    				remainYears-=dAmount/(365);
    			}
    		}
    	}
    	
    	class DoubleTimeTraveler implements TimeTraveler{
    		DoubleTimeTraveler(String name){
    			this.name = name;
    			remainYears = 1.0;
    		}
    		private final String name;
    		private Double remainYears;
    		@Override public String getName(){
    			return name;
    		}
    		@Override public Double getRemainingYearsOfTravel(){
    			return remainYears;
    		}
    		@Override public void adjust(Time unit, int amount, boolean ahead){
    			Double dAmount = new Double(amount);
    			if(unit==Time.Hours){			
    				remainYears-=2*(dAmount/(24*365));   				
    			}else{
    				remainYears-=2*(dAmount/(365));
    			}
    		}
    	}
    	
    	class ExponentialTimeTraveler implements TimeTraveler{
    		ExponentialTimeTraveler(String name,Double dExpRate){
    			this.name = name;
    			remainYears = 1.0;
    			this.dExpRate = dExpRate;
    		}
    		private final String name;
    		private Double remainYears;
    		private final Double dExpRate;//r is a positive rate called exponential decay constant
    		@Override public String getName(){
    			return name;
    		}
    		@Override public Double getRemainingYearsOfTravel(){
    			return remainYears;
    		}
    		@Override public void adjust(Time unit, int amount, boolean ahead){
    			Double dAmount = new Double(amount);
    			if(unit==Time.Hours){
    				remainYears *= Math.pow(Math.E,(-1)*dExpRate*dAmount/(24*365));				
    			}else{
    				//unit is Time.Days
    				remainYears *=Math.pow(Math.E,(-1)*dExpRate*dAmount/(365));
    			}
    			//if the remainYears' time is less than one hour, we should consider that the 
    			//traveler's ability has exhausted
    			final Double dONEHOUR = new Double(1)/(365*24);
    			if(remainYears<dONEHOUR){
    				remainYears = 0d;
    			}
    		}
    	};
        // experiment on each TimeTraveler   	
    	LinearTimeTraveler Alex = new LinearTimeTraveler("Alex");
    	DoubleTimeTraveler Bob = new DoubleTimeTraveler("Bob");
    	ExponentialTimeTraveler  Cathy = new ExponentialTimeTraveler("Cathy",1.0);//set r value
    	madScientist.experiment(Alex);
    	madScientist.experiment(Bob);
    	madScientist.experiment(Cathy);
        // a TimeTraveler should always start with 100 years of time travel strength
        // one TimeTraveler implementation should linearly decay (i.e., one year of actual time travel reduces the
        // time traveler's ability by one year)
        // one TimeTraveler implementation should decay double the travel value (i.e., one year of actual time travel reduces
        // the time traveler's ability by two years)
        // one TimeTraveler implementation should have exponential decay with a decay constant inputted by the scientist (see http://en.wikipedia.org/wiki/Exponential_decay)

        // continue to experiment until all the TimeTraveler's have exhausted their ability to travel    	
		//Traveler who has exhausted the ability to travel should no longer be called
    	
    	/*while(Alex.getRemainingYearsOfTravel()>0 || Bob.getRemainingYearsOfTravel()>0 || Cathy.getRemainingYearsOfTravel()>0){
    		madScientist.experiment(Alex);
    		madScientist.experiment(Bob);
    		madScientist.experiment(Cathy);

    		try{
    			//sleep for 0.01s in case too many threads are established
    			Thread.sleep(10);
    		}catch(Exception e){
    			System.out.println(e);
    			return;
    		} 		

    	}

    	
    	*/
    }

    public void experiment(TimeTraveler timeTraveler) {
        // invoke the time-machine and print how much time has passed using a callback and adjust the time traveler's ability to travel
    	try{
    		this.timeMachine.travel(timeTraveler, new TimeTravelCallback(){
    			
    			@Override public void leaped (Time unit, int amount, boolean ahead) {
    				if(timeTraveler.getRemainingYearsOfTravel()<0){
    					return;
    				}
    				String strTime = (unit==Time.Days?"Days":"Hours");
    				String strAhead = (ahead?"Future":"Past");
    				//show info about how much time traveler has passed in this travel
    				System.out.printf("%s has passed %d %s to the %s\n",timeTraveler.getName(),amount,strTime,strAhead);
    				//show info about how much time traveler has left for time traveling
    				System.out.printf("%s 's time ability left : %s years\n", timeTraveler.getName(),timeTraveler.getRemainingYearsOfTravel());
    				//adjust timeTravler's ability to travel for the next time
    				timeTraveler.adjust(unit, amount, ahead);
    				
    				experiment(timeTraveler);
    			}
    			
    		});
    	}catch(IllegalArgumentException e){
    		//if time traveler's ability has exhausted,the error will be catght here and show info
			System.out.println(e.getMessage());
			return;
    	}
    }
}
