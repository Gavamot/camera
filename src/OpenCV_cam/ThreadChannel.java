package OpenCV_cam;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class ThreadChannel extends Thread{
    
    private static class ColorCollection{
        private static int i = 0;
        private static int[][] colors = new int[][] { 
            { 12, 145, 144 },
            { 179, 29, 39 },
            { 41, 28, 220 },
            { 222, 15, 114 },
            { 16, 177, 188 },
            { 19, 138, 22 },
            { 191, 185, 36 },
            { 16, 16, 100 },
            { 41, 218, 220 },
            { 12, 14, 144 },
            { 79, 29, 39 },
            { 41, 28, 22 },
            { 228, 215, 14 },
            { 19, 229, 39 },
            { 41, 28, 220 },
        }; 
        
        public static Color getColor(){
            if(i >= colors.length) i = 0; 
            int[] rgb = colors[i++];
            Color res = new Color(rgb[0],rgb[1],rgb[2]);
            return res;
        }
    }
    
    public int id;
    
    TimeSeries series = new TimeSeries("Random Data", Millisecond.class);
    TimeSeriesCollection dataset = new TimeSeriesCollection(series);
    
   // Logger logger = LogManager.getLogger(getClass().getName());
    
    public JFreeChart chart;
    public ChartPanel chartPanel;
    public JPanel panel;
    public Channel obj;  
    
    public ThreadChannel(Channel obj) {
       super("Channel " + obj.name);
       this.obj = obj;
       
        chart = ChartFactory.createTimeSeriesChart
        (
            obj.name, // Заголовок
            "", //подпись оси-x 
            obj.axis_label, //подпись оси-y
            dataset, 
            false, //легенда
            true, 
            false
        );
        
        chartPanel = new ChartPanel(chart);
        XYPlot plot = chart.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        
        this.id = obj.id;
        axis.setAutoRange(true);
        axis.setFixedAutoRange(30000.0);  // 30 seconds

        axis = plot.getRangeAxis();
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(3.5f));
        plot.getRenderer().setSeriesPaint(0, ColorCollection.getColor());

        axis.setAutoRange(true);
        axis.setRange(obj.minValue, obj.maxValue);
    }
    
    @Override
    public String toString(){
        return "Канала ID=" + id; 
    }
    
    @Override
    public void run()
    {
        
        //logger.info(getName() + " начался");
        while (!this.isInterrupted()) {
            if(panel == null) return;
            this.panel.add(chartPanel);
            Double resD = Manager.getChannelWriter().getValue(obj.nn);    
            series.add(new Millisecond(), resD); //добавление точки на графике
            try{ this.sleep(1000);}catch(Exception e){}
        }
        //logger.info(getName() + " завершен");
    }

}
