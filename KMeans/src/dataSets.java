
public class dataSets {
	
	double sepalLength, sepalWidth, petalLength, petalWidth;
	int cluster;
	
	public dataSets(double sL, double sW, double pL, double pW){
		this.sepalLength = sL;
		this.sepalWidth = sW;
		this.petalLength = pL;
		this.petalWidth = pW;
		return;
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
	
	public void Cluster(int nClus){
		this.cluster = nClus;
			
	}
	
	int getCluster(){
		return cluster;
	}


}
