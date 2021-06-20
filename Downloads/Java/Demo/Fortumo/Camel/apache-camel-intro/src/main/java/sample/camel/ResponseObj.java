package sample.camel;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by NghiaTM on 9/29/2015.
 */
public class ResponseObj implements Serializable {
    private String code;
    private String message;
    private String transId;

    public ResponseObj() {

    }

    public ResponseObj(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseObj(String code, String message, String transId) {
        this.code = code;
        this.message = message;
        if(transId!=null){
            if(transId.length()>0){
                this.transId = transId;
            }
        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String toJson(){
        Gson gson= new Gson();
        return gson.toJson(this);
    }

}
