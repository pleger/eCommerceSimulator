package InputManager;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Loader {

    public static void read() throws Exception {
        final String INPUT_FILE = "input.xlsx";

        try {
            FileInputStream file = new FileInputStream(new File("../input/" + INPUT_FILE));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet conf = workbook.getSheet("configuration");
            HSSFSheet data = workbook.getSheet("data");

            //get configutation
            HashMap<String, Double> confs = new HashMap<String, Double>();
            for (Row row : conf) {
                confs.put(row.getCell(0).getStringCellValue().toUpperCase(), row.getCell(1).getNumericCellValue());
            }
            Configuration.set(confs);

            if (data.getLastRowNum() - 3 != Configuration.ENDORSEMENTS) {
                throw new Exception("Endorsements are not matched:" + (data.getLastRowNum() - 3) + " " + Configuration.ENDORSEMENTS);
            }

            HashMap<String, ArrayList<Double[]>> datas = new HashMap<String, ArrayList<Double[]>>();
            ArrayList<String> marketNames = new ArrayList<String>();
            ArrayList<Double> endor = new ArrayList<Double>();
            ArrayList<Double[]> endors = new ArrayList<Double[]>();

            int levels = Configuration.LEVELS;

            for (Row row : data) {
                String name = "NO NAME";

                //get market names
                if (row.getRowNum() == 2) {
                    for (Cell cell : row) {
                        if (cell.getColumnIndex() > levels && cell.getColumnIndex() % levels == 0) {
                            marketNames.add(cell.getStringCellValue());
                        }
                    }
                }

                //get data from markets
                if (row.getRowNum() > 3) {
                    for (Cell cell : row) {
                        if (cell.getColumnIndex() == 0) {
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
                    datas.put(name, endors);
                }
            }

            Markets.set(datas, marketNames);
            Buyers.set(datas);

        } catch (IOException e) {
            System.out.println("Input cannot be open:" + e);
        }
    }
}
