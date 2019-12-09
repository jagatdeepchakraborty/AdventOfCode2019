package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day7 {
    /*public static void main(String[] args) {
        String[] input = new String[0];
        try {
            input = Util.loadResource("Day7").split(",");
            Map<String, Integer> outputMap = new HashMap<>(1);
            for (String combination : generateInput(0)) {
                String[] combinationArr = combination.split(",");
                Map<String, Integer> checkDuplicate = new HashMap<>(1);
                for (String i : combinationArr) {
                    if (checkDuplicate.containsKey(i)) {
                        checkDuplicate.put(i, checkDuplicate.get(i) + 1);
                    } else {
                        checkDuplicate.put(i, 1);
                    }
                }
                if (checkDuplicate.values().stream().filter(integer -> integer > 1).mapToInt(value -> value).sum() == 0) {
                    int thrust = getThrusts(input, combination.split(","), outputMap);
                    outputMap.put(combination, thrust);
                }
            }
            *//*for (int i = 0; i < 100; i++) {
                System.out.println(i);
                for (String combination : generateInput(5)) {
                    String[] combinationArr = combination.split(",");
                    Map<String, Integer> checkDuplicate = new HashMap<>(1);
                    for (String str : combinationArr) {
                        if (checkDuplicate.containsKey(i)) {
                            checkDuplicate.put(str, checkDuplicate.get(str) + 1);
                        } else {
                            checkDuplicate.put(str, 1);
                        }
                    }
                    if (checkDuplicate.values().stream().filter(integer -> integer > 1).mapToInt(value -> value).sum() == 0) {
                        int thrust = getThrustsInFeedback(input, combination.split(","),
                                (Integer) outputMap.values().toArray()[outputMap.size()-1]);
                        outputMap.put(combination, thrust);
                    }
                }
            }*//*

            System.out.println(outputMap);
            System.out.println("Max thrust: " + outputMap.values().stream().mapToInt(value -> value).max().getAsInt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getThrusts(String[] input, String[] combinationArr, Map<String, Integer> outputMap) {
        Integer[] intArr = Arrays.stream(input).map(Integer::parseInt).toArray(Integer[]::new);
        int prevOutput = 0;
        Map<Integer, Integer[]> ampMap = new HashMap<>(1);
        for (int i = 0; i < 5; i++) {
            Pair<Integer, Integer[]> response = Day2.workOnMainArray(intArr, Integer.parseInt(combinationArr[i]),
                    prevOutput);
            prevOutput = response.getValue0();
            Integer[] currIntArr = new Integer[response.getValue1().length];
            for (int j = 0; j < response.getValue1().length; j++) {
                currIntArr[j] = response.getValue1()[j];
            }
            ampMap.put(i, currIntArr);
        }
        for (String combination : generateInput(5)) {
            combinationArr = combination.split(",");
            Map<String, Integer> checkDuplicate = new HashMap<>(1);
            for (String i : combinationArr) {
                if (checkDuplicate.containsKey(i)) {
                    checkDuplicate.put(i, checkDuplicate.get(i) + 1);
                } else {
                    checkDuplicate.put(i, 1);
                }
            }
            if (checkDuplicate.values().stream().filter(integer -> integer > 1).mapToInt(value -> value).sum() == 0) {
                for (int i = 0; i < 5; i++) {
                    Pair<Integer, Integer[]> response = Day2.workOnMainArray(ampMap.get(i),
                            Integer.parseInt(combinationArr[i]),
                            prevOutput);
                    prevOutput = response.getValue0();
                    Integer[] currIntArr = new Integer[response.getValue1().length];
                    for (int j = 0; j < response.getValue1().length; j++) {
                        currIntArr[j] = response.getValue1()[j];
                    }
                }
                outputMap.put(combination, prevOutput);
            }
        }
        return prevOutput;
    }

    public static ArrayList<String> generateInput(int init) {
        ArrayList<String> inputArr = new ArrayList<>(1);
        for (int i = init; i < init+5; i++) {
            for (int j = init; j < init+5; j++) {
                for (int k = init; k < init+5; k++) {
                    for (int l = init; l < init+5; l++) {
                        for (int m = init; m < init+5; m++) {
                            inputArr.add(i + "," + j + "," + k + "," + l + "," + m);
                        }
                    }
                }
            }
        }
        return inputArr;
    }*/
}
