package com.template.v1.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonRequest {

    private PersonRequest() {
    }

    public static class CreateRequest {
        @JsonProperty(value = "msisdn", required = false)
        private String msisdn;

        @JsonProperty(value = "firstName", required = false)
        private String firstName;

        @JsonProperty(value = "lastName", required = false)
        private String lastName;

        @JsonProperty(value = "email", required = false)
        private String email;

        public String getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public static class FindRequest {

        private String msisdn;

        public String getMsisdn() {
            return msisdn;
        }

        public void setMsisdn(String msisdn) {
            this.msisdn = msisdn;
        }
    }

}
