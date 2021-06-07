package com.codetest.springbootapi.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileWorkerThread implements Runnable {

    private String fileName;
    private List<String> tagNumbers;

    FileWorkerThread(String filename, List<String> tagNumbers) {
        this.fileName = filename;
        this.tagNumbers = tagNumbers;
    }

    @Override
    public void run() {
        File file;
        PrintWriter writer = null;
        try {

            File f = new File(fileName);
            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";
            int line = 1;
            int fileNameCounter = 1;
            String fileNumber = fileName.split(".txt")[0]
                    .substring(fileName.split(".txt")[0].length() - 1, fileName.split("\\.txt")[0].length());

            while ((readLine = b.readLine()) != null) {

                // get processed results from user requested tag number
                String result = Arrays.stream(readLine.split("\\^A"))
                        .filter(tag -> tagNumbers.contains(tag.split("=")[0]))
                        .collect(Collectors.joining("^A"));

                // store result in file, create new file every 2 lines
                if (line % 2 != 0) {
                    file = new File(fileName.split("input")[0] + "output/data/file" + fileNumber + "-" + fileNameCounter + ".txt");
                    file.createNewFile();
                    writer = new PrintWriter(file);
                    writer.println(result);
                } else {
                    writer.println(result);
                    writer.close();
                    // create ctl file
                    file = new File(fileName.split("input")[0] + "output/ctl/file" + fileNumber + "-" + fileNameCounter + ".ctl");
                    file.createNewFile();
                    fileNameCounter++;
                }
                line++;
            }

            if (writer != null) {
                writer.close();
                file = new File(fileName.split("input")[0] + "output/ctl/file" + fileNumber + "-" + fileNameCounter + ".ctl");
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Processed file " + fileName);
    }
}
