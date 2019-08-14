package GUI;

import Agents.MarketFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InformationPanel {
    private static JFrame frame;
    private static JPanel panel;
    private static JTabbedPane tabs;
    private static ArrayList<JLabel> marketLabels;
    private static ArrayList<ArrayList<String>> marketInfo;
    public static void createInformationPanel(int marketsSize){
        marketInfo=new ArrayList<>();
        marketLabels=new ArrayList<>();

        for(int i=0;i<marketsSize;i++){
            ArrayList<String> individualMarketInfo=new ArrayList<>();
            int marketNumber=i+1;
            individualMarketInfo.add(MarketFactory.getMarketName(marketNumber)+"(Market "+marketNumber +")");
            marketInfo.add(individualMarketInfo);
        }
        tabs=new JTabbedPane();

        frame=new JFrame();
        frame.setTitle("Information");
        frame.setLocationRelativeTo(null);
        frame.setSize(400,100*marketsSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        panel=new JPanel();
        panel.setLayout(new GridLayout(marketsSize,1));
        for(int i=0;i<marketsSize;i++){
            JLabel label=new JLabel();
            marketLabels.add(i,label);
            panel.add(label);
        }
        tabs.addTab("Buyers On Market",panel);
        frame.getContentPane().add(tabs);
    }
    private static void clearMarketInfo(){
        for(int i=0;i<marketInfo.size();i++){
            marketInfo.get(i).clear();
            int marketNumber=i+1;
            marketInfo.get(i).add(MarketFactory.getMarketName(marketNumber)+"(Market "+ (marketNumber) +")");
        }
    }
    public static void addInfo(int marketId,String buyerId){
        if(marketId!=0){
            marketInfo.get(marketId-1).add(buyerId);
        }
    }
    public static void displayPanel(){
        frame.setVisible(true);
        updatePanel();
    }
    public static void updatePanel(){
        for(int i=0;i<marketInfo.size();i++){
            ArrayList<String> infoLine=marketInfo.get(i);
            String display=infoLine.get(0)+"("+(infoLine.size()-1)+" buyers)"+": ";
            JLabel label=marketLabels.get(i);
            for(int j=1;j<infoLine.size();j++){
                String info=infoLine.get(j);
                display+="Buyer "+info+", ";
            }
            System.out.println(display);
            label.setText(display);
        }
        clearMarketInfo();
    }



}
