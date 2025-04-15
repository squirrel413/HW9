package world;

import bee.Worker;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;


public class FlowerField {

    public final int MAX_WORKERS = 10;
    private int numCurrentWorkers = 0;

    public FlowerField(){}

    public void enterField(Worker worker) {
        System.out.println("*FF* " + worker.toString() + " enters field");
        while(this.numCurrentWorkers == MAX_WORKERS){
            try{
                wait();
            } catch (InterruptedException e) {
                System.out.println("A bee was interrupted! (waiting to work)");
            }
        }
        try{
            incrementNumCurrentWorkers();
            Thread.sleep(Worker.WORKER_SLEEP_TIME_MS);
        } catch(InterruptedException e) {
            System.out.println("A bee was interrupted! (was collecting)");
        }
    }

    public void exitField(Worker worker) {
        decrementNumCurrentWorkers();
        notify();
        System.out.println("*FF* " + worker.toString() + " leaves field");
    }

    private synchronized void incrementNumCurrentWorkers(){this.numCurrentWorkers++;}

    private synchronized void decrementNumCurrentWorkers(){this.numCurrentWorkers--;}
}
