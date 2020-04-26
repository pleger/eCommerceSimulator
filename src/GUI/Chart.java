package GUI;

import Agent.Buyer;
import Agent.Market;
import InputManager.Configuration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chart {
    private static final Logger logger = LogManager.getRootLogger();

    private static XYChart chart;

    public static void display(List<Buyer> buyers, List<Market> markets) {
        createXChartDriver(markets);
        buyers.iterator().forEachRemaining(buyer -> registerSeries(buyer.getDataSeries()));
        drawChart();
        saveChart();
    }

    private static void createXChartDriver(List<Market> markets) {
        chart = new XYChartBuilder().width(800).height(600).title("Simulation")
                .xAxisTitle("Period").yAxisTitle("Market").build();

        chart.getStyler().setYAxisDecimalPattern("#0").setYAxisMax(markets.size() * 1.0).setLegendPosition(Styler.LegendPosition.InsideNE);
        Map<Double, Object> customYAxisTickLabelsMap = new HashMap<>();
        for (Market market : markets) {
            customYAxisTickLabelsMap.put(market.getID() * 1.0, market.getName());
        }
        chart.setYAxisLabelOverrideMap(customYAxisTickLabelsMap);
    }

    private static void registerSeries(DataChart dataChart) {
        chart.addSeries(dataChart.getName(), dataChart.getXData(), dataChart.getYData());
    }

    private static void drawChart() {
        (new SwingWrapper<>(chart)).displayChart();
    }

    private static void saveChart() {
        String fileName = Configuration.OUTPUT_FILE;
        DateFormat df = new SimpleDateFormat("dd-MM-yy(HH-mm-ss)");
        fileName += df.format(new Date()) + ".png";

        try {
            logger.trace("Chart: Saving chart");
            BitmapEncoder.saveBitmap(chart, "output/" + fileName, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException ex) {
            logger.error("Image cannot be saved: " + fileName);
            logger.error("ERROR: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
