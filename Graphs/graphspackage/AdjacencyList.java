package graphspackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AdjacencyList{
	AList<String> aL = new AList<String>();
    private boolean weighted = false;
    private boolean directed = false;
    private int numOfVertices;
    private int numberOfEdges = 0;
    private String fromVertex = "n";
    private String toVertex = "n";

	public AdjacencyList(String fileName) throws IOException{
		readGraph(fileName);
	}

	public void hasEdge(){

	}
	public void addEdge(String weight){
		aL.addEdge(fromVertex, toVertex, weight, directed);
		numberOfEdges++;
	}
	public void addEdge(){
		aL.addEdge(fromVertex, toVertex, "n/a", directed);
		numberOfEdges++;
	}
	public void deleteEdge(){

	}
	public void addVertex(String vertex){
		aL.addVertexNode(vertex);

	}
	public void deleteVertex(){
		
	}
	public void isSparse(){
		
	}
	public void isDense(){
		
	}
	public void countVertices(){
		
	}
	public void countEdges(){
		
	}
	public void isConnected(){
		
	}
	public void isFullyConnected(){
		
	}
	public void printAdjacencyList(){
		aL.output();
    }
	public void readGraph(String fileName)throws FileNotFoundException, IOException{
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

                    //adding the vertices to adjacency list

                    for(String vertex:currentLine){
                    	addVertex(vertex);
                    }

                    //adds initial edges to adjacency list before functions are called
                    while(!(line = br.readLine()).equals("end")){
                    	System.err.println(line);
                    	currentLine = line.split(" ");
                    	fromVertex = currentLine[0];
                    	toVertex = currentLine[1];

                    	if(weighted == true){
                    		addEdge(currentLine[2]);
                    	}else{
                    		addEdge();
                    	}
                    }

                    printAdjacencyList();

                //     printAdjacencyMatrix();
                // }

                // //running functions starts here
                // if (line.equals("end")) {
                //     while ((line = br.readLine()) != null) {
                //         currentLine = line.split(" ");
                //         System.err.println(line);

                //         if (currentLine[0].equals("hasEdge")) {
                //             if ((hasEdge(currentLine[1], currentLine[2])) == true) {
                //                 System.err.println("True");
                //             } else if ((hasEdge(currentLine[1], currentLine[2])) == false) {
                //                 System.err.println("False");
                //             } else {
                //                 System.err.println("There was an issue with determining if there was an edge.");
                //             }
                //         }
                //         if (currentLine[0].equals("addEdge")) {
                //             fromIndex = getVertexIndex(currentLine[1]);
                //             toIndex = getVertexIndex(currentLine[2]);
                //             if (weighted == false) {
                //                 addEdge();
                //             } else if (weighted == true) {
                //                 addEdge(currentLine[3]);
                //             } else {
                //                 System.err.println("There was an error adding edge.");
                //             }
                //             printAdjacencyMatrix();
                //         }
                //         if (currentLine[0].equals("deleteEdge")) {
                //             fromIndex = getVertexIndex(currentLine[1]);
                //             toIndex = getVertexIndex(currentLine[2]);
                //             deleteEdge();
                //             System.err.println("The edge from " + currentLine[1]
                //                     + " to " + currentLine[2] + " has been deleted");
                //             printAdjacencyMatrix();
                //         }
                //         if (currentLine[0].equals("addVertex")) {
                //             addVertex(currentLine[1]);
                //         }
                //         if (currentLine[0].equals("deleteVertex")) {
                //             deleteVertex(currentLine[1]);
                //         }
                //         if (currentLine[0].equals("isSparse")) {
                //             if (isSparse() == true) {
                //                 System.err.println("The graph is sparse");
                //             } else if (isSparse() == false) {
                //                 System.err.println("The graph is not sparse");
                //             } else {
                //                 System.err.println("There was an error checking if the graph is sparse");
                //             }
                //         }
                //         if (currentLine[0].equals("isDense")) {
                //             if (isDense() == true) {
                //                 System.err.println("The graph is dense");
                //             } else if (isDense() == false) {
                //                 System.err.println("The graph is not dense");
                //             } else {
                //                 System.err.println("There was an error checking if the graph is dense");
                //             }
                //         }
                //         if (currentLine[0].equals("countVertices")) {
                //             System.err.println("There are: " + countVertices() + " Vertices");
                //         }
                //         if (currentLine[0].equals("countEdges")) {
                //             System.err.println("There are: " + countEdges() + " Edges");
                //         }
                //         if (currentLine[0].equals("isConnected")) {
                //             if (isConnected() == true) {
                //                 System.err.println("The graph is connected");
                //             } else if (isConnected() == false) {
                //                 System.err.println("The graph is not connected");
                //             } else {
                //                 System.err.println("There was an error with checking if the graph is connected");
                //             }
                //         }
                //         if (currentLine[0].equals("isFullyConnected")) {
                //             if (isFullyConnected() == true) {
                //                 System.err.println("The graph is fully connected");
                //             } else if (isFullyConnected() == false) {
                //                 System.err.println("The graph is not fully connected");
                //             } else {
                //                 System.err.println("There was an error with checking if the graph is fully connected");
                //             }
                //         }
                //         if (currentLine[0].equals("printGraph")) {
                //             printGraph(fileName);
                //         }
                //     }
                // }
            }
        }
    }
 //        printGraph(fileName);
	// }
	// public static void printGraph(){
		
	// }
}

