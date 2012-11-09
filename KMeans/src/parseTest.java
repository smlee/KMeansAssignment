import java.io.FileNotFoundException;
import java.util.Scanner;

public class parseTest {

	public static void main(String... aArgs) throws FileNotFoundException {
	    parseText parser = new parseText("iris.data");
	    parser.processLines();
	    log("Done.");
	  }
	
	private static void log(Object aObject){
	    System.out.println(String.valueOf(aObject));
	  }
}
