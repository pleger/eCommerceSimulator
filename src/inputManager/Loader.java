package inputManager;

import logger.Console;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Loader {
    private static Sheet markets;
    private static Sheet buyers;

    public static void read() {
        File file = new File("input/" + Configuration.FILE_NAME + ".xlsx");
        try {
            FileInputStream fileStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileStream);
            markets = workbook.getSheet("markets");
            buyers = workbook.getSheet("buyers");

            Configuration.set(readConfiguration(workbook.getSheet("configuration")));

            Markets.set(readMarketAttributes(markets, Configuration.LEVELS),
                    readMarketNames(markets, Configuration.LEVELS),
                    readMarketQuota(workbook.getSheet("marketQuota")));

            Buyers.set(readBuyers(buyers));

            Configuration.setAttributes(Markets.attributeSize(), Buyers.attributeSize());
            Configuration.setMarkets(Markets.getInnerMarkets().size());
        } catch (Exception ex) {
            Console.error("Input cannot be open: " + file.getAbsolutePath());
            Console.error("ERROR: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static HashMap<String, Double> readMarketQuota(Sheet marketQuota) {
        HashMap<String, Double> quota = new HashMap<>();

        for (Row row : marketQuota) {
            quota.put(row.getCell(0).getStringCellValue().toUpperCase(), row.getCell(1).getNumericCellValue()/100.0);
        }
        return quota;
    }

    private static HashMap<String, Double> readConfiguration(Sheet conf) {
        HashMap<String, Double> confs = new HashMap<>();
        for (Row row : conf) {
            confs.put(row.getCell(0).getStringCellValue().toUpperCase(), row.getCell(1).getNumericCellValue());
        }
        return confs;
    }

    private static ArrayList<String> readMarketNames(Sheet market, int levels) {
        ArrayList<String> marketNames = new ArrayList<>();

        for (Row row : market) {
            if (row.getRowNum() == 1) {
                for (Cell cell : row) {
                    if (cell.getColumnIndex() > 0 && (cell.getColumnIndex() + 1) % levels == 0) {
                        marketNames.add(cell.getStringCellValue().toUpperCase());
                    }
                }
            }
        }
        return marketNames;
    }

    private static HashMap<String, ArrayList<Double[]>> readMarketAttributes(Sheet market, int levels) {
        HashMap<String, ArrayList<Double[]>> datas = new HashMap<>();
        ArrayList<Double> endor = new ArrayList<>();
        ArrayList<Double[]> endors = new ArrayList<>();

        for (Row row : market) {
            String name = "NO NAME";

            //get data from attributes
            if (row.getRowNum() > 2) {  // where starts attributes
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {  //adding attributeName
                        name = cell.getStringCellValue().toUpperCase();
                    } else {
                        endor.add(cell.getNumericCellValue());
                        if (cell.getColumnIndex() % levels == 0) {
                            Double[] oneEndorsement = new Double[levels];
                            endors.add(endor.toArray(oneEndorsement));
                            endor.clear();
                        }
                    }
                }

                datas.put(name, new ArrayList<>(endors));
                endors.clear();
            }
        }
        return datas;
    }

    private static HashMap<String, Double> readBuyers(Sheet buyer) {
        HashMap<String, Double> buyers = new HashMap<>();
        for (Row row : buyer) {
            buyers.put(row.getCell(0).getStringCellValue().toUpperCase(), row.getCell(1).getNumericCellValue());
        }
        return buyers;
    }

    public static Sheet getBuyers() {
        return buyers;
    }

    public static Sheet getMarkets() {
        return markets;
    }
}
