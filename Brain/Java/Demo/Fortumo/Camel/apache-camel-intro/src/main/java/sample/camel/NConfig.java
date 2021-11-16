/** 
* Author: NghiaTM 
* Generated source files should not be edited. The changes will be lost when sources are regenerated
*/ 
package sample.camel;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class NConfig {
 private static Properties configProperties;

    private static final Object lock = new Object();
    public static Properties getConfigProperties() throws IOException {

        if( configProperties==null){
           synchronized (lock){
               if(configProperties==null){
                   configProperties= new Properties();
                   ClassLoader classLoader = NConfig.class.getClassLoader();
                   InputStream input = classLoader.getResourceAsStream("config.nproperties");
                   try{
                       InputStreamReader inputStreamReader = new InputStreamReader(input, "UTF-8");
                       try {
                           configProperties.load(inputStreamReader);
                       }finally {
                           inputStreamReader.close();
                       }
                   }finally {
                       input.close();
                   }
                }
           }
        }
        return  configProperties;
    }

    public static void loadProperties() throws IOException {
        if( configProperties==null){
            configProperties= new Properties();

        }
        ClassLoader classLoader = NConfig.class.getClassLoader();
        InputStream input = classLoader.getResourceAsStream("config.nproperties");
        InputStreamReader inputStreamReader = new InputStreamReader(input, "UTF-8");
        configProperties.load(inputStreamReader);
            inputStreamReader.close();
            input.close();
    }

    public static String getStringProperty(String property) throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty(property);
    }


    public static Integer getIntProperty(String property) throws IOException {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty(property));
    }

    public static List<String> getStringArrayProperty(String property, String splitRegex) throws IOException {
        Properties properties= getConfigProperties();
        String rawString= properties.getProperty(property);
        List<String> result= Arrays.asList(rawString.split(splitRegex)) ;

        return result;
    }

    public static boolean getBooleanProperty(String property) throws IOException{
        Properties properties= getConfigProperties();
        String rString= properties.getProperty(property);
        if(rString.trim().equalsIgnoreCase("true")){
            return true;
        }else{
            return false;
        }
    }
	public static String log_api_schedule_path_key="log_api_schedule_path" ;
	public static String get_log_api_schedule_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-schedule-path");
    }
	public static String log_fortumo_association_path_key="log_fortumo_association_path" ;
	public static String get_log_fortumo_association_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-fortumo-association-path");
    }
	public static String postpaid_5m_group_key="postpaid_5m_group" ;
	public static String get_postpaid_5m_group()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("postpaid-5m-group");
    }
	public static String rtec_port_key= "rtec_port" ;
	public static int get_rtec_port() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-rtec-port"));
    }
	public static String cdr_delay_time_key="cdr_delay_time" ;
	public static String get_cdr_delay_time()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("cdr-delay-time");
    }
	public static String log_refund_responseFail_key="log_refund_responseFail" ;
	public static String get_log_refund_responseFail()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-refund-responseFail");
    }
	public static String log_api_auth_path_key="log_api_auth_path" ;
	public static String get_log_api_auth_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-auth-path");
    }
	public static String send_email_sender_password_key="send_email_sender_password" ;
	public static String get_send_email_sender_password()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("send-email-sender-password");
    }
	public static String mongodb_host_key="mongodb_host" ;
	public static String get_mongodb_host()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("mongodb-host");
    }
	public static String cps_tps_key= "cps_tps" ;
	public static int get_cps_tps() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-cps-tps"));
    }
	public static String log_email_path_key="log_email_path" ;
	public static String get_log_email_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-email-path");
    }
	public static String cps_vas_server_port_key= "cps_vas_server_port" ;
	public static int get_cps_vas_server_port() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-cps-vas-server-port"));
    }
	public static String log_cancel_check_path_key="log_cancel_check_path" ;
	public static String get_log_cancel_check_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-cancel-check-path");
    }
	public static String smpp_from_number_key="smpp_from_number" ;
	public static String get_smpp_from_number()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("smpp-from-number");
    }
	public static String ung_tien_password_key="ung_tien_password" ;
	public static String get_ung_tien_password()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("ung-tien-password");
    }
	public static String cdr_maxtime_key= "cdr_maxtime" ;
	public static int get_cdr_maxtime() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-cdr-maxtime"));
    }
	public static String commandlog_maxtime_key= "commandlog_maxtime" ;
	public static int get_commandlog_maxtime() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-commandlog-maxtime"));
    }
	public static String payment_check_url_key="payment_check_url" ;
	public static String get_payment_check_url()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("payment-check-url");
    }
	public static String log_clean_path_key="log_clean_path" ;
	public static String get_log_clean_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-clean-path");
    }
	public static String activemq_host_key="activemq_host" ;
	public static String get_activemq_host()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("activemq-host");
    }
	public static String exception_phone_key="exception_phone" ;
	public static String get_exception_phone()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("exception-phone");
    }
	public static String ung_tien_username_key="ung_tien_username" ;
	public static String get_ung_tien_username()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("ung-tien-username");
    }
	public static String default_postpaid_group_key="default_postpaid_group" ;
	public static String get_default_postpaid_group()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("default-postpaid-group");
    }
	public static String log_5m_path_key="log_5m_path" ;
	public static String get_log_5m_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-5m-path");
    }
	public static String smpp_send_key="smpp_send" ;
	public static String get_smpp_send()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("smpp-send");
    }
	public static String default_group_key="default_group" ;
	public static String get_default_group()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("default-group");
    }
	public static String log_api_refund_path_key="log_api_refund_path" ;
	public static String get_log_api_refund_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-refund-path");
    }
	public static String cps_module_host_key="cps_module_host" ;
	public static String get_cps_module_host()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("cps-module-host");
    }
	public static String contentid_file_key="contentid_file" ;
	public static String get_contentid_file()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("contentid-file");
    }
	public static String send_email_sender_username_key="send_email_sender_username" ;
	public static String get_send_email_sender_username()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("send-email-sender-username");
    }
	public static String log_retry_postpaid_key="log_retry_postpaid" ;
	public static String get_log_retry_postpaid()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-retry-postpaid");
    }
	public static String ftp_server_cdr_key="ftp_server_cdr" ;
	public static String get_ftp_server_cdr()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("ftp-server-cdr");
    }
	public static String purchaselog_maxtime_key= "purchaselog_maxtime" ;
	public static int get_purchaselog_maxtime() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-purchaselog-maxtime"));
    }
	public static String log_api_mt_path_key="log_api_mt_path" ;
	public static String get_log_api_mt_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-mt-path");
    }
	public static String mongodb_db_name_key="mongodb_db_name" ;
	public static String get_mongodb_db_name()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("mongodb-db-name");
    }
	public static String log_exception_path_key="log_exception_path" ;
	public static String get_log_exception_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-exception-path");
    }
	public static String jmx_key="jmx" ;
	public static String get_jmx()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("jmx");
    }
	public static String payment_check_password_key="payment_check_password" ;
	public static String get_payment_check_password()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("payment-check-password");
    }
	public static String log_api_cancel_schedule_path_key="log_api_cancel_schedule_path" ;
	public static String get_log_api_cancel_schedule_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-cancel-schedule-path");
    }
	public static String log_check_payment_path_key="log_check_payment_path" ;
	public static String get_log_check_payment_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-check-payment-path");
    }
	public static String byte_data_coding_key= "byte_data_coding" ;
	public static byte get_byte_data_coding() throws IOException  {
        Properties properties= getConfigProperties();
        return Byte.parseByte(properties.getProperty("byte-data-coding"));
    }
	public static String log_vas_sync_input_path_key="log_vas_sync_input_path" ;
	public static String get_log_vas_sync_input_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-vas-sync-input-path");
    }
	public static String log_cdr_path_key="log_cdr_path" ;
	public static String get_log_cdr_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-cdr-path");
    }
	public static String log_api_cancel_path_key="log_api_cancel_path" ;
	public static String get_log_api_cancel_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-cancel-path");
    }
	public static String log_cps_path_key="log_cps_path" ;
	public static String get_log_cps_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-cps-path");
    }
	public static String rtec_max_session_key= "rtec_max_session" ;
	public static int get_rtec_max_session() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-rtec-max-session"));
    }
	public static String cps_login_password_key="cps_login_password" ;
	public static String get_cps_login_password()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("cps-login-password");
    }
	public static String log_jmx_delete_path_key="log_jmx_delete_path" ;
	public static String get_log_jmx_delete_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-jmx-delete-path");
    }
	public static String smpp_send_throttle_key= "smpp_send_throttle" ;
	public static int get_smpp_send_throttle() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-smpp-send-throttle"));
    }
	public static String mongodb_port_key= "mongodb_port" ;
	public static int get_mongodb_port() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-mongodb-port"));
    }
	public static String send_email_smtp_host_key="send_email_smtp_host" ;
	public static String get_send_email_smtp_host()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("send-email-smtp-host");
    }
	public static String payment_check_username_key="payment_check_username" ;
	public static String get_payment_check_username()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("payment-check-username");
    }
	public static String exception_time_gap_key= "exception_time_gap" ;
	public static int get_exception_time_gap() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-exception-time-gap"));
    }
	public static String log_api_refund_schedule_path_key="log_api_refund_schedule_path" ;
	public static String get_log_api_refund_schedule_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-refund-schedule-path");
    }
	public static String log_api_charge_path_key="log_api_charge_path" ;
	public static String get_log_api_charge_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-charge-path");
    }
	public static String check_timeout_delay_hour_key= "check_timeout_delay_hour" ;
	public static int get_check_timeout_delay_hour() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-check-timeout-delay-hour"));
    }
	public static String cdr_b_number_key="cdr_b_number" ;
	public static String get_cdr_b_number()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("cdr_b_number");
    }
	public static String log_smpp_send_path_key="log_smpp_send_path" ;
	public static String get_log_smpp_send_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-smpp-send-path");
    }
	public static String exception_email_key="exception_email" ;
	public static String get_exception_email()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("exception-email");
    }
	public static String testing_key="testing" ;
	public static boolean is_testing()  throws IOException {
        Properties properties= getConfigProperties();
        String rString= properties.getProperty("b-testing");
        if(rString.trim().equalsIgnoreCase("true")){
            return true;
        }else{
            return false;
        }
    }
	public static String smpp_receive_key="smpp_receive" ;
	public static String get_smpp_receive()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("smpp-receive");
    }
	public static String cps_module_port_key= "cps_module_port" ;
	public static int get_cps_module_port() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-cps-module-port"));
    }
	public static String log_auto_refund_path_key="log_auto_refund_path" ;
	public static String get_log_auto_refund_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-auto-refund-path");
    }
	public static String log_auth_main_path_key="log_auth_main_path" ;
	public static String get_log_auth_main_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-auth-main-path");
    }
	public static String log_api_provisioning_path_key="log_api_provisioning_path" ;
	public static String get_log_api_provisioning_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-provisioning-path");
    }
	public static String VAT_key= "VAT" ;
	public static int get_VAT() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-VAT"));
    }
	public static String log_api_back_path_key="log_api_back_path" ;
	public static String get_log_api_back_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-back-path");
    }
	public static String log_external_warning_path_key="log_external_warning_path" ;
	public static String get_log_external_warning_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-external-warning-path");
    }
	public static String cps_login_user_key="cps_login_user" ;
	public static String get_cps_login_user()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("cps-login-user");
    }
	public static String cps_max_connections_key= "cps_max_connections" ;
	public static int get_cps_max_connections() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-cps-max-connections"));
    }
	public static String send_email_smtp_auth_enable_key="send_email_smtp_auth_enable" ;
	public static String get_send_email_smtp_auth_enable()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("send-email-smtp-auth-enable");
    }
	public static String cps_shotcode_key="cps_shotcode" ;
	public static String get_cps_shotcode()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("cps-shotcode");
    }
	public static String log_rtec_path_key="log_rtec_path" ;
	public static String get_log_rtec_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-rtec-path");
    }
	public static String cps_realm_name_key="cps_realm_name" ;
	public static String get_cps_realm_name()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("cps-realm-name");
    }
	public static String cdr_export_folder_key="cdr_export_folder" ;
	public static String get_cdr_export_folder()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("cdr-export-folder");
    }
	public static String cps_vas_server_ip_key="cps_vas_server_ip" ;
	public static String get_cps_vas_server_ip()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("cps-vas-server-ip");
    }
	public static String ung_tien_url_key="ung_tien_url" ;
	public static String get_ung_tien_url()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("ung-tien-url");
    }
	public static String log_laucher_path_key="log_laucher_path" ;
	public static String get_log_laucher_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-laucher-path");
    }
	public static String log_api_over_tps_path_key="log_api_over_tps_path" ;
	public static String get_log_api_over_tps_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-over-tps-path");
    }
	public static String send_email_smtp_port_key="send_email_smtp_port" ;
	public static String get_send_email_smtp_port()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("send-email-smtp-port");
    }
	public static String log_smpp_receive_path_key="log_smpp_receive_path" ;
	public static String get_log_smpp_receive_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-smpp-receive-path");
    }
	public static String log_web_comm_path_key="log_web_comm_path" ;
	public static String get_log_web_comm_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-web-comm-path");
    }
	public static String smpp_time_period_key= "smpp_time_period" ;
	public static int get_smpp_time_period() throws IOException  {
        Properties properties= getConfigProperties();
        return Integer.parseInt(properties.getProperty("i-smpp-time-period"));
    }
	public static String log_api_path_key="log_api_path" ;
	public static String get_log_api_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-api-path");
    }
	public static String log_tbts_check_path_key="log_tbts_check_path" ;
	public static String get_log_tbts_check_path()  throws IOException {
        Properties properties= getConfigProperties();
        return properties.getProperty("log-tbts-check-path");
    }
	public static String whitelist_prefix_key="whitelist_prefix" ;
	public static List<String> get_whitelist_prefix(String splitRegex)  throws IOException {
        Properties properties= getConfigProperties();
        String rawString= properties.getProperty("li-whitelist-prefix");
        List<String> result= Arrays.asList(rawString.split(splitRegex)) ;

        return result;
    }
 }
