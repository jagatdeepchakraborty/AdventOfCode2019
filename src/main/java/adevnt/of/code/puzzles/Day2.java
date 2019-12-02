package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;

import java.util.Arrays;

public class Day2 {
    public static void main(String[] args) {
        try {
            String[] input = Util.loadResource("Day2").split(",");
            Integer[] intArr;
            boolean found = false;
            for (int n = 0; n < 100; n++) {
                for (int v = 0; v < 100; v++) {
                    intArr = Arrays.stream(input).map(Integer::parseInt).toArray(Integer[]::new);
                    boolean quit = false;
                    intArr[1] = n;
                    intArr[2] = v;
                    int initPos = 0;
                    while (initPos < intArr.length && !quit) {
                        Integer[] subArr = Arrays.copyOfRange(intArr, initPos, initPos + 4);
                        quit = workOnArray(intArr, subArr);
                        initPos += 4;
                    }
                    if (intArr[0] == 19690720) {
                        System.out.println("Answer for 19690720: " + (100 * n + v));
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean workOnArray(Integer[] intArr, Integer[] subArr) {
        int opcode = subArr[0];
        boolean quit = false;
        switch (opcode) {
            case 1:
                intArr[subArr[3]] = intArr[subArr[1]] + intArr[subArr[2]];
                break;
            case 2:
                intArr[subArr[3]] = intArr[subArr[1]] * intArr[subArr[2]];
                break;
            case 99:
                //System.out.println(Arrays.toString(intArr));
                quit = true;
                break;
            default:
                break;
        }
        return quit;
    }
}
