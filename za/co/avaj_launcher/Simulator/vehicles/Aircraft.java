package za.co.avaj_launcher.simulator.vehicles;

import za.co.avaj_launcher.weather.Coordinates;

public class Aircraft {

    protected long          id;
    protected String        name;
    protected Coordinates   coordinates;
    private static long     idCounter;

    protected Aircraft(String name, Coordinates coordinates) {
        this.id = this.nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        return ++(Aircraft.idCounter);
    }

}
