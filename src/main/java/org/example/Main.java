package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        Scanner scanner = new Scanner(System.in);
        String path;
        System.out.println("Enter file path or file name:");
        path = scanner.next();

        File file = new File(path);
        scanner.close();

        String absPath = file.getAbsolutePath();
        System.out.println("Path to your file: " + absPath);

        String line = null;
        String change = "";

        int letters = 0;
        int numbers = 0;
        int marks = 0;
        int symbols = 0;

        Matcher m;

        Pattern numb = Pattern.compile("0-9");
        Pattern punct = Pattern.compile("\\p{Punct}");

        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                while ((line = br.readLine()) != null) {
                    String[] text = line.split("\n");
                    for (String t : text) {
                        symbols += t.length();
                        m = punct.matcher(t);
                        while (m.find()) marks++;
                        m = numb.matcher(t);
                        while (m.find()) numbers++;
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            System.out.println(change);
            letters = symbols - numbers - marks;
            System.out.println("In the text " + letters + " letters, " + numbers + " numbers " + marks + " punctuation marks.");

        } else {
            System.out.println("File doesn't exist.");
        }
    }
}