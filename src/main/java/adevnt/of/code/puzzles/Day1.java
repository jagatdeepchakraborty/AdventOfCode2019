package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;

public class Day1 {
    public static void main(String[] args) {
        int totalFuel = 0;
        try {
            String[] input = Util.loadResource("Day1").split("\r\n");
            if (input.length > 0) {
                for (String mass : input) {
                    int fuelPerModule = 0;
                    int fuel = fuelPerMass(Integer.parseInt(mass));
                    fuelPerModule = fuel;
                    while (fuelPerMass(fuel) >= 0) {
                        fuelPerModule += fuelPerMass(fuel);
                        fuel = fuelPerMass(fuel);
                    }
                    totalFuel += fuelPerModule;
                }
                System.out.println("Total Fuel: " + totalFuel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int fuelPerMass(int mass) {
        return mass / 3 - 2;
    }
}
