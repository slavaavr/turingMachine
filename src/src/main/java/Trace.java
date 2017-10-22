package src.main.java;

import java.io.FileWriter;
import java.io.IOException;

public class Trace {
    private FileWriter fw;
    private int countWhiteSpaces;

    public Trace(String outputFile) {
        try {
            fw = new FileWriter(outputFile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(Table table, String str) throws IOException {
        char[] example = str.toCharArray();
        String state = "q1";
        String[] ceil;
        do {
            fw.write(example);
            ceil = table.getCeil(String.valueOf(example[countWhiteSpaces]), state);
            example[countWhiteSpaces] = ceil[0].charAt(0);
            printWhiteSpaces(ceil[1]);
            state = ceil[2];
        } while (!state.equals("stop"));

    }

    private void printWhiteSpaces(String pattern) throws IOException {
        if (pattern.equals(">")) {
            fw.write("\n");
            for (int i = 0; i < countWhiteSpaces; i++) {
                fw.write(" ");
            }
            fw.write("^\n");
            countWhiteSpaces++;
        } else if (pattern.equals("<")) {
            fw.write("\n");
            for (int i = 0; i < countWhiteSpaces; i++) {
                fw.write(" ");
            }
            fw.write("^\n");
            countWhiteSpaces--;
        }
        fw.flush();
    }
}