package com.comp680.backend.messaging;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MyMessage {

    public MyMessage() {

    }

    public MyMessage(long fromUser, long toUser, String message) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.message = message;
    }

    /**
     * @param fromEmail the fromEmail to set
     */
    @JsonProperty("fromUser")
    public void setFromUser(long from) {
        this.fromUser = from;
    }

    /**
     * @param toEmail the toEmail to set
     */
    @JsonProperty("toUser")
    public void setToEmail(long to) {
        this.toUser = to;
    }

    /**
     * @param message the message to set
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the fromEmail
     */
    public long getFromUser() {
        return fromUser;
    }

    /**
     * @return the toEmail
     */
    public long getToUser() {
        return toUser;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    
    long fromUser;
    
    long toUser;
    
    String message;
}
