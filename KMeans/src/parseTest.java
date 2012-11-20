import java.io.FileNotFoundException;
import java.awt.Color;



import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;




	
	

public class parseTest{
	static int k = parseText.k; 
	static int totalData = parseText.totalData;

   
	    
	public static void main(String... aArgs) throws FileNotFoundException {
		
	    parseText parser = new parseText("iris.data");
	    parser.processLines();
	    log("Done importing iris.data");
	    parser.init();
	    parser.Clustering();
	    
	    plotclust demo = new plotclust("Iris Clustering");
	    demo.pack();
	    RefineryUtilities.centerFrameOnScreen(demo);
	    demo.setVisible(true);
	   
	   

	    
	    for(int i = 0; i < k; i++)
	    {
	        System.out.println("Cluster " + (i+1) + " includes:");
	        for(int j = 0; j < totalData; j++)
	        {
	            if(parser.dataSet.get(j).getCluster() == i){
	                System.out.println("     (" + parser.dataSet.get(j).getsL() + ", " + parser.dataSet.get(j).getsW() + 
	                		", " + parser.dataSet.get(j).getpL()+ ", " + parser.dataSet.get(j).getpW()+ ")");
	            }
	        } 
	        System.out.println();
	    } 
	  
	
	    System.out.println("Centroids finalized at:");
	    	for(int i = 0; i < k; i++){
	    		System.out.println("     (" + parseText.centroidSet.get(i).getsL() + ", " + parseText.centroidSet.get(i).getsW() +
	    				", " + parseText.centroidSet.get(i).getpL() + ", " + parseText.centroidSet.get(i).getpW() + ")");
    }
	    	System.out.print("\n");
	    	return;
}
	
	 public static XYDataset createDataset() {
		 
		 XYSeries s4 = new XYSeries("Cluster 1 Centroid");
	      
	     s4.add(parseText.centroidSet.get(0).getsL(),parseText.centroidSet.get(0).getpL());
	     
	     XYSeries s5 = new XYSeries("Cluster 2 Centroid");
	     
	     s5.add(parseText.centroidSet.get(1).getsL(),parseText.centroidSet.get(1).getpL());
	     
	     XYSeries s6 = new XYSeries("Cluster 3 Centroid");
	     s6.add(parseText.centroidSet.get(2).getsL(),parseText.centroidSet.get(2).getpL());

	      XYSeries s1 = new XYSeries("Cluster 1");
	      
		    
		       
		        for(int j = 0; j < totalData; j++)
		        {
		            if(parseText.dataSet.get(j).getCluster() == 0){
		            	
		            
	        s1.add(parseText.dataSet.get(j).getsL(),parseText.dataSet.get(j).getpL());
		        }
		        }
		    
	     XYSeries s2 = new XYSeries("Cluster 2");
	     for(int j = 0; j < totalData; j++)
	        {
	            if(parseText.dataSet.get(j).getCluster() == 1){
	            	
	            
     s2.add(parseText.dataSet.get(j).getsL(),parseText.dataSet.get(j).getpL());
	        }
	        }
	     
	     XYSeries s3 = new XYSeries("Cluster 3");
	     for(int j = 0; j < totalData; j++)
	        {
	            if(parseText.dataSet.get(j).getCluster() == 2){
	            	
	            
     s3.add(parseText.dataSet.get(j).getsL(),parseText.dataSet.get(j).getpL());
	        }
	        }
	     
	    
	     
	        XYSeriesCollection dataset = new XYSeriesCollection();
	        dataset.addSeries(s4);
	        dataset.addSeries(s5);
	        dataset.addSeries(s6);
	        dataset.addSeries(s1);
	        dataset.addSeries(s2);
	        dataset.addSeries(s3);
	       
	        
	        return dataset;

	    }

		    
	
	private static void log(Object aObject){
	    System.out.println(String.valueOf(aObject));
	  }

	
public static class plotclust extends ApplicationFrame{
	private static final long serialVersionUID = 1L;

    {
        
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow",
                true));
    }

 
    public plotclust(String title) {
        super(title);
        ChartPanel chartPanel = (ChartPanel) createDemoPanel();
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

 
    public JFreeChart createChart(XYDataset dataset) {

      
    	JFreeChart chart = ChartFactory.createScatterPlot("Iris Data Clustering",
    			"Sepal Length", "Petal Length", 
    			dataset,
    			PlotOrientation.VERTICAL,
    			true,
    			true,
    			false);
    	
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        return chart;

    }

   
   
    public JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        return panel;
    }
    
   
    
}

}