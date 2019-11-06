package za.co.avaj_launcher.simulator;
import za.co.avaj_launcher.simulator.vehicles.Flyable;

import java.util.ArrayList;
import java.util.List;

public class Tower {

    private List<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable) {
        if (observers.contains(flyable))
            return;
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }

    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++)
            observers.get(i).updateConditions();
    }
}
