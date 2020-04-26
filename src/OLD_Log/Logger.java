package Log;

import java.util.ArrayList;

public class Logger {
    private ArrayList<ArrayList<String>> data;
    private ArrayList<String> headers;

    public Logger(ArrayList<String> headers) {
        data = new ArrayList<>();
        this.headers = headers;
    }

    public void addLog(ArrayList<String> record) {
        data.add(record);
    }

    public void writeLog() {
        Reporter.writeReport(dump());
    }


    public ArrayList<ArrayList<String>> dump() {
        data.add(0, headers);
        return data;
    }
}
