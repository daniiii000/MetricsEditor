package com.example.metricseditor.files;

import com.example.metricseditor.Condition;
import com.example.metricseditor.Modifier;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class FileOperations {

    public static void createProperties(String name, String description, String object, String pattern, String subject, String teamextension, List<Condition> conditionList) throws IOException {
        String filename = name.toLowerCase();
        filename = filename.replace(" ", "_");
        File file = new File("/Users/danie/archivos/" + filename + "_template.properties");
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
            if (subject.equals("Individual") && teamextension.equals("Standard Deviation")) {
                if (!conditionList.isEmpty()) {
                    for (int i = 0; i < conditionList.size(); ++i) {
                        if (conditionList.get(i).getType().equals("Self") && (conditionList.get(i).getCondition_attribute().equals("Commiter") || conditionList.get(i).getCondition_attribute().equals("Responsible"))) {
                            bw.write("name=[USERNAME] " + name);
                        }
                    }
                }
                else bw.write("name=" + name);
            }
            else bw.write("name=" + name);
            bw.newLine();
            bw.write("description=" + description);
            bw.newLine();
            bw.write("factors=none");
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
            if (object.equals("Task")) partial = "task";
            else if (object.equals("User Story")) partial = "userstory";
            else if (object.equals("Commit")) partial = "commit";

            String variable = "";
            String variable2 = "";

            String obj = "";
            if (object.equals("Task")) obj = object.toLowerCase() + "s";
            else if (object.equals("User Story")) obj = "userstories";
            else if (object.equals("Commit")) obj = object.toLowerCase() + "s";


            if (pattern.equals("Percentage")) {
                if(object.equals("Commit")) {
                    variable = "repo_commits";
                    variable2 = "commits";
                    bw.write("result." + total);
                    bw.write("=aggregations." + variable + ".value");
                    bw.newLine();
                    bw.write("result." + partial);
                    bw.write("=aggregations.user." + variable2 + ".value");
                }
                else {
                    if (subject.equals("Individual") && teamextension.equals("Standard Deviation")) {
                        if (!conditionList.isEmpty()) {
                            for (int i = 0; i < conditionList.size(); ++i) {
                                if (conditionList.get(i).getType().equals("Self") && (conditionList.get(i).getCondition_attribute().equals("Commiter") || conditionList.get(i).getCondition_attribute().equals("Responsible"))) {
                                    bw.write("result." + total);
                                    bw.write("=aggregations." + partial + ".doc_count");
                                }
                                else {
                                    bw.write("result." + total);
                                    bw.write("=hits.total");
                                }
                            }
                        }
                        else {
                            bw.write("result." + total);
                            bw.write("=hits.total");
                        }
                    }
                    else {
                        bw.write("result." + total);
                        bw.write("=hits.total");
                    }
                    variable = "";
                    bw.newLine();
                    bw.write("result." + partial);
                    bw.write("=aggregations." + variable + ".doc_count");
                }
                bw.newLine();
                bw.newLine();
            }
            else if (pattern.equals("Standard Deviation")) {
                if (object.equals("Task") || object.equals("User Story")) {
                    bw.write("result." + total);
                    bw.write("=hits.total");
                    bw.newLine();
                    bw.write("[BEGIN_RESULTS]"); bw.newLine();
                    bw.write("result." + obj + "[ELASTIC_USERNAME]=");
                    bw.write("aggregations.[ELASTIC_USERNAME]_" + obj + ".doc_count");
                    bw.newLine();
                    bw.write("[END_RESULTS]"); bw.newLine();
                }
                else if (object.equals("Commit")) {
                    bw.write("result." + total);
                    bw.write("=aggregations.repo_commits.doc_count");
                    bw.newLine();
                    bw.write("[BEGIN_RESULTS]"); bw.newLine();
                    bw.write("result." + obj + "[ELASTIC_USERNAME]=");
                    bw.write("aggregations.[ELASTIC_USERNAME]_" + obj + ".doc_count");
                    bw.newLine();
                    bw.write("[END_RESULTS]"); bw.newLine();
                }
            }
            else if (pattern.equals("Frequency")) {
                variable = "";
                bw.write("result." + partial);
                bw.write("=aggregations." + variable + ".doc_count");
                bw.newLine();
                bw.newLine();
            }

            bw.write("# metric defines a formula based on execution results of parameter- and metric-queries");
            bw.newLine();

            if (pattern.equals("Percentage")) bw.write("metric= " + partial + " / " + total);
            else if (pattern.equals("Standard Deviation")) bw.write("metric=[SD_METRIC]");
            else if (pattern.equals("Frequency")) bw.write("metric= " + partial);

            bw.newLine();
            bw.write("onError=set0");

        bw.close();
    }

    public static void createQueries(String name, String object, String pattern, List<Modifier> modifierList, List<Condition> conditionList, String subject, String teamextension) throws IOException {
        String filename = name.toLowerCase();
        filename = filename.replace(" ", "_");

        String aggsname = "";

        String aggsobject = "";
        if (object.equals("Task")) aggsobject = object.toLowerCase() + "s";
        else if (object.equals("User Story")) aggsobject = "userstories";
        else if (object.equals("Commit")) aggsobject = object.toLowerCase() + "s";

        File file = new File("/Users/danie/archivos/" + filename + "_template.query");
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(file));
        if (pattern.equals("Percentage")) {
            bw.write("{");
            bw.newLine();
            bw.write("  \"size\" : 0,");
            bw.newLine();
            bw.write("  \"query\":  {");
            bw.newLine();
            bw.write("   \"bool\": {");
            bw.newLine();
            bw.write("     \"must\":");
            if (object.equals("Task") || object.equals("User Story")) {
                if (subject.equals("Individual") && teamextension.equals("Standard Deviation")) {
                    if (!conditionList.isEmpty()) {
                        for (int i = 0; i < conditionList.size(); ++i) {
                            if (conditionList.get(i).getType().equals("Self") && (conditionList.get(i).getCondition_attribute().equals("Commiter") || conditionList.get(i).getCondition_attribute().equals("Responsible"))) {
                                bw.write(" {"); bw.newLine();
                                if (modifierList.isEmpty()) {
                                    bw.write("      \"match_all: {}\"");
                                }
                                else {
                                    bw.write("       \"match: { \"assigned\": \"[USERNAME]\" }\"");
                                }
                                bw.newLine();
                                bw.write("     }"); bw.newLine();
                                bw.write("    }"); bw.newLine();
                                bw.write("   },"); bw.newLine();
                                bw.write("  \"aggs\": {"); bw.newLine();
                                bw.write("   \"total_"+ aggsobject +"\""); bw.newLine();
                                bw.write("    \"filter\": {"); bw.newLine();
                                bw.write("     \"match_all: {}\""); bw.newLine();
                                bw.write("    }"); bw.newLine();
                                bw.write("   },"); bw.newLine();
                                bw.write("   \"" + aggsname + "_" + aggsobject + "\": {"); bw.newLine();
                                bw.write("    \"filter\": {"); bw.newLine();
                                bw.write("     \"bool\": {"); bw.newLine();
                                bw.write("      \"must\": ["); bw.newLine();
                                if (modifierList.isEmpty()) {
                                    bw.write("      { \"match\": { \"assigned\": \"[USERNAME]\" }} ");
                                }
                                else {
                                    for (int j = 0; j < modifierList.size(); ++j) {
                                        if (modifierList.get(j).getType().equals("State") && modifierList.get(j).getModifier_attribute().equals("Closed")) {
                                            bw.write("        { \"match\": { \"is_closed\": true } } ");
                                        }
                                    }
                                }
                                bw.write("]");
                                bw.newLine();
                                bw.write("      }");
                                bw.newLine();
                                bw.write("     }");
                                bw.newLine();
                                bw.write("    }");
                                bw.newLine();
                                bw.write("  }");
                                bw.newLine();
                                bw.write("}");

                            }
                        }
                    }
                }
                else {

                    bw.write(" [");
                    bw.newLine();
                    bw.write("       {\"match\": { \"milestone_closed\": false} }");

                    if (!modifierList.isEmpty()) {
                        for (int i = 0; i < modifierList.size(); ++i) {
                            if (!modifierList.get(i).getType().equals("None")) {
                                bw.write(",");
                                bw.newLine();
                                if (modifierList.get(i).getType().equals("State") && modifierList.get(i).getModifier_attribute().equals("Closed")) {
                                    bw.write("{        {\"match\":  { \"is_closed\": true}}");
                                }
                            }
                        }
                    }
                    bw.write("]");
                    bw.newLine();
                    bw.write("}");
                    bw.newLine();
                    bw.write("},");
                    bw.newLine();

                    bw.write("  \"aggs\": {");
                    bw.newLine();
                    bw.write("   \"" + aggsname + "_" + aggsobject + "\": {");
                    bw.newLine();
                    bw.write("    \"filter\": {");
                    bw.newLine();
                    bw.write("      \"bool\" : {");
                    bw.newLine();
                    bw.write("        \"must\": [");
                    bw.newLine();
                    bw.write("          {\"match\": { \"milestone_closed\": false} }");
                    if (!conditionList.isEmpty()) {
                        for (int i = 0; i < conditionList.size(); ++i) {
                            bw.write(",");
                            bw.newLine();
                            if (conditionList.get(i).getType().equals("Defined")) {
                                if (conditionList.get(i).getCondition_attribute().equals("Estimated effort")) {
                                    bw.write("          { \"exists\": {\"field\": \"estimated_effort\"}}");
                                } else if (conditionList.get(i).getCondition_attribute().equals("Actual effort")) {
                                    bw.write("          { \"exists\": {\"field\": \"actual_effort\"}}");
                                }
                            }
                        }
                    }
                    if (!modifierList.isEmpty()) {
                        for (int i = 0; i < modifierList.size(); ++i) {
                            if (!modifierList.get(i).getType().equals("None")) {
                                bw.write(",");
                                bw.newLine();
                                if (modifierList.get(i).getType().equals("State") && modifierList.get(i).getModifier_attribute().equals("Closed")) {
                                    bw.write("          { \"match\":  { \"is_closed\": true}}");
                                }
                            }
                        }
                    }
                    bw.newLine();
                    bw.write("       }");
                    bw.newLine();
                    bw.write("     }");
                    bw.newLine();
                    bw.write("   }");
                    bw.newLine();
                    bw.write("  }");
                    bw.newLine();
                    bw.write("}");
                }

            } else if (object.equals("Commit")) {
                bw.write(" {"); bw.newLine();
                bw.write("\"match_all\" {}"); bw.newLine();
                bw.write("     }"); bw.newLine();
                bw.write("    }"); bw.newLine();
                bw.write("   },"); bw.newLine();
                bw.write("");

                bw.write("  \"aggs\": {");
                bw.newLine();
                bw.write("   \"repo_commits\": {");
                bw.newLine();
                bw.write("    \"filter\": {");
                bw.newLine();
                bw.write("      \"bool\" : {");
                bw.newLine();
                bw.write("        \"must\": [");
                bw.newLine();
                bw.write("[BEGIN]{ \"match\": { \"repository\": \"[REPO_NAME]\" }}[END]");
                bw.newLine();
                bw.write("],");
                bw.newLine();

            }
        }
        else if (pattern.equals("Standard Deviation")) {
            if (object.equals("Task") || object.equals("User Story")) {

            }
            else if (object.equals("Commit")) {

            }
        }
        else if (pattern.equals("Frequency")) {

        }

        bw.close();
    }

    public static void deleteProperties(String name) {
        String filename_aux = name.toLowerCase();
        filename_aux = filename_aux.replace(" ", "_");
        String filename = "/Users/danie/archivos/" + filename_aux + "_template.properties";
        Path path = Paths.get(filename);
        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("File" + name + "_template.properties is deleted!");
            } else {
                System.out.println("Sorry, could not delete the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteQueries(String name) {
        String filename_aux = name.toLowerCase();
        filename_aux = filename_aux.replace(" ", "_");
        String filename = "/Users/danie/archivos/" + filename_aux + "_template.query";
        Path path = Paths.get(filename);
        try {
            boolean result = Files.deleteIfExists(path);
            if (result) {
                System.out.println("File" + name + "_template.query is deleted!");
            } else {
                System.out.println("Sorry, could not delete the file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readProperties(String name) throws Exception {
            String filename_aux = name.toLowerCase();
            filename_aux = filename_aux.replace(" ", "_");
            File file = new File("/Users/danie/archivos/" + filename_aux + "_template.properties");

            String text = "";

            try {
                BufferedReader obj = new BufferedReader(new FileReader(file));

                String temp = "";
                String string;
                while ((string = obj.readLine()) != null) {
                    temp = temp + string;
                }

                text = temp;
            }
            catch (Exception e) {
                System.err.println("File not found");
            }
            return text;
    }

    public static void readQueries(String name) throws Exception {
            String filename_aux = name.toLowerCase();
            filename_aux = filename_aux.replace(" ", "_");
            File file = new File("/Users/danie/archivos/" + filename_aux + "_template.query");


            BufferedReader obj = new BufferedReader(new FileReader(file));

            String string;
            while ((string = obj.readLine()) != null)
                System.out.println(string);
    }
}
