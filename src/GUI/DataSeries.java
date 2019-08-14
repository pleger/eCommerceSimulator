package GUI;

import java.util.ArrayList;

public class DataSeries {
    private String seriesName;
    private ArrayList<Integer> xData;
    private ArrayList<Integer> yData;
    public DataSeries(String seriesName,ArrayList<Integer> xData,ArrayList<Integer> yData){
        this.seriesName=seriesName;
        this.xData=xData;
        this.yData=yData;
    }
    public void addData(int x,int y){
        xData.add(x);
        yData.add(y);
        if(xData.get(0)==-1 && yData.get(0)==-1){
            xData.remove(0);
            yData.remove(0);
        }
        /*
        if(xData.size()>20){
            xData.remove(0);
            yData.remove(0);
        }
         */
    }
    public ArrayList<Integer> getXData(){
        return xData;
    }
    public ArrayList<Integer> getYData(){
        return yData;
    }
    public String getSeriesName(){
        return seriesName;
    }
}
