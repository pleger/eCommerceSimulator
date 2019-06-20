package Agents;

import java.util.ArrayList;
import java.util.Random;

public class BuyerFactory {
    public static Buyer getBuyer(int type){
        int [][] buyerEndorsmentList;
        double base;
        if(type==1){
            base=1.2;
            buyerEndorsmentList=new int [10][2];
            buyerEndorsmentList[0][0]=EndorsmentList.altaSeguridad;;
            buyerEndorsmentList[0][1]=4;
            buyerEndorsmentList[1][0]=EndorsmentList.altaVariedad;
            buyerEndorsmentList[1][1]=3;
            buyerEndorsmentList[2][0]=EndorsmentList.altaConfianzaVen;
            buyerEndorsmentList[2][1]=2;
            buyerEndorsmentList[3][0]=EndorsmentList.altaCalidad;
            buyerEndorsmentList[3][1]=1;
            buyerEndorsmentList[4][0]=EndorsmentList.altaVeracidad;
            buyerEndorsmentList[4][1]=5;
            buyerEndorsmentList[5][0]=EndorsmentList.bajaSeguridad;
            buyerEndorsmentList[5][1]=-4;
            buyerEndorsmentList[6][0]=EndorsmentList.bajaVariedad;
            buyerEndorsmentList[6][1]=-2;
            buyerEndorsmentList[7][0]=EndorsmentList.bajaConfianzaVen;
            buyerEndorsmentList[7][1]=-1;
            buyerEndorsmentList[8][0]=EndorsmentList.bajaCalidad;
            buyerEndorsmentList[8][1]=-1;
            buyerEndorsmentList[9][0]=EndorsmentList.bajaVeracidad;
            buyerEndorsmentList[9][1]=-4;
            return new Buyer(buyerEndorsmentList,base);
        }else if (type==2) {
            base=1.2;
            buyerEndorsmentList=new int [10][2];
            buyerEndorsmentList[0][0]=EndorsmentList.altaSeguridad;;
            buyerEndorsmentList[0][1]=2;
            buyerEndorsmentList[1][0]=EndorsmentList.altaVariedad;
            buyerEndorsmentList[1][1]=4;
            buyerEndorsmentList[2][0]=EndorsmentList.altaConfianzaVen;
            buyerEndorsmentList[2][1]=1;
            buyerEndorsmentList[3][0]=EndorsmentList.altaCalidad;
            buyerEndorsmentList[3][1]=5;
            buyerEndorsmentList[4][0]=EndorsmentList.altaVeracidad;
            buyerEndorsmentList[4][1]=1;
            buyerEndorsmentList[5][0]=EndorsmentList.bajaSeguridad;
            buyerEndorsmentList[5][1]=-2;
            buyerEndorsmentList[6][0]=EndorsmentList.bajaVariedad;
            buyerEndorsmentList[6][1]=-3;
            buyerEndorsmentList[7][0]=EndorsmentList.bajaConfianzaVen;
            buyerEndorsmentList[7][1]=-1;
            buyerEndorsmentList[8][0]=EndorsmentList.bajaCalidad;
            buyerEndorsmentList[8][1]=-4;
            buyerEndorsmentList[9][0]=EndorsmentList.bajaVeracidad;
            buyerEndorsmentList[9][1]=-1;
            return new Buyer(buyerEndorsmentList,base);
        }
        return null;
    }

    /**
     * Creates a list of buyers, taking a list of probabilities for every buyer.
     * The list must be in order, that means, first value is for the first type , second value
     * is for the second type, and so on.
     * @param buyerQuantity the number of buyers to be created
     * @param probList the probabilities of every buyer in order from first to last
     * @return probabilistically created arraylist
     */
    public static ArrayList<Buyer> getBuyerList(int buyerQuantity,double[]probList){
        ArrayList<Buyer> buyersList=new ArrayList<>();

        int cantProb=probList.length;
        double [] intervals=new double[cantProb];
        intervals[0]=probList[0];//first probability
        for(int i=1;i<cantProb;i++){
            intervals[i]=probList[i]+intervals[i-1];
        }

        Random randomNum=new Random();
        for(int i=0;i<buyerQuantity;i++){
            Double prob=randomNum.nextDouble();
            for(int h=0;h<cantProb;h++)
                if(prob<=intervals[h]) {
                    buyersList.add(getBuyer(h+1));
                    break;
                }
        }
        return buyersList;
    }
}
