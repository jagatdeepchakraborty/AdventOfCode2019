package adevnt.of.code.puzzles;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day4 {
    public static void main(String[] args) {
        ArrayList<Integer> passwords = new ArrayList<>(1);
        for (int i = 128392; i < 643281; i++) {
            int[] digits = Integer.toString(i).chars().map(c -> c - '0').toArray();
            int currDig = 0;
            boolean crtr1 = false;
            boolean crtr2 = true;
            Map<Integer, Integer> dupDigMap = new HashMap<>();
            for (int digit : digits) {
                if(currDig == digit){
                    if(dupDigMap.containsKey(digit)){
                        dupDigMap.put(digit, dupDigMap.get(digit)+1);
                    } else {
                        dupDigMap.put(digit, 2);
                    }
                }
                currDig = digit;
            }
            if(dupDigMap.containsValue(2)){
                crtr1 = true;
            }
            if(!crtr1){
                continue;
            }
            currDig = 0;
            for (int digit : digits) {
                if (currDig > digit) {
                    crtr2 = false;
                    break;
                }
                currDig = digit;
            }
            if (crtr1 && crtr2) {
                passwords.add(i);
            }
        }
        System.out.println("Possible password combinations: " + passwords.size());
    }
}
