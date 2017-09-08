package Problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DecodeTheTape {

    public static void main(String[] args) throws Exception {
        DecodeTheTape dp = new DecodeTheTape();
        dp.decode();
    }

    public void decode() throws Exception{
        String in,temp;
        char test;
        int value = 0;

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        bufferedReader.readLine();

        in = bufferedReader.readLine();
        while (!(in.equals("___________"))){
            temp = in;
            temp = temp.replace(" ","0");
            temp = temp.replace("o","1");
            temp = temp.substring(1,10);
            temp = temp.substring(0,5) + temp.substring(6,9);


            for (int i = 0; i <temp.length() ; i++) {
                if(temp.charAt(i) == '1'){
                    value += (int) Math.pow(2,Math.abs(i-7));
                }
            }

            test = (char) value;
            value = 0;
            in = bufferedReader.readLine();
            System.out.print(test);
        }

    }

}
