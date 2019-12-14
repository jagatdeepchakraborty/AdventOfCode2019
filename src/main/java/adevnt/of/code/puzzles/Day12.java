package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;
import org.javatuples.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {
    public static void main(String[] args) {
        try {
            String[] input = Util.loadResource("Day12").split("\r\n");
            List<Pair<int[], int[]>> moonPosList = new ArrayList<>(1);
            List<Pair<int[], int[]>> initMoonPosList = new ArrayList<>(1);

            for (String row : input) {
                int[] velArr = {0, 0, 0};
                int[] velArrInit = {0, 0, 0};
                moonPosList.add(new Pair<>(Arrays.stream(row.replace("<", "").replace(">", "").split(",")).mapToInt(value -> Integer.parseInt(value.split("=")[1])).toArray(),
                        velArr));
                initMoonPosList.add(new Pair<>(Arrays.stream(row.replace("<", "").replace(">", "").split(",")).mapToInt(value -> Integer.parseInt(value.split("=")[1])).toArray(),
                        velArrInit));
            }
            BigInteger stepCount = BigInteger.ZERO;
            BigInteger subStepCount = BigInteger.ZERO;
            boolean repeat = false;
            Map<String, String> posVelMap = new HashMap<>(1);
            while (!repeat) {
                for (int i = 0; i < moonPosList.size(); i++) {
                    for (int j = 0; j < moonPosList.size(); j++) {
                        if (i != j) {
                            moonPosList.get(i).getValue1()[0] = compareCoordinates(moonPosList.get(i).getValue0()[0],
                                    moonPosList.get(j).getValue0()[0], moonPosList.get(i).getValue1()[0]);

                            moonPosList.get(i).getValue1()[1] = compareCoordinates(moonPosList.get(i).getValue0()[1],
                                    moonPosList.get(j).getValue0()[1], moonPosList.get(i).getValue1()[1]);

                            moonPosList.get(i).getValue1()[2] = compareCoordinates(moonPosList.get(i).getValue0()[2],
                                    moonPosList.get(j).getValue0()[2], moonPosList.get(i).getValue1()[2]);

                        }
                    }
                }
                for (int i = 0; i < moonPosList.size(); i++) {
                    moonPosList.get(i).getValue0()[0] =
                            moonPosList.get(i).getValue0()[0] + moonPosList.get(i).getValue1()[0];
                    moonPosList.get(i).getValue0()[1] =
                            moonPosList.get(i).getValue0()[1] + moonPosList.get(i).getValue1()[1];
                    moonPosList.get(i).getValue0()[2] =
                            moonPosList.get(i).getValue0()[2] + moonPosList.get(i).getValue1()[2];
                }
                String allPos = "";
                String allVel = "";
                for (int i = 0; i < moonPosList.size(); i++) {
                    allPos = allPos +
                            moonPosList.get(i).getValue0()[0] + "," + moonPosList.get(i).getValue0()[1] + "," + moonPosList.get(i).getValue0()[2] + ",";
                    allVel = allVel +
                            moonPosList.get(i).getValue1()[0] + "," + moonPosList.get(i).getValue1()[1] + "," + moonPosList.get(i).getValue1()[2] + ",";

                   /*if((moonPosList.get(i).getValue0()[0]==initMoonPosList.get(i).getValue0()[0]
                            && moonPosList.get(i).getValue0()[1]==initMoonPosList.get(i).getValue0()[1]
                            && moonPosList.get(i).getValue0()[2]==initMoonPosList.get(i).getValue0()[2]
                            && moonPosList.get(i).getValue1()[0]==initMoonPosList.get(i).getValue1()[0]
                            && moonPosList.get(i).getValue1()[1]==initMoonPosList.get(i).getValue1()[1]
                            && moonPosList.get(i).getValue1()[2]==initMoonPosList.get(i).getValue1()[2])){
                       repeat = true;
                   } else {
                       repeat = false;
                       break;
                   }*/
                }
                if(posVelMap.containsKey(allPos)){
                    if(posVelMap.get(allPos).equalsIgnoreCase( allVel)){
                        repeat = true;
                        break;
                    }
                } else {
                    posVelMap.put(allPos, allVel);
                }
                stepCount = stepCount.add(BigInteger.ONE);
                subStepCount = subStepCount.add(BigInteger.ONE);
                if(subStepCount.compareTo(new BigInteger("100000000")) == 0){
                    System.out.println("Current step count: " + stepCount);
                    subStepCount = BigInteger.ZERO;
                }

            }

            System.out.println("Velocity");
            for (Pair<int[], int[]> pair : moonPosList) {
                System.out.println(Arrays.toString(pair.getValue1()));
            }
            System.out.println("Position");
            for (Pair<int[], int[]> pair : moonPosList) {
                System.out.println(Arrays.toString(pair.getValue0()));
            }
            int totalEnergy = moonPosList.stream().mapToInt(pair ->
                    (Math.abs(pair.getValue0()[0]) + Math.abs(pair.getValue0()[1]) + Math.abs(pair.getValue0()[2]))
                            *
                            (Math.abs(pair.getValue1()[0]) + Math.abs(pair.getValue1()[1]) + Math.abs(pair.getValue1()[2]))
            ).sum();
            System.out.println("Total energy: " + totalEnergy);
            System.out.println("Repeat at step: " + stepCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int compareCoordinates(int coord1, int coord2, int vel) {
        if (coord1 < coord2) {
            vel = vel + 1;
        } else if (coord1 > coord2) {
            vel = vel - 1;
        }
        return vel;
    }
}
