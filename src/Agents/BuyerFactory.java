package Agents;

import java.util.ArrayList;
import java.util.Random;

public class BuyerFactory {
    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;

    public static final int NUMBER_OF_BUYERS = 2;

    public static String getBuyerType(int type) {
        switch (type) {
            case TYPE1:
                return "Type 1";
            case TYPE2:
                return "Type 2";
            default:
                return "Invalid";
        }
    }

    public static int getBuyerNumber(String type) {
        switch (type) {
            case "Type 1":
                return TYPE1;
            case "Type 2":
                return TYPE2;
            default:
                return -1;
        }
    }

    private static Buyer getBuyer(int type) {
        int[][] buyerEndorsmentList;
        if (type == TYPE1) {
            final double base = 1.2;
            buyerEndorsmentList = new int[10][2]; //se puede hacer más ordenado
            buyerEndorsmentList[0][0] = EndorsementList.ALTA_SEGURIDAD;
            buyerEndorsmentList[0][1] = 4;
            buyerEndorsmentList[1][0] = EndorsementList.ALTA_VARIEDAD;
            buyerEndorsmentList[1][1] = 3;
            buyerEndorsmentList[2][0] = EndorsementList.ALTA_CONFIANZA_VEN;
            buyerEndorsmentList[2][1] = 2;
            buyerEndorsmentList[3][0] = EndorsementList.ALTA_CALIDAD;
            buyerEndorsmentList[3][1] = 1;
            buyerEndorsmentList[4][0] = EndorsementList.ALTA_VERACIDAD;
            buyerEndorsmentList[4][1] = 5;
            buyerEndorsmentList[5][0] = EndorsementList.BAJA_SEGURIDAD;
            buyerEndorsmentList[5][1] = -4;
            buyerEndorsmentList[6][0] = EndorsementList.BAJA_VARIEDAD;
            buyerEndorsmentList[6][1] = -2;
            buyerEndorsmentList[7][0] = EndorsementList.BAJA_CONFIANZA_VEN;
            buyerEndorsmentList[7][1] = -1;
            buyerEndorsmentList[8][0] = EndorsementList.BAJA_CALIDAD;
            buyerEndorsmentList[8][1] = -1;
            buyerEndorsmentList[9][0] = EndorsementList.BAJA_VERACIDAD;
            buyerEndorsmentList[9][1] = -4;
            return new Buyer(buyerEndorsmentList, base, getBuyerType(TYPE1));
        } else if (type == TYPE2) {
            final double base = 1.2;
            buyerEndorsmentList = new int[10][2];
            buyerEndorsmentList[0][0] = EndorsementList.ALTA_SEGURIDAD;
            buyerEndorsmentList[0][1] = 2;
            buyerEndorsmentList[1][0] = EndorsementList.ALTA_VARIEDAD;
            buyerEndorsmentList[1][1] = 4;
            buyerEndorsmentList[2][0] = EndorsementList.ALTA_CONFIANZA_VEN;
            buyerEndorsmentList[2][1] = 1;
            buyerEndorsmentList[3][0] = EndorsementList.ALTA_CALIDAD;
            buyerEndorsmentList[3][1] = 5;
            buyerEndorsmentList[4][0] = EndorsementList.ALTA_VERACIDAD;
            buyerEndorsmentList[4][1] = 1;
            buyerEndorsmentList[5][0] = EndorsementList.BAJA_SEGURIDAD;
            buyerEndorsmentList[5][1] = -2;
            buyerEndorsmentList[6][0] = EndorsementList.BAJA_VARIEDAD;
            buyerEndorsmentList[6][1] = -3;
            buyerEndorsmentList[7][0] = EndorsementList.BAJA_CONFIANZA_VEN;
            buyerEndorsmentList[7][1] = -1;
            buyerEndorsmentList[8][0] = EndorsementList.BAJA_CALIDAD;
            buyerEndorsmentList[8][1] = -4;
            buyerEndorsmentList[9][0] = EndorsementList.BAJA_VERACIDAD;
            buyerEndorsmentList[9][1] = -1;
            return new Buyer(buyerEndorsmentList, base, getBuyerType(TYPE2));
        }
        return null;
    }

    /**
     * Public method used for debugging
     *
     * @return buyer
     */
    public static ArrayList<Buyer> dGetBuyer(int type) {
        ArrayList<Buyer> debugUserList = new ArrayList<>();//only one user
        Buyer buyer = getBuyer(type);
        debugUserList.add(buyer);
        return debugUserList;
    }

    /**
     * Creates a list of buyers, taking a list of probabilities for every buyer.
     * The list must be in order, that means, first value is for the first type , second value
     * is for the second type, and so on.
     *
     * @param buyerQuantity the number of buyers to be created
     * @param probList      the probabilities of every buyer in order from first to last
     * @return probabilistically created arraylist
     */
    public static ArrayList<Buyer> getBuyerList(int buyerQuantity, double[] probList) {
        ArrayList<Buyer> buyersList = new ArrayList<>();

        int cantProb = probList.length;
        double[] intervals = new double[cantProb];
        intervals[0] = probList[0];//first probability
        for (int i = 1; i < cantProb; i++) {
            intervals[i] = probList[i] + intervals[i - 1];
        }


        Random randomNum = new Random();
        for (int i = 0; i < buyerQuantity; i++) {
            Double prob = randomNum.nextDouble();
            for (int h = 0; h < cantProb; h++)
                if (prob <= intervals[h]) {
                    Buyer newBuyer = getBuyer(h + 1);
                    buyersList.add(newBuyer);
                    break;
                }
        }
        return buyersList;
    }

    private static ArrayList<Buyer> getOneByType() {
        ArrayList<Buyer> oneByType = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_BUYERS; i++) {
            oneByType.add(getBuyer(i));
        }
        return oneByType;
    }

    public static ArrayList<ArrayList<String>> dumpBuyerInfo() {
        ArrayList<ArrayList<String>> buyerInfo = new ArrayList<>();

        ArrayList<Buyer> oneByType = getOneByType();
        for (Buyer buyer : oneByType) {
            ArrayList<String> singleBuyerInfo = new ArrayList<>();
            String[] info = buyer.toString().split("\n");
            for (int i = 0; i < info.length; i++) {
                singleBuyerInfo.add(info[i]);
            }
            buyerInfo.add(singleBuyerInfo);
        }
        return buyerInfo;
    }
}
