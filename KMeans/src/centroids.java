
public class centroids {

	double sepalLength, sepalWidth, petalLength, petalWidth;
	
	
	public centroids(double nsL, double nsW, double npL, double npW){
		sepalLength = nsL;
		sepalWidth = nsW;
		petalLength = npL;
		petalWidth = npW;
	}
	
	public void getsL(double nX){
		sepalLength = nX;
		return;
	}
	
	double getsL(){
		return sepalLength;
	}
	

	public void getsW(double nY){
		sepalWidth = nY;
		return;
	}
	
	double getsW(){
		return sepalWidth;
	}
	
	public void getpL(double nZ){
		petalLength = nZ;
		return;
	}
	double getpL(){
		return petalLength;
	}
	
	public void getpW(double nG){
		petalWidth = nG;
		return;	
	}
	
	double getpW(){
		return petalWidth;
	}
	
}