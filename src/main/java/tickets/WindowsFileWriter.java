package tickets;

import java.io.*;

/**
 * Created by taldo on 23/08/2017.
 */
public class WindowsFileWriter {
    Writer writer;
    private static String REPORT_PATH = "C:\\tickets\\reports\\report.txt";


    public void write(String text){
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(REPORT_PATH), "utf-8"));
            writer.write(text);
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
