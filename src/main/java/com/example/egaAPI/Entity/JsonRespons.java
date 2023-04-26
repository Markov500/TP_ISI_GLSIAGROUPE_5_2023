package com.example.egaAPI.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;


public class JsonRespons  {
    private int status;
    private String message;

    private Object result;

    private int count;

    public JsonRespons(int status, String message, Object result, int count){
        this.status = status;
        this.message = message;
        this.result =result;
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getResult() {
        return result;
    }

    public int getCount() {
        return count;
    }
}
