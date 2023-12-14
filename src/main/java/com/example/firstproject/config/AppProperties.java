package com.example.firstproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {
    private final Auth auth = new Auth();

    private final Credits credits = new Credits();

    public static class Auth {
        private String tokenSecret;
        private long tokenExpirationMsec;


        public String getTokenSecret() {
            return tokenSecret;
        }

        public void setTokenSecret(String tokenSecret) {
            this.tokenSecret = tokenSecret;
        }

        public long getTokenExpirationMsec() {
            return tokenExpirationMsec;
        }

        public void setTokenExpirationMsec(long tokenExpirationMsec) {
            this.tokenExpirationMsec = tokenExpirationMsec;
        }

    }

    public static class Credits {
        private long credit;
        private long creditForCreate;
        private long creditForAssigner;

        public long getCredit() {
            return credit;
        }

        public void setCredit(long credit) {
            this.credit = credit;
        }

        public long getCreditForCreate() {
            return creditForCreate;
        }

        public void setCreditForCreate(long creditForCreate) {
            this.creditForCreate = creditForCreate;
        }

        public long getCreditForAssigner() {
            return creditForAssigner;
        }

        public void setCreditForAssigner(long creditForAssigner) {
            this.creditForAssigner = creditForAssigner;
        }
    }

    public Auth getAuth() {
        return auth;
    }

    public Credits getCredits() {
        return credits;
    }

}
