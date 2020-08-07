package com.samlinasoft.gcp;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class VersionFileReaderTest {
    @Test
    public void whenGetVersionNumberCalled_then_returnsCorrectVerisonInformation() throws Exception {
        VersionFileReader versionFileReader = new VersionFileReader();
        String actionVersionNumber = versionFileReader.getVersionNumber("src/test/resources/VersionNumberTest.txt");
        assertThat(actionVersionNumber, equalTo("8.8.8.8"));
    }
}