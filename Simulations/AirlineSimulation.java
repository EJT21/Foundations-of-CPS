import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
class AirlineSimulation
    {
      public static void main(String[] args)
      {
            int takeofftime=2;
            int landtime=4;
            int totaltime=6000;
            double ARRIVALPROB=0.05,DEPARTPROB=0.05;
            int crashTime=2;
            airplaneSimulate(takeofftime,landtime,totaltime,ARRIVALPROB,DEPARTPROB,crashTime);
           
            ARRIVALPROB=0.1;
            DEPARTPROB=0.1;
            crashTime=5;
            airplaneSimulate(takeofftime,landtime,totaltime,ARRIVALPROB,DEPARTPROB,crashTime);
            
      }
      public static void airplaneSimulate(int takeoff,int land, int totalTime, double arrivalProb,double departProb,int crashTime)
        {
            Queue<Integer> arrivalTime=new LinkedList<Integer>();//Highest priority
            Queue<Integer> departTime=new LinkedList<Integer>();
            
            BooleanSource randomPlane = new BooleanSource(arrivalProb);
            
            Runway runway=new Runway(2,4);
            Averager AvgTakingOff = new Averager();
            Averager AvgLanding = new Averager();
            int currentSecond,crash=0;       
            int next;
            System.out.println("Amount of minutes to land: " + land);
            System.out.println("Amount of minutes to take off: " + takeoff);
            System.out.println("Probability of arrival during minute: "+arrivalProb);
            System.out.println("Probability of departure during a minute: "+departProb);
            
            System.out.println("Maximum amount of time in the air before crashing: " + crashTime);
            System.out.println("Total simulation seconds: " + totalTime);
            System.out.println();
            if (takeoff <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0 || departProb < 0 || departProb > 1)
                
            {
                throw new IllegalArgumentException("Values out of range"); 
            }
            for(currentSecond = 0; currentSecond < totalTime; currentSecond++)
            {  
                if(randomPlane.query())//if a plane arrives, they get go in the Queue
                 {
                    arrivalTime.add(currentSecond);
                 }
                if(randomPlane.query())//if a plane wants to depart, we add them to the Queue
                {
                     departTime.add(currentSecond);
                }
                //someone is waiting to land and the runway is not being used, we let them land
                if((!runway.isBusy()) && !arrivalTime.isEmpty())
                 {
                        while(!arrivalTime.isEmpty())//must check all the planes in the Queue
                            {
                                if(crashTime+arrivalTime.peek()<currentSecond)
                                {
                                    next=arrivalTime.remove();
                                    crash++;
                                }
                                else//if we find a plane that needs to land we exit the loop
                                {
                                    break;
                                }
                            }
                        if(!arrivalTime.isEmpty())
                        {
                            next=arrivalTime.remove();
                            AvgLanding.addNumber(currentSecond-next);
                            runway.startLanding();
                        }
                }
                //if the runway is not busy and nobody is waiting to land and someone wants to depart, they depart
                if((!runway.isBusy()) && (arrivalTime.isEmpty()) && (!departTime.isEmpty()))
                {
                    next = departTime.remove();
                    AvgTakingOff.addNumber(currentSecond-next);
                    runway.startTakeoff();
                }
                runway.reduceRemainingTime();
             }
             System.out.println("Average amount of time between planes to land: "+totalTime/AvgTakingOff.howManyNumbers());
             System.out.println("Average amount of time between planes to take off:"+totalTime/AvgTakingOff.howManyNumbers());
             System.out.println("Number of planes taken off: " + AvgTakingOff.howManyNumbers()); 
             System.out.println("Number of planes landed "+ AvgLanding.howManyNumbers());
             System.out.println("Number of planes crashed: "+crash);
             if (AvgTakingOff.howManyNumbers( ) > 0)
             {
                 System.out.printf("%s%.2f%s\n","Average wait for taking off: ",AvgTakingOff.average()," minutes");
             }
             if (AvgLanding.howManyNumbers( ) > 0)
             { 
                    System.out.printf("%s%.2f%s\n","Average wait for landing: ",AvgLanding.average()," minutes");
             }
             System.out.println();
     }
}
class BooleanSource
{
   private double probability; 
   public BooleanSource(double p)
   {
       if ((p < 0) || (1 < p))
           throw new IllegalArgumentException("Illegal p: " + p);
       probability = p;
   }
   public boolean query()
   {
      return (Math.random( ) < probability);
   }
}
class Runway
{
   private int TakingOffSeconds; 
   private int LandingSeconds;   
   private int RunwayTimeLeft;
   public Runway(int s, int r)
   {
       TakingOffSeconds=s;
       LandingSeconds=r;
       RunwayTimeLeft=0;
   } 
   public boolean isBusy( )
   {
      return (RunwayTimeLeft > 0);
   }
   public void reduceRemainingTime( )
   {
       if(RunwayTimeLeft > 0)
       {
           RunwayTimeLeft--;
       }
   } 
   public void startTakeoff( )
   {
      if(RunwayTimeLeft>0)
      {
          throw new IllegalStateException("Runway is already busy.");
      }
      RunwayTimeLeft=TakingOffSeconds;
   }
   public void startLanding()
   {
      if(RunwayTimeLeft>0)
      {
          throw new IllegalStateException("Runway is already busy.");
      }
      RunwayTimeLeft=LandingSeconds;
   }
}
class Averager
{
   private int count;  
   private double sum; 

   public Averager()
   {
       count=0;
       sum=0;
   }
  
   public void addNumber(double value)
   {
      if(count==Integer.MAX_VALUE)
      {
          throw new IllegalStateException("Too many numbers");
      }   
      count++;
      sum+=value;
   }

   public double average()
   {
      if(count == 0)
      {
        return Double.NaN;
      }
         
      else
      {
         return sum/count;
      }
   } 
   public int howManyNumbers()
   {
      return count;
   }
}
//
