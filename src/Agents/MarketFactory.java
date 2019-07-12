package Agents;

//PL: consejos de BuyerMarket

import java.util.ArrayList;

public class MarketFactory {
    public static final int MARKET1 = 1;
    public static final int MARKET2 = 2;
    public static final int MARKET3 = 3;


    private static int NUMBER_OF_MARKETS = 3;

    private static String getMarketName(int number) {
        switch (number) {
            case MARKET1:
                return "Alibaba";
            case MARKET2:
                return "DHGate";
            case MARKET3:
                return "Banggood";
            default:
                return "Invalid";
        }
    }


    private static Market getMarket(int type) {
        double[][] marketEndorsmentProbabilityList;
        if (type == MARKET1) {
            marketEndorsmentProbabilityList = new double[5][3];
            marketEndorsmentProbabilityList[0][0] = EndorsementList.ALTA_VARIEDAD;
            marketEndorsmentProbabilityList[0][1] = 0.9;
            marketEndorsmentProbabilityList[0][2] = EndorsementList.BAJA_VARIEDAD;
            marketEndorsmentProbabilityList[1][0] = EndorsementList.ALTA_SEGURIDAD;
            marketEndorsmentProbabilityList[1][1] = 0.5;
            marketEndorsmentProbabilityList[1][2] = EndorsementList.BAJA_SEGURIDAD;
            marketEndorsmentProbabilityList[2][0] = EndorsementList.ALTA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[2][1] = 0.3;
            marketEndorsmentProbabilityList[2][2] = EndorsementList.BAJA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[3][0] = EndorsementList.ALTA_CALIDAD;
            marketEndorsmentProbabilityList[3][1] = 0.3;
            marketEndorsmentProbabilityList[3][2] = EndorsementList.BAJA_CALIDAD;
            marketEndorsmentProbabilityList[4][0] = EndorsementList.ALTA_VERACIDAD;
            marketEndorsmentProbabilityList[4][1] = 0.2;
            marketEndorsmentProbabilityList[4][2] = EndorsementList.BAJA_VERACIDAD;
            return new Market(marketEndorsmentProbabilityList, getMarketName(MARKET1));
        } else if (type == MARKET2) {
            marketEndorsmentProbabilityList = new double[5][3];
            marketEndorsmentProbabilityList[0][0] = EndorsementList.ALTA_VARIEDAD;
            marketEndorsmentProbabilityList[0][1] = 0.3;
            marketEndorsmentProbabilityList[0][2] = EndorsementList.BAJA_VARIEDAD;
            marketEndorsmentProbabilityList[1][0] = EndorsementList.ALTA_SEGURIDAD;
            marketEndorsmentProbabilityList[1][1] = 0.4;
            marketEndorsmentProbabilityList[1][2] = EndorsementList.BAJA_SEGURIDAD;
            marketEndorsmentProbabilityList[2][0] = EndorsementList.ALTA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[2][1] = 0.8;
            marketEndorsmentProbabilityList[2][2] = EndorsementList.BAJA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[3][0] = EndorsementList.ALTA_CALIDAD;
            marketEndorsmentProbabilityList[3][1] = 0.7;
            marketEndorsmentProbabilityList[3][2] = EndorsementList.BAJA_CALIDAD;
            marketEndorsmentProbabilityList[4][0] = EndorsementList.ALTA_VERACIDAD;
            marketEndorsmentProbabilityList[4][1] = 0.4;
            marketEndorsmentProbabilityList[4][2] = EndorsementList.BAJA_VERACIDAD;
            return new Market(marketEndorsmentProbabilityList, getMarketName(MARKET2));
        } else if (type == MARKET3) {
            marketEndorsmentProbabilityList = new double[5][3];
            marketEndorsmentProbabilityList[0][0] = EndorsementList.ALTA_VARIEDAD;
            marketEndorsmentProbabilityList[0][1] = 0.4;
            marketEndorsmentProbabilityList[0][2] = EndorsementList.BAJA_VARIEDAD;
            marketEndorsmentProbabilityList[1][0] = EndorsementList.ALTA_SEGURIDAD;
            marketEndorsmentProbabilityList[1][1] = 0.8;
            marketEndorsmentProbabilityList[1][2] = EndorsementList.BAJA_SEGURIDAD;
            marketEndorsmentProbabilityList[2][0] = EndorsementList.ALTA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[2][1] = 0.5;
            marketEndorsmentProbabilityList[2][2] = EndorsementList.BAJA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[3][0] = EndorsementList.ALTA_CALIDAD;
            marketEndorsmentProbabilityList[3][1] = 0.5;
            marketEndorsmentProbabilityList[3][2] = EndorsementList.BAJA_CALIDAD;
            marketEndorsmentProbabilityList[4][0] = EndorsementList.ALTA_VERACIDAD;
            marketEndorsmentProbabilityList[4][1] = 0.6;
            marketEndorsmentProbabilityList[4][2] = EndorsementList.BAJA_VERACIDAD;
            return new Market(marketEndorsmentProbabilityList, getMarketName(MARKET3));
        }
        return null;
    }

    public static ArrayList<Market> getMarketList() {
        ArrayList<Market> markets = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_MARKETS; i++) {
            markets.add(getMarket(i + 1));
        }
        return markets;
    }

    private static ArrayList<Market> getOneByType() {
        ArrayList<Market> oneByType = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_MARKETS; i++) {
            oneByType.add(getMarket(i));
        }
        return oneByType;
    }

    public static ArrayList<ArrayList<String>> dumpMarketInfo() {
        ArrayList<ArrayList<String>> marketInfo = new ArrayList<>();

        ArrayList<Market> oneByType = getOneByType();
        for (Market market : oneByType) {
            ArrayList<String> singleMarketInfo = new ArrayList<>();
            String[] info = market.toString().split("\n");
            for (int i = 0; i < info.length; i++) {
                singleMarketInfo.add(info[i]);
            }
            marketInfo.add(singleMarketInfo);
        }
        return marketInfo;
    }
}
