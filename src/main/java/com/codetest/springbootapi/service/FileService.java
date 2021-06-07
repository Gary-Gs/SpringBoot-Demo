package com.codetest.springbootapi.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FileService {

    static ConcurrentHashMap<String, Boolean> processedFiles = new ConcurrentHashMap<>();
    static List<String> tagNumbers;

    // process file in specific DIR and output to specific DIR
    public void process() throws IOException {
        System.out.println("Program start ...");
        ExecutorService executorService = Executors.newCachedThreadPool();

        // request tag number from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter tag number needed in the format of: number,number,number....");
        String input = scanner.nextLine();
        tagNumbers = Arrays.asList(input.split(","));
        for (ListIterator<String> iter = tagNumbers.listIterator(); iter.hasNext(); ) {
            int line = Integer.parseInt(iter.next());
            if (line < 7 && line > 0) {
                iter.set("tag" + line);
            } else {
                System.out.println("Invalid tag number");
                return;
            }
        }

        // DIR
        String currentDir = Path.of("").toAbsolutePath().toString();
        String readyIndicatorDir = currentDir + "/input/ctl/";

        // create DIR
        Files.createDirectories(Paths.get(currentDir + "/output/data"));
        Files.createDirectories(Paths.get(currentDir + "/output/ctl"));

        while (true) {
            // get list of files
            File folder = new File(readyIndicatorDir);
            File[] listOfFiles = folder.listFiles();

            // find new files which are not processed
            if (listOfFiles == null) {
                continue;
            }
            for (File file : listOfFiles) {
                if (!processedFiles.containsKey(file.getAbsolutePath())) {
                    // get corresponding file path
                    String filePath = file.getAbsolutePath()
                            .replace("\\ctl", "\\data")
                            .replace(".ctl", ".txt");
                    // process them with threads
                    executorService.execute(new FileWorkerThread(filePath, tagNumbers));
                    processedFiles.put(file.getAbsolutePath(), true);
                }
            }
        }
    }
}
