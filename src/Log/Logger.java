package Log;

import java.util.ArrayList;

public class Logger {
    private ArrayList<ArrayList<String>> data;

    public Logger(){
        data=new ArrayList<>();
    }

    public void addLog(ArrayList<String> record){
        data.add(record);
    }

    public void writeLog(){
        Reporter.writeReport(dump());
    }

    public  ArrayList<ArrayList<String>> dump(){
        return data;
    }
}
