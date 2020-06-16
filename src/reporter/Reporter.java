package reporter;

import inputManager.Configuration;
import inputManager.Loader;
import logger.Console;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Reporter {
    private static final List<IterationData> iterationData = new ArrayList<>();
    private static final List<EndorsementData> endorsData = new ArrayList<>();
    private static final List<MarketEvaluationData> marketEvaluationData = new ArrayList<>();
    private static final List<SalesPerMarketData> salesPerMarketData = new ArrayList<>();
    private static final List<SalesUniquePerMarketData> salesUniquePerMarketData = new ArrayList<>();

    public static void write() {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Console.info("Reporter: Adding sheets");
        writeConfiguration(workbook.createSheet("Configuration"));
        addSheet(workbook, Loader.getMarkets());
        addSheet(workbook, Loader.getBuyers());
        writeResults(workbook.createSheet("Results"));
        writeSalesPerMarket(workbook.createSheet("SalesPerMarket"), salesPerMarketData);
        writeSalesPerMarket(workbook.createSheet("SalesUniquePerMarket"), salesUniquePerMarketData);

        if (Configuration.SAVED_DETAILED_RESULTS) writeDetailedResults(workbook.createSheet("DetailedResult"));
        else marketEvaluationData.clear();
        if (Configuration.SAVED_ENDORSEMENTS) writeEndorsements(workbook.createSheet("Endorsements"));
        else endorsData.clear();

        Console.info("Reporter: Writing to the disk");
        writeDisk(workbook);
    }

    private static void writeSalesPerMarket(XSSFSheet salesPerMarket, List<? extends SalesPerMarketData> sales) {
        Console.info("Reporter: Adding Sales Per Market: " + sales.size());
        Row headRow = salesPerMarket.createRow(0);

        int column = 0;
        for (String head : SalesPerMarketData.getHeader()) {
            Cell cell = headRow.createCell(column);
            cell.setCellValue(head);
            ++column;
        }

        int rowIndex = 1;
        for (SalesPerMarketData oneRow : sales) {
            Row dataRow = salesPerMarket.createRow(rowIndex);
            dataRow.createCell(0).setCellValue(oneRow.simulationId);
            dataRow.createCell(1).setCellValue(oneRow.period);

            for (int i = 0; i < oneRow.sales.length; ++i) {
                //accSales[i] += oneRow.sales[i];
                dataRow.createCell(2 + i).setCellValue(oneRow.sales[i]);
            }
            ++rowIndex;
        }
    }

    private static void writeDetailedResults(Sheet detailedResults) {
        Console.info("Reporter: Adding Detailed Results: " + marketEvaluationData.size());
        Row headRow = detailedResults.createRow(0);

        int column = 0;
        for (String head : MarketEvaluationData.getHeader()) {
            Cell cell = headRow.createCell(column);
            cell.setCellValue(head);
            ++column;
        }

        int rowIndex = 1;
        for (MarketEvaluationData oneRow : marketEvaluationData) {
            Row dataRow = detailedResults.createRow(rowIndex);
            dataRow.createCell(0).setCellValue(oneRow.simulationId);
            dataRow.createCell(1).setCellValue(oneRow.period);
            dataRow.createCell(2).setCellValue(oneRow.buyerId);
            dataRow.createCell(3).setCellValue(oneRow.marketName);
            dataRow.createCell(4).setCellValue(oneRow.evaluation);
            ++rowIndex;
        }
    }

    private static void addSheet(XSSFWorkbook workbook, Sheet sheet) {
        Sheet newSheet = workbook.createSheet(sheet.getSheetName());

        for (int i = 0; i < sheet.getLastRowNum(); ++i) {
            Row row = sheet.getRow(i);
            Row newRow = newSheet.createRow(i);
            for (int j = 0; j < row.getLastCellNum(); ++j) {
                Cell cell = row.getCell(j);
                String cellType = cell.getCellTypeEnum().name();
                Cell newCell = newRow.createCell(j);
                if (cellType.equalsIgnoreCase("STRING")) {
                    newCell.setCellValue(cell.getRichStringCellValue());
                }
                if (cellType.equalsIgnoreCase("NUMERIC") || cellType.equalsIgnoreCase("FORMULA")) {
                    newCell.setCellValue(cell.getNumericCellValue());
                }
            }
        }
    }

    public static void addEndorsementData(ArrayList<EndorsementData> endors) {
        endorsData.addAll(endors);
    }

    public static void addIterationData(IterationData oneRow) {
        iterationData.add(oneRow);
    }

    public static void addMarketEvaluationData(MarketEvaluationData oneRow) {
        marketEvaluationData.add(oneRow);
    }

    public static void addSalesByMarketData(SalesPerMarketData oneRow) {
        salesPerMarketData.add(oneRow);
    }

    public static void addSalesUniqueByMarketData(SalesUniquePerMarketData oneRow) {
        salesUniquePerMarketData.add(oneRow);
    }

    public static List<? extends SalesPerMarketData> getSalesPerMarketData() {
        return salesUniquePerMarketData;
    }

    private static void writeEndorsements(Sheet results) {
        Console.info("Reporter: Adding endorsements: " + endorsData.size());
        Row headRow = results.createRow(0);

        int column = 0;
        for (String head : EndorsementData.getHeader()) {
            Cell cell = headRow.createCell(column);
            cell.setCellValue(head);
            ++column;
        }

        int rowIndex = 1;
        for (EndorsementData oneRow : endorsData) {
            Row dataRow = results.createRow(rowIndex);
            dataRow.createCell(0).setCellValue(oneRow.simulationId);
            dataRow.createCell(1).setCellValue(oneRow.period);
            dataRow.createCell(2).setCellValue(oneRow.buyerId);
            dataRow.createCell(3).setCellValue(oneRow.marketName);
            dataRow.createCell(4).setCellValue(oneRow.attribute);
            dataRow.createCell(5).setCellValue(oneRow.value);
            ++rowIndex;
        }
    }

    private static void writeResults(Sheet results) {
        Console.info("Reporter: Adding Results: " + iterationData.size());
        Row headRow = results.createRow(0);

        int column = 0;
        for (String head : IterationData.getHeader()) {
            Cell cell = headRow.createCell(column);
            cell.setCellValue(head);
            ++column;
        }

        int rowIndex = 1;
        for (IterationData oneRow : iterationData) {
            Row dataRow = results.createRow(rowIndex);
            dataRow.createCell(0).setCellValue(oneRow.simulationId);
            dataRow.createCell(1).setCellValue(oneRow.period);
            dataRow.createCell(2).setCellValue(oneRow.buyerId);
            dataRow.createCell(3).setCellValue(oneRow.marketName);
            dataRow.createCell(4).setCellValue(oneRow.evaluation);
            ++rowIndex;
        }
    }

    private static void writeConfiguration(Sheet conf) {
        Console.info("Reporter: Adding Configuration");
        Map<String, Double> dump = Configuration.toMap();

        int rowIndex = 0;
        for (String key : dump.keySet()) {
            double value = dump.get(key);
            Row row = conf.createRow(rowIndex);
            Cell keyCell = row.createCell(0);
            Cell valueCell = row.createCell(1);
            keyCell.setCellValue(key);
            valueCell.setCellValue(value);
            ++rowIndex;
        }
    }

    private static void writeDisk(XSSFWorkbook workbook) {
        System.gc(); //call garbage collector (memory leaks?)
        
        String fullFileName = Configuration.OUTPUT_DIRECTORY + "/" + Configuration.FILE_NAME;
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yy(HH-mm-ss)");
            fullFileName += df.format(new Date()) + ".xlsx";

            FileOutputStream file = new FileOutputStream(fullFileName);
            workbook.write(file);
            file.close();
            Console.info("Reporter: File saved.");
        } catch (IOException ex) {
            Console.error("Input cannot be created: " + fullFileName);
            Console.error("ERROR: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }
}


