package DataStructuresAndGraphTraversals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotSoMobile {

    private boolean result;
    private BufferedReader reader;

    public static void main(String[] args) throws IOException {
        NotSoMobile notSoMobile = new NotSoMobile();
        notSoMobile.mobile();
    }

    public void mobile() throws IOException {
        int testCases;
        int numbers[] = new int[4];
        String input[];
        String res = "as";
        reader = new BufferedReader(new InputStreamReader(System.in));

        testCases = Integer.parseInt(reader.readLine());


        for (int i = 0; i < testCases; i++) {
            result = true;
            res = reader.readLine();
            input = reader.readLine().split(" ");
            numbers = getNumbers(input);
            notSoMobile(numbers[0],numbers[1],numbers[2],numbers[3]);
            if (result){
                System.out.print("YES\n");
            }else{
                System.out.print("NO\n");
            }
            if (i != testCases-1){
                System.out.println("");
            }
        }
    }


    public int notSoMobile(int wl,int dl,int wr,int dr) throws IOException {
        int wghr = 0, wghl = 0,total = 0;
        String res[] = new String[4];
        int numbers[] = new int[4];

        if(wl == 0){
            res = reader.readLine().split(" ");
            numbers = getNumbers(res);
            wghl = notSoMobile(numbers[0],numbers[1],numbers[2],numbers[3]);
        }
        wghl +=wl;
        if(wr == 0){
            res = reader.readLine().split(" ");
            numbers = getNumbers(res);
            wghr = notSoMobile(numbers[0],numbers[1],numbers[2],numbers[3]);
        }
        wghr +=wr;


        if(!((wghr * dr) == (wghl * dl))){
            result = false;
        }


        total = wghl + wghr ;
        return total;

    }

    public int[] getNumbers(String array[]){
        int numbers[] = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            numbers[i] = Integer.parseInt(array[i]);
        }
        return numbers;
    }

}
