package com.music.enums;



import org.springframework.util.StringUtils;

import java.util.HashMap;


public enum WebLoginEnum {

    /**
     *
     */
    QQ_MUSIC("music","https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=100497308&redirect_uri=https%3A%2F%2Fy.qq.com%2Fportal%2Fwx_redirect.html%3Flogin_type%3D1%26surl%3Dhttps%253A%252F%252Fy.qq.com%252Fportal%252Fradio.html%26use_customer_cb%3D0&state=state&display=pc"),
    NETEASE_MUSIC("netease","https://music.163.com/api/sns/authorize?snsType=5&amp;clientType=web2&amp;callbackType=Login&amp;forcelogin=true"),
    CSDN("csdn","https://passport.csdn.net/v1/register/authorization?authType=qq"),

    ;
    private static final HashMap<String,String> WEB_LOGIN_ENUM_HASH_MAP =new HashMap<>();

    static {
        for (WebLoginEnum value : WebLoginEnum.values()) {
            WEB_LOGIN_ENUM_HASH_MAP.put(value.name,value.url);
        }
    }

    public static String getUrl(WebLoginEnum webLoginEnum){
        return WEB_LOGIN_ENUM_HASH_MAP.getOrDefault(webLoginEnum.getName(),webLoginEnum.getName());
    }

   private final String name;
   private final String url;

    WebLoginEnum(String name,String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

}
