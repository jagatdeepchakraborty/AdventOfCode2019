package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day13 {
    public static void main(String[] args) {
        try {
            String[] input = Util.loadResource("Day13").split(",");
            List<BigInteger> intArr = Arrays.stream(input).map(BigInteger::new).collect(Collectors.toList());
            for (int i = intArr.size(); i < 10010; i++) {
                intArr.add(new BigInteger("0"));
            }
            Day2.workOnMainArray(intArr, BigInteger.ZERO, BigInteger.ZERO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int moveJoystick(List<BigInteger> outputList) {

        int initPos = 0;
        int blockCount = 0;
        Map<String, Integer> coordTileMap = new HashMap<>(1);
        int paddlePosX = 0;
        int ballPosX = 0;
        while (initPos < outputList.size()) {
            int[] currArr =
                    outputList.subList(initPos, initPos + 3).stream().mapToInt(BigInteger::intValue).toArray();

            if (currArr[2] == 4) {
                ballPosX = currArr[0];
            }
            if (currArr[2] == 3) {
                paddlePosX = currArr[0];
            }
            if (currArr[2] == 2) {
                blockCount++;
            }
            coordTileMap.put(currArr[0] + "," + currArr[1], currArr[2]);
            initPos += 3;
        }
        System.out.println("ballPosX: " + ballPosX + "      paddlePosX: " + paddlePosX);
        System.out.println("Final score:" + coordTileMap.get("-1,0"));
        //System.out.println("Block count: " + blockCount);
        if (ballPosX == paddlePosX) {
            return 0;
        } else if (ballPosX > paddlePosX) {
            return 1;
        } else if (ballPosX < paddlePosX) {
            return -1;
        }

        /*System.out.println("Block count: " + blockCount);
        System.out.println("Final score:" + coordTileMap.get("-1,0"));

        int[][] panelImage = new int[24][42];
        for (Map.Entry<String, Integer> entry : coordTileMap.entrySet()) {
            String[] splitKey = entry.getKey().split(",");
            int x = Integer.parseInt(splitKey[0]);
            int y = Integer.parseInt(splitKey[1]);
            if (x > 0 && y > 0) {
                panelImage[y][x] = entry.getValue();
            }
        }*/
        return 0;
    }
}
