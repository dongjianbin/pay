package src;

import java.util.Timer;
import src.TaskSyncGoodsList;
import src.TaskUploadOrders;


public class TaskMain {
	public static void main(String[] args){
	      Timer timer = new Timer(); 
	      timer.schedule(new TaskSyncGoodsList(), 2 * 1000, 100*1000);
	      timer.schedule(new TaskUploadOrders() ,2*1000, 300*1000);
	    }
	public void TaskRun(){
	      Timer timer = new Timer(); 
	      timer.schedule(new TaskSyncGoodsList(), 2 * 1000, 100*1000);
	      timer.schedule(new TaskUploadOrders() ,2*1000, 300*1000);
		
	}
}
