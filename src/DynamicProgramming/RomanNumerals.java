package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class RomanNumerals {

    private BufferedReader bufferedReader;
    private HashMap<Character, Integer> dictionaryChar;
    private String output;
    private boolean isRunning;
    private int solution;


    public void start() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        createDictionaryChar();
        isRunning = true;
        while (isRunning){
            roman();
        }
    }


    public int translateToInteger(String roman) {
        int number = 0;
        for (int i = 0; i < roman.length(); i++) {
            int n1 = dictionaryChar.get(roman.charAt(i));
            if (i == roman.length() - 1) {
                number = number + n1;
            } else {
                int n2 = dictionaryChar.get(roman.charAt(i + 1));
                if (n1 < n2) {
                    number = number -  n1;
                } else {
                    number = number + n1;
                }
            }
        }
        return number;
    }

    public void roman() throws IOException{
        String input = bufferedReader.readLine();
        output = "";
        boolean[] leadingCharacters = new boolean[128];
        boolean[] potEncoding = new boolean[10];
        int[] value = new int[128];
        HashSet<Character> characters = new HashSet<>();
        String p1,p2,p3;

        //Checks if there is more to do
        if (input.contains("#")){
            isRunning = false;
            return;
        }


        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != '+' && input.charAt(i) != '='){
                characters.add(input.charAt(i));
            }
        }

        ArrayList<Character> allCharacters = new ArrayList<>(characters);

        String[] temp = input.split("\\+|=");

        for (int i = 0; i < temp.length; i++) {
            leadingCharacters[temp[i].charAt(0)] = true;
        }

        p1 = temp[0];
        p2 = temp[1];
        p3 = temp[2];

        solution = 0;

        if ((translateToInteger(p1) + translateToInteger(p2)) == translateToInteger(p3)){
            output = output + "Correct";
        }else {
            output = output + "Incorrect";
        }

        arabicEncoding(0,allCharacters,value,potEncoding,leadingCharacters,p1,p2,p3);

        switch (solution){
            case 0:
                output = output + " impossible";
                break;
            case 1:
                output = output + " valid";
                break;
            default:
                output = output + " ambiguous";
                break;
        }
        System.out.println(output);
    }

    private void arabicEncoding(int curIndex, ArrayList<Character> characters, int[] value,
                                boolean[] potEncoding, boolean[] leadingCharacters,String p1,String p2,String p3) {
        if (solution > 1){
            return;
        }

        if (curIndex == characters.size()){
            int vc1 = 0,vc2 = 0,vc3 = 0;

            for (int i = 0; i < p1.length(); i++) {
                vc1 = vc1 * 10 + value[p1.charAt(i)];
            }

            for (int i = 0; i < p2.length(); i++) {
                vc2 = vc2 * 10 + value[p2.charAt(i)];
            }

            for (int i = 0; i < p3.length(); i++) {
                vc3 = vc3 * 10 + value[p3.charAt(i)];
            }

            if ((vc1 + vc2) == vc3){
                solution++;
            }
            return;
        }

        boolean leadingOrNah = leadingCharacters[characters.get(curIndex)];
        int start;
        if (!leadingOrNah){
            start = 0;
        }else {
            start = 1;
        }

        for (int i = start; i < 10 && solution <= 1; i++) {
            if (!potEncoding[i]){
                value[characters.get(curIndex)] = i;
                potEncoding[i] = true;
                arabicEncoding(curIndex+1,characters,value,potEncoding,leadingCharacters,p1,p2,p3);
                potEncoding[i] = false;
            }
        }
    }

    private void createDictionaryChar() {
        dictionaryChar = new HashMap<>();
        dictionaryChar.put('M', 1000);
        dictionaryChar.put('D', 500);
        dictionaryChar.put('C', 100);
        dictionaryChar.put('L', 50);
        dictionaryChar.put('X', 10);
        dictionaryChar.put('V', 5);
        dictionaryChar.put('I', 1);
    }

    public static void main(String[] args) {
        RomanNumerals roman = new RomanNumerals();
        try {
            roman.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
