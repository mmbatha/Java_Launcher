package za.co.avaj_launcher.simulator.vehicles;

import za.co.avaj_launcher.simulator.Simulator;
import za.co.avaj_launcher.simulator.WeatherTower;
import za.co.avaj_launcher.weather.Coordinates;

import java.util.HashMap;

public class Balloon extends Aircraft implements Flyable {

    private WeatherTower    weatherTower;

    Balloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        HashMap<String, String> messages = new HashMap<String, String>() {
            {
                put("SUN", "It's always sunny in Durban. I miss Durban");
                put("RAIN", "Rain, rain, rain go away! Let the sun come out");
                put("FOG", "Foggy conditions are better than mist");
                put("SNOW", "Let it snow! Do you wanna build a snowman?");
            }
        };

        if (weather.equals("SUN"))
            this.coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude() + 0, coordinates.getHeight() + 4);
        else if (weather.equals("RAIN"))
            this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 5);
        else if (weather.equals("FOG"))
            this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 3);
        else if (weather.equals("SNOW"))
            this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0, coordinates.getHeight() - 15);

        Simulator.writer.println("Balloon#" + this.name + "(" + this.id + "): " + messages.get(weather));
        if (this.coordinates.getHeight() == 0) {
            Simulator.writer.println("Balloon#" + this.name + "(" + this.id + "): landing.");
            this.weatherTower.unregister(this);
            Simulator.writer.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        Simulator.writer.println("Tower says: Balloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
    }
}
