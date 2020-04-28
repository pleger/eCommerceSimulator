package reporter;

import inputManager.Configuration;
import inputManager.Loader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
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
    private static final Logger logger = LogManager.getRootLogger();

    private static final List<IterationData> iterationData = new ArrayList<>();
    private static final List<EndorsementData> endorsData = new ArrayList<>();
    private static final List<MarketEvaluationData> marketEvaluationData = new ArrayList<>();

    public static void write() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet conf = workbook.createSheet("Configuration");
        Sheet results = workbook.createSheet("Results");
        Sheet detailedResults = workbook.createSheet("DetailResult");
        Sheet endors = workbook.createSheet("endorsement");


        writeConfiguration(conf);
        addSheet(workbook, Loader.getMarkets());
        addSheet(workbook, Loader.getBuyers());
        writeResults(results);
        writeDetailedResults(detailedResults);
        writeEndorsements(endors);

        writeDisk(workbook);
    }

    private static void writeDetailedResults(Sheet detailedResults) {
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
                Cell newCell = newRow.createCell(j);
                CellType ct = cell.getCellTypeEnum();

                if (ct.name().equalsIgnoreCase("STRING")) {
                    newCell.setCellValue(cell.getRichStringCellValue());
                }
                if (ct.name().equalsIgnoreCase("NUMERIC")) {
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

    private static void writeDisk(XSSFWorkbook workbook) {
        String fileName = Configuration.OUTPUT_FILE;
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yy(HH-mm-ss)");
            fileName += df.format(new Date()) + ".xlsx";

            FileOutputStream file = new FileOutputStream("output/" + fileName);
            workbook.write(file);
            file.close();
        } catch (IOException ex) {
            logger.error("Input cannot be open: " + fileName);
            logger.error("ERROR: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void writeEndorsements(Sheet results) {
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
}
