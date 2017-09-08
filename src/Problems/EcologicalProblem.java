package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EcologicalProblem {

    public static void main(String[] args) throws IOException {
        EcologicalProblem ep = new EcologicalProblem();
        ep.ecologicalProblem();


    }

    public void ecologicalProblem() throws IOException {
        String in;
        String temp[];
        int money = 0,testCases = 0,farmers = 0;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        in = input.readLine();
        testCases = Integer.parseInt(in);

        for (int i = 0; i < testCases; i++) {
            in = input.readLine();
            farmers = Integer.parseInt(in);

            for (int j = 0; j < farmers; j++) {
                temp = input.readLine().split(" ");
                money += Integer.parseInt(temp[0]) * Integer.parseInt(temp[2]);
            }
            System.out.println(money);
            money = 0;
        }
    }
}
