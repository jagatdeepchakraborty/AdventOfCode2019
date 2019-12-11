package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;
import adevnt.of.code.model.Day11Prop;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day11 {
    public static void main(String[] args) {
        String[] input = new String[0];
        try {
            input = Util.loadResource("Day11").split(",");
            List<BigInteger> intArr = Arrays.stream(input).map(BigInteger::new).collect(Collectors.toList());
            for (int i = intArr.size(); i < 10010; i++) {
                intArr.add(new BigInteger("0"));
            }
            Day2.workOnMainArray(intArr, BigInteger.ZERO, BigInteger.ZERO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void changeDirBasedOnOutput(List<BigInteger> outputList, Day11Prop day11Prop, Map<String, Integer> panelMap) {
        int x = day11Prop.getX();
        int y = day11Prop.getY();
        //0-up, 1-left, 2-down, 3-right
        int dir = day11Prop.getDir();

        if (outputList.get(0).compareTo(BigInteger.ZERO) == 0) {
            day11Prop.setManualInput(BigInteger.ZERO);
        } else if (outputList.get(0).compareTo(BigInteger.ONE) == 0) {
            day11Prop.setManualInput(BigInteger.ONE);
        }
        String panel = day11Prop.getX() + "," + day11Prop.getY();
        if (panelMap.containsKey(panel)) {
            panelMap.put(panel, day11Prop.getManualInput().intValue());
        } else {
            panelMap.put(panel, day11Prop.getManualInput().intValue());
        }
        if (outputList.get(1).compareTo(BigInteger.ZERO) == 0) {
            if (dir == 0) {
                dir = 1;
            } else if (dir == 1) {
                dir = 2;
            } else if (dir == 2) {
                dir = 3;
            } else if (dir == 3) {
                dir = 0;
            }
        } else if (outputList.get(1).compareTo(BigInteger.ONE) == 0) {
            if (dir == 0) {
                dir = 3;
            } else if (dir == 3) {
                dir = 2;
            } else if (dir == 2) {
                dir = 1;
            } else if (dir == 1) {
                dir = 0;
            }
        }
        if (dir == 0) {
            y = y - 1;
        } else if (dir == 1) {
            x = x - 1;
        } else if (dir == 2) {
            y = y + 1;
        } else if (dir == 3) {
            x = x + 1;
        }
        day11Prop.setDir(dir);
        day11Prop.setX(x);
        day11Prop.setY(y);
        day11Prop.setPanelCount(day11Prop.getPanelCount() + 1);

        String dirStr = null;
        if (dir == 0) {
            dirStr = "^";
        } else if (dir == 1) {
            dirStr = "<";
        } else if (dir == 2) {
            dirStr = "v";
        } else if (dir == 3) {
            dirStr = ">";
        }
        System.out.println("x:" + x + ",y:" + y + " dir:" + dirStr);
    }
}

