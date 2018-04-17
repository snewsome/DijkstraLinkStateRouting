// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph
import java.util.*;
import java.lang.*;
import java.io.*;
 
public class linkstate
{
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    int V=6;
    int minDistance(int dist[], Boolean sptSet[], int V)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] < min)
            {
                min = dist[v];
				min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed distance array
    void printSolution(int dist[],int updateSet[],String nprime, int V, int step)
    {
        System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.print(step);
		System.out.print("\t");
		System.out.printf("%1$"+V*3+"s",nprime);
		System.out.print("\t");
        for (int i = 1; i < V; i++){
			if(dist[i]!= 2147483647)
				System.out.print(dist[i] + "," + updateSet[i] + "\t\t");
			else
				System.out.print("N" + "," + "x" + "\t\t");
		}
		System.out.println();
    }
 
    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    void dijkstra(int graph[][], int src, int V)
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];
		int updateSet[] = new int[V];
		String nprime = "";
		
		for(int i = 0; i < V; i++){
			updateSet[i] = 1;
		}
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet, V);
 
            // Mark the picked vertex as processed
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++){
 
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v]!=0 && dist[u] != Integer.MAX_VALUE && dist[u]+graph[u][v] < dist[v]){
					dist[v] = dist[u] + graph[u][v];
					updateSet[v] = u + 1;
					//System.out.println("value: " + v);
				}
			}
			nprime = nprime+ (u + 1) + ",";
			printSolution(dist,updateSet,nprime, V, count);
        }
 
        // print the constructed distance array
        
    }
 
    // Driver method
    public static void main (String[] args)
    {
		String fileName = args[0];
		try (FileReader fileReader = 
		new FileReader(fileName);){
		// FileReader reads text files in the default encoding.
		

		// Always wrap FileReader in BufferedReader.
		BufferedReader bufferedReader = 
		new BufferedReader(fileReader);
		
		String line = bufferedReader.readLine();
		String lineArray[] = line.split(",");
		//System.out.println("Length: " + lineArray.length);
		
		Boolean first = true;
		int i = 0;
		int j = 0;
		int V = lineArray.length;
		int graph[][] = new int[lineArray.length][lineArray.length];
		
		while(line != null){
			int tempArray[] = new int[lineArray.length];
			while(i < lineArray.length) {
				if(lineArray[i].charAt(lineArray[i].length()-1) == ('.')){
					lineArray[i] = lineArray[i].replace(".",""); 
					if(lineArray[i].equals("N")){
						lineArray[i]= lineArray[i].replace("N","0"); 
					}
				}
				
				if(lineArray[i].equals("N")){
					lineArray[i]= lineArray[i].replace("N","0"); 
				}
				tempArray[i] = Integer.parseInt(lineArray[i]);
				//System.out.print(tempArray[i] + ",");
				i++;
			}
			//System.out.println(".");
			graph[j] = tempArray;
			j++;
			//reset while loop
			i = 0;
			
			line = bufferedReader.readLine();
			if(line.equals("EOF.")){
				//System.out.println("end of file");
				break;
			}
			lineArray = line.split(",");
			//System.out.print("new line read: " + line);
		}
		
		System.out.print("Step");
		System.out.print("\t");
		System.out.printf("%1$"+V*3+"s","N\'");
		System.out.print("\t");
        for (int x = 1; x < V; x++){
			System.out.print("D(" + (x+1) +"),p(" + (x+1) + ")\t");
		}
		System.out.println();
		
        linkstate t = new linkstate();
        t.dijkstra(graph, 0, V);
		
		bufferedReader.close();
    }
	
	catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                fileName);
            System.exit(1);
        } 
	}
}
