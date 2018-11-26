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



                //     //adds initial edges to adjacency matrix set up before functions are called
                //     while (!(line = br.readLine()).equals("end")) {
                //         System.err.println(line);
                //         currentLine = line.split(" ");

                //         fromIndex = getVertexIndex(currentLine[0]);
                //         toIndex = getVertexIndex(currentLine[1]);

                //         if (weighted == true) {
                //             addEdge(currentLine[2]);
                //         } else {
                //             addEdge();
                //         }
                //     }

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

		public Node(String vertex, String edge, Node<T> next){
			vertex = vertex;
			edge = edge;
			weight = null;
			if(vertex!="n/a"){
				nextVertex = next;
			}else{
				nextEdge = next;
			}
		}
		public T getVertex(){
			return vertex;
		}
		public T getEdge(){
			return edge;
		}
		public Node<T> getNextVertex(){
			return nextVertex;
		}
		public Node<T> getNextEdge(){
			return nextEdge;
		}
		public void setNextVertex(Node<T> next){
			nextVertex = next;
		}
		public void setNextEdge(Node<T> next){
			nextEdge = next;
		}
		public void setWeight(String weight){
			weight = weight;
		}
	}

	//List Implementation
	private Node<T> head = null;
	private Node<T> tail = null;
	private int size = 0;

	//adds vertex to the end of the list
	public void addVertexNode(String vertex){
		Node<T> vertexNode = new Node<>(vertex, "n/a", null);
		if(isEmpty()){
			head = vertexNode;
		}else{
			tail.setNextVertex(vertexNode);
		}
		tail=vertexNode;
		size++;

		System.out.println("Vertex "+vertex+" added to list.");
	}

	public void addEdgeNode(String vertex, String weight){
		Node<T> edgeNode = new Node<>("n/a", vertex, null);
		while(nextEdge!=null){
			getNextEdge(nextEdge);
			// Node<T> cur = new Node<>("n/a", this.edge, nextEdge);
			// Node<T> temp = new Node<>(nextEdge.getVertex,nextEdge.getNextVertex,nextEdge.getNextEdge)
		}
		nextEdge=edgeNode;
		weight=weight;		

	}
	public void addEdge(String fromVertex, String toVertex, String weight, boolean directed){
		if(directed == false){
			while(aL.getNextVertex!=null){
				if(aL.getVertex==fromVertex){
					aL.addEdgeNode(toVertex, weight);
					System.out.println("Edge from "+fromVertex+" to "+toVertex+" with weight of "+weight);
				}
				if(aL.getVertex==toVertex){
					aL.addEdgeNode(fromVertex, weight);
					System.out.println("Edge from "+toVertex+" to "+fromVertex+" with weight of "+weight);					
				}
			}
		}else{
			while(aL.getVertex!=null){
				if(aL.getNextVertex!=null){
					aL.addEdgeNode(toVertex, weight);
					System.out.println("Edge from "+fromVertex+" to "+toVertex+" with weight of "+weight);
				}
			}
		}
	}

	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size==0;
	}

	// public T first(){
	// 	if(isEmpty()){
	// 		return null;
	// 	}
	// 	return head.getElement();
	// }
	// public T last(){
	// 	if(isEmpty()){
	// 		return null;
	// 	}
	// 	return tail.getElement();
	// }
	// public void addFirst(T e){
	// 	head = new Node<>(e, head);
	// 	if(size == 0){
	// 		tail = head;
	// 	}
	// 	size++;
	// 	System.out.println("Added head node with "+head.getElement()+" element.");
	// }
	// public void addLast(T e){
	// 	Node<T> newNode = new Node<>(e, null);
	// 	if(isEmpty()){
	// 		head = newNode;
	// 	}else{
	// 		tail.setNext(newNode);
	// 	}
	// 	tail = newNode;
	// 	size++;
	// 	System.out.println("Added tail node with "+ tail.getElement()+" element.");
	// }
	// public T removeFirst(){
	// 	if(isEmpty()){
	// 		return null;
	// 	}
	// 	T answer = head.getElement();
	// 	head=head.getNext();
	// 	size--;
	// 	if(size==0){
	// 		tail=null;
	// 	}
	// 	System.out.println("Removed head node with "+answer+" element.");
	// 	return answer;
	// }
}
}