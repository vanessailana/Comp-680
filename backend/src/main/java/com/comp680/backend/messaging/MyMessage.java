package com.comp680.backend.messaging;
import com.fasterxml.jackson.annotation.JsonProperty;
public class MyMessage {

    

    
    public MyMessage() {

    }

    public MyMessage(long toUser, long fromUser, String message, String date) {
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.message = message;
        this.sentAtDate = date;
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

    @JsonProperty("sentAtDate")
    public void setSentAtDate(String date) {
       this.sentAtDate = date;
    }


    public String getSentAtDate()
    {
        return sentAtDate;
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

    String sentAtDate;
}
