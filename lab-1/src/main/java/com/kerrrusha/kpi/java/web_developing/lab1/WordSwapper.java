package com.kerrrusha.kpi.java.web_developing.lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class WordSwapper implements Callable<List<String>> {
    private final static String SPLIT_REGEX = "\\P{L}+";
    private final File file;

    public WordSwapper(File file) {
        this.file = file;
    }

    private static List<String> removeEmptyWords(String[] words) {
        return Arrays.stream(words).filter(word -> word.length() != 0).collect(Collectors.toList());
    }
    public static String swapWords(String str) {
        String[] splitted = str.split(SPLIT_REGEX);
        List<String> words = removeEmptyWords(splitted);
        if (words.size() < 2)
            return str;

        String firstWord = words.get(0);
        int firstWordStartPos = str.indexOf(firstWord);
        int firstWordEndPos = firstWordStartPos + firstWord.length();

        String lastWord = words.get(words.size() - 1);
        int lastWordStartPos = str.lastIndexOf(lastWord);
        int lastWordEndPos = lastWordStartPos + lastWord.length();

        return str.substring(0, firstWordStartPos) + lastWord +
                str.substring(firstWordEndPos, lastWordStartPos) + firstWord +
                str.substring(lastWordEndPos);
    }
    private void rewrite(File f, String content) {
        try(FileWriter writer = new FileWriter(f.getAbsolutePath())){
            f.delete();
            f.createNewFile();
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String readAndGetSwapped(File f) {
        try (Scanner sc = new Scanner(new FileInputStream(f))) {
            StringBuilder newFileContent = new StringBuilder();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String swapped = swapWords(line);
                newFileContent.append(swapped).append(System.lineSeparator());
            }
            return newFileContent.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String swapInFile(File f) {
        String newFileContent = readAndGetSwapped(f);
        rewrite(f, newFileContent);
        return f.getName();
    }

    private boolean isATextFile(File f) {
        String name = f.getName();
        return name.substring(name.lastIndexOf(".") + 1).equalsIgnoreCase("txt");
    }
    @Override
    public List<String> call() {
        List<String> filesFound = new ArrayList<>();

        File[] directoryContent = file.listFiles();
        List<Future<List<String>>> results = new ArrayList<>();

        assert directoryContent != null;
        try {
            for (File f : directoryContent) {
                if (f.isDirectory()) {
                    WordSwapper swapper = new WordSwapper(f);
                    FutureTask<List<String>> task = new FutureTask<>(swapper);
                    results.add(task);
                    Executor executor = (runnable) -> new Thread(runnable).start();
                    executor.execute(task);
                } else {
                    if (! isATextFile(f)) {
                        System.out.println(f.getName() + " is not a txt file - passing it.");
                        continue;
                    }
                    filesFound.add(swapInFile(f));
                }
            }
            for (Future<List<String>> result : results) {
                filesFound.addAll(result.get());
            }
            System.out.println(Thread.currentThread().getName() + " successfully finished swapping words.");
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return filesFound;
    }
}
