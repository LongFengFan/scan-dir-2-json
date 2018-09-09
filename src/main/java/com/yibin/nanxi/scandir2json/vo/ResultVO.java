package com.yibin.nanxi.scandir2json.vo;

import java.io.Serializable;

/**
 * Created by LongFF on 2018/8/14
 */
public class ResultVO implements Serializable {
    private Object result;

    private String message = "{}";

    private String status;

    public Object getResult() {
        return result;
    }

    public ResultVO setResult(Object result) {
        this.result = result;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultVO setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ResultVO setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"result\":")
                .append(result);
        sb.append(",\"message\":\"")
                .append(message).append('\"');
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
