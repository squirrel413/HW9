package world;

import bee.Queen;
import bee.Drone;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueensChamber {

    private Queen queen;
    private ConcurrentLinkedQueue<Drone> queue = new ConcurrentLinkedQueue<Drone>();

    public QueensChamber(){}


    public synchronized void enterChamber(Drone drone){
        System.out.println("*QC* " + drone.toString() + " enters chamber");
        this.queue.add(drone);
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.println("A bee was interrupted! (drone waiting)");
        }
    }

    public synchronized void summonDrone(){
        Drone summoned = queue.poll();
        assert summoned != null;
        System.out.println("*QC* Queen mates with " + summoned.toString());
        summoned.setMated(true);
        notifyAll();

    }

    public void dismissDrone(){
        Drone dismiss = this.queue.poll();
        assert dismiss != null;
        dismiss.interrupt();
    }

    public boolean hasDrone(){
        return !this.queue.isEmpty();
    }
}
