package edu.touro.mco152.bm.Observers;

import edu.touro.mco152.bm.persist.DiskRun;

import java.util.ArrayList;

public class CustomObservable{
    /**
     * A custom implementation of the Observable class that will call all the update()
     * methods of the {@link edu.touro.mco152.bm.Observers.CustomObserver CustomObserver} implementations
     */

    DiskRun diskRun;

    ArrayList<CustomObserver> observers = new ArrayList<>();

    public CustomObservable(){
    };

    public void  registerObserver(CustomObserver observer){
        observers.add(observer);
    }

    public void updateAll(){
        for (int i = 0; i < observers.size(); i++){
            observers.get(i).update(diskRun);
        }
    }
    public void setDiskRun(DiskRun diskRun){
        this.diskRun=diskRun;
    }
}
