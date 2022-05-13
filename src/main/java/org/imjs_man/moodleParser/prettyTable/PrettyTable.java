package org.imjs_man.moodleParser.prettyTable;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PrettyTable {
    private final List<String> headers = new ArrayList<>();
    private final List<List<String>> data = new ArrayList<>();

    public PrettyTable(String... headers) {
        this.headers.addAll(Arrays.asList(headers));
    }

    public void addRow(String... row) {
        data.add(Arrays.asList(row));
    }

    private int getMaxSize(int column) {
        int maxSize = headers.get(column).length();
        for (List<String> row : data) {
            if (row.get(column).length() > maxSize)
                maxSize = row.get(column).length();
        }
        return maxSize;
    }
    private String center(String word, int stringSize)
    {
        int word_size = word.length();
        int left = (stringSize-word_size)/2;
        int right = stringSize-word_size-left;
        StringBuilder out = new StringBuilder();
        while (left>1) {
            out.append(" ");
            left = left - 1;
        }
        out.append(word);
        while (right>1) {
            out.append(" ");
            right = right - 1;
        }
        return out.toString();

    }

    private String formatRow(List<String> row) {
        StringBuilder result = new StringBuilder();
        result.append("|");
        for (int i = 0; i < row.size(); i++) {
            result.append(center(row.get(i), getMaxSize(i) + 2));
            result.append("|");
        }
        result.append("\n");
        return result.toString();
    }

    private String formatRule() {
        StringBuilder result = new StringBuilder();
        result.append("+");
        for (int i = 0; i < headers.size(); i++) {
            for (int j = 0; j < getMaxSize(i) + 2; j++) {
                result.append("-");
            }
            result.append("+");
        }
        result.append("\n");
        return result.toString();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(formatRule());
        result.append(formatRow(headers));
        result.append(formatRule());
        for (List<String> row : data) {
            result.append(formatRow(row));
        }
        result.append(formatRule());
        return result.toString();
    }

}