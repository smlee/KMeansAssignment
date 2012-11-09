import java.io.*;
import java.util.Scanner;

public class parseText {
	static int counter = 1;
	/*public static void main(String... aArgs) throws FileNotFoundException {
	    parseText parser = new parseText("beamout.txt");
	    parser.processLines();
	    print("Done.");
	  }
	*/
	  public parseText(String aFileName){
	    fFile = new File(aFileName);  
	  }
	  
	  /** Template method that calls {@link #processLine(String)}.  */
	  public final void processLines() throws FileNotFoundException {
	    //Note that FileReader is used, not File, since File is not Closeable
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
	    if ( scanner.hasNext() ){
	      String level = scanner.next();
	      String axis = scanner.next();
	      String endpoint1 = scanner.next();
	      String endpoint2 = scanner.next();
	      String beamDepth = scanner.next();
	      print("Level is " + quote(level.trim()) + ", Axis is " + quote(axis.trim())+", Endpoint 1 is "+endpoint1+", Endpoint 2 is "+endpoint2+", and Depth is "+beamDepth );
	      print(counter);
	      counter++;
	    }
	    else {
	      print("Empty or invalid line. Unable to process.");
	    }
	  
	  }
	  
	  private final File fFile;
	  
	  private static void print(Object aObject){
	    System.out.println(String.valueOf(aObject));
	  }
	  
	  private String quote(String aText){
	    String QUOTE = "'";
	    return QUOTE + aText + QUOTE;
	  }
}
