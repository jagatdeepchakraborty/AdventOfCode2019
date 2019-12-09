package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day8 {
    public static void main(String[] args) {
        try {
            String input = Util.loadResource("Day8");
            int wide = 25;
            int tall = 6;
            int initPos = 0;
            int layer = 1;
            Map<Integer, String> layerMap = new HashMap<>(1);
            while (initPos < input.length()) {
                layerMap.put(layer, input.substring(initPos, initPos + (wide * tall)));
                initPos = initPos + (wide * tall);
                layer++;
            }
            Map<Integer, Triplet<Integer, Integer, Integer>> layerDigitCountMap = new HashMap<>(1);
            int[] finalImage = null;
            for (Map.Entry<Integer, String> entry : layerMap.entrySet()) {
                int zeroCount = 0;
                int oneCount = 0;
                int twoCount = 0;
                int[] digits = entry.getValue().chars().map(c -> c - '0').toArray();
                if (finalImage == null) {
                    finalImage = new int[wide];
                    finalImage = digits;
                }
                //0 is black, 1 is white, and 2 is transparent
                for (int i=0; i<digits.length; i++) {
                    if(finalImage[i] == 2 && digits[i] < 2){
                        finalImage[i] = digits[i];
                    }
                }
                for (int i : digits) {
                    if (i == 0) {
                        zeroCount++;
                    }
                    if (i == 1) {
                        oneCount++;
                    }
                    if (i == 2) {
                        twoCount++;
                    }
                }
                layerDigitCountMap.put(entry.getKey(), new Triplet<>(zeroCount, oneCount, twoCount));
            }
            System.out.println(layerDigitCountMap);
            int lowestZero = 0;
            int levelForOutput = 0;
            for (Map.Entry<Integer, Triplet<Integer, Integer, Integer>> entry : layerDigitCountMap.entrySet()) {
                if (lowestZero == 0) {
                    lowestZero = entry.getValue().getValue0();
                }
                if (lowestZero > entry.getValue().getValue0()) {
                    lowestZero = entry.getValue().getValue0();
                    levelForOutput = entry.getKey();
                }
            }
            System.out.println("Part 1 result: " + layerDigitCountMap.get(levelForOutput).getValue1() * layerDigitCountMap.get
                    (levelForOutput).getValue2());
            System.out.println(layerMap);
            System.out.println("Part 2 result: " + Arrays.toString(finalImage));
            initPos = 0;
            while (initPos < finalImage.length){
                System.out.println(Arrays.toString(Arrays.copyOfRange(finalImage, initPos, initPos + wide)));
                initPos = initPos + wide;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
