package za.co.avaj_launcher.simulator.vehicles;

import za.co.avaj_launcher.simulator.WeatherTower;

public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
}
