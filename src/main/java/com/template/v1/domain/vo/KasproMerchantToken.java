package com.template.v1.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KasproMerchantToken {

    private KasproMerchantToken() {
    }

    public static class Request {
        @JsonProperty("mobileNumber")
        private String mobileNumber;

        @JsonProperty("requestId")
        private String requestId;

        @JsonProperty("type")
        private String type;

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Response {
        @JsonProperty("code")
        public int code;

        @JsonProperty("message")
        public String message;

        @JsonProperty("responseId")
        public String responseId;

    }


}
