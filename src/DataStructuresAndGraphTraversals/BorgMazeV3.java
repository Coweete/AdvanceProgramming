package DataStructuresAndGraphTraversals;


import java.io.IOException;
import java.util.*;

public class BorgMazeV3 {

    private Scanner scanner;
    private int boardx, boardy;
    private boolean board[][];
    private Vertices startNode;
    private ArrayList<Vertices> vertices;
    private HashMap<String,Integer> boardWeight;
    private PriorityQueue<Vertices> priorityQueue;

    public static void main(String[] args) {
        BorgMazeV3 borgMazeV3 = new BorgMazeV3();
        try {
            borgMazeV3.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException{
        scanner = new Scanner(System.in);
        String res = scanner.nextLine();
        int testCases = Integer.parseInt(res);


        for (int i = 0; i < testCases; i++) {
            res = scanner.nextLine();
            String[] strings = res.split(" ");
            boardx = Integer.parseInt(strings[0]);
            boardy = Integer.parseInt(strings[1]);
            boardWeight = new HashMap<>();
            vertices = new ArrayList<>();
            System.out.println("X: " + boardx);
            System.out.println("Y: " + boardy);
            initBoards(boardx,boardy);
            Vertices start = vertices.remove(0);
            dijsktra(vertices,start,boardWeight);

        }
    }


    public void initBoards(int x,int y){
        System.out.println("In init Boards");
        System.out.println("X: " + x);
        System.out.println("Y: " + y);
        board = new boolean[y][x];

        String res;
        for (int i = 0; i < y; i++) {
            res = scanner.nextLine();
            for (int j = 0; j < x; j++) {
                if (res.charAt(j) == 'S'){
                    vertices.add(new Vertices(j,i,0,"" + j +"," + i));
                    boardWeight.put("" + j +"," + i,0);
                    board[i][j] = true;
                }else if (res.charAt(j) == 'A'){
                    vertices.add(new Vertices(j,i,0,"" + j +"," + i));
                    boardWeight.put("" + j +"," + i,0);
                    board[i][j] = true;
                }else if (res.charAt(j) == ' '){
                    boardWeight.put("" + j +"," + i,Integer.MAX_VALUE);
                    board[i][j] = true;
                }else {
                    boardWeight.put("" + j +"," + i,Integer.MAX_VALUE);
                    board[i][j] = false;
                }
            }
        }

    }

    public HashMap<String,Vertices> dijsktra(ArrayList<Vertices> nodes,Vertices startnode,HashMap<String,Integer> localBoardWeight){
        priorityQueue = new PriorityQueue<>(Comparator.comparing(Vertices::getWeight));
        for (int i = 0; i < nodes.size(); i++) {
            localBoardWeight.put(nodes.get(i).id,Integer.MAX_VALUE);
        }
        localBoardWeight.put(startnode.id,0);
        priorityQueue.add(startnode);
        while (!priorityQueue.isEmpty()){
            System.out.println("ajsodjasodja");
            Vertices node = priorityQueue.remove();
            if ((node.x +1 < boardx) && (board[node.y][node.x +1])){
                System.out.println("x +1");
            }
            if ((node.x -1 >= 0) && (board[node.y][node.x-1])){
                System.out.println("x -1");
            }
            if ((node.y + 1 < boardy) && (board[node.y +1][node.x])){
                System.out.println("y +1");
            }
            if ((node.y - 1>= 0) && (board[node.y -1][node.x])){
                System.out.println("y -1");
            }
        }

        return null;
    }

    private class Vertices{
        int x,y;
        int weight;
        String id;
        boolean visited;

        public Vertices(int x, int y, int weight, String id) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.id = id;
        }

        public int getWeight() {
            return weight;
        }
    }

}
