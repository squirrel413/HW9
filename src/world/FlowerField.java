package world;

import bee.Worker;

public class FlowerField {

    public final int MAX_WORKERS = 10;
    private int numCurrentWorkers = 0;

    public FlowerField(){}

    public synchronized void enterField(Worker worker) {
        System.out.println("*FF* " + worker.toString() + " enters field");
        while (this.numCurrentWorkers == MAX_WORKERS){
            try{
                wait();
            } catch (InterruptedException e) {
                System.out.println("A bee was interrupted! (waiting to work)");
            }
        }
        this.numCurrentWorkers++;
    }

    public synchronized void exitField(Worker worker) {
        this.numCurrentWorkers--;
        notify();
        System.out.println("*FF* " + worker.toString() + " leaves field");
    }

    private synchronized void incrementNumCurrentWorkers(){this.numCurrentWorkers++;}

    private synchronized void decrementNumCurrentWorkers(){this.numCurrentWorkers--;}
}
