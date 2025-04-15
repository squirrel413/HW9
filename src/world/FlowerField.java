package world;

import bee.Worker;

public class FlowerField {

    public final int MAX_WORKERS = 10;
    private int numberOfBees = 0;

    public FlowerField(){}

    public void enterField(Worker worker) {
        if (this.numberOfBees >= MAX_WORKERS)
            try{
                wait();
            } catch (InterruptedException e) {
                System.out.println("A bee was interrupted! (at flower field)");
            }
        incrementBees();
        System.out.println("*FF* " + worker.toString() + " enters field");

    }

    public void exitField(Worker worker) {
        decrementBees();
        notify();
        System.out.println("*FF* " + worker.toString() + " leaves field");
    }

    private synchronized void incrementBees(){
        this.numberOfBees++;
    }

    private synchronized void decrementBees(){
        this.numberOfBees--;
    }
}
