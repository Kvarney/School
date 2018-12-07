package graphspackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AdjacencyMatrix{
    private static boolean weighted=false;
    private static boolean directed=false;
    private static int numOfVertices=0;
    public static String[][] adjacencyMatrix=new String[2][2];
    private static int toIndex=0;
    private static int fromIndex=0;
    private static int numberOfEdges=0;

    public AdjacencyMatrix(final String fileName)throws IOException{
        readGraph(fileName);
    }
    public static void addVertex(String vertex){
    	numOfVertices++;
        String[][] temp=new String[numOfVertices+1][numOfVertices+1];
        //Copying existing adjacency matrix into a new larger adjacency matrix
        for(int from=0;from<numOfVertices;from++){
            for(int to=0;to<numOfVertices;to++){
                temp[from][to]=adjacencyMatrix[from][to];
            }
        }
        //Adding new vertex to the adjacency matrix
        temp[numOfVertices][0]=vertex;
        temp[0][numOfVertices]=vertex;
        //Initializing all edges of new vertex to false
        for(int from=1;from<=numOfVertices;from++){
            temp[from][numOfVertices]="F";
        }
        for(int to=1;to<=numOfVertices;to++){
            temp[numOfVertices][to]="F";
        }
        adjacencyMatrix=temp;
    }
    public static void deleteVertex(String vertex){
        String[][] temp=new String[numOfVertices][numOfVertices];
        int index=getVertexIndex(vertex);
        //First Quad
        for(int from=0;from<index;from++){
            for(int to=0;to<index;to++){
                temp[from][to]=adjacencyMatrix[from][to];
            }
        }
        //Second Quad
        for(int from=0;from<index;from++){
            for(int to=index+1;to<numOfVertices+1;to++){
                temp[from][to - 1] = adjacencyMatrix[from][to];
            }
        }
        //Third Quad
        for(int from=index+1;from<numOfVertices+1;from++){
            for(int to=0;to<index;to++){
                temp[from-1][to]=adjacencyMatrix[from][to];
            }
        }
        //Fourth Quad
        for(int from=index+1;from<numOfVertices+1;from++){
            for(int to=index+1;to<numOfVertices+1;to++){
                temp[from-1][to-1]=adjacencyMatrix[from][to];
            }
        }
        adjacencyMatrix=temp;
        numOfVertices--;
        printAdjacencyMatrix();
    }
    public static void addEdge() {
        if (directed) {
            adjacencyMatrix[fromIndex][toIndex]="T";
        }else{
            adjacencyMatrix[fromIndex][toIndex]="T";
            adjacencyMatrix[toIndex][fromIndex]="T";            
        }
        numberOfEdges++;
    }
    public static void addEdge(String weight){
        if (directed){
            adjacencyMatrix[fromIndex][toIndex]=weight;
        }else{
            adjacencyMatrix[fromIndex][toIndex]=weight;
            adjacencyMatrix[toIndex][fromIndex]=weight;            
        }
        numberOfEdges++;
    }
    public static void deleteEdge(){
        if(directed){
            adjacencyMatrix[fromIndex][toIndex]="F";
        }else{
            adjacencyMatrix[fromIndex][toIndex]="F";
            adjacencyMatrix[toIndex][fromIndex]="F";
        }
        numberOfEdges--;
    }
    public static boolean hasEdge(String from,String to) {
        fromIndex=getVertexIndex(from);
        toIndex=getVertexIndex(to);
        return !(adjacencyMatrix[fromIndex][toIndex]).equals("F");
    }
    public static boolean isSparse(){
        int maxEdges=((numOfVertices*(numOfVertices-1))/2);
        return(numberOfEdges<=(maxEdges*0.15));
    }
    public static boolean isDense(){
        int maxEdges=((numOfVertices*(numOfVertices-1))/2);
        return(numberOfEdges>=(maxEdges*0.85));
    }
    public static boolean isConnected() {
        String[] connected=new String[numOfVertices];
        int visited=0;
        int willVisit=1;
        int connectedIndex=0;
        int fromVertex=0;
        String toVertex="n";
        connected[visited]=adjacencyMatrix[0][1];
        while(willVisit!=0){
            willVisit--;
            fromVertex=getVertexIndex(connected[visited]);
            for(int j=1;j<=numOfVertices;j++){
                if(!(adjacencyMatrix[j][fromVertex].equals("F"))){
                    toVertex=adjacencyMatrix[j][0];
                    if(!contains(connected,toVertex,connectedIndex)){
                        connected[connectedIndex+1]=toVertex;
                        connectedIndex++;
                        willVisit++;
                    }
                }
            }
            visited++;
        }
        return (visited==numOfVertices);
    }
    public static boolean isFullyConnected(){
    	int maxEdges=((numOfVertices*(numOfVertices-1))/2);
    	return(numberOfEdges==maxEdges);
    }
    public static int countVertices(){
        return numOfVertices;
    }
    public static int countEdges(){
        return numberOfEdges;
    }
    public static void readGraph(String fileName) throws FileNotFoundException, IOException {
        FileReader fr=new FileReader("./Tests/"+fileName);
        BufferedReader br=new BufferedReader(fr);
        String[] currentLine;
        String line;
        //Assumptions that we will not run into any empty lines during this portion of code
        //Get first line of the file
        line=br.readLine();
        System.err.println(line);
        currentLine=line.split(" ");
        //Cycle through comment lines
        while(currentLine[0].equals("*")){
        	line=br.readLine();
            System.err.println(line);
        	currentLine=line.split(" ");
        }
        //Get weighted
        System.err.println(line);
        if(line.equals("weighted")){
        	weighted=true;
        }
        //Get directed
        line=br.readLine();
        System.err.println(line);
        currentLine=line.split(" ");
        if(line.equals("directed")){
        	directed=true;
        }
        //An extra read to get pass the begin keyword in test file
        line=br.readLine();
        System.err.println(line);
        //Build graph
        line=br.readLine();
        System.err.println(line);
        currentLine=line.split(" ");
        //Adding the initial vertices to adjacency matrix
        for(String vertex:currentLine){
        	addVertex(vertex);
        }
        printAdjacencyMatrix();
        //Adding the initial edges to the adjacency matrix
        while(!(line=br.readLine()).equals("end")){
            currentLine=line.split(" ");
            System.err.println(line);
        	fromIndex=getVertexIndex(currentLine[0]);
        	toIndex=getVertexIndex(currentLine[1]);
        	if(weighted){
        		addEdge(currentLine[2]);
        	}else{
        		addEdge();
        	}
        }
        System.err.println(line);
        printAdjacencyMatrix();
        //Starts running commands given in text file
        while((line=br.readLine())!=null){
            System.err.println(line);
        	currentLine=line.split(" ");
        	if(currentLine[0].equals("addVertex")){
    			addVertex(currentLine[1]);
                printAdjacencyMatrix();
    		}
    		if(currentLine[0].equals("deleteVertex")){
    			deleteVertex(currentLine[1]);
    		}
            if(currentLine[0].equals("addEdge")){
            	fromIndex=getVertexIndex(currentLine[1]);
            	toIndex=getVertexIndex(currentLine[2]);
            	if(weighted){
            		addEdge(currentLine[3]);
            	}else{
            		addEdge();
            	}
            }
            if(currentLine[0].equals("deleteEdge")){
            	fromIndex=getVertexIndex(currentLine[1]);
            	toIndex=getVertexIndex(currentLine[2]);
            	deleteEdge();
            }
            if(currentLine[0].equals("hasEdge")){
            	if((hasEdge(currentLine[1],currentLine[2]))){
            		System.err.println("True");
            	}else{
            		System.err.println("False");
            	}
            }
            if(currentLine[0].equals("isSparse")){
            	if(isSparse()){
            		System.err.println("The graph is sparse");
            	}else{
            		System.err.println("The graph is not sparse");
            	}
            }
            if(currentLine[0].equals("isDense")){
            	if(isDense()){
            		System.err.println("The graph is dense");
            	}else{
            		System.err.println("The graph is not dense");
            	}
            }
            if(currentLine[0].equals("isConnected")){
            	if(isConnected()){
            		System.err.println("The graph is connected");
            	}else{
            		System.err.println("The graph is not connected");
            	}
            }
            if(currentLine[0].equals("isFullyConnected")){
            	if(isFullyConnected()){
            		System.err.println("The graph is fully connected");
            	}else{
            		System.err.println("The graph is not fully connected");
            	}
            }
            if(currentLine[0].equals("countVertices")){
            	System.err.println("There are: "+countVertices()+" Vertices");
            }
            if(currentLine[0].equals("countEdges")){
            	System.err.println("There are: "+countEdges()+" Edges");
            }
        }
        printGraph(fileName);
    }
    public static void printGraph(String fileName)throws IOException{
        String OUTPUTFILE=fileName.replace(".txt",".log");
        FileWriter fileWriter=new FileWriter(OUTPUTFILE);
        PrintWriter printWriter=new PrintWriter(fileWriter);
        //Prints if graph is weighted
        if(weighted){
            printWriter.println("weighted");
        }else{
            printWriter.println("unweighted");
        }
        //Prints if graph is directed
        if(directed){
            printWriter.println("directed");
        }else{
        	printWriter.println("undirected");
        }
        printWriter.println("begin");
        //Prints out the vertices
        for(int i=1;i<numOfVertices+1;i++){
            printWriter.print(adjacencyMatrix[0][i]+" ");
        }
        //Prints out the edges and value if weighted
        for(int from=1;from<numOfVertices+1;from++){
            for(int to=1;to<numOfVertices+1;to++){
                if(!(adjacencyMatrix[from][to].equals("F"))){
                    printWriter.println();
                    if(weighted){
                        printWriter.print(adjacencyMatrix[from][0]+" ");
                        printWriter.print(adjacencyMatrix[0][to]+" ");
                        printWriter.print(adjacencyMatrix[from][to]+" ");
                    }else{
                        printWriter.print(adjacencyMatrix[from][0]+" ");
                        printWriter.print(adjacencyMatrix[0][to]);
                    }
                }
            }
        }
        printWriter.println();
        printWriter.println("end");
        printWriter.close();
    }
    //Some helper methods
    public static int getVertexIndex(String vertex){
        int index = 0;
        for(int i=1;i<numOfVertices+1;i++){
            if(vertex.equals(adjacencyMatrix[0][i])){
                index=i;
                return index;
            }
        }
        return index;
    }
    public static boolean contains(String[] str,String element,int index){
        boolean contains=false;
        for(int i=0;i<=index;i++){
            if(str[i]!=null&&str[i].equals(element)){
                return true;
            }
        }
        return contains;
    }
    public static void printAdjacencyMatrix(){
        System.err.print("==========================");
        for(int from=0;from<numOfVertices;from++){
            System.err.println();
            for(int to=0;to<numOfVertices;to++){
                System.err.print(adjacencyMatrix[from][to]+ " ");
            }
        }
        System.err.println();
        System.err.println("==========================");
    }
}