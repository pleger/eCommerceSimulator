package GUI;

import java.util.ArrayList;

public class DataChart {
    private final String seriesName;
    private final ArrayList<Integer> xData;
    private final ArrayList<Integer> yData;

    public DataChart(String seriesName) {
        this.seriesName = seriesName;
        xData = new ArrayList<>();
        yData = new ArrayList<>();
    }

    public void addData(int x, int y) {
        xData.add(x);
        yData.add(y);
        if (xData.get(0) == -1 && yData.get(0) == -1) {
            xData.remove(0);
            yData.remove(0);
        }
    }

    public ArrayList<Integer> getXData() {
        return xData;
    }

    public ArrayList<Integer> getYData() {
        return yData;
    }

    public String getName() {
        return seriesName;
    }
}
