package gem.training3.enterprisenetwork.common;

/**
 * Constants for app
 * Created by neo on 2/15/2016.
 */
public interface Constants {
    //share preference file name
    String USER_INFO = "UserInfo";
    //share preference key when getting user
    String SHARE_PREFERENCE_KEY_USER_JSON = "userJson";
    //time out in splash screen
    int SPLASH_TIME_OUT = 1000;

    //constant string
    String token = "token";
    String deviceId = "deviceId";
    String intent_storeId = "storeId";
    String columnNameAsc ="name,asc";

    //num of item per page when getting store list
    int SIZE_PAGE_STORE = 10;
    //time out when clicking back button
    int BACK_TIMEOUT = 2000;
}
