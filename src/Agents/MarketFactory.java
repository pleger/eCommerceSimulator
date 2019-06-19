package Agents;

import sun.util.resources.ro.CalendarData_ro;

public class MarketFactory {
    public static Market getMarket(String type){
        double [][] marketEndorsmentProbabilityList;
        if(type.equalsIgnoreCase("Type 1")){
            marketEndorsmentProbabilityList=new double[5][2];
            marketEndorsmentProbabilityList[0][0]=EndorsmentList.altaVariedad;
            marketEndorsmentProbabilityList[0][1]=0.9;
            marketEndorsmentProbabilityList[1][0]=EndorsmentList.altaSeguridad;
            marketEndorsmentProbabilityList[1][1]=0.5;
            marketEndorsmentProbabilityList[2][0]=EndorsmentList.altaConfianzaVen;
            marketEndorsmentProbabilityList[2][1]=0.3;
            marketEndorsmentProbabilityList[3][0]=EndorsmentList.altaCalidad;
            marketEndorsmentProbabilityList[3][1]=0.3;
            marketEndorsmentProbabilityList[4][0]=EndorsmentList.altaVeracidad;
            marketEndorsmentProbabilityList[4][1]=0.2;
            return new Market(marketEndorsmentProbabilityList);
        }else if(type.equalsIgnoreCase("Type 2")){
            marketEndorsmentProbabilityList=new double[5][2];
            marketEndorsmentProbabilityList[0][0]=EndorsmentList.altaVariedad;
            marketEndorsmentProbabilityList[0][1]=0.3;
            marketEndorsmentProbabilityList[1][0]=EndorsmentList.altaSeguridad;
            marketEndorsmentProbabilityList[1][1]=0.4;
            marketEndorsmentProbabilityList[2][0]=EndorsmentList.altaConfianzaVen;
            marketEndorsmentProbabilityList[2][1]=0.8;
            marketEndorsmentProbabilityList[3][0]=EndorsmentList.altaCalidad;
            marketEndorsmentProbabilityList[3][1]=0.7;
            marketEndorsmentProbabilityList[4][0]=EndorsmentList.altaVeracidad;
            marketEndorsmentProbabilityList[4][1]=0.4;
            return new Market(marketEndorsmentProbabilityList);
        }else if(type.equalsIgnoreCase("Type 3")){
            marketEndorsmentProbabilityList=new double[5][2];
            marketEndorsmentProbabilityList[0][0]=EndorsmentList.altaVariedad;
            marketEndorsmentProbabilityList[0][1]=0.4;
            marketEndorsmentProbabilityList[1][0]=EndorsmentList.altaSeguridad;
            marketEndorsmentProbabilityList[1][1]=0.8;
            marketEndorsmentProbabilityList[2][0]=EndorsmentList.altaConfianzaVen;
            marketEndorsmentProbabilityList[2][1]=0.5;
            marketEndorsmentProbabilityList[3][0]=EndorsmentList.altaCalidad;
            marketEndorsmentProbabilityList[3][1]=0.5;
            marketEndorsmentProbabilityList[4][0]=EndorsmentList.altaVeracidad;
            marketEndorsmentProbabilityList[4][1]=0.6;
            return new Market(marketEndorsmentProbabilityList);
        }
        return null;
    }
}
