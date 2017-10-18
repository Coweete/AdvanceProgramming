package ComputationalGeometry;

import java.awt.geom.Line2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheArtGallery {

    private BufferedReader reader;

    public static void main(String[] args) {
        TheArtGallery artGallery = new TheArtGallery();

        try {
            artGallery.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        int numberofPoints, temp, temp1;
        ArrayList<Point> points;
        ArrayList<Boolean> booleans = new ArrayList<>();
        boolean flag, startValue;
        numberofPoints = Integer.parseInt(reader.readLine());
        while (true) {
            points = createPoints(numberofPoints);
            if (!isPolygon(points)) {
                System.out.println("No");
            } else {
                flag = true;

                startValue = ccw(points.get(0),points.get(1),points.get(2));

                for (int i = 1; i < points.size(); i++) {
                    if ((i + 2) == points.size()) {
                        temp = 0;
                    } else if ((i + 2) > points.size()) {
                        temp = 1;
                    } else {
                        temp = i + 2;
                    }
                    if (((i + 1) >= points.size())) {
                        temp1 = 0;
                    } else {
                        temp1 = i + 1;
                    }
                    if (startValue != ccw(points.get(i), points.get(temp1), points.get(temp))){
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    System.out.println("No");
                } else {
                    System.out.println("Yes");
                }
            }
            points.clear();
            booleans.clear();
            numberofPoints = Integer.parseInt(reader.readLine());
            if (numberofPoints == 0) {
                break;
            }
        }
    }

    private ArrayList<Point> createPoints(int numberofpoints) throws IOException {
        ArrayList<Point> points = new ArrayList<>();
        String[] input;
        for (int i = 0; i < numberofpoints; i++) {
            //input = scanner.nextLine().split(" ");
            input = reader.readLine().toString().split(" ");
            points.add(new Point(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
        return points;
    }

    public boolean isPolygon(ArrayList<Point> points) {
        int temp;
        for (int i = 0; i < points.size(); i++) {
            for (int j = (i + 1); j < points.size() - 1; j++) {
                if (j + 2 >= points.size()) {
                    temp = 0;
                } else {
                    temp = j + 2;
                }
                if (Line2D.linesIntersect(points.get(j - 1).x, points.get(j - 1).y, points.get(j).x, points.get(j).y,
                        points.get(j + 1).x, points.get(j + 1).y, points.get(temp).x, points.get(temp).y)) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean ccw(Point a, Point b, Point c) {
        double sum = signed_triangle_area(a, b, c);
        if (sum > 0) {
            return true;
        } else {
            return false;
        }
    }

    private double signed_triangle_area(Point a, Point b, Point c) {
        return (((a.x * b.y) - (a.y * b.x) + (a.y * c.x) - (a.x * c.y)
                + (b.x * c.y) - (c.x * b.y)) / 2.0);
    }

    private class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
