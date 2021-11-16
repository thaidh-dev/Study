package sample.camel;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by NghiaTM on 7/27/2016.
 */
@Entity("partner")
public class Partner implements Serializable {

    public static final long serialVersionUID = 42L;

    @Id
    ObjectId _id;
    String name;
    String ip;
    int maxSession;
    int tps;
    Date created;
    boolean status;
    String shortcode;
    String cpId;
    int timeRefundCheck; //hours

    public Partner() {
    }

    public int getTimeRefundCheck() {
        return timeRefundCheck;
    }

    public void setTimeRefundCheck(int timeRefundCheck) {
        this.timeRefundCheck = timeRefundCheck;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getMaxSession() {
        return maxSession;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public int getTps() {
        return tps;
    }

    public void setTps(int tps) {
        this.tps = tps;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
