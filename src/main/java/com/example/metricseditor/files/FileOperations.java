package com.example.metricseditor.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class FileOperations {

    public static void createProperties(String name, String object, String pattern) throws IOException {
        String filename = name.toLowerCase();
        filename = filename.replace(" ", "_");
        File file = new File("/Users/danie/archivos/" + filename + ".properties");
        BufferedWriter bw;

            bw = new BufferedWriter(new FileWriter(file));
            bw.write("# values starting with $$ are looked up in project.properties");
            bw.newLine();
            bw.write("index=$$");

            if (object.equals("Task")) bw.write("taiga.task.index");
            else if (object.equals("Commit")) bw.write("github.index");
            else if (object.equals("User Story")) bw.write("taiga.userstory.index");

            bw.newLine();
            bw.newLine();
            bw.write("# metric props");
            bw.newLine();
            bw.write("enabled=true");
            bw.newLine();
            bw.write("name=" + name);
            bw.newLine();
            bw.write("description=" + name);
            bw.newLine();
            bw.write("factors=");
            bw.newLine();
            bw.write("weights=1.0");
            bw.newLine();
            bw.newLine();
            bw.write("# query results");
            bw.newLine();

            String total = "";
            if (object.equals("Task")) total = object.toLowerCase() + "sTotal";
            else if (object.equals("User Story")) total = "userstoriesTotal";
            else if (object.equals("Commit")) total = object.toLowerCase() + "sTotal";

            String partial = "";
            if (object.equals("Task")) partial = object.toLowerCase() + "taskx";
            else if (object.equals("User Story")) partial = "userstoryx";
            else if (object.equals("Commit")) partial = "commitx";

            String variable = "hola";

            bw.write("result." + total); bw.write("=hits.total");
            bw.newLine();
            bw.write("result." + partial); bw.write("=aggregations." + variable + ".doc_count");
            bw.newLine();
            bw.newLine();
            bw.write("# metric defines a formula based on execution results of parameter- and metric-queries");
            bw.newLine();

            if (pattern.equals("Percentage")) bw.write("metric= " + partial + " / " + total);
            else if (pattern.equals("Standard Deviation")) bw.write("metric=[SD_METRIC]");
            else if (pattern.equals("Frequency")) bw.write("");

            bw.newLine();
            bw.write("onError=set0");

        bw.close();
    }

    public static void createQueries(String name) throws IOException {
        String filename = name.toLowerCase();
        filename = filename.replace(" ", "_");
        File file = new File("/Users/danie/archivos/" + filename + ".query");
        BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("");

        bw.close();
    }

    public static void deleteProperties(String name) {
        String filename = "/Users/danie/archivos/" + name + ".properties";
        Path path = Paths.get(filename);
        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("File" + name + ".properties is deleted!");
            } else {
                System.out.println("Sorry, could not delete the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteQueries(String name) {
        String filename = "/Users/danie/archivos/" + name + ".query";
        Path path = Paths.get(filename);
        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("File" + name + ".query is deleted!");
            } else {
                System.out.println("Sorry, could not delete the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
