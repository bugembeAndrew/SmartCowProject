package com.smartcowprojekt.smartcow.app;

/**
 * Created by ANDREIS on 2/13/2016.
 */
public class AppConfig {

    // User server requests #10.0.2.2 #192.16.137.1 #modernconsultant.net/smart_cow_api
    public static String LOGIN_URL = "http://modernconsultant.net/smart_cow_api" +
            "/user.php?request=login_user";

    public static String REGISTER_URL = "http://modernconsultant.net/smart_cow_api" +
            "/user.php?request=add_user";

    public static String USER_BY_UUID_URL = "http://10.0.2.2/smart_cow_api/user.php?" +
            "request=user_by_uid&uuid=";

    public static String USER_BY_ID_URL = "http://10.0.2.2/smart_cow_api/user.php?" +
            "request=user_by_category&category=";

    public static String ALL_USERS_URL = "http://modernconsultant.net/smart_cow_api/user.php?" +
            "request=all_users";

    public static String UPDATE_USER_URL = "http://10.0.2.2/smart_cow_api/user.php?" +
            "request=update_user";

    public static String DELETE_USER_URL = "http://10.0.2.2/smart_cow_api/user.php?" +
            "request=delete_user&user_id=";

    public static String SEARCH_USER_URL = "http://10.0.2.2/smart_cow_api/user.php?" +
            "request=search_user&term=";

    public static String CHANGE_PASSWORD_URL = "http://10.0.2.2/smart_cow_api/user.php?" +
            "request=change_password";

    public static String FORGOT_PASSWORD_URL = "http://10.0.2.2/smart_cow_api/user.php?" +
            "request=forgot_password";

    //Farm server requests
    public static String ADD_FARM_URL = "http://10.0.2.2/smart_cow_api/farm.php?" +
            "request=add_farm";

    public static String FARM_BY_ID_URL = "http://10.0.2.2/smart_cow_api/farm.php?" +
            "request=farm_by_id&farm_id=";

    public static String FARM_BY_UUID_URL = "http://10.0.2.2/smart_cow_api/farm.php?" +
            "request=farm_by_uuid&uuid=";

    public static String ALL_FARMS_URL = "http://modernconsultant.net/smart_cow_api/farm.php?" +
            "request=all_farms";

    public static String UPDATE_FARM_URL = "http://10.0.2.2/smart_cow_api/farm.php?" +
            "request=update_farm";

    public static String DELETE_FARM_URL = "http://10.0.2.2/smart_cow_api/farm.php?" +
            "request=delete_farm&farm_id=";

    public static String SEARCH_FARM_URL = "http://10.0.2.2/smart_cow_api/farm.php?" +
            "request=search_farm&term=";

    //Displacement server requests
    public static String ADD_DISPLACEMENT_URL = "http://10.0.2.2/smart_cow_api/displacement.php?" +
            "request=add_displacement";

    public static String DISPLACEMENT_BY_ID_URL = "http://10.0.2.2/smart_cow_api/" +
            "displacement.php?request=displacement_by_id&displacement_id=";

    public static String DISPLACEMENT_BY_CATTLE_URL = "http://10.0.2.2/smart_cow_api/" +
            "displacement.php?request=displacement_by_cattle&cattle=";

    public static String ALL_DISPLACEMENTS_URL = "http://modernconsultant.net/smart_cow_api/" +
            "displacement.php?request=all_displacements";

    public static String UPDATE_DISPLACEMENT_URL = "http://10.0.2.2/smart_cow_api/" +
            "displacement.php?request=update_displacement";

    public static String DELETE_DISPLACEMENT_URL = "http://10.0.2.2/smart_cow_api/" +
            "displacement.php?request=delete_displacement&displacement_id=";

    public static String SEARCH_DISPLACEMENT_URL = "http://10.0.2.2/smart_cow_api/" +
            "displacement.php?request=search_displacement&term=";

    //Cattle server requests
    public static String ADD_CATTLE_URL = "http://10.0.2.2/smart_cow_api/" +
            "cattle.php?request=add_cattle";

    public static String CATTLE_BY_ID_URL = "http://modernconsultant.net/smart_cow_api/" +
            "cattle.php?request=cattle_by_id&cattle_id=";

    public static String CATTLE_BY_FARM_URL = "http://10.0.2.2/smart_cow_api/" +
            "cattle.php?request=cattle_by_farm&farm=";

    public static String ALL_CATTLE_URL = "http://modernconsultant.net/smart_cow_api/" +
            "cattle.php?request=all_cattle";

    public static String UPDATE_CATTLE_URL = "http://10.0.2.2/smart_cow_api/" +
            "cattle.php?request=update_cattle";

    public static String DELETE_CATTLE_URL = "http://10.0.2.2/smart_cow_api/" +
            "cattle.php?request=delete_cattle&cattle_id=";

    public static String SEARCH_CATTLE_URL = "http://10.0.2.2/smart_cow_api/" +
            "cattle.php?request=search_cattle&term=";

}
