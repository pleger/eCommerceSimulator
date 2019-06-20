package Agents;


import java.util.ArrayList;

public class MarketFactory {
    public static int numberOfMarkets=3;
    public static Market getMarket(int type){
        double [][] marketEndorsmentProbabilityList;
        if(type==1){
            marketEndorsmentProbabilityList=new double[5][3];
            marketEndorsmentProbabilityList[0][0]=EndorsmentList.altaVariedad;
            marketEndorsmentProbabilityList[0][1]=0.9;
            marketEndorsmentProbabilityList[0][2]=EndorsmentList.bajaVariedad;
            marketEndorsmentProbabilityList[1][0]=EndorsmentList.altaSeguridad;
            marketEndorsmentProbabilityList[1][1]=0.5;
            marketEndorsmentProbabilityList[1][2]=EndorsmentList.bajaSeguridad;;
            marketEndorsmentProbabilityList[2][0]=EndorsmentList.altaConfianzaVen;
            marketEndorsmentProbabilityList[2][1]=0.3;
            marketEndorsmentProbabilityList[2][2]=EndorsmentList.bajaConfianzaVen;
            marketEndorsmentProbabilityList[3][0]=EndorsmentList.altaCalidad;
            marketEndorsmentProbabilityList[3][1]=0.3;
            marketEndorsmentProbabilityList[3][2]=EndorsmentList.bajaCalidad;
            marketEndorsmentProbabilityList[4][0]=EndorsmentList.altaVeracidad;
            marketEndorsmentProbabilityList[4][1]=0.2;
            marketEndorsmentProbabilityList[4][2]=EndorsmentList.bajaVeracidad;
            return new Market(marketEndorsmentProbabilityList);
        }else if(type==2){
            marketEndorsmentProbabilityList=new double[5][3];
            marketEndorsmentProbabilityList[0][0]=EndorsmentList.altaVariedad;
            marketEndorsmentProbabilityList[0][1]=0.3;
            marketEndorsmentProbabilityList[0][2]=EndorsmentList.bajaVariedad;
            marketEndorsmentProbabilityList[1][0]=EndorsmentList.altaSeguridad;
            marketEndorsmentProbabilityList[1][1]=0.4;
            marketEndorsmentProbabilityList[1][2]=EndorsmentList.bajaSeguridad;
            marketEndorsmentProbabilityList[2][0]=EndorsmentList.altaConfianzaVen;
            marketEndorsmentProbabilityList[2][1]=0.8;
            marketEndorsmentProbabilityList[2][2]=EndorsmentList.bajaConfianzaVen;
            marketEndorsmentProbabilityList[3][0]=EndorsmentList.altaCalidad;
            marketEndorsmentProbabilityList[3][1]=0.7;
            marketEndorsmentProbabilityList[3][2]=EndorsmentList.bajaCalidad;
            marketEndorsmentProbabilityList[4][0]=EndorsmentList.altaVeracidad;
            marketEndorsmentProbabilityList[4][1]=0.4;
            marketEndorsmentProbabilityList[4][2]=EndorsmentList.bajaVeracidad;
            return new Market(marketEndorsmentProbabilityList);
        }else if(type==3){
            marketEndorsmentProbabilityList=new double[5][3];
            marketEndorsmentProbabilityList[0][0]=EndorsmentList.altaVariedad;
            marketEndorsmentProbabilityList[0][1]=0.4;
            marketEndorsmentProbabilityList[0][2]=EndorsmentList.bajaVariedad;
            marketEndorsmentProbabilityList[1][0]=EndorsmentList.altaSeguridad;
            marketEndorsmentProbabilityList[1][1]=0.8;
            marketEndorsmentProbabilityList[1][2]=EndorsmentList.bajaSeguridad;
            marketEndorsmentProbabilityList[2][0]=EndorsmentList.altaConfianzaVen;
            marketEndorsmentProbabilityList[2][1]=0.5;
            marketEndorsmentProbabilityList[2][2]=EndorsmentList.bajaConfianzaVen;
            marketEndorsmentProbabilityList[3][0]=EndorsmentList.altaCalidad;
            marketEndorsmentProbabilityList[3][1]=0.5;
            marketEndorsmentProbabilityList[3][2]=EndorsmentList.bajaCalidad;
            marketEndorsmentProbabilityList[4][0]=EndorsmentList.altaVeracidad;
            marketEndorsmentProbabilityList[4][1]=0.6;
            marketEndorsmentProbabilityList[4][2]=EndorsmentList.bajaVeracidad;
            return new Market(marketEndorsmentProbabilityList);
        }
        return null;
    }
    public static ArrayList<Market> getMarketList(){
        ArrayList<Market> markets=new ArrayList<>();
        for (int i=0;i<numberOfMarkets;i++){
            markets.add(getMarket(i+1));
        }
        return markets;
    }
}
