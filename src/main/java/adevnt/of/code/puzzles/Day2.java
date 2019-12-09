package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) {
        try {
            String[] input = Util.loadResource("Day2").split(",");
            List<BigInteger> intArr = Arrays.stream(input).map(BigInteger::new).collect(Collectors.toList());
            for(int i = intArr.size(); i<10010; i++){
                intArr.add(new BigInteger("0"));
            }
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
            workOnMainArray(intArr, new BigInteger("1"), new BigInteger("0"));
            System.out.println(intArr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static Pair<BigInteger, List<BigInteger>> workOnMainArray(List<BigInteger> intArr, BigInteger manualInput1,
                                                 BigInteger manualInput2) {
        //intArr.set(9, new BigInteger("8"));
        BigInteger relativeBase = new BigInteger("0");
        boolean quit = false;
        int initPos = 0;
        BigInteger output = new BigInteger("0");
        int manualInputCount = 0;
        BigInteger manualInput = new BigInteger("0");
        boolean outputBool = false;
        while (initPos < intArr.size() && !quit && !outputBool) {
            int opcode = intArr.get(initPos).intValue();
            //System.out.println("opcode: " + opcode);
            if (opcode > 100) {
                int[] digits = Integer.toString(opcode).chars().map(c -> c - '0').toArray();
                opcode = digits[digits.length - 1];
            }
            if (opcode == 1 || opcode == 2 || opcode == 7 || opcode == 8) {
                List<BigInteger> subArr = intArr.subList(initPos, initPos+4);
                workOnSubArray(intArr, subArr, initPos, manualInput, relativeBase);
                initPos += 4;
            }
            if (opcode == 3 || opcode == 4 || opcode == 9) {
                List<BigInteger> subArr = intArr.subList( initPos, initPos + 2);
                if(manualInputCount == 1){
                    manualInput = manualInput2;
                } else if (manualInputCount == 0){
                    manualInput = manualInput1;
                }
                if(manualInputCount > 2){
                    System.out.println(manualInputCount);
                }
                Triplet<Integer, BigInteger, BigInteger> response = workOnSubArray(intArr, subArr, initPos, manualInput,
                        relativeBase);
                output = response.getValue1();
                manualInput = output;
                initPos += 2;
                if(opcode == 3){
                    manualInputCount++;
                }
                if(opcode == 4){
                    System.out.println(response.getValue1());
                    //outputBool = true;
                }
                if(opcode == 9){
                    relativeBase = response.getValue2();
                }
            }
            if (opcode == 5 || opcode == 6) {
                List<BigInteger> subArr = intArr.subList(initPos, initPos + 3);
                initPos = workOnSubArray(intArr, subArr, initPos, manualInput, relativeBase).getValue0();
            }

            if (opcode == 99) {
                quit = true;
                initPos++;
                System.out.println(initPos);
            }
        }
        System.out.println(relativeBase);
        return new Pair<>(output, intArr);
    }

    private static Triplet<Integer, BigInteger, BigInteger> workOnSubArray(List<BigInteger> intArr, List<BigInteger> subArr,
                                                                   int initPos,
                                                               BigInteger manualInput, BigInteger relativeBase ) {
        BigInteger param1 = new BigInteger("0");
        BigInteger param2 = new BigInteger("0");
        BigInteger param3 = new BigInteger("0");
        int opcode = subArr.get(0).intValue();
        BigInteger output = new BigInteger("0");
        System.out.println(initPos);
        if (opcode > 100) {
            int[] digits = Integer.toString(opcode).chars().map(c -> c - '0').toArray();
            opcode = digits[digits.length - 1];
            int[] splitOpcode = {0, 0, 0, 0, 0};
            splitOpcode[4] = digits[digits.length - 1];
            splitOpcode[3] = digits[digits.length - 2];
            splitOpcode[2] = digits[digits.length - 3];
            if (digits.length > 3) {
                splitOpcode[1] = digits[digits.length - 4];
                if (digits.length == 5) {
                    splitOpcode[0] = digits[digits.length - 5];
                }
            }

            if (splitOpcode[2] == 1) {
                param1 = subArr.get(1);
            } else if (splitOpcode[2] == 0) {
                param1 = intArr.get(subArr.get(1).intValue());
            } else if (splitOpcode[2] == 2){
                param1 = intArr.get((subArr.get(1).add(relativeBase)).intValue());
                if(opcode == 3){
                    param1 = subArr.get(1).add(relativeBase);
                }
            }


            if (opcode != 4 && opcode != 9 && opcode!= 3) {
                if (splitOpcode[1] == 1) {
                    param2 = subArr.get(2);
                } else if (splitOpcode[1] == 0) {
                    param2 = intArr.get(subArr.get(2).intValue());
                } else if (splitOpcode[1] == 2){
                    param2 = intArr.get(subArr.get(2).add(relativeBase).intValue());
                }
                if(subArr.size() == 4){
                    if (splitOpcode[0] == 0) {
                        param3 = subArr.get(3);
                    } else if (splitOpcode[0] == 2){
                        param3 = subArr.get(3).add(relativeBase);
                    }
                }

            }

        } else {
            if (opcode == 4 || opcode == 3 || opcode == 9) {
                param1 = intArr.get(subArr.get(1).intValue());
                if(opcode == 3){
                    param1 = subArr.get(1);
                }
            } else {
                param1 = intArr.get(subArr.get(1).intValue());
                param2 = intArr.get(subArr.get(2).intValue());
                param3 = subArr.get(3);
            }
        }

        /*if(initPos == 223){
            System.out.println(relativeBase);
        }*/
        switch (opcode) {
            case 1:
                intArr.set(param3.intValue(),  param1.add(param2));
                //System.out.println("new value: " + intArr.get(param3.intValue()));
                break;
            case 2:
                intArr.set(param3.intValue(),  param1.multiply(param2));
                //System.out.println("new value: " + intArr.get(param3.intValue()));
                break;
            case 3:
                //intArr[subArr[1].intValue()] = manualInput;
                System.out.println("Enter a value: ");
                Scanner scanner = new Scanner(System.in);
                intArr.set(param1.intValue(), new BigInteger("2"));
                //System.out.println(intArr);
                scanner.close();
                break;
            case 4:
                System.out.println("Value at address " + subArr.get(1) + " is " + param1);
                output = param1;
                break;
            case 5:
                initPos = (param1.compareTo(new BigInteger("0")) != 0) ? param2.intValue() : initPos + 3;
                break;
            case 6:
                initPos = (param1.compareTo(new BigInteger("0")) == 0) ? param2.intValue() : initPos + 3;
                break;
            case 7:
                intArr.set(param3.intValue(), new BigInteger((param1.compareTo(param2) < 0) ? "1" : "0"));
                //System.out.println("new value: " + intArr.get(param3.intValue()));
                //intArr[subArr[3].intValue()] = new BigInteger((param1.compareTo(param2) < 0) ? "1" : "0");
                break;
            case 8:
                intArr.set(param3.intValue(), new BigInteger((param1.compareTo(param2) == 0) ? "1" : "0"));
                //System.out.println("new value: " + intArr.get(param3.intValue()));
                //intArr[subArr[3].intValue()] = new BigInteger((param1.compareTo(param2) == 0) ? "1" : "0");
                break;
            case 9:
                relativeBase = relativeBase.add(param1);
                //System.out.println(relativeBase + "   param1: " + param1);
            default:
                break;
        }
        return new Triplet<>(initPos, output, relativeBase);
    }
}
