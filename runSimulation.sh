#!/usr/bin/env bash
javac za/co/avaj_launcher/**/*.java --release 7
java za.co.avaj_launcher.simulator.Simulator scenario.txt
cat simulation.txt
## -- Create jar file
## jar cfe Main.jar za.co.technoris.myapp.Main za/co/avaj/**/*.class 
