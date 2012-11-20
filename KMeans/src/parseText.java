import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class parseText {
	
	static ArrayList<dataSets> dataSet = new ArrayList<dataSets>();
    static ArrayList<centroids>centroidSet = new ArrayList<centroids>();
    static ArrayList<dataSets> dataSet1 = new ArrayList<dataSets>();
    private final File fFile;
    static boolean notDone;
    
    public static final int k = 3;    // number of clusters.
    public static final int totalData = 150; //number of iris data lines
    
	  public parseText(String aFileName){
	    fFile = new File(aFileName);  
	  }
	  
	  
	  public final void processLines() throws FileNotFoundException {
	    Scanner scanner = new Scanner(new FileReader(fFile));
	    try {
	      //first use a Scanner to get each line
	      while ( scanner.hasNextLine() ){
	        processLine(scanner.nextLine() );
	      }
	    }
	    finally {
	      scanner.close();
	    }
	  }
	  protected void processLine(String aLine){
	    //use a second Scanner to parse the content of each line 
	    Scanner scanner = new Scanner(aLine);
	    scanner.useDelimiter(",");
	    
	    if ( scanner.hasNext() ){
	      double sL = scanner.nextDouble();
	      double sW = scanner.nextDouble();
	      double pL = scanner.nextDouble();
	      double pW = scanner.nextDouble();
	      String cN = scanner.next(); //not interested in the name of the flower
	      
	      dataSet1.add(new dataSets(sL, sW, pL, pW));
	      
	    }
	    else {
	      print("Empty or invalid line. Unable to process.");
	    }
	  } 
	 
	  
	  private static void print(Object aObject){
	    System.out.println(String.valueOf(aObject));
	  }
	  
	  public static void init(){
		  //Initialize random centroids
		  System.out.println("Initial centroids:");
		  centroidSet.add(new centroids(4.9,3.1,1.5,0.1)); // lowest set.
	      centroidSet.add(new centroids(5.8,2.7,4.1,1.0));
	      centroidSet.add(new centroids(7.7,2.6,6.9,2.3));
	      for (int i = 0; i< k; i++){
	    	  System.out.println("("+centroidSet.get(i).getsL()+", "+centroidSet.get(i).getsW()+
	    			  ", "+centroidSet.get(i).getpL()+", "+centroidSet.get(i).getpW()+")" );
	      } 
	     // return;
		  
		  
	  }
	  
	  public static void Clustering(){
		  final double bigNumber = Math.pow(10, 10);    // some big number that's sure to be larger than our data range.
	        double minimum = bigNumber;                   // The minimum value to beat. 
	        double distance = 0.0;                        // The current minimum value.
	        int index = 0;
	        int cluster = 0;
	        boolean notDone = true;
	        dataSets testData = null;
	        
	        // Add in new data, one at a time, recalculating centroids with each new one. 
	        while(dataSet.size() < totalData)
	        {
	        	dataSets dS = (dataSets)dataSet1.get(index);
	        	testData = new dataSets(dS.getsL(), dS.getsW(),
	        			dS.getpL(), dS.getpW());
	        	dataSet.add(testData);
	            minimum = bigNumber;
	            for(int i = 0; i < k; i++)
	            {
	                distance = eDist (testData, centroidSet.get(i));
	                if(distance < minimum){
	                    minimum = distance;
	                    cluster = i;
	                }
	            }
	            testData.setCluster(cluster);
	  
	        for(int i = 0; i < k; i++)
            {
                double tsLength = 0; // sepal Length
                double tsWidth = 0; // sepal Width
                double tpLength = 0; // petal Length
                double tpWidth = 0; // petal Width
                int totalclusterMembers = 0;
                
                for(int j = 0; j < dataSet.size(); j++)
                {
                    if(dataSet.get(j).getCluster() == i){
                        tsLength += dataSet.get(j).getsL();
                        tsWidth += dataSet.get(j).getsW();
                        tpLength += dataSet.get(j).getpL();
                        tpWidth += dataSet.get(j).getpW();
                        totalclusterMembers++;
                    }
                }
                if(totalclusterMembers > 0){
                    centroidSet.get(i).getsL(tsLength / totalclusterMembers);
                    centroidSet.get(i).getsW(tsWidth / totalclusterMembers);
                    centroidSet.get(i).getpL(tpLength / totalclusterMembers);
                    centroidSet.get(i).getpW(tpWidth / totalclusterMembers);
                }
            }
            index++;
	  }
	  
	  while(notDone){
          // calculate new centroids.
          for(int i = 0; i < k; i++)
          {
              double tsLength = 0;
              double tsWidth = 0;
              double tpLength = 0;
              double tpWidth = 0;
              int totalclusterMembers = 0;
              for(int j = 0; j < dataSet.size(); j++)
              {
                  if(dataSet.get(j).getCluster() == i){
                      tsLength += dataSet.get(j).getsL();
                      tsWidth += dataSet.get(j).getsW();
                      tpLength+= dataSet.get(j).getpL();
                      tpWidth += dataSet.get(j).getpW();
                      totalclusterMembers++;
                  }
              }
              if(totalclusterMembers > 0){
                  centroidSet.get(i).getsL(tsLength / totalclusterMembers);
                  centroidSet.get(i).getsW(tsWidth / totalclusterMembers);
                  centroidSet.get(i).getpL(tpLength / totalclusterMembers);
                  centroidSet.get(i).getpW(tpWidth / totalclusterMembers);
              }
          }
          
         
          notDone = false;
          
          for(int i = 0; i < dataSet.size(); i++)
          {
              dataSets tempData = dataSet.get(i);
              minimum = bigNumber;
              for(int j = 0; j < k; j++)
              {
                  distance = eDist(tempData, centroidSet.get(j));
                  if(distance < minimum){
                      minimum = distance;
                      cluster = j;
                  }
              }
              tempData.setCluster(cluster);
              if(tempData.getCluster() != cluster){
                  tempData.setCluster(cluster);
                  notDone = true;
              }
          }
      }
  }


	    private static double eDist(dataSets d, centroids c)
	    {
	        return Math.sqrt(Math.pow(c.getsL()- d.getsL(), 2) + Math.pow((c.getsW() - d.getsW()), 2)
	        		+ Math.pow(c.getpL()-d.getpL(), 2) + Math.pow(c.getpW()-d.getpW(), 2));
	    }
	  }