class AList<T>{
	public class Node<T>{
		private T vertex;
		private T edge;
		private T weight;
		private Node<T> nextVertex;
		private Node<T> nextEdge;

		public Node(){

		}
		public T getVertex(){
			return vertex;
		}		
		public void setVertex(T vertex){
			this.vertex = vertex;
		}
		public T getEdge(){
			return edge;
		}
		public void setEdge(T edge){
			this.edge=edge;
		}
		public T getWeight(){
			return weight;
		}
		public void setWeight(T weight){
			this.weight = weight;
		}		
		public Node<T> getNextVertex(){
			return nextVertex;
		}
		public Node<T> getNextEdge(){
			return nextEdge;
		}
		public void setNextVertex(Node<T> next){
			this.nextVertex = next;
		}
		public void setNextEdge(Node<T> next){
			this.nextEdge = next;
		}
	}
	//List Implementation
	private Node<T> head = null;
	private Node<T> tail = null;
	//adds vertex to the end of the list
	public void addVertexNode(T vertex){
		//building new vertex node
		Node<T> newNode = new Node<>();
		newNode.setVertex(vertex);
		newNode.setEdge(null);
		newNode.setWeight(null);
		newNode.setNextVertex(null);
		newNode.setNextEdge(null);

		if(head == null){
			head = newNode;
		}else{
			tail.setNextVertex(newNode);
		}

		tail=newNode;
	}
	public void addEdge(T fromVertex, T toVertex, T weight, boolean directed){
		Node<T> newNode = new Node<>();
		newNode = head;

		if(directed == false){
			while(newNode != null){
				if(newNode.getVertex().equals(fromVertex)){
					addEdgeNode(newNode,toVertex,weight);
				}
				if(newNode.getVertex().equals(toVertex)){
					addEdgeNode(newNode,fromVertex,weight);				
				}
				newNode = newNode.getNextVertex();
			}
		}else{
			while(newNode != null){
				if(newNode.getVertex().equals(fromVertex)){
					addEdgeNode(newNode,toVertex,weight);
				}
				newNode = newNode.getNextVertex();
			}
		}
	}
	public void addEdgeNode(Node<T> currentNode, T vertex, T weight){
		//building new edge node
		Node<T> newNode = new Node<>();
		newNode.setVertex(null);
		newNode.setEdge(vertex);
		newNode.setWeight(weight);
		newNode.setNextVertex(null);
		newNode.setNextEdge(null);
		if(currentNode.getNextEdge() == null){
			currentNode.setNextEdge(newNode);
		}else{
			Node<T> temp = new Node<>();
			temp = currentNode.getNextEdge();
			newNode.setNextEdge(temp);
			currentNode.setNextEdge(newNode);
		}
	}
	public void output(){
		Node<T> currentVertex = new Node<>();
		Node<T> currentEdge = new Node<>();
		currentVertex = head;

		System.err.print("==========================");
		while(currentVertex != null){
			System.err.print("\n"+currentVertex.getVertex()+"|");
			if(currentVertex.getNextEdge() != null){
				currentEdge = currentVertex.getNextEdge();
				while(currentEdge != null){
					System.err.print("["+currentEdge.getEdge()+","+currentEdge.getWeight()+"] ");
					currentEdge = currentEdge.getNextEdge();
				}
			}
			currentVertex = currentVertex.nextVertex;
		} 
        System.err.println();
        System.err.println("==========================");
	}
}
}