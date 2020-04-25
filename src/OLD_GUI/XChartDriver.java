package OLD_GUI;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;

public class XChartDriver {
    static XYChart chart;
    static ArrayList<DataSeries> dataSeries;
    static SwingWrapper<XYChart> sw;

    public static void createXChartDriver(int dataSeriesSize) {
        chart = new XYChartBuilder().width(800).height(600).title("OLD_Simulation")
                .xAxisTitle("Iteration time").yAxisTitle("Market").build();
        chart.getStyler().setYAxisDecimalPattern("#0");
        dataSeries = new ArrayList<>(dataSeriesSize);
        sw = new SwingWrapper<XYChart>(chart);
    }

    public static void registerNewSeries(int buyerId, String seriesName, ArrayList<Integer> xData, ArrayList<Integer> yData) {
        DataSeries newDataSeries = new DataSeries(seriesName, xData, yData);
        dataSeries.add(buyerId, newDataSeries);
        chart.addSeries(seriesName, xData, yData);
    }

    public static void addSeriesData(int buyerId, int x, int y) {
        dataSeries.get(buyerId).addData(x, y);
    }

    public static void updateChart() {
        for (DataSeries series : dataSeries) {
            chart.updateXYSeries(series.getSeriesName(), series.getXData(), series.getYData(), null);
        }
        sw.repaintChart();
    }

    public static void drawChart() {
        sw.displayChart();
    }
}
