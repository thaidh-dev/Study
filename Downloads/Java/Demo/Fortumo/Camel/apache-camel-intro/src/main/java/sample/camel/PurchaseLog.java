package sample.camel;

import com.google.gson.Gson;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by NghiaTM on 7/27/2016.
 */
@Entity("purchase-log")
public class PurchaseLog implements Serializable {
    public static final long serialVersionUID = 42L;

    public static String manualCancelTable = "ManualCancelTable";

    public static int TYPE_PREPAID= 0;
    public static int TYPE_POSTPAID =1;

    public static int PROVIDER_GOOGLE=1;
    public static int PROVIDER_OTHER=0;

    public static int RESPONSE_SUCCESS=0;
    public static int RESPONSE_FAIL=1;

    public static int PAYMENT_STATUS_BOOKING=-1;
    public static int PAYMENT_STATUS_AUTH=0;
    public static int PAYMENT_STATUS_CHARGED=1;
    public static int PAYMENT_STATUS_CANCEL=2;
    public static int PAYMENT_STATUS_WAITING_CANCEL=-2;
    public static int PAYMENT_STATUS_REFUNDED=3;
    public static int PAYMENT_STATUS_WAITING_REFUNDED=-3;



    @Id
    ObjectId _id;
    String msisdn; //số điện thoại
    String channel; //kênh thực hiện
    String correlationId; //mã giao dịch do đối tác sinh
    ObjectId partner; //id đối tác
    String transId; //mã giao dịch do hệ thống sinh
    long requestTimestamp; //thời gian gửi request do đối tác sinh
    Date created; //thời gian nhận request của hệ thống
    Date updated; //thời gian hoàn tất xử lý của hệ thống
    Date chargingDate; //thời gian hoàn tất trừ cước
    Date chargingDateRequestTime; //thời gian gửi request sang module trừ cước
    Date chargingDateResponseTime; //thời gian nhân phản hồi kết quả của module trừ cước
    Date chargeStartDate; //thời gian bắt đầu của giao dịch
    String action; //bước giao dịch Booking|Auth|Charge|Cancel|Refund
    int type; //kiểu thanh toán
//    boolean isCompleted; //đã hoàn tất giao dịch chưa
    String reason; //li do hoan cuoc
    int accType=-1; //0: tra truoc, 1: tra sau
    boolean isConfirmCheck= false;

    String store; //optinal- Store name
    String storeTransId; //mã giao dịch do store sinh (vd: Google)
    String item; //required- item's name
    long itemPrice; //required- item's price (NET)
    String itemDes;//optinal- Description
    String itemProv;//optinal- Provider
    String itemPkg; //optinal- Package name or app name
    long itemPriceGross; //required- item's price (GROSS)
    int provider = 0; //0: noi dung khac| 1:Google

    String refundInfo; //mã truyền sang cps khi refund
    String statusCode; //mã kết quả bước giao dịch
    String detailMessage; //thông tin thêm
    String cpsCode; //mã trả về từ CPS
    String cpsSessionId;//ko dung nua
    int retryCount=0; //retry tru cuoc
    int responseStatus= 0; //trạng thái phản hồi của request: 0:success, 1:error
    int paymentStatus=0; //trạng thái của cả giao dịch:  -1:booking,0:auth,1:charged,2:cancel,3:refund
    int onlineCharging = 1; //thực hiện công/trừ tiền online ko: 1:online, 0:offline

    int exportStatus=0; //trạng thái xuất file cdr: 0:chưa xuất, 1 đã xuất
    String exportFile; //tên file cdr
    Date exportDate; //thời gian xuất file
    int cdrStatus; //trang thai ghi trong file cdr theo cong van 3677/MOBIFONE-MVAS

    String categoryId;
    String contentId;
    String b_isdn;
    String cpId;

    Date check9029RequestTime; //thời gian gửi sang module check 9029
    Date check9029ResponseTime; //thời gian nhận kq từ module check 9029
    String check9029Result; //kết quả check 9029

    Date checkInfoRequestTime; //thời gian gửi sáng module kiểm tra thông tin thuê bao
    Date checkInfoResponseTime; //thời gian nhận kết quả từ module kiểm tra thông tin thuê bao
    String checkInfoResult; //kết quả kiểm tra thông tin thuê bao
//    int invoice; //co trong invoice

    public PurchaseLog() {
    }

    public int getCdrStatus() {
        return cdrStatus;
    }

    public void setCdrStatus(int cdrStatus) {
        this.cdrStatus = cdrStatus;
    }

    public Date getChargingDateResponseTime() {
        return chargingDateResponseTime;
    }

    public void setChargingDateResponseTime(Date chargingDateResponseTime) {
        this.chargingDateResponseTime = chargingDateResponseTime;
    }

    public int getOnlineCharging() {
        return onlineCharging;
    }

    public void setOnlineCharging(int onlineCharging) {
        this.onlineCharging = onlineCharging;
    }

    public Date getChargingDateRequestTime() {
        return chargingDateRequestTime;
    }

    public void setChargingDateRequestTime(Date chargingDateRequestTime) {
        this.chargingDateRequestTime = chargingDateRequestTime;
    }

    public Date getCheck9029RequestTime() {
        return check9029RequestTime;
    }

