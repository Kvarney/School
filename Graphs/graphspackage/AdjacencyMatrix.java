package graphspackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AdjacencyMatrix{
    private static final int INITIAL_SIZE = 27;
    public static String[][] adjacencyMatrix = new String[INITIAL_SIZE][INITIAL_SIZE];
    private static boolean weighted = false;
    private static boolean directed = false;
    private static int numOfVertices = 0;
    private static int toIndex = 0;
    private static int fromIndex = 0;
    private static int numberOfEdges = 0;

    public AdjacencyMatrix(final String fileName) throws IOException {
        readGraph(fileName);
    }

    public static boolean hasEdge(String from, String to) {
        fromIndex = getVertexIndex(from);
        toIndex = getVertexIndex(to);
        return !(adjacencyMatrix[fromIndex][toIndex]).equals("false");

    }

    public static void addEdge(String weight) {
        if (directed == false) {
            adjacencyMatrix[fromIndex][toIndex] = weight;
            adjacencyMatrix[toIndex][fromIndex] = weight;
        } else {
            adjacencyMatrix[fromIndex][toIndex] = weight;
        }
        numberOfEdges++;
    }

    public static void addEdge() {
        if (directed == false) {
            adjacencyMatrix[fromIndex][toIndex] = "true";
            adjacencyMatrix[toIndex][fromIndex] = "true";
        } else {
            adjacencyMatrix[fromIndex][toIndex] = "true";
        }
        numberOfEdges++;
    }

    public static void deleteEdge() {
        if (directed == false) {
            adjacencyMatrix[fromIndex][toIndex] = "false";
            adjacencyMatrix[toIndex][fromIndex] = "false";
        } else {
            adjacencyMatrix[fromIndex][toIndex] = "false";
        }
        numberOfEdges--;
    }

    public static void addVertex(String vertex) {
        String[][] temp = new String[numOfVertices + 2][numOfVertices + 2];
        //Copying existing adjacency matrix into a new larger adjacency matrix.
        for (int from = 0; from < numOfVertices + 1; from++) {
            for (int to = 0; to < numOfVertices + 1; to++) {
                temp[from][to] = adjacencyMatrix[from][to];
            }
        }
        //Adding new vertex to the adjacency matrix.
        temp[numOfVertices + 1][0] = vertex;
        temp[0][numOfVertices + 1] = vertex;
        numOfVertices++;
        //Initializing all edges of new vertex to false.
        for (int from = 1; from < numOfVertices + 1; from++) {
            temp[from][numOfVertices] = "false";
        }
        for (int to = 1; to < numOfVertices + 1; to++) {
            temp[numOfVertices][to] = "false";
        }
        adjacencyMatrix = temp;
        numOfVertices++;
        printAdjacencyMatrix();
    }

    public static void deleteVertex(String vertex) {
        String[][] temp = new String[numOfVertices][numOfVertices];
        int index = getVertexIndex(vertex);
        //First Quad
        for (int from = 0; from < index; from++) {
            for (int to = 0; to < index; to++) {
                temp[from][to] = adjacencyMatrix[from][to];
            }
        }
        //Second Quad
        for (int from = 0; from < index; from++) {
            for (int to = index + 1; to < numOfVertices + 1; to++) {
                temp[from][to - 1] = adjacencyMatrix[from][to];
            }
        }
        //Third Quad
        for (int from = index + 1; from < numOfVertices + 1; from++) {
            for (int to = 0; to < index; to++) {
                temp[from - 1][to] = adjacencyMatrix[from][to];
            }
        }
        //Fourth Quad
        for (int from = index + 1; from < numOfVertices + 1; from++) {
            for (int to = index + 1; to < numOfVertices + 1; to++) {
                temp[from - 1][to - 1] = adjacencyMatrix[from][to];
            }
        }
        adjacencyMatrix = temp;
        numOfVertices--;
        System.err.println("Vertex " + vertex + " removed");
        printAdjacencyMatrix();
    }

    public static boolean isSparse() {
        int maxEdges = ((numOfVertices * (numOfVertices - 1)) / 2);
        return (numberOfEdges <= (maxEdges * 0.15));
    }

    public static boolean isDense() {
        int maxEdges = ((numOfVertices * (numOfVertices - 1)) / 2);
        return (numberOfEdges >= (maxEdges * 0.85));
    }

    public static int countVertices() {
        return numOfVertices;
    }

    public static int countEdges() {
        return numberOfEdges;
    }

    public static boolean isConnected() {
        return true;
    }

    public static boolean isFullyConnected() {
        int maxEdges = ((numOfVertices * (numOfVertices - 1)) / 2);
        return (numberOfEdges == maxEdges);
    }

    public static void readGraph(String fileName) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("./Tests/" + fileName);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            System.err.println(line);
            String[] currentLine = line.split(" ");
            if (!currentLine[0].equals("*")) {
                if (line.equals("weighted")) {
                    weighted = true;
                    //message for testing proper functionality
                    System.err.println("**The graph is weighted");
                }
                if (line.equals("directed")) {
                    directed = true;
                    //message for testing proper functionality
                    System.err.println("**The graph is directed");
                }
                if (line.equals("begin")) {
                    //Reads line with initial list of vertices.
                    line = br.readLine();
                    currentLine = line.split(" ");
                    System.err.println(line);
                    numOfVertices = currentLine.length;

                    //message for testing proper functionality
                    System.err.println("**The number of Verticies: " + numOfVertices);

                    //adding the vertices to adjacency matrix
                    for (int i = 1; i < numOfVertices + 1; i++) {
                        adjacencyMatrix[i][0] = currentLine[i - 1];
                        adjacencyMatrix[0][i] = currentLine[i - 1];
                    }

                    //setting rest of adjacency matrix to false
                    for (int i = 1; i < numOfVertices + 1; i++) {
                        for (int j = 1; j < numOfVertices + 1; j++) {
                            adjacencyMatrix[i][j] = "false";
                        }
                    }

                    String[][] temp = new String[numOfVertices + 1][numOfVertices + 1];
                    for (int j = 0; j < numOfVertices + 1; j++) {
                        for (int i = 0; i < numOfVertices + 1; i++) {
                            temp[j][i] = adjacencyMatrix[j][i];
                        }
                    }

                    adjacencyMatrix = temp;

                    //adds initial edges to adjacency matrix set up before functions are called
                    while (!(line = br.readLine()).equals("end")) {
                        System.err.println(line);
                        currentLine = line.split(" ");

                        fromIndex = getVertexIndex(currentLine[0]);
                        toIndex = getVertexIndex(currentLine[1]);

                        if (weighted == true) {
                            addEdge(currentLine[2]);
                        } else {
                            addEdge();
                        }
                    }

                    printAdjacencyMatrix();
                }

                //running functions starts here
                if (line.equals("end")) {
                    while ((line = br.readLine()) != null) {
                        currentLine = line.split(" ");
                        System.err.println(line);

                        if (currentLine[0].equals("hasEdge")) {
                            if ((hasEdge(currentLine[1], currentLine[2])) == true) {
                                System.err.println("True");
                            } else if ((hasEdge(currentLine[1], currentLine[2])) == false) {
                                System.err.println("False");
                            } else {
                                System.err.println("There was an issue with determining if there was an edge.");
                            }
                        }
                        if (currentLine[0].equals("addEdge")) {
                            fromIndex = getVertexIndex(currentLine[1]);
                            toIndex = getVertexIndex(currentLine[2]);
                            if (weighted == false) {
                                addEdge();
                            } else if (weighted == true) {
                                addEdge(currentLine[3]);
                            } else {
                                System.err.println("There was an error adding edge.");
                            }
                            printAdjacencyMatrix();
                        }
                        if (currentLine[0].equals("deleteEdge")) {
                            fromIndex = getVertexIndex(currentLine[1]);
                            toIndex = getVertexIndex(currentLine[2]);
                            deleteEdge();
                            System.err.println("The edge from " + currentLine[1]
                                    + " to " + currentLine[2] + " has been deleted");
                            printAdjacencyMatrix();
                        }
                        if (currentLine[0].equals("addVertex")) {
                            addVertex(currentLine[1]);
                        }
                        if (currentLine[0].equals("deleteVertex")) {
                            deleteVertex(currentLine[1]);
                        }
                        if (currentLine[0].equals("isSparse")) {
                            if (isSparse() == true) {
                                System.err.println("The graph is sparse");
                            } else if (isSparse() == false) {
                                System.err.println("The graph is not sparse");
                            } else {
                                System.err.println("There was an error checking if the graph is sparse");
                            }
                        }
                        if (currentLine[0].equals("isDense")) {
                            if (isDense() == true) {
                                System.err.println("The graph is dense");
                            } else if (isDense() == false) {
                                System.err.println("The graph is not dense");
                            } else {
                                System.err.println("There was an error checking if the graph is dense");
                            }
                        }
                        if (currentLine[0].equals("countVertices")) {
                            System.err.println("There are: " + countVertices() + " Vertices");
                        }
                        if (currentLine[0].equals("countEdges")) {
                            System.err.println("There are: " + countEdges() + " Edges");
                        }
                        if (currentLine[0].equals("isConnected")) {
                            if (isConnected() == true) {
                                System.err.println("The graph is connected");
                            } else if (isConnected() == false) {
                                System.err.println("The graph is not connected");
                            } else {
                                System.err.println("There was an error with checking if the graph is connected");
                            }
                        }
                        if (currentLine[0].equals("isFullyConnected")) {
                            if (isFullyConnected() == true) {
                                System.err.println("The graph is fully connected");
                            } else if (isFullyConnected() == false) {
                                System.err.println("The graph is not fully connected");
                            } else {
                                System.err.println("There was an error with checking if the graph is fully connected");
                            }
                        }
                        if (currentLine[0].equals("printGraph")) {
                            printGraph(fileName);
                        }
                    }
                }
            }
        }
        printGraph(fileName);
    }

    public static void printGraph(String fileName) throws IOException {
        String OUTPUTFILE = fileName.replace(".txt", ".log");
        FileWriter fileWriter = new FileWriter(OUTPUTFILE);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        //prints if graph is weighted
        if (weighted == true) {
            printWriter.println("weighted");
        } else if (weighted == false) {
            printWriter.println("unweighted");
        }
        //prints if graph is directed
        if (directed == true) {
            printWriter.println("directed");
        } else if (directed == false) {
            printWriter.println("undirected");
        }
        printWriter.println("begin");
        //prints out the vertices
        for (int i = 1; i < numOfVertices + 1; i++) {
            printWriter.print(adjacencyMatrix[0][i] + " ");
        }
        //prints out the edges and value if weighted
        for (int from = 1; from < numOfVertices + 1; from++) {
            for (int to = 1; to < numOfVertices + 1; to++) {
                if (!(adjacencyMatrix[from][to].equals("false"))) {
                    printWriter.println();
                    if (weighted == true) {
                        printWriter.print(adjacencyMatrix[from][0] + " ");
                        printWriter.print(adjacencyMatrix[0][to] + " ");
                        printWriter.print(adjacencyMatrix[from][to] + " ");
                    } else if (weighted == false) {
                        printWriter.print(adjacencyMatrix[from][0] + " ");
                        printWriter.print(adjacencyMatrix[0][to]);
                    }
                }
            }
        }
        printWriter.println();
        printWriter.println("end");
        printWriter.close();
    }

    public static int getVertexIndex(String vertex) {
        int index = 0;
        for (int i = 1; i < numOfVertices + 1; i++) {
            if (vertex.equals(adjacencyMatrix[0][i])) {
                index = i;
            }
        }
        return index;
    }

    public static void printAdjacencyMatrix() {
        System.err.print("==========================");
        for (int from = 0; from < numOfVertices; from++) {
            System.err.println();
            for (int to = 0; to < numOfVertices; to++) {
                System.err.print(adjacencyMatrix[from][to] + " ");
            }
        }
        System.err.println();
        System.err.println("==========================");

    }

    public static void checkForConnection(String vertex) {
        String[] toDoList = new String[numOfVertices];
        String[] visitedList = new String[numOfVertices];
        int toDoIndex = 0;
        int visitedIndex = 0;
        for (int i = 1; i < numOfVertices + 1; i++) {
            if (!adjacencyMatrix[1][i].equals("false")) {
                toDoList[toDoIndex] = adjacencyMatrix[fromIndex][i];
                toDoIndex++;
            }
        }
    }
}