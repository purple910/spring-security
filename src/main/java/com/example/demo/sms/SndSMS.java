package com.example.demo.sms;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Set;

/**
 * @ClassName SndSMS
 * @Description 发送短信
 * @PackageName com.example.demo.sms.SndSMS
 * @Author fate
 * @Date 2020/10/17 11:40
 **/
@Configuration
public class SndSMS {
    @Bean
    public CCPRestSmsSDK smsSDK(){
        return new CCPRestSmsSDK();
    };

    public boolean sndMessage(String mobile, String code){
        //生产环境请求地址：app.cloopen.com url = 'https://app.cloopen.com:8883'
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "8aaf0708732220a6017433b594b3741a";
        String accountToken = "bef2b9290a934cecb99bf9d55a5c2759";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8aaf0708732220a6017433b595ac7421";
        CCPRestSmsSDK sdk = smsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        // 模板
        String templateId= "1";
        String[] datas = {code,"60s","1234"};

//        String subAppend="1234";  //可选 扩展码，四位数字 0~9999
//        String reqId="fadfafas";  //可选 第三方自定义消息id，最大支持32位英文数字，同账号下同一自然天内不允许重复
        //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        HashMap<String, Object> result = sdk.sendTemplateSMS(mobile,templateId,datas,null,null);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
            return true;
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
            return false;
        }
    }

}
