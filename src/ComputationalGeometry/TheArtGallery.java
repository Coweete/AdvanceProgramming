package ComputationalGeometry;

import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TheArtGallery {

    private Scanner scanner;

    public static void main(String[] args) {
        TheArtGallery artGallery = new TheArtGallery();

        try {
            artGallery.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        scanner = new Scanner(System.in);
        int numberofPoints, temp;
        ArrayList<Point> points;
        ArrayList<Boolean> booleans = new ArrayList<>();
        boolean sum,flag;
        numberofPoints = Integer.parseInt(scanner.nextLine());
        while (true){
            points = createPoints(numberofPoints);
            if (!isPolygon(points)){
                System.out.println("No, int");
            }else {
                flag = true;
                for (int i = 0; i < points.size()-1; i++) {
                    if ((i+2) >= points.size()){
                        temp = 0;
                    }else {
                        temp = i+2;
                    }
                    booleans.add(ccw(points.get(i),points.get(i+1),points.get(temp)));
                }
                for (int i = 1; i < booleans.size(); i++) {
                    if (!(booleans.get(0) == booleans.get(i))){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    System.out.println("No");
                }else{
                    System.out.println("Yes");
                }
            }
            points.clear();
            booleans.clear();
            numberofPoints = Integer.parseInt(scanner.nextLine());
            if (numberofPoints == 0){
                break;
            }
        }
    }

    private ArrayList<Point> createPoints(int numberofpoints){
        ArrayList<Point> points = new ArrayList<>();
        String[] input;
        for (int i = 0; i < numberofpoints; i++) {
            input = scanner.nextLine().split(" ");
            points.add(new Point(Integer.parseInt(input[0]),Integer.parseInt(input[1])));
        }
        return points;
    }

    public boolean isPolygon(ArrayList<Point> points){
        int temp;
        for (int i = 0; i < points.size(); i++) {
            for (int j = (i + 1); j < points.size()-1; j++) {
                if (j+2 >= points.size()){
                    temp = 0;
                }else {
                    temp = j+2;
                }
                if (Line2D.linesIntersect(points.get(j-1).x,points.get(j-1).y,points.get(j).x,points.get(j).y,
                        points.get(j+1).x,points.get(j+1).y,points.get(temp).x,points.get(temp).y)){
                    return false;
                }
            }
        }
        return true;
    }


    private boolean ccw(Point a,Point b,Point c){
        double sum = signed_triangle_area(a,b,c);
        //System.out.println("Sum:" + sum);
        if (sum > 0){
            return true;
        }else {
            return false;
        }
    }

    private double signed_triangle_area(Point a,Point b,Point c){
        return (((a.x * b.y) - (a.y * b.x) + (a.y * c.x) - (a.x * c.y)
                + (b.x * c.y) - (c.x * b.y)) / 2.0);
    }

    private class Point{
        int x,y;

        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
}
