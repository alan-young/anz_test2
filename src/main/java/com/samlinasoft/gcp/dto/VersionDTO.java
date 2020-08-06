package com.samlinasoft.gcp.dto;

public class VersionDTO {

    private Info[] myApplication;

    public VersionDTO(String version, String lastcommitsha, String description) {
        myApplication = new Info[1];
        myApplication[0] = new Info(version, lastcommitsha, description);
    }

    public Info[] getMyApplication() {
        Info[] copyOfMyApplication = new Info[myApplication.length];

        for (int i = 0; i < myApplication.length; i++) {
            Info info = myApplication[i];
            copyOfMyApplication[i] = new Info(info.getVersion(), info.getLastcommitsha(), info.getDescription());
        }

        return copyOfMyApplication;
    }

    static class Info {
        private String version;
        private String lastcommitsha;
        private String description;

        public String getVersion() {
            return version;
        }

        public String getLastcommitsha() {
            return lastcommitsha;
        }

        public String getDescription() {
            return description;
        }

        Info(String version, String lastcommitsha, String description) {
            this.version = version;
            this.lastcommitsha = lastcommitsha;
            this.description = description;
        }
    }
}
