package za.co.avaj_launcher.simulator;
import za.co.avaj_launcher.simulator.vehicles.AircraftFactory;
import za.co.avaj_launcher.weather.WeatherProvider;

import java.io.*;

public class Simulator {

    public static PrintWriter   writer;
    public static int           cycles;

    public static void main(String[] args) {
        if (args.length < 1)
            return;

        String sceneFile = args[0];

        File simFile = new File("simulation.txt");

        try {
            writer = new PrintWriter(simFile);
        }
        catch (FileNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
            return;
        }

        if (simFile.exists() && !simFile.isDirectory())
            writer.print("");

        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();
        try {
            FileInputStream fstream = new FileInputStream(sceneFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String str;
            int line;
            String[] lineArray;

            line = 1;

            while ((str = br.readLine()) != null) {
                if (line == 1) {
                    try {
                        cycles = Integer.parseInt(str);
                        if (cycles < 0) {
                            System.out.println("Error: Scenario file must begin with a POSITIVE integer!");
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Error: Scenario file must begin with an integer! ");
                        return;
                    }
                }
                else {
                    lineArray = str.split(" ");
                    if (lineArray.length == 1 && lineArray[0].isEmpty())
                        continue;
                    if (lineArray.length != 5)
                        throw new Exception("Error: Scenario file line " + line + ": Not enough parameters!");

                    try {
                        aircraftFactory.newAircraft(lineArray[0], lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4])).registerTower(weatherTower);
                    }
                    catch (NumberFormatException ex) {
                        System.out.println("Error: Scenario fine line " + line + ": Parameters 3 to 5 must be integers!");
                        return;
                    }
                    catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        return;
                    }
                }
                System.out.println(str);
                line++;
            }
            br.close();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return;
        }

        WeatherProvider weatherProvider = WeatherProvider.getProvider();
        while (cycles > 0) {
            weatherTower.changeWeather();
            cycles--;
        }
        writer.close();
    }
}
