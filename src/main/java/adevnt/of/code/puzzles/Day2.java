package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;

import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        try {
            String[] input = Util.loadResource("Day2").split(",");
            Integer[] intArr = Arrays.stream(input).map(Integer::parseInt).toArray(Integer[]::new);
            ;
            /*boolean found = false;
            for (int n = 0; n < 100; n++) {
                for (int v = 0; v < 100; v++) {
                    intArr = Arrays.stream(input).map(Integer::parseInt).toArray(Integer[]::new);

                    intArr[1] = n;
                    intArr[2] = v;
                    workOnMainArray(intArr);
                    if (intArr[0] == 19690720) {
                        System.out.println("Answer for 19690720: " + (100 * n + v));
                        found = true;
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }*/
            workOnMainArray(intArr);
            System.out.println(intArr.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void workOnMainArray(Integer[] intArr) {
        //intArr[3] = 7;
        boolean quit = false;
        int initPos = 0;
        while (initPos < intArr.length && !quit) {
            int opcode = intArr[initPos];
            System.out.println("opcode: " + opcode);
            if (opcode > 100) {
                int[] digits = Integer.toString(opcode).chars().map(c -> c - '0').toArray();
                opcode = digits[digits.length - 1];
            }
            if (opcode == 1 || opcode == 2 || opcode == 7 || opcode == 8) {
                Integer[] subArr = Arrays.copyOfRange(intArr, initPos, initPos + 4);
                workOnSubArray(intArr, subArr, initPos);
                initPos += 4;
            }
            if (opcode == 3 || opcode == 4) {
                Integer[] subArr = Arrays.copyOfRange(intArr, initPos, initPos + 2);
                workOnSubArray(intArr, subArr, initPos);
                initPos += 2;
            }
            if(opcode == 5 || opcode == 6){
                Integer[] subArr = Arrays.copyOfRange(intArr, initPos, initPos + 3);
                initPos = workOnSubArray(intArr, subArr, initPos);
            }

            if (intArr[initPos] == 99) {
                quit = true;
            }
        }
        System.out.println(Arrays.toString(intArr));
    }

    private static int workOnSubArray(Integer[] intArr, Integer[] subArr, int initPos) {
        int param1 = 0;
        int param2 = 0;
        int opcode = subArr[0];

        if (opcode > 100) {
            int[] digits = Integer.toString(opcode).chars().map(c -> c - '0').toArray();
            opcode = digits[digits.length - 1];
            int[] splitOpcode = {0, 0, 0, 0};
            splitOpcode[3] = digits[digits.length - 1];
            splitOpcode[2] = digits[digits.length - 2];
            splitOpcode[1] = digits[digits.length - 3];
            if (digits.length > 3) {
                splitOpcode[0] = digits[digits.length - 4];
            }
            if (splitOpcode[1] == 1) {
                param1 = subArr[1];
            } else if (splitOpcode[1] == 0) {
                param1 = intArr[subArr[1]];
            }
            if (opcode != 4) {
                if (splitOpcode[0] == 1) {
                    param2 = subArr[2];
                } else if (splitOpcode[0] == 0) {
                    param2 = intArr[subArr[2]];
                }
            }

        } else {
            if(opcode == 4 || opcode == 3){
                param1 = intArr[subArr[1]];
            } else {
                param1 = intArr[subArr[1]];
                param2 = intArr[subArr[2]];
            }
        }

        Scanner scanner = new Scanner(System.in);
        switch (opcode) {
            case 1:
                intArr[subArr[3]] = param1 + param2;
                break;
            case 2:
                intArr[subArr[3]] = param1 * param2;
                break;
            case 3:
                System.out.println("Enter a value: ");
                intArr[subArr[1]] = scanner.nextInt();
                scanner.close();
                break;
            case 4:
                System.out.println("Value at address " + subArr[1] + " is " + param1);
                break;
            case 5:
                initPos = (param1 != 0) ? param2 : initPos + 3;
                break;
            case 6:
                initPos = (param1 == 0) ? param2 : initPos + 3;
                break;
            case 7:
                intArr[subArr[3]] = (param1 < param2) ? 1 : 0;
                break;
            case 8:
                intArr[subArr[3]] = (param1 == param2) ? 1 : 0;
                break;
            default:
                break;
        }
        return initPos;
    }
}
