package com.kerrrusha.kpi.java.web_developing.lab1;

import java.io.File;
import java.util.Scanner;

public class PreparingTool {
    private static final String ENTER_PATH = "Enter path to operate:";
    private static final String INVALID_PATH_INPUT = "\nThere is no such file/folder.";
    private final Scanner sc;

    public PreparingTool() {
        sc = new Scanner(System.in);
    }

    public File prepareFile() {
        File file;

        while ( ! (file = new File(askForPath())).exists() ) {
            System.out.println(INVALID_PATH_INPUT);
        }

        return file;
    }
    private String askForPath() {
        System.out.println(ENTER_PATH);
        return sc.nextLine();
    }
}
