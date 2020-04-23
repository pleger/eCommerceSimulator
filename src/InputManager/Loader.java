package InputManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private static final Logger logger = LogManager.getRootLogger();

    public static void read(String fileName) {
        File file = new File("input/" + fileName);
        try {
            FileInputStream fileStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fileStream);
            Sheet conf = workbook.getSheet("configuration");
            Sheet market = workbook.getSheet("markets");
            Sheet buyer = workbook.getSheet("buyers");

            Configuration.set(readConfiguration(conf));
            Markets.set(readMarketAttributes(market, Configuration.LEVELS), readMarketNames(market, Configuration.LEVELS));
            Buyers.set(readBuyers(buyer));

            Configuration.setAttributes(Markets.attributeSize(), Buyers.attributeSize());
            Configuration.setMarkets(Markets.getMarkets().size());
        } catch (Exception ex) {
            logger.error("Input cannot be open: " + file.getAbsolutePath());
            logger.error("ERROR: " + ex);
            ex.printStackTrace();
            System.exit(1);
        }
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
            if (row.getRowNum() == 2) {
                for (Cell cell : row) {
                    if (cell.getColumnIndex() > 0 && cell.getColumnIndex() % levels == 0) {
                        marketNames.add(cell.getStringCellValue());
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

            //get data from endorsements
            if (row.getRowNum() > 2) {  // where starts endorsements
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {  //adding attributeName
                        name = cell.getStringCellValue();
                    } else {
                        endor.add(cell.getNumericCellValue());
                        if (cell.getColumnIndex() % levels == 0) {
                            Double[] oneEndorsement = new Double[levels];
                            endors.add(endor.toArray(oneEndorsement));
                            endor.clear();
                        }
                    }
                }

                datas.put(name, (ArrayList<Double[]>) endors.clone());
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
}
