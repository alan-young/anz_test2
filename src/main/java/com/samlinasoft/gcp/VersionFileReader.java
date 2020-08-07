package com.samlinasoft.gcp;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class VersionFileReader {
    public String getVersionNumber() throws Exception {

        FileInputStream fis = null;
        BufferedReader reader = null;
        String line="";

        try {
            fis = new FileInputStream("VERSION.txt");
            reader = new BufferedReader(new InputStreamReader(fis));
            line = reader.readLine();
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException ex) {
            }
        }

        return line;
    }
}