package sample.camel;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nghiatm on 4/6/2018.
 */
public class SocketMessage implements Serializable {
    public static final long serialVersionUID = 42L;

    PurchaseLog purchaseLog;
    String sessionId;
    Customer customer;
    int status=0;  //-1: connect exception, 0:normal
    Exception exception;
    List<String> smsDestination=null;
    String message;

    public SocketMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getSmsDestination() {
        return smsDestination;
    }

    public void setSmsDestination(List<String> smsDestination) {
        this.smsDestination = smsDestination;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PurchaseLog getPurchaseLog() {
        return purchaseLog;
    }

    public void setPurchaseLog(PurchaseLog purchaseLog) {
        this.purchaseLog = purchaseLog;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String toJson(){
        Gson gson= new Gson();
        return gson.toJson(this);
    }
}
