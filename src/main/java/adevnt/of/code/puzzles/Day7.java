package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;
import org.javatuples.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 {
   /* public static void main(String[] args) {
        String[] input = new String[0];
        try {
            input = Util.loadResource("Day7").split(",");
            Map<String, BigInteger> outputMap = new HashMap<>(1);
            for (String combination : generateInput(0)) {
                String[] combinationArr = combination.split(",");
                Map<String, Integer> checkDuplicate = new HashMap<>(1);
                for (String i : combinationArr) {
                    if (checkDuplicate.containsKey(i)) {
                        checkDuplicate.put(i, checkDuplicate.get(i)+1) ;
                    } else {
                        checkDuplicate.put(i, 1);
                    }
                }
                if (checkDuplicate.values().stream().filter(integer -> integer > 1).mapToInt(value -> value).sum() == 0) {
                    BigInteger thrust = getThrusts(input, combination.split(","), outputMap);
                    outputMap.put(combination, thrust);
                }
            }

            System.out.println(outputMap);
            System.out.println("Max thrust: " + outputMap.values().stream().mapToInt(BigInteger::intValue).max().getAsInt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static BigInteger getThrusts(String[] input, String[] combinationArr, Map<String, BigInteger> outputMap) {
        List<BigInteger> intArr = Arrays.stream(input).map(BigInteger::new).collect(Collectors.toList());
        BigInteger prevOutput = new BigInteger("0");
        Map<Integer, List<BigInteger>> ampMap = new HashMap<>(1);
        for (int i = 0; i < 5; i++) {
            Pair<BigInteger, List<BigInteger>> response = Day2.workOnMainArray(intArr, new BigInteger(combinationArr[i]),
                    prevOutput);
            prevOutput = response.getValue0();
            List<BigInteger> currIntArr =response.getValue1();

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
                    Pair<BigInteger, List<BigInteger>> response = Day2.workOnMainArray(ampMap.get(i),
                            new BigInteger(combinationArr[i]),
                            prevOutput);
                    prevOutput = response.getValue0();
                    ampMap.put(i, response.getValue1());
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
