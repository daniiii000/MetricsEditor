package com.example.metricseditor.files;

import com.example.metricseditor.models.Condition;
import com.example.metricseditor.models.Modifier;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class FileOperations {

    public static void createProperties(String name, String description, String object, String pattern, String subject, String teamextension, List<Condition> conditionList, List<Modifier> modifierList) throws IOException {
        String filename = name.toLowerCase();
        filename = filename.replace(" ", "_");
        Path path = Paths.get("/Users/danie/archivos/" + Paths.get(filename) + "_template.properties");
        File file = new File(String.valueOf(path));
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
                        if ((conditionList.get(i).getType().equals("Self") && (conditionList.get(i).getCondition_attribute().equals("Committer") || conditionList.get(i).getCondition_attribute().equals("Responsible")))
                           ||(conditionList.get(i).getType().equals("State") && conditionList.get(i).getCondition_attribute().equals("Closed"))) {
                            bw.write("name=[USERNAME] " + name);
                        }
                        else bw.write("name=" + name);
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
            if (pattern.equals("Percentage")) variable = "percentage";
            else if (pattern.equals("Standard Deviation")) variable = "standard_deviation";
            else if (pattern.equals("Frequency")) variable = "frequency";

            String variable2 = "";

            String obj = "";
            if (object.equals("Task")) obj = object.toLowerCase() + "s";
            else if (object.equals("User Story")) obj = "userstories";
            else if (object.equals("Commit")) obj = object.toLowerCase() + "s";


            if (pattern.equals("Percentage")) {
                if(object.equals("Commit")) {
                    if (!conditionList.isEmpty()) {
                        for (int i = 0; i < conditionList.size(); ++i) {
                            if (conditionList.get(i).getType().equals("Self") && (conditionList.get(i).getCondition_attribute().equals("Committer"))) {
                                if (!modifierList.isEmpty()) {
                                    for (int j = 0; j < modifierList.size(); ++j) {
                                        if (modifierList.get(j).getType().equals("Sum") && (modifierList.get(j).getModifier_attribute().equals("Lines of code"))) {
                                            variable2 = "user";
                                            bw.write("result." + total);
                                            bw.write("=aggregations." + variable + "_" + obj + ".value");
                                            bw.newLine();
                                            bw.write("result." + partial);
                                            bw.write("=aggregations.user." + variable2 + "_" + obj + ".value");
                                        }
                                        else {
                                            variable2 = "repo_commits";
                                            bw.write("result." + total);
                                            bw.write("=aggregations." + variable2 + ".doc_count");
                                            bw.newLine();
                                            bw.write("result." + partial);
                                            bw.write("=aggregations." + variable + "_" + obj + ".doc_count");
                                        }
                                    }
                                }
                                else {
                                    variable2 = "repo_commits";
                                    bw.write("result." + total);
                                    bw.write("=aggregations." + variable2 + ".doc_count");
                                    bw.newLine();
                                    bw.write("result." + partial);
                                    bw.write("=aggregations." + variable + "_" + obj + ".doc_count");
                                }
                            }
                            else if (conditionList.get(i).getType().equals("Not Defined") && (conditionList.get(i).getCondition_attribute().equals("Committer"))) {
                                bw.write("result." + total);
                                bw.write("=hits.total");
                                bw.newLine();
                                bw.write("result." + partial);
                                bw.write("=aggregations." + variable + "_" + obj + ".doc_count");
                            }
                            else {
                                variable2 = "repo_commits";
                                bw.write("result." + total);
                                bw.write("=aggregations." + variable2 + ".doc_count");
                                bw.newLine();
                                bw.write("result." + partial);
                                bw.write("=aggregations." + variable + "_" + obj + ".doc_count");
                            }
                        }
                    }
                }
                else {
                    if (subject.equals("Individual") && teamextension.equals("Standard Deviation")) {
                        if (!conditionList.isEmpty()) {
                            for (int i = 0; i < conditionList.size(); ++i) {
                                if (conditionList.get(i).getType().equals("Self") && conditionList.get(i).getCondition_attribute().equals("Responsible")) {
                                    variable2 = "self";
                                    bw.write("result." + total);
                                    bw.write("=aggregations." + variable2 + "_" + obj + ".doc_count");
                                }
                                else if (conditionList.get(i).getType().equals("State") && conditionList.get(i).getCondition_attribute().equals("Closed")) {
                                    variable2 = "state";
                                    bw.write("result." + total);
                                    bw.write("=aggregations." + variable2 + "_" + obj + ".doc_count");
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
                    bw.newLine();
                    bw.write("result." + partial);
                    bw.write("=aggregations." + variable + "_" + obj + ".doc_count");
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
                    bw.write("result." + partial + "[ELASTIC_USERNAME]=");
                    bw.write("aggregations.[ELASTIC_USERNAME]_" + obj + ".doc_count");
                    bw.newLine();
                    bw.write("[END_RESULTS]"); bw.newLine();
                    bw.newLine();
                }
                else if (object.equals("Commit")) {
                    bw.write("result." + total);
                    bw.write("=aggregations.repo_commits.doc_count");
                    bw.newLine();
                    bw.write("[BEGIN_RESULTS]"); bw.newLine();
                    bw.write("result." + partial + "[ELASTIC_USERNAME]=");
                    bw.write("aggregations.[ELASTIC_USERNAME]_" + obj + ".doc_count");
                    bw.newLine();
                    bw.write("[END_RESULTS]"); bw.newLine();
                    bw.newLine();
                }
            }
            else if (pattern.equals("Frequency")) {
                bw.write("result." + partial);
                bw.write("=aggregations." + variable + "_" + obj + ".doc_count");
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

    public static void createQueries(String name, String object, String pattern, List<Modifier> modifierList, List<Condition> conditionList, String subject, String teamextension, String count_type, String count_attribute) throws IOException {
        String filename = name.toLowerCase();
        filename = filename.replace(" ", "_");
        Path path = Paths.get("/Users/danie/archivos/" + Paths.get(filename) + "_template.query");

        String variable = "";
        if (pattern.equals("Percentage")) variable = "percentage";
        else if (pattern.equals("Standard Deviation")) variable = "standard_deviation";
        else if (pattern.equals("Frequency")) variable = "frequency";

        String variable2 = "";
        if (!conditionList.isEmpty()) {
            for (int i = 0; i < conditionList.size(); ++i) {
                if (conditionList.get(i).getType().equals("Self") && (conditionList.get(i).getCondition_attribute().equals("Committer"))) {
                    if (!modifierList.isEmpty()) {
                        for (int j = 0; j < modifierList.size(); ++j) {
                            if (modifierList.get(j).getType().equals("Sum") && (modifierList.get(j).getModifier_attribute().equals("Lines of code"))) {
                                variable2 = "user";
                            }
                            else variable2 = "repo_commits";
                        }
                    }
                    else variable2 = "repo_commits";
                }
                else if (conditionList.get(i).getType().equals("Self") && (conditionList.get(i).getCondition_attribute().equals("Responsible"))) {
                    variable2 = "self";
                }
                else if (conditionList.get(i).getType().equals("State") && conditionList.get(i).getCondition_attribute().equals("Closed")) {
                    variable2 = "state";
                }
                else variable2 = "repo_commits";
            }
        }

        String obj = "";
        if (object.equals("Task")) obj = object.toLowerCase() + "s";
        else if (object.equals("User Story")) obj = "userstories";
        else if (object.equals("Commit")) obj = object.toLowerCase() + "s";

        File file = new File(String.valueOf(path));
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(file));

        bw.write("{");
        bw.newLine();
        bw.write("  \"size\" : 0,");
        bw.newLine();
        bw.write("  \"query\":  {");
        bw.newLine();
        bw.write("   \"bool\": {");
        bw.newLine();
        bw.write("     \"must\":");

        if (pattern.equals("Percentage")) {
            if (object.equals("Task") || object.equals("User Story")) {
                if (subject.equals("Individual") && teamextension.equals("Standard Deviation")) {
                    if (!conditionList.isEmpty()) {
                        for (int i = 0; i < conditionList.size(); ++i) {
                            if ((conditionList.get(i).getType().equals("Self") && conditionList.get(i).getCondition_attribute().equals("Responsible"))
                               ||(conditionList.get(i).getType().equals("State") && conditionList.get(i).getCondition_attribute().equals("Closed"))){
                                bw.write(" {");
                                bw.newLine();
                                if (!modifierList.isEmpty()) {
                                    for (int j = 0; j < modifierList.size(); ++j) {
                                        if (modifierList.get(j).getType().equals("None") && !conditionList.get(i).getType().equals("State")) {
                                            bw.write("      \"match_all: {}\"");
                                        } else {
                                            bw.write("       \"match\": { \"assigned\": \"[USERNAME]\" }");
                                        }
                                    }
                                }
                                bw.newLine();
                                bw.write("     }");
                                bw.newLine();
                                bw.write("    }");
                                bw.newLine();
                                bw.write("   },");
                                bw.newLine();
                                bw.write("  \"aggs\": {");
                                bw.newLine();
                                bw.write("   \"" + variable2 + "_" + obj + "\"");
                                bw.newLine();
                                bw.write("    \"filter\": {");
                                bw.newLine();
                                bw.write("     \"match_all: {}\"");
                                bw.newLine();
                                bw.write("    }");
                                bw.newLine();
                                bw.write("   },");
                                bw.newLine();
                                bw.write("   \"" + variable + "_" + obj + "\": {");
                                bw.newLine();
                                bw.write("    \"filter\": {");
                                bw.newLine();
                                bw.write("     \"bool\": {");
                                bw.newLine();
                                bw.write("      \"must\": [");
                                bw.newLine();
                                if (!modifierList.isEmpty()) {
                                    for (int j = 0; j < modifierList.size(); ++j) {
                                        if (modifierList.get(j).getType().equals("None") && !conditionList.get(i).getType().equals("State")) {
                                            bw.write("      { \"match\": { \"assigned\": \"[USERNAME]\" }} ");
                                        }
                                        else if ((modifierList.get(j).getType().equals("State") && modifierList.get(j).getModifier_attribute().equals("Closed"))
                                                ||(conditionList.get(i).getType().equals("State") && conditionList.get(i).getCondition_attribute().equals("Closed"))) {
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
                } else {

                    bw.write(" [");
                    bw.newLine();
                    bw.write("       {\"match\": { \"milestone_closed\": false} }");

                    if (!modifierList.isEmpty()) {
                        for (int i = 0; i < modifierList.size(); ++i) {
                            if (!modifierList.get(i).getType().equals("None")) {
                                bw.write(",");
                                bw.newLine();
                                if (modifierList.get(i).getType().equals("State") && modifierList.get(i).getModifier_attribute().equals("Closed")) {
                                    bw.write("       {\"match\":  { \"is_closed\": true}}");
                                }
                            }
                        }
                    }
                    bw.write("]");
                    bw.newLine();
                    bw.write("   }");
                    bw.newLine();
                    bw.write("  },");
                    bw.newLine();

                    bw.write("  \"aggs\": {");
                    bw.newLine();
                    bw.write("   \"" + variable + "_" + obj + "\": {");
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
                            else if(object.equals("User Story") && conditionList.get(i).getType().equals("Apply") && conditionList.get(i).getCondition_attribute().equals("Check template US")) {
                                bw.write("          {\"match\":  { \"pattern\":  true}}");
                            }
                            else if(object.equals("User Story") && conditionList.get(i).getType().equals("Card") && conditionList.get(i).getCondition_attribute().equals("Acceptance criteria")) {
                                bw.write("          {\"match\":  { \"acceptance_criteria\":  true}}");
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
                    bw.write("        ]");
                    if (!conditionList.isEmpty()) {
                        for (int i = 0; i < conditionList.size(); ++i) {
                            if (conditionList.get(i).getType().equals("Not Defined") && conditionList.get(i).getCondition_attribute().equals("Responsible")) {
                                bw.write(",");
                                bw.newLine();
                                bw.write("         \"must_not\": {");
                                bw.newLine();
                                bw.write("          \"exists\": {");
                                bw.newLine();
                                bw.write("           \"field\": \"assigned\"");
                                bw.newLine();
                                bw.write("         }");
                                bw.newLine();
                                bw.write("        }");
                                bw.newLine();
                            }
                        }
                    }
                    bw.newLine();
                    bw.write("      }");
                    bw.newLine();
                    bw.write("    }");
                    bw.newLine();
                    bw.write("   }");
                    bw.newLine();
                    bw.write("  }");
                    bw.newLine();
                    bw.write("}");
                }

            } else if (object.equals("Commit")) {
                if (!modifierList.isEmpty()) {
                    for (int j = 0; j < modifierList.size(); ++j) {
                        if (modifierList.get(j).getType().equals("Sum") && modifierList.get(j).getModifier_attribute().equals("Lines of code")) {
                            bw.write(" [");
                            bw.newLine();
                            bw.write("         [BEGIN]{ \"match\": { \"repository\": \"[REPO_NAME]\" }}[END]");
                            bw.newLine();
                            bw.write("         ],");
                            bw.newLine();
                            bw.write("        \"must_not\": [");
                            bw.newLine();
                            bw.write("           { \"match\": { \"user.login\": \"anonymous\" }}]");
                            bw.newLine();
                            bw.write("    }");
                            bw.newLine();
                            bw.write("   },");
                            bw.newLine();
                            bw.write("  \"aggs\": {");
                            bw.newLine();
                            bw.write("   \"" + variable + "_" + obj + "\": {");
                            bw.newLine();
                            bw.write("      \"sum\": {");
                            bw.newLine();
                            bw.write("        \"field\": \"stats.total\"");
                            bw.newLine();
                            bw.write("       }");
                            bw.newLine();
                            bw.write("    },");
                            bw.newLine();

                            bw.write("    \"user\": {");
                            bw.newLine();
                            bw.write("      \"filter\": {");
                            bw.newLine();
                            bw.write("        \"bool\" : {");
                            bw.newLine();
                            bw.write("          \"must\": [");
                            bw.newLine();
                            bw.write("            { \"match\": { \"user.login\": [USERNAME] }} ],");
                            bw.newLine();
                            bw.write("        }");
                            bw.newLine();
                            bw.write("       },");
                            bw.newLine();

                            bw.write("       \"aggs\": {");
                            bw.newLine();
                            bw.write("        \"" + variable2 + "_" + obj + "\": {");
                            bw.newLine();
                            bw.write("          \"sum\": {");
                            bw.newLine();
                            bw.write("            \"field\": \"stats.total\"");
                            bw.newLine();
                            bw.write("          }");
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
                        else if (modifierList.get(j).getType().equals("None")) {
                            if (!conditionList.isEmpty()) {
                                for (int i = 0; i < conditionList.size(); ++i) {
                                    if (conditionList.get(i).getType().equals("Not Defined") && conditionList.get(i).getCondition_attribute().equals("Committer")) {
                                        bw.write(" [");
                                        bw.newLine();
                                        bw.write("         [BEGIN]{ \"match\": { \"repository\": \"[REPO_NAME]\" }}[END]");
                                        bw.newLine();
                                        bw.write("         ]");
                                        bw.newLine();
                                        bw.write("    }");
                                        bw.newLine();
                                        bw.write("   },");
                                        bw.newLine();
                                        bw.write("  \"aggs\": {");
                                        bw.newLine();
                                        bw.write("   \"" + variable + "_" + obj + "\": {");
                                        bw.newLine();
                                        bw.write("    \"filter\": {");
                                        bw.newLine();
                                        bw.write("      \"bool\" : {");
                                        bw.newLine();
                                        bw.write("        \"must\": [");
                                        bw.newLine();
                                        bw.write("         { \"match\": { \"user.login\": \"anonymous\" }} ]");
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
                                    else {
                                        bw.write(" {");
                                        bw.newLine();
                                        bw.write("      \"match_all\" {}");
                                        bw.newLine();
                                        bw.write("     }");
                                        bw.newLine();
                                        bw.write("    }");
                                        bw.newLine();
                                        bw.write("   },");
                                        bw.newLine();

                                        bw.write("  \"aggs\": {");
                                        bw.newLine();
                                        bw.write("   \"" + variable2 + "\": {");
                                        bw.newLine();
                                        bw.write("    \"filter\": {");
                                        bw.newLine();
                                        bw.write("      \"bool\" : {");
                                        bw.newLine();
                                        bw.write("        \"must\": [");
                                        bw.newLine();
                                        bw.write("         [BEGIN]{ \"match\": { \"repository\": \"[REPO_NAME]\" }}[END]");
                                        bw.newLine();
                                        bw.write("         ],");
                                        bw.newLine();
                                        bw.write("        \"must_not\": [");
                                        bw.newLine();
                                        bw.write("           { \"match\": { \"user.login\": \"anonymous\" }}]");
                                        bw.newLine();
                                        bw.write("");
                                        bw.write("     }");
                                        bw.newLine();
                                        bw.write("    }");
                                        bw.newLine();
                                        bw.write("   },");
                                        bw.newLine();
                                        bw.write("   \"" + variable + "_" + obj + "\": {");
                                        bw.newLine();
                                        bw.write("    \"filter\": {");
                                        bw.newLine();
                                        bw.write("      \"bool\" : {");
                                        bw.newLine();
                                        bw.write("        \"must\": [");
                                        bw.newLine();
                                        if (conditionList.get(i).getType().equals("Apply") && conditionList.get(i).getCondition_attribute().equals("Check task reference")) {
                                            bw.write("         { \"match\": { \"task_is_written\": true }} ],");
                                            bw.newLine();
                                            bw.write("              \"must_not\": [");
                                            bw.newLine();
                                            bw.write("         { \"match\": { \"user.login\": \"anonymous\"}}]");

                                        } else if (conditionList.get(i).getType().equals("Self") && conditionList.get(i).getCondition_attribute().equals("Committer")) {

                                            bw.write("         { \"match\": { \"user.login\": [USERNAME] }} ],");
                                            bw.newLine();
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
                                }
                            }
                        }
                    }
                }
            }
        }
        else if (pattern.equals("Standard Deviation")) {
            bw.write(" {"); bw.newLine();

            if (object.equals("Task") || object.equals("User Story")) {
                bw.write("       \"exists\": {"); bw.newLine();
                bw.write("         \"field\": \"assigned\"");

                bw.newLine();
                bw.write("       }");
                bw.newLine();
                bw.write("     }");
                bw.newLine();
                bw.write("   }");
                bw.newLine();
                bw.write("  },");
                bw.newLine();

                bw.write("  \"aggs\": {");
                bw.newLine();
                bw.write("   \"" + variable + "_" + obj + "\": {");
                bw.newLine();
                bw.write("    \"filter\": {");
                bw.newLine();
                bw.write("     \"match_all\": {}");
                bw.newLine();
                bw.write("    }"); bw.newLine();
                bw.write("   },");
                bw.newLine();
                bw.write("   [AGG_BEGIN],"); bw.newLine();
                bw.write("  \"[ELASTIC_USERNAME]_" + obj + "\": {");
                bw.newLine();
                bw.write("    \"filter\": {");
                bw.newLine();
                bw.write("      \"bool\" : {");
                bw.newLine();
                bw.write("        \"must\":");
                bw.newLine();
                bw.write("          { \"match\": { \"assigned\": \"[USERNAME]\" } }");
                bw.newLine();
                bw.write("        }");
                bw.newLine();
                bw.write("      }");
                bw.newLine();
                bw.write("    },");
                bw.newLine();
                bw.write("    [AGG_END]"); bw.newLine();
                bw.write("  }"); bw.newLine();
                bw.write("}");

            }
            else if (object.equals("Commit")) {
                bw.write("     \"match_all\": {}");
                bw.newLine();
                bw.write("    }"); bw.newLine();
                bw.write("   }");
                bw.newLine();
                bw.write("  },");
                bw.newLine();
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
                bw.write("         [BEGIN]{ \"match\": {\"repository\": \"[REPO_NAME]\" }}[END]");
                bw.newLine();
                bw.write("        ],");
                bw.newLine();
                bw.write("        \"must_not\": [");
                bw.newLine();
                bw.write("          { \"match\": { \"user.login\": \"anonymous\"}}]");
                bw.newLine();
                bw.write("       }");
                bw.newLine();
                bw.write("     }");
                bw.newLine();
                bw.write("   },");
                bw.newLine();
                bw.write("   [AGG_BEGIN],");
                bw.newLine();
                bw.write("   \"[ELASTIC_USERNAME]_" + obj + "\": {");
                bw.newLine();
                bw.write("     \"filter\": {");
                bw.newLine();
                bw.write("      \"bool\" : {");
                bw.newLine();
                bw.write("       \"must\": [");
                bw.newLine();
                bw.write("         { \"match\": { \"user.login\": \"[USERNAME]\" }} ]");
                bw.newLine();
                bw.write("      }");
                bw.newLine();
                bw.write("     }");
                bw.newLine();
                bw.write("   },");
                bw.newLine();
                bw.write("   [AGG_END]");
                bw.newLine();
                bw.write("  }");
                bw.newLine();
                bw.write("}");
            }
        }
        else if (pattern.equals("Frequency")) {
            bw.write(" [");
            bw.newLine();
            bw.write("       {\"match\": { \"milestone_closed\": false} }");

            if (count_type.equals("State") && count_attribute.equals("Closed")) {
                bw.write(",");
                bw.newLine();
                bw.write("        {\"match\":  { \"is_closed\": true}}");
            }

            bw.write("]");
            bw.newLine();
            bw.write("   }");
            bw.newLine();
            bw.write("  },");
            bw.newLine();

            bw.write("  \"aggs\": {");
            bw.newLine();
            bw.write("   \"" + variable + "_" + obj + "\": {");
            bw.newLine();
            bw.write("    \"filter\": {");
            bw.newLine();
            bw.write("      \"bool\" : {");
            bw.newLine();
            bw.write("        \"must\": [");
            bw.newLine();
            bw.write("          {\"match\": { \"milestone_closed\": false} }");


            if (count_type.equals("State") && count_attribute.equals("Closed")) {
                bw.write(",");
                bw.newLine();
                bw.write("          { \"match\":  { \"is_closed\": true}}");
            }

            bw.write(" ]");
            bw.newLine();
            bw.write("        }");
            bw.newLine();
            bw.write("      }");
            bw.newLine();
            bw.write("    }");
            bw.newLine();
            bw.write("  }");
            bw.newLine();
            bw.write("}");
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
            e.getMessage();
            e.getCause();
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
                    temp = temp + string +"\n";
                }
                text = temp;
                obj.close();
            }
            catch (Exception e) {
                System.err.println("File not found");
            }
            return text;
    }

    public static String readQueries(String name) throws Exception {
            String filename_aux = name.toLowerCase();
            filename_aux = filename_aux.replace(" ", "_");
            File file = new File("/Users/danie/archivos/" + filename_aux + "_template.query");


        String text = "";

        try {
            BufferedReader obj = new BufferedReader(new FileReader(file));

            String temp = "";
            String string;
            while ((string = obj.readLine()) != null) {
                temp = temp + string +"\n";
            }
            text = temp;
            obj.close();
        }
        catch (Exception e) {
            System.err.println("File not found");
        }
        return text;
    }
}
