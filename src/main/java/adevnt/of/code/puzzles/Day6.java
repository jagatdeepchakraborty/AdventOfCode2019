package adevnt.of.code.puzzles;

import adevnt.of.code.Utils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day6 {
    public static void main(String[] args) {
        try {
            String[] input = Util.loadResource("Day6").split("\r\n");
            Map<String, Set<String>> orbitMap = new HashMap<>(1);
            for (String map : input) {
                String[] splitMap = map.split("\\)");
                if (orbitMap.containsKey(splitMap[1])) {
                    orbitMap.get(splitMap[1]).add(splitMap[0]);
                } else {
                    Set<String> stringSet = new HashSet<>(1);
                    stringSet.add(splitMap[0] + "*");
                    orbitMap.put(splitMap[1], stringSet);
                }
            }

            for (Map.Entry<String, Set<String>> outer : orbitMap.entrySet()) {
                for (Map.Entry<String, Set<String>> inner : orbitMap.entrySet()) {
                    Set<String> innerValue = inner.getValue().stream().map(s -> s.replace("*", "")
                    ).collect(Collectors.toSet());
                    Set<String> outerValue = outer.getValue().stream().map(s -> s.replace("*", "")
                    ).collect(Collectors.toSet());
                    if (innerValue.contains(outer.getKey())) {
                        inner.getValue().addAll(outerValue);
                    }
                }
            }
            System.out.println("YOU: " + orbitMap.get("YOU"));
            System.out.println("SAN: " + orbitMap.get("SAN"));
            System.out.println("Total orbits: " + orbitMap.values().stream().mapToInt(Set::size).sum());

            Set<String> commonSet = new HashSet<>(1);
            String fromYou = null;
            String fromSan = null;
            for (String node : orbitMap.get("YOU")) {
                if (orbitMap.get("SAN").contains(node)) {
                    commonSet.add(node);
                }
                if (node.contains("*")) {
                    fromYou = node;
                }
            }
            for (String node : orbitMap.get("SAN")) {
                if (node.contains("*")) {
                    fromSan = node;
                }
            }
            System.out.println(fromYou + "      " + fromSan);
            System.out.println(commonSet);
            findSteps(input, commonSet, fromYou, fromSan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void findSteps(String[] input, Set<String> commonSet, String fromYou, String fromSan) {
        fromSan = fromSan.replace("*", "");
        fromYou = fromYou.replace("*", "");
        Map<String, Set<String>> orbitMap = new HashMap<>(1);
        for (String map : input) {
            String[] splitMap = map.split("\\)");
            if (orbitMap.containsKey(splitMap[1])) {
                orbitMap.get(splitMap[1]).add(splitMap[0]);
            } else {
                Set<String> stringSet = new HashSet<>(1);
                stringSet.add(splitMap[0]);
                orbitMap.put(splitMap[1], stringSet);
            }
        }
        Map<String, Integer> stepsArray = new HashMap<>(1);

        for (String common : commonSet) {
            int youSteps = 0;
            int sanSteps = 0;
            String currYouStep = fromYou;
            String currSanStep = fromSan;
            for (Map.Entry<String, Set<String>> outerEntry : orbitMap.entrySet()) {
                for (Map.Entry<String, Set<String>> innerEntry : orbitMap.entrySet()) {
                    if (innerEntry.getKey().equals(currYouStep)) {
                        currYouStep = (String) innerEntry.getValue().toArray()[0];
                        youSteps++;
                    }
                }
                if(currYouStep.equals(common)){
                    break;
                }
            }
            for (Map.Entry<String, Set<String>> outerEntry : orbitMap.entrySet()) {
                for (Map.Entry<String, Set<String>> innerEntry : orbitMap.entrySet()) {
                    if (innerEntry.getKey().equals(currSanStep)) {
                        currSanStep = (String) innerEntry.getValue().toArray()[0];
                        sanSteps++;
                    }
                }
                if(currSanStep.equals(common)){
                    break;
                }
            }
            stepsArray.put(common, sanSteps + youSteps);
            /*if(youSteps > 0 && sanSteps > 0){
                stepsArray.add(sanSteps + youSteps);
            }*/
        }
        System.out.println(stepsArray);
        System.out.println("Min steps: " + stepsArray.values().stream().mapToInt(value -> value).min().getAsInt());
    }
}
