package GUI;

import Agent.Buyer;
import Agent.Market;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chart {
    static XYChart chart;
    static ArrayList<DataChart> data;
    static SwingWrapper<XYChart> sw;

    public static void createXChartDriver(List<Buyer> buyers, List<Market> markets) {
        chart = new XYChartBuilder().width(800).height(600).title("Simulation")
                .xAxisTitle("Period").yAxisTitle("Market").build();

        chart.getStyler().setYAxisDecimalPattern("#0").setYAxisMax(markets.size() * 1.0).setLegendPosition(Styler.LegendPosition.InsideNE);
        Map<Double, Object> customYAxisTickLabelsMap = new HashMap<>();
        for (Market market : markets) {
            customYAxisTickLabelsMap.put(market.getID() * 1.0, market.getName());
        }
        chart.setYAxisLabelOverrideMap(customYAxisTickLabelsMap);
        data = new ArrayList<>(buyers.size());
        sw = new SwingWrapper<>(chart);
    }

    private static void registerSeries(DataChart dataChart) {
        chart.addSeries(dataChart.getName(), dataChart.getXData(), dataChart.getYData());
    }

    public static void display(List<Buyer> buyers, List<Market> markets) {
        createXChartDriver(buyers, markets);
        buyers.iterator().forEachRemaining(buyer -> registerSeries(buyer.getDataSeries()));
        drawChart();
    }

    public static void drawChart() {
        sw.displayChart();
    }
}