    public void setCheck9029RequestTime(Date check9029RequestTime) {
        this.check9029RequestTime = check9029RequestTime;
    }

    public Date getCheck9029ResponseTime() {
        return check9029ResponseTime;
    }

    public void setCheck9029ResponseTime(Date check9029ResponseTime) {
        this.check9029ResponseTime = check9029ResponseTime;
    }

    public String getCheck9029Result() {
        return check9029Result;
    }

    public void setCheck9029Result(String check9029Result) {
        this.check9029Result = check9029Result;
    }

    public Date getCheckInfoRequestTime() {
        return checkInfoRequestTime;
    }

    public void setCheckInfoRequestTime(Date checkInfoRequestTime) {
        this.checkInfoRequestTime = checkInfoRequestTime;
    }

    public Date getCheckInfoResponseTime() {
        return checkInfoResponseTime;
    }

    public void setCheckInfoResponseTime(Date checkInfoResponseTime) {
        this.checkInfoResponseTime = checkInfoResponseTime;
    }

    public String getCheckInfoResult() {
        return checkInfoResult;
    }

    public void setCheckInfoResult(String checkInfoResult) {
        this.checkInfoResult = checkInfoResult;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public String getStoreTransId() {
        return storeTransId;
    }

    public void setStoreTransId(String storeTransId) {
        this.storeTransId = storeTransId;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public long getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(long requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getB_isdn() {
        return b_isdn;
    }

    public void setB_isdn(String b_isdn) {
        this.b_isdn = b_isdn;
    }

    public Date getChargeStartDate() {
        return chargeStartDate;
    }

    public void setChargeStartDate(Date chargeStartDate) {
        this.chargeStartDate = chargeStartDate;
    }

    public int getProvider() {
        return provider;
    }

    public void setProvider(int provider) {
        this.provider = provider;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public void plusRetryCount(int number){
        this.retryCount += number;
    }

    public boolean isConfirmCheck() {
        return isConfirmCheck;
    }

    public void setConfirmCheck(boolean confirmCheck) {
        isConfirmCheck = confirmCheck;
    }

    public String getCpsSessionId() {
        return cpsSessionId;
    }

    public void setCpsSessionId(String cpsSessionId) {
        this.cpsSessionId = cpsSessionId;
    }

    public int getAccType() {
        return accType;
    }

    public void setAccType(int accType) {
        this.accType = accType;
    }

    public String getCpsCode() {
        return cpsCode;
    }

    public void setCpsCode(String cpsCode) {
        this.cpsCode = cpsCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public int getExportStatus() {
        return exportStatus;
    }

    public void setExportStatus(int exportStatus) {
        this.exportStatus = exportStatus;
    }

    public String getExportFile() {
        return exportFile;
    }

    public void setExportFile(String exportFile) {
        this.exportFile = exportFile;
    }

    public long getItemPriceGross() {
        return itemPriceGross;
    }

    public void setItemPriceGross(long itemPriceGross) {
        this.itemPriceGross = itemPriceGross;
    }

    public String getRefundInfo() {
        return refundInfo;
    }

    public void setRefundInfo(String refundInfo) {
        this.refundInfo = refundInfo;
    }

    public String getItemPkg() {
        return itemPkg;
    }

    public void setItemPkg(String itemPkg) {
        this.itemPkg = itemPkg;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

//    public boolean isCompleted() {
//        return isCompleted;
//    }
//
//    public void setIsCompleted(boolean isCompleted) {
//        this.isCompleted = isCompleted;
//    }

    public Date getChargingDate() {
        return chargingDate;
    }

    public void setChargingDate(Date chargingDate) {
        this.chargingDate = chargingDate;
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

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public ObjectId getPartner() {
        return partner;
    }

    public void setPartner(ObjectId partner) {
        this.partner = partner;
        try {
            this.cpId= PartnerManager.getInstance().getPartner(partner.toHexString()).getCpId();
        }catch (Exception e){
            e.printStackTrace();
            this.cpId=null;
        }

    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
        if(action.equals(Action.ACTION_REFUND)){
            this.categoryId="000002";
        }else if(action.equals(Action.ACTION_AUTH)|| action.equals(Action.ACTION_CHARGING)){
            this.categoryId="000001";
        }else if(action.equals(Action.ACTION_BOOKING)){
            this.categoryId="000003";
        }else if(action.equals(Action.ACTION_CANCEL)){
            this.categoryId="000005";
        }
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
        if(item.startsWith("GOOGLE")){
            this.provider= PROVIDER_GOOGLE;
            this.b_isdn="0000000002";
        }else{
            this.provider= PROVIDER_OTHER;
            this.b_isdn="0000000001";
        }
    }

    public long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(long itemPrice) throws IOException {
        this.itemPrice = itemPrice;
        this.itemPriceGross= itemPrice+(itemPrice* NConfig.get_VAT()/100);
    }

    public String getItemDes() {
        return itemDes;
    }

    public void setItemDes(String itemDes) {
        this.itemDes = itemDes;
    }

    public String getItemProv() {
        return itemProv;
    }

    public void setItemProv(String itemProv) {
        this.itemProv = itemProv;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public String toJson(){
        Gson gson= new Gson();
        return gson.toJson(this);
    }
}
