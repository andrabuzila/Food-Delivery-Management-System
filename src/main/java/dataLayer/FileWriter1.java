package dataLayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriter1 {
    File file = new File("text.txt");

    public void createFile() throws IOException {
        file.createNewFile();
    }

    public void writeInFile(String s) throws IOException {
        try {
            FileWriter write = new FileWriter("D:\\PT_30229_Buzila_Andra_Assignment_4\\text.txt",true);
            BufferedWriter buffWr = new BufferedWriter(write);
            buffWr.append(s);
            buffWr.newLine();
            buffWr.close();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
