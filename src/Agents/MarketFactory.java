package Agents;

//PL: consejos de BuyerMarket

import java.util.ArrayList;

public class MarketFactory {
    public static final int MARKET1 = 1;
    public static final int MARKET2 = 2;
    public static final int MARKET3 = 3;


    private static int numberOfMarkets = 3;

    private static Market getMarket(int type) {
        double[][] marketEndorsmentProbabilityList;
        if (type == MARKET1) {
            marketEndorsmentProbabilityList = new double[5][3];
            marketEndorsmentProbabilityList[0][0] = EndorsmentList.ALTA_VARIEDAD;
            marketEndorsmentProbabilityList[0][1] = 0.9;
            marketEndorsmentProbabilityList[0][2] = EndorsmentList.BAJA_VARIEDAD;
            marketEndorsmentProbabilityList[1][0] = EndorsmentList.ALTA_SEGURIDAD;
            marketEndorsmentProbabilityList[1][1] = 0.5;
            marketEndorsmentProbabilityList[1][2] = EndorsmentList.BAJA_SEGURIDAD;
            marketEndorsmentProbabilityList[2][0] = EndorsmentList.ALTA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[2][1] = 0.3;
            marketEndorsmentProbabilityList[2][2] = EndorsmentList.BAJA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[3][0] = EndorsmentList.ALTA_CALIDAD;
            marketEndorsmentProbabilityList[3][1] = 0.3;
            marketEndorsmentProbabilityList[3][2] = EndorsmentList.BAJA_CALIDAD;
            marketEndorsmentProbabilityList[4][0] = EndorsmentList.ALTA_VERACIDAD;
            marketEndorsmentProbabilityList[4][1] = 0.2;
            marketEndorsmentProbabilityList[4][2] = EndorsmentList.BAJA_VERACIDAD;
            return new Market(marketEndorsmentProbabilityList);
        } else if (type == MARKET2) {
            marketEndorsmentProbabilityList = new double[5][3];
            marketEndorsmentProbabilityList[0][0] = EndorsmentList.ALTA_VARIEDAD;
            marketEndorsmentProbabilityList[0][1] = 0.3;
            marketEndorsmentProbabilityList[0][2] = EndorsmentList.BAJA_VARIEDAD;
            marketEndorsmentProbabilityList[1][0] = EndorsmentList.ALTA_SEGURIDAD;
            marketEndorsmentProbabilityList[1][1] = 0.4;
            marketEndorsmentProbabilityList[1][2] = EndorsmentList.BAJA_SEGURIDAD;
            marketEndorsmentProbabilityList[2][0] = EndorsmentList.ALTA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[2][1] = 0.8;
            marketEndorsmentProbabilityList[2][2] = EndorsmentList.BAJA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[3][0] = EndorsmentList.ALTA_CALIDAD;
            marketEndorsmentProbabilityList[3][1] = 0.7;
            marketEndorsmentProbabilityList[3][2] = EndorsmentList.BAJA_CALIDAD;
            marketEndorsmentProbabilityList[4][0] = EndorsmentList.ALTA_VERACIDAD;
            marketEndorsmentProbabilityList[4][1] = 0.4;
            marketEndorsmentProbabilityList[4][2] = EndorsmentList.BAJA_VERACIDAD;
            return new Market(marketEndorsmentProbabilityList);
        } else if (type == MARKET3) {
            marketEndorsmentProbabilityList = new double[5][3];
            marketEndorsmentProbabilityList[0][0] = EndorsmentList.ALTA_VARIEDAD;
            marketEndorsmentProbabilityList[0][1] = 0.4;
            marketEndorsmentProbabilityList[0][2] = EndorsmentList.BAJA_VARIEDAD;
            marketEndorsmentProbabilityList[1][0] = EndorsmentList.ALTA_SEGURIDAD;
            marketEndorsmentProbabilityList[1][1] = 0.8;
            marketEndorsmentProbabilityList[1][2] = EndorsmentList.BAJA_SEGURIDAD;
            marketEndorsmentProbabilityList[2][0] = EndorsmentList.ALTA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[2][1] = 0.5;
            marketEndorsmentProbabilityList[2][2] = EndorsmentList.BAJA_CONFIANZA_VEN;
            marketEndorsmentProbabilityList[3][0] = EndorsmentList.ALTA_CALIDAD;
            marketEndorsmentProbabilityList[3][1] = 0.5;
            marketEndorsmentProbabilityList[3][2] = EndorsmentList.BAJA_CALIDAD;
            marketEndorsmentProbabilityList[4][0] = EndorsmentList.ALTA_VERACIDAD;
            marketEndorsmentProbabilityList[4][1] = 0.6;
            marketEndorsmentProbabilityList[4][2] = EndorsmentList.BAJA_VERACIDAD;
            return new Market(marketEndorsmentProbabilityList);
        }
        return null;
    }

    public static ArrayList<Market> getMarketList() {
        ArrayList<Market> markets = new ArrayList<>();
        for (int i = 0; i < numberOfMarkets; i++) {
            markets.add(getMarket(i + 1));
        }
        return markets;
    }
}
