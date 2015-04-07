package floyd;

// Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	// Hardcoded adjacency matrix is a 2-dimensional int array
	static int[][] graph;
	
	// Use BufferedReader to get user input
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	
	// Main method
	public static void main(String[] args) {
		
		// First, initialize and fill adjacency matrix with INF values
		graph = new int[5][5];
		
		int numInitialEdges = 0;
		
		// Fill graph with INF (Integer.MAX_VALUE) values
		for(int a = 0; a < 5; a++) {
			for(int b = 0; b < 5; b++) {
				graph[a][b] = Integer.MAX_VALUE;
			}
		}
		
		// Looping edges should have 0 weight
		for (int a = 0; a < 5; a++) {
			for (int b = 0; b < 5; b++) {
				if (a == b) 
					graph[a][b] = 0;
			}
		}
		
		// Variables for x and y axes in matrix
				int i = 0;
				int j = 0;
		
		// Fill graph with 8 random edge weights at random locations (no loop weights)
		while(numInitialEdges < 8) {
			
				// Randomly choose vertices
				i = (int) (Math.random() * 5);
				j = (int) (Math.random() * 5);
			
			// Ignore looping edges (should have a weight of 0, technically speaking)
			if (i != j) {
				// If the matrix index is still INF, fill with value
				if (graph[i][j] == Integer.MAX_VALUE) {
					graph[i][j] = (int) (Math.random() * 20) + 1;
					numInitialEdges++;
				}
			}
		} // End while loop
					
// 		   TEST VALUES	
//		graph[0][1] = 50;
//		graph[0][3] = 80;
//		graph[1][2] = 60;
//		graph[1][3] = 90;
//		graph[2][4] = 40;
//		graph[3][2] = 20;
//		graph[3][4] = 70;
//		graph[4][1] = 50;
		
		// Tell user important note
		System.out.println("NOTE: This program displays an");
		System.out.println("edge weight of 0 at all looping");
		System.out.println("edges, since there is no need to");
		System.out.println("travel to and from the same edge.");
		System.out.println("But don't worry, the Floyd's");
		System.out.println("Algorithm code does not consider");
		System.out.println("looping edges as it traverses the");
		System.out.println("adjacency matrix of the graph,");
		System.out.println("meaning all of the shortest paths");
		System.out.println("are still computed correctly.");
		System.out.println();
		
		// Display initial graph
		System.out.println("INITIAL ADJACENCY MATRIX:");
		System.out.println();
		displayGraph(graph);
		
		// Perform Floyd's algorithm on graph
		floydsAlgorithm(graph);
		
		// Display final graph
		System.out.println("AFTER FLOYD'S ALGORITHM...\n");
		System.out.println("FINAL ADJACENCY MATRIX:");
		System.out.println();
		displayGraph(graph);
		
		// Exit with good manners
		System.out.println();
		System.out.println("Goodbye...");
		
	} // End of main method
	
	
	// Display graph
	public static void displayGraph(int[][] graph) {
		// Display graph (four leading spaces, three inbetween each letter)
				System.out.println("    A   B   C   D   E");
				System.out.println("  =====================");
				
				int charCode = 0;
				// Print graph edge weights, --- if INF
				for (int a = 0; a < 5; a++) {
					// Print row letter
					System.out.print((char) (charCode + 65) + " ");
					
					for (int b = 0; b < 5; b++) {
						System.out.print(" ");
						if (graph[a][b] == Integer.MAX_VALUE) {
							System.out.print("---");
						} else {
						// Use three spaces to print edge weight
						System.out.format("%3s", graph[a][b]);
						}
					}
					charCode++;
					System.out.println();
				} // End double for loop
				System.out.println();
	} // End displayGraph method
	
	
	// Perform Floyd's algorithm and display graph each time a shortest path length is updated
	public static void floydsAlgorithm(int[][] graph) {
		// Iterate through adjacency matrix
		for (int a = 0; a < 5; a++) {
			for (int b = 0; b < 5; b++) {
				// Ignore looping edges
				if (a != b) {
					// If there is a value
					if (graph[a][b] != Integer.MAX_VALUE) {
						// Run through values in column of row letter
						for (int c = 0; c < 5; c++) {
							// Ignore looping edges
							if (c != a) {
								// If there is a value in the column
								if (graph[c][a] != Integer.MAX_VALUE) {
									// Compute path length
									int tempSum = graph[a][b] + graph[c][a];
									// Ignore looping edges
									if (c != b) {
										// If path length is INF, update path length and print graph
										if (graph[c][b] == Integer.MAX_VALUE) {
											
											graph[c][b] = tempSum;
											// Print graph after each update
											System.out.println("All-pairs shortest paths are:");
											displayGraph(graph);
											// Prompt user to continue
											System.out.println("Press enter to continue:");
											try {
												// If user hit enter
												if (input.read() == 13) {
													continue;
												}
											} catch (IOException e) {
												e.printStackTrace();
											}
											
										} else {
											// If path length is less than current, update path length and print graph
											if (tempSum < graph[c][b]) {
												
												graph[c][b] = tempSum;
												// Print graph after each update
												System.out.println("All-pairs shortest paths are:");
												displayGraph(graph);
												
												// Prompt user to continue
												System.out.println("Press enter to continue:");
												try {
													// If user hit enter
													if (input.read() == 13) {
														continue;
													}
												} catch (IOException e) {
													e.printStackTrace();
												}
											}
										} 
									}
								} 
							}
						}
					} 
				}
			}
		} // End double for loop
	} // End floydsAlgorithm method
	
	
} // End of Main class