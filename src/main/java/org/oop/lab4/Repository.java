package org.oop.lab4;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Repository {
    private final String path = "C:\\Users\\Arseniy\\IdeaProjects\\lab4\\repository\\output.json";
    private String text = "";

    private String readFile() {
        try {
            String content = Files.lines(Paths.get(path)).reduce("", String::concat);
            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertJO(JSONObject jsonObject) {
        String jo = jsonObject.toString();
        return jo;
    }

    private String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }

    private void write(Object value) {
        text += "\"" + value + "\"";
    }

    private void writeItem(Object key, Object value) {
        write(key);
        text += ": ";
        write(value);
        text += ", ";
    }

    private void writeSteps(List<Pair<String, String>> steps) {
        text += ", ";
        write("Steps");
        text += ": [";

        for (Pair<String, String> step : steps) {
            text += "(";
            write(step.first);
            text += ", ";
            write(step.second);
            text += "), ";
        }

        text = text.substring(0, text.length() - 2);
        text += "]";
    }

    public void saveSolution(Pair<Polynomial, Polynomial> input, Pair<Polynomial, Polynomial> solution) {
        String content = readFile();
        text += content.substring(0, content.length() - 1);
        text += ", ";
        write(getTime());
        text += ": ";
        text += "{";
        writeItem("Divided", input.first);
        writeItem("Divider", input.second);
        writeItem("Quotient", solution.first);
        writeItem("Remainder", solution.second);

        text = text.substring(0, text.length() - 2);
        text += "}}";
        try {
            FileWriter file = new FileWriter(path);
            file.write(text);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            text = "";
        }

    }

    public void saveSolutionWithSteps(List<Pair<String, String>> steps) {
        steps = new ArrayList<>(steps);
        Pair<String, String> input = steps.get(0);
        Pair<String, String> solution = steps.get(steps.size() - 1);
        steps.remove(0);
        steps.remove(steps.size() - 1);

        String content = readFile();
        text += content.substring(0, content.length() - 1);
        text += ", ";
        write(getTime());
        text += ": ";
        text += "{";
        writeItem("Divided", input.first);
        writeItem("Divider", input.second);
        writeItem("Quotient", solution.first);
        writeItem("Remainder", solution.second);

        writeSteps(steps);

        text += "}}";
        try {
            FileWriter file = new FileWriter(path);
            file.write(text);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
