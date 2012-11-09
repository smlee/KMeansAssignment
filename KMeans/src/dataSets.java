
public class dataSets {
	
	double sepalLength, sepalWidth, petalLength, petalWidth;
	String className;
	
	public dataSets(double sL, double sW, double pL, double pW, String cN){
		sepalLength = sL;
		sepalWidth = sW;
		petalLength = pL;
		petalWidth = pW;
		className = cN;
	}
	
	double getsL(){
		return sepalLength;
	}
	
	double getsW(){
		return sepalWidth;
	}
	
	double getpL(){
		return petalLength;
	}
	
	double getpW(){
		return petalWidth;
	}
	
	String getcN(){
		return className;
	}

}
