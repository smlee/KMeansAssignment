import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class parseText {
	
	static ArrayList<dataSets> dataSet = new ArrayList<dataSets>();
    static ArrayList<centroids>centroidSet = new ArrayList<centroids>();
    static ArrayList<dataSets> dataSet1;
    public static final int k = 3;    // Total clusters.
    public static final int totalData = 150; 
    
	  public parseText(String aFileName){
	    fFile = new File(aFileName);  
	  }
	  
	  
	  public final void processLines() throws FileNotFoundException {
	    Scanner scanner = new Scanner(new FileReader(fFile));
	    try {
	      //first use a Scanner to get each line
	      while ( scanner.hasNextLine() ){
	        processLine( scanner.nextLine() );
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
	    dataSet1 = new ArrayList<dataSets>();
	    if ( scanner.hasNext() ){
	    	
	      double sL = scanner.nextDouble();
	      double sW = scanner.nextDouble();
	      double pL = scanner.nextDouble();
	      double pW = scanner.nextDouble();
	      String cN = scanner.next();
	      dataSet1.add(new dataSets(sL, sW, pL, pW));
	 
	      
	    }
	    else {
	      print("Empty or invalid line. Unable to process.");
	    }
	  } 
	    /**for (int x = 0; x<dataSet.size();x++){
	    	dataSets dS = (dataSets)dataSet.get(x);
		    print("Sepal length is " + dS.sepalLength + ", Sepal Width is " + dS.getsW()+", Petal length is "+dS.getpL()+", Petal width is "+dS.getpW()+", and Class is "+dS.className );
	    }
	  
	  }
	  **/
	  private final File fFile;
	  private boolean isStillMoving;
	  
	  private static void print(Object aObject){
	    System.out.println(String.valueOf(aObject));
	  }
	  
	  public static void init(){
		  //Initialize random centroids
		  System.out.println("Initial centroids:");
		  centroidSet.add(new centroids(1.0, 1.0, 1.0, 1.0)); // lowest set.
	      centroidSet.add(new centroids(4.0, 4.0, 4.0, 4.0));
	      centroidSet.add(new centroids(7.0, 7.0, 7.0, 7.0));
	      //System.out.println("Centre 1: " +centroidSet.get(0));
	     // System.out.println("Centre 2: " +centroidSet.get(1));
	     // System.out.println("Centre 3: " +centroidSet.get(2));
	      return;// highest set.
		  
		  
	  }
	  
	  public static void Clustering(){
		  final double bigNumber = Math.pow(10, 10);    // some big number that's sure to be larger than our data range.
	        double minimum = bigNumber;                   // The minimum value to beat. 
	        double distance = 0.0;                        // The current minimum value.
	        int index = 0;
	        int cluster = 0;
	        boolean isStillMoving = true;
	        dataSets newData = null;
	        
	        // Add in new data, one at a time, recalculating centroids with each new one. 
	        while(dataSet.size() < totalData)
	        {
	           // newData = (dataSets) dataSet.get(index);
	        	newData = new dataSets(dataSet1.get(index).getsL(), dataSet1.get(index).getsW(),
	        			dataSet1.get(index).getpL(), dataSet1.get(index).getpW());
	        	dataSet.add(newData);
	            minimum = bigNumber;
	            for(int i = 0; i < k; i++)
	            {
	                distance = eDist (newData, centroidSet.get(i));
	                if(distance < minimum){
	                    minimum = distance;
	                    cluster = i;
	                }
	            }
	            newData.Cluster(cluster);
	  
	        for(int i = 0; i < k; i++)
            {
                int totalX = 0; // sepal Length
                int totalY = 0; // sepal Width
                int totalZ = 0; // petal Length
                int totalG = 0; // petal Width
                int clusterMembers = 0;
                
                for(int j = 0; j < dataSet.size(); j++)
                {
                    if(dataSet.get(j).getCluster() == i){
                        totalX += dataSet.get(j).getsL();
                        totalY += dataSet.get(j).getsW();
                        totalZ += dataSet.get(j).getpL();
                        totalG += dataSet.get(j).getpW();
                        clusterMembers++;
                    }
                }
                if(clusterMembers > 0){
                    centroidSet.get(i).getsL(totalX / clusterMembers);
                    centroidSet.get(i).getsW(totalY / clusterMembers);
                    centroidSet.get(i).getpL(totalZ / clusterMembers);
                    centroidSet.get(i).getpW(totalG / clusterMembers);
                }
            }
            index++;
	  }
	  
	  while(isStillMoving){
          // calculate new centroids.
          for(int i = 0; i < k; i++)
          {
              int totalX = 0;
              int totalY = 0;
              int totalZ = 0;
              int totalG = 0;
              int clusterMembers = 0;
              for(int j = 0; j < dataSet.size(); j++)
              {
                  if(dataSet.get(j).getCluster() == i){
                      totalX += dataSet.get(j).getsL();
                      totalY += dataSet.get(j).getsW();
                      totalZ += dataSet.get(j).getpL();
                      totalG += dataSet.get(j).getpW();
                      clusterMembers++;
                  }
              }
              if(clusterMembers > 0){
                  centroidSet.get(i).getsL(totalX / clusterMembers);
                  centroidSet.get(i).getsW(totalY / clusterMembers);
                  centroidSet.get(i).getpL(totalZ / clusterMembers);
                  centroidSet.get(i).getpW(totalG / clusterMembers);
              }
          }
          
         
          isStillMoving = false;
          
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
              tempData.Cluster(cluster);
              if(tempData.getCluster() != cluster){
                  tempData.Cluster(cluster);
                  isStillMoving = true;
              }
          }
      }
      return;
  }

	  
	  /**
	   * 
	   * @param d
	   * @param c
	   * @return
	   */
	 
	    private static double eDist(dataSets d, centroids c)
	    {
	        return Math.sqrt(Math.pow(c.getsL()- d.getsL(), 2) + Math.pow((c.getsW() - d.getsW()), 2)
	        		+ Math.pow(c.getpL()-d.getpL(), 2) + Math.pow(c.getpW()-d.getpW(), 2));
	    }
	  }

