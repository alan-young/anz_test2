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
    

    @RequestMapping(value="/version", method= RequestMethod.GET)
    public VersionDTO getVersion() {
        return new VersionDTO(env.getProperty("VERSION"), env.getProperty("COMMIT_SHA"), "pre-interview technical test");
    }
}
