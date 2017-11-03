package DataStructuresAndGraphTraversals;

import Problems.BoxOfBricks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Borg_Maze {

    private BufferedReader bufferedReader;
    private ArrayList<BoardTile> nodes;
    private int counter;

    public static void main(String[] args) {
        Borg_Maze maze = new Borg_Maze();
        try {
            maze.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start() throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input[];
        int xIndex, yIndex, testcases, sum;
        testcases = Integer.parseInt(bufferedReader.readLine());
        nodes = new ArrayList<>();

        for (int i = 0; i < testcases; i++) {
            counter = 0;
            input = bufferedReader.readLine().split(" ");
            xIndex = Integer.parseInt(input[0]);
            yIndex = Integer.parseInt(input[1]);
            BoardTile[][] boardTiles = initBoard(xIndex, yIndex);

            ArrayList<Edges> edges = new ArrayList<>();
            for (int j = 0; j < nodes.size(); j++) {
                int[][] array = weight_list(xIndex, yIndex, nodes.get(j).x, nodes.get(j).y);
                ArrayList<BoardTile> temp = new ArrayList<>(nodes);
                bfs(array, boardTiles, nodes.get(j));
                for (int k = j + 1; k < temp.size(); k++) {
                    edges.add(new Edges(nodes.get(j), temp.get(k), array[temp.get(k).y][temp.get(k).x]));
                }

            }

            sum = kruskal(nodes, edges);
            System.out.println(sum);
            edges.clear();
            nodes.clear();
        }


    }


    private void bfs(int[][] weight, BoardTile[][] boardTiles, BoardTile startNode) {
        LinkedList<BoardTile> queue = new LinkedList<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            BoardTile tempNode = queue.pop();

            if ((tempNode.x + 1 < boardTiles[0].length) && !(boardTiles[tempNode.y][tempNode.x + 1].id == '#')
                    && (weight[tempNode.y][tempNode.x + 1] == -1)) {

                weight[tempNode.y][tempNode.x + 1] = weight[tempNode.y][tempNode.x] + 1;
                BoardTile search = boardTiles[tempNode.y][tempNode.x + 1];
                queue.add(search);
            }
            if ((tempNode.x - 1 >= 0) && !(boardTiles[tempNode.y][tempNode.x - 1].id == '#')
                    && (weight[tempNode.y][tempNode.x - 1] == -1)) {

                weight[tempNode.y][tempNode.x - 1] = weight[tempNode.y][tempNode.x] + 1;
                BoardTile search = boardTiles[tempNode.y][tempNode.x - 1];
                queue.add(search);
            }
            if ((tempNode.y + 1 < boardTiles.length) && !(boardTiles[tempNode.y + 1][tempNode.x].id == '#')
                    && (weight[tempNode.y + 1][tempNode.x] == -1)) {

                weight[tempNode.y + 1][tempNode.x] = weight[tempNode.y][tempNode.x] + 1;
                BoardTile search = boardTiles[tempNode.y + 1][tempNode.x];
                queue.add(search);

            }
            if ((tempNode.y - 1 >= 0) && !(boardTiles[tempNode.y - 1][tempNode.x].id == '#')
                    && (weight[tempNode.y - 1][tempNode.x] == -1)) {

                weight[tempNode.y - 1][tempNode.x] = weight[tempNode.y][tempNode.x] + 1;
                BoardTile search = boardTiles[tempNode.y - 1][tempNode.x];
                queue.add(search);
            }


        }

    }

    private int kruskal(ArrayList<BoardTile> vertices, ArrayList<Edges> edges) {
        ArrayList<Edges> treeSet = new ArrayList<>();
        int sum = 0;
        Collections.sort(edges, Comparator.comparing(Edges::getDistance));
        for (int i = 0; i < edges.size(); i++) {
            if (find(edges.get(i).start) != find(edges.get(i).end)) {
                treeSet.add(edges.get(i));
                union(edges.get(i).start, edges.get(i).end);
                sum += edges.get(i).distance;
            }
        }
        return sum;
    }

    private BoardTile find(BoardTile node) {
        while (node != node.parent) {
            node = node.parent;
        }
        return node;
    }

    private void union(BoardTile x, BoardTile y) {
        BoardTile tempR = find(x);
        BoardTile tempL = find(y);
        if (tempR.equals(tempL))
            return;
        if (tempR.rank > tempL.rank) {
            tempL.parent = tempR;
        } else {
            tempR.parent = tempL;
            if (tempR.rank == tempL.rank) {
                tempL.rank += 1;
            }
        }
    }

    private int[][] weight_list(int x, int y, int startX, int startY) {
        int[][] board = new int[y][x];
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                board[i][j] = -1;
            }
        }
        board[startY][startX] = 0;
        return board;
    }

    private BoardTile[][] initBoard(int x, int y) throws IOException {
        BoardTile[][] boardTiles = new BoardTile[y][x];
        String input;
        for (int i = 0; i < y; i++) {
            input = bufferedReader.readLine();
            for (int j = 0; j < x; j++) {
                if (input.charAt(j) == 'S') {
                    nodes.add(new BoardTile(j, i, 'S', "S"));
                } else if (input.charAt(j) == 'A') {
                    nodes.add(new BoardTile(j, i, 'A', "A" + counter++));
                }
                boardTiles[i][j] = new BoardTile(j, i, input.charAt(j));
            }
        }
        return boardTiles;
    }

    private void printArray(BoardTile[][] boardTiles) {
        for (int i = 0; i < boardTiles.length; i++) {
            System.out.print("[");
            for (int j = 0; j < boardTiles[i].length; j++) {
                System.out.print(boardTiles[i][j].id + ",");
            }
            System.out.print("]");
            System.out.println("");
        }
    }

    private void printNodeList() {
        System.out.println("");
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(nodes.get(i).id);
        }
        System.out.println("");

    }

    private void printIntBoard(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("[");
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + ",");
            }
            System.out.println("]");
        }
    }

    private void printEdges(ArrayList<Edges> edges) {
        for (int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i).start.name + "," + edges.get(i).end.name + "," + edges.get(i).distance);
        }
    }

    private void printParent(ArrayList<BoardTile> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("Parent:" + vertices.get(i).parent + ",this:" + vertices.get(i));
        }
    }


    private class BoardTile {
        int x;
        int y;
        char id;
        String name;
        BoardTile parent = this;
        int rank = 0;

        public BoardTile(int x, int y, char id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }

        public BoardTile(int x, int y, char id, String name) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.name = name;
        }
    }

    private class Edges {
        BoardTile start;
        BoardTile end;
        int distance;

        public Edges(BoardTile start, BoardTile end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }
    }
}
