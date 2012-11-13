import java.io.FileNotFoundException;
import java.util.Scanner;

public class parseTest {

	static int k = parseText.k; 
	static int totalData = parseText.totalData;
	
	public static void main(String... aArgs) throws FileNotFoundException {
	    parseText parser = new parseText("iris.data");
	    parser.processLines();
	    log("Done.");
	    System.out.println(parser.dataSet1.get(0).getpL());
	    parser.init();
	    parser.Clustering();
	    
	    for(int i = 0; i < k; i++)
	    {
	        System.out.println("Cluster " + i + " includes:");
	        for(int j = 0; j < totalData; j++)
	        {
	            if(parser.dataSet.get(j).getCluster() == i){
	                System.out.println("     (" + parser.dataSet.get(j).getsL() + ", " + parser.dataSet.get(j).getsW() + 
	                		", " + parser.dataSet.get(j).getpL()+ ", " + parser.dataSet.get(j).getpW()+ ")");
	            }
	        } // j
	        System.out.println();
	    } // i
	  
	
	    System.out.println("Centroids finalized at:");
	    	for(int i = 0; i < k; i++){
	    		System.out.println("     (" + parseText.centroidSet.get(i).getsL() + ", " + parseText.centroidSet.get(i).getsW() +
	    				", " + parseText.centroidSet.get(i).getpL() + ", " + parseText.centroidSet.get(i).getpW() + ")");
    }
	    	System.out.print("\n");
	    	return;
}
	
	private static void log(Object aObject){
	    System.out.println(String.valueOf(aObject));
	  }

   
    
}