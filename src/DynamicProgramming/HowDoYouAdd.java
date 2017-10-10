package DynamicProgramming;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class HowDoYouAdd {

    private Scanner scanner;
    private int[][] array;

    public static void main(String[] args) {
        HowDoYouAdd howDoYouAdd = new HowDoYouAdd();

        try {
            howDoYouAdd.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        scanner = new Scanner(System.in);
        int a,b;

        String[] res;
        res = scanner.nextLine().split(" ");
        a = Integer.parseInt(res[0]);
        b = Integer.parseInt(res[1]);
        while (true){
            arraySum(a,b);
            res = scanner.nextLine().split(" ");
            a = Integer.parseInt(res[0]);
            b = Integer.parseInt(res[1]);
            if (a == 0 && b == 0){
                break;
            }
        }
    }


    public void arraySum(int a,int b){
        long[][] array = new long[a][b + 1];

        for (int i = 0; i < array.length; i++) {
            Arrays.fill(array[i],1);
            array[i][0] = 0;
        }

        for (int i = 0; i < array[0].length; i++) {
            array[0][i] = i;
        }

        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                array[i][j] = (array[i-1][j] + array[i][j-1]) % 1000000;
            }
        }
        System.out.println(array[a-1][b]);
    }
}
