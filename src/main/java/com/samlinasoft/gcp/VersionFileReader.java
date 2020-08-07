package com.samlinasoft.gcp;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class VersionFileReader {
    public String getVersionNumber(String versionFile) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(versionFile, UTF_8))) {
            return br.readLine();
        }
    }
}