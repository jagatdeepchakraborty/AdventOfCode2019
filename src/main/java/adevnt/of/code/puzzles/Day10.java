package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 {
    public static void main(String[] args) {
        try {
            String[] input = Util.loadResource("Day10").split("\r\n");
            List<char[]> inputArr = new ArrayList<>(1);
            for (String row : input) {
                inputArr.add(row.toCharArray());
            }
            List<String> asteroidPosList = new ArrayList<>(1);
            for (int i = 0; i < inputArr.size(); i++) {
                char[] row = inputArr.get(i);
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == '#') {
                        asteroidPosList.add(i + "," + j);
                    }
                }
            }
            Map<String, Integer> dlosMap = new HashMap<>(1);
            for (String asteroidPos : asteroidPosList) {
                int[] asteroidPosSplit =
                        Arrays.stream(asteroidPos.split(",")).mapToInt(Integer::parseInt).toArray();
                int dlosCount = 0;
                dlosCount = getDlosCountForRow(inputArr, asteroidPosSplit, dlosCount);
                for (int i = asteroidPosSplit[0]+1; i < inputArr.size(); i++) {
                    char[] row = inputArr.get(i);
                    for (int j = 0; j < row.length; j++) {
                        if (row[j] == '#') {

                        }
                    }
                }
                dlosMap.put(asteroidPos, dlosCount);
            }
            System.out.println(dlosMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getDlosCountForRow(List<char[]> inputArr, int[] asteroidPosSplit, int dlosCount) {
        for (int i = 0; i < inputArr.size(); i++) {
            char[] row = inputArr.get(i);
            boolean rowblocked = false;
            if (i == asteroidPosSplit[0]) {
                for (int j = asteroidPosSplit[1] + 1; j < row.length; j++) {
                    if (row[j] == '#' && !rowblocked) {
                        rowblocked = true;
                        dlosCount++;
                    }
                }
                rowblocked = false;
                for (int j = asteroidPosSplit[1] - 1; j >= 0; j--) {
                    if (row[j] == '#' && !rowblocked) {
                        rowblocked = true;
                        dlosCount++;
                    }
                }
            }

        }
        return dlosCount;
    }
}
