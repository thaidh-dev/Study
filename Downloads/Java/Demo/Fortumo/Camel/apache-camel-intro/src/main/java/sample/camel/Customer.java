package sample.camel;

import com.google.gson.Gson;
import io.github.benas.randombeans.annotation.Randomizer;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by NghiaTM on 7/27/2016.
 */
@Entity("customer")
public class Customer implements Serializable {
    public static final long serialVersionUID = 42L;

    public static int STATUS_NEW_ACTIVED=0;
    public static int STATUS_BANED_1_DIR= 1;
    public static int STATUS_BANED_2_DIR= 2;
    public static int STATUS_OPEN_1_DIR= 3;
    public static int STATUS_OPEN_2_DIR= 4;
    public static int STATUS_STOP_SERVICE= 5;
    public static int STATUS_SWITCHTO_PREPAID= 6;
    public static int STATUS_SWITCHTO_POSTPAID= 7;

    public static int TYPE_PREPAID=0;
    public static int TYPE_POSTPAID=1;
    public static int TYPE_UNKNOWN=-1;

    @Id
    ObjectId _id;
    @Randomizer(MsisdnRandomizer.class)
    String msisdn; //số điện thoại
    int status;// 1: chan 1 chieu, 2: chan 2 chieu, 3: mo 1 chieu, 4: mo 2 chieu, 5: cat dv, 6: cat chuyen tra truoc, 0: kich hoat moi
    Date created; //thời gian tạo
    ObjectId group; //id nhóm khách hàng
    Date updated; //thời gian cập nhật
    int active=1; //0:ngung dv(blacklisted), 1:dang hoat dong
    String note; //ly do, chu thich
    int type=-1; //0:tra truoc, 1: tra sau, -1:unknown

    public Customer() {
    }

    public Customer(String msisdn, int status, ObjectId group, int type) {
        this.msisdn = msisdn;
        this.status = status;
        this.created = new Date();
        this.group = group;
        this.updated= new Date();
        this.active=1;
        this.type= type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public ObjectId getGroup() {
        return group;
    }

    public void setGroup(ObjectId group) {
        this.group = group;
    }
    public String toJson(){
        Gson gson= new Gson();
        return gson.toJson(this);
    }
}
