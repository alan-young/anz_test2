package com.samlinasoft.gcp;

import com.samlinasoft.gcp.dto.VersionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class SimpleApplicationController {

    @Autowired
    private Environment env;

    @Autowired
    private VersionFileReader versionFileReader;

    public static final String VERSION_FILENAME = "VERSION.txt";
    public static final String COMMIT_SHA = "COMMIT_SHA";

    @RequestMapping(value="/version", method= RequestMethod.GET)
    public VersionDTO getVersion() throws Exception {

        return new VersionDTO(versionFileReader.getVersionNumber(VERSION_FILENAME), env.getProperty(COMMIT_SHA), "pre-interview technical test");    }
}
