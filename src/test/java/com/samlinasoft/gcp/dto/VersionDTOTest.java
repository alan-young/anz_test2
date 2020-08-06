package com.samlinasoft.gcp.dto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VersionDTOTest {

    @Test
    public void whenCreatingDTO_then_allFieldsSetCorrectly() {

        VersionDTO versionDTO = new VersionDTO("1.1", "123abc", "test description");

        assertEquals(versionDTO.getMyApplication()[0].getVersion(),"1.1");
        assertEquals(versionDTO.getMyApplication()[0].getLastcommitsha(),"123abc");
        assertEquals(versionDTO.getMyApplication()[0].getDescription(),"test description");
    }
}
