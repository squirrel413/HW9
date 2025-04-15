package world;

import bee.Worker;

public class FlowerField {

    public final int MAX_WORKERS = 10;
    private int numberOfActiveWorkers = 0;

    public FlowerField(){}

    public void enterField(Worker worker) {

        incrementBees();
        System.out.println("*FF* " + worker.toString() + " enters field");
        try{
            Thread.sleep(Worker.WORKER_SLEEP_TIME_MS);
        } catch(InterruptedException e) {
            System.out.println("A bee was interrupted! (was collecting)");
        }
    }

    public void exitField(Worker worker) {
        decrementBees();
        System.out.println("*FF* " + worker.toString() + " leaves field");
        notify();
    }

    private synchronized void incrementBees(){
        this.numberOfActiveWorkers++;
    }

    private synchronized void decrementBees(){
        this.numberOfActiveWorkers--;
    }
}
