package ComputationalGeometry;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TheClosestPairProblem {

    private BufferedReader bufferedReader;

    public static void main(String[] args) {
        TheClosestPairProblem problem = new TheClosestPairProblem();

        try {
            problem.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        int nbrPoints,minDistance;
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<Point> sorted = new ArrayList<>();

        nbrPoints = Integer.parseInt(bufferedReader.readLine());
        while (true){
            minDistance = 10000;

            for (int i = 0; i < nbrPoints; i++) {
                input = bufferedReader.readLine().split(" ");
                points.add(new Point(Integer.parseInt(input[0]),Integer.parseInt(input[1])));
            }




            points.clear();
            nbrPoints = Integer.parseInt(bufferedReader.readLine());
            if (nbrPoints == 0){
                return;
            }
        }
    }



    public Point[] ClosestPair(ArrayList<Point> points){
        ArrayList<Point> sorterd = points;
        ArrayList<Point> temp = new ArrayList<>();
        Point[] closestPair;

        Collections.sort(sorterd,Comparator.comparing(Point::getX));
        temp = sorterd;
        double currentMinDistance = 10000;

        for (int i = 0; i < points.size(); i++) {

            Point current = sorterd.get(i);
            if (current.x - sorterd.get(i).x > currentMinDistance){
                temp.remove(sorterd.get(i));
            }



        }

        return null;
    }
}
