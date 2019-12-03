package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        try {
            String[] input = Util.loadResource("Day3").split("\n");
            String[] wire1 = input[0].split(",");
            String[] wire2 = input[1].split(",");
            ArrayList<Integer> x = new ArrayList<>(1);
            x.add(0);
            ArrayList<Integer> y = new ArrayList<>(1);
            y.add(0);
            List<Pair<Integer, Integer>> pointsWire1 = inputToPoint(wire1);
            List<Pair<Integer, Integer>> pointsWire2 = inputToPoint(wire2);
            List<Pair<Integer, Integer>> intersections = new ArrayList<>(1);
            List<Pair<Integer, Pair<Integer, Integer>>> stepsToIntersection = new ArrayList<>(1);
            for (Pair<Integer, Integer> wire1Pos : pointsWire1) {
                for (Pair<Integer, Integer> wire2Pos : pointsWire2) {
                    if (wire1Pos.getValue0().equals(wire2Pos.getValue0()) && wire1Pos.getValue1().equals(wire2Pos.getValue1())) {
                        intersections.add(wire1Pos);
                    }
                }
            }
            int minSteps = 0;
            for (Pair<Integer, Integer> intersection : intersections) {
                x.add(intersection.getValue0());
                y.add(intersection.getValue1());
                int steps = 0;
                for (Pair<Integer, Integer> wire1Pos : pointsWire1){
                    steps++;
                    if(wire1Pos.getValue0().equals(intersection.getValue0()) && wire1Pos.getValue1().equals(intersection.getValue1())){
                        break;
                    }
                }
                for (Pair<Integer, Integer> wire2Pos : pointsWire2){
                    steps++;
                    if(wire2Pos.getValue0().equals(intersection.getValue0()) && wire2Pos.getValue1().equals(intersection.getValue1())){
                        break;
                    }
                }
                if(minSteps == 0 || minSteps > steps){
                    minSteps = steps;
                }
                stepsToIntersection.add(new Pair<>(steps, intersection));
            }
            //System.out.println("Intersections: " + intersections.toString());
            System.out.println("Shortest distance: " + distance(x, y));
            //System.out.println("wire1StepsToIntersection: " + stepsToIntersection.toString());
            System.out.println("Minimum steps to intersection: " + minSteps);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int distance(ArrayList<Integer> x, ArrayList<Integer> y)
    {
        ArrayList<Triplet> distanceList = new ArrayList<>(1);
        int distance = 0;

        for (int i = 0; i < x.size(); i++){
            //System.out.println((Math.abs(x.get(0) - x.get(i)) + Math.abs(y.get(0) - y.get(i))));
            if(distance > (Math.abs(x.get(0) - x.get(i)) + Math.abs(y.get(0) - y.get(i))) || distance == 0){
                distance = (Math.abs(x.get(0) - x.get(i)) + Math.abs(y.get(0) - y.get(i)));
            }
        }

        return distance;
    }

    private static List<Pair<Integer, Integer>> inputToPoint(String[] wire) {
        List<Pair<Integer, Integer>> pointsWire1 = new ArrayList<>(1);
        Pair<Integer, Integer> currPos = new Pair<>(0, 0);
        for (String movement : wire) {
            movement = movement.replace("\r", "").replace("\n", "").replace("\r\n", "");
            char dir = movement.charAt(0);
            int dist = Integer.parseInt(movement.substring(1));
            for (int i = 0; i < dist; i++) {
                switch (dir) {
                    case 'R':
                        currPos = new Pair<>(currPos.getValue0() + 1, currPos.getValue1());
                        break;
                    case 'L':
                        currPos = new Pair<>(currPos.getValue0() - 1, currPos.getValue1());
                        break;
                    case 'U':
                        currPos = new Pair<>(currPos.getValue0(), currPos.getValue1() + 1);
                        break;
                    case 'D':
                        currPos = new Pair<>(currPos.getValue0(), currPos.getValue1() - 1);
                        break;
                }
                pointsWire1.add(currPos);
            }

        }
        return pointsWire1;
    }
}
