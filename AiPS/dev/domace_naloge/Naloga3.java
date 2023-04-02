import java.util.Scanner;

class Graph {
    private int[][] adjacencyMatrix;
    private int numberOfVertices;

    Graph(int numberOfVertices, String[] input) {
        this.numberOfVertices = numberOfVertices;
        this.generateMatrixFromInput(input);
    }

    private void generateMatrixFromInput(String[] input) {
        int inputLength = input.length;
        int[][] array = new int[inputLength][2];
        for (int i = 0; i < inputLength; i++) {
            array[i][0] = Integer.parseInt(input[i].split(" ")[0]);
            array[i][1] = Integer.parseInt(input[i].split(" ")[1]);
        }

        // int maxRow = getMaxRowAndCol(array)[0];
        // int maxCol = getMaxRowAndCol(array)[1];

        adjacencyMatrix = new int[numberOfVertices][numberOfVertices];

        for (int i = 0; i < array.length; i++) {
            int row = array[i][0];
            int col = array[i][1];
            adjacencyMatrix[row][col] += 1;
        }


    }

    private int[] getMaxRowAndCol(int[][] input) {
        int maxRow = input[0][0];
        int maxCol = input[0][1];
        for (int i = 0; i < input.length; i++) {
            if (input[i][0] > maxRow) {
                maxRow = input[i][0];
            }
            if (input[i][1] > maxCol) {
                maxCol = input[i][0];
            }
        }
        return new int[]{ maxRow, maxCol };
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}

class GraphAlgorithms {
    private String type, action, parameter;
    private int numberOfVertices;
    private Graph graph;

    GraphAlgorithms(String type, String action, String parameter, String[] input, int numberOfVertices) {
        this.type = type;
        this.action = action;
        this.parameter = parameter;
        this.numberOfVertices = numberOfVertices;
        this.graph = new Graph(numberOfVertices, input);
    }

    public void executeAction() {
        switch (action) {
            case "info": {
                break;
            }
            case "walks": {
                break;
            }
            case "dfs": {
                break;
            }
            case "bfs": {
                break;
            }
            case "sp": {
                break;
            }
            case "comp": {
                break;
            }
            case "ham": {
                break;
            }
            default: break;
        }
    }
}

public class Naloga3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Graph type, action on graph and optional parameter for the action
        /*String[] settings = sc.nextLine().split(" ");
        String type = settings[0];
        String action = settings[1];
        String parameter = settings.length > 2 ? settings[2] : null;*/

        // Number of lines and string array of connections
        int numberOfVertices = Integer.parseInt(sc.nextLine().trim());
        StringBuffer buffer = new StringBuffer();
        String connection = sc.nextLine();
        while (sc.hasNextLine()) {
            if (!connection.isEmpty()) {
                buffer.append(connection + "|");
            }
            connection = sc.nextLine();
        }
        sc.close();

        String inputString = buffer.toString();
        int numberOfConnections = inputString.split("|").length;
        String[] input = new String[numberOfConnections];
        for (int i = 0; i < numberOfConnections; i++) {
            input[i] = inputString.split("|")[i];
            System.out.print(input[i]);
        }
        /*GraphAlgorithms graphAlgorithms = new GraphAlgorithms(type, action, parameter, input, numberOfVertices);
        graphAlgorithms.executeAction();*/
    }
}

/*
undirected info
10
0 1
1 2
1 3
1 4
2 4
2 5
3 0
4 3
4 5
6 7
7 9
*/
