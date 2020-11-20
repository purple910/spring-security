package com.example.demo.alipay;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName AlipayConfig
 * @Description
 * @PackageName com.example.demo.alipay.AlipayConfig
 * @Author fate
 * @Date 2020/10/19 14:30
 **/
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String APP_ID = "2016102600764637";

    // 商户私钥，您的PKCS8格式RSA2私钥，这些就是我们刚才设置的
    public static String MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfXiUjCZjKteDOvpovWBR8z8KUhNqAbxYDcwO10i4BimEuwnCE1Vyt7gi2gCoGGAYSQFkDSv/zvTW7og36rldt1S8UL5u3QhatzLwBhmejO3NLyl65Edt+k5WeLIR93S4BOrPJvkBEaarkYHCw6y9yFt/88tevmHJZK8w2wZQUy7C7663dhDEZ04Yd+KIpnVx58qYGROVqV9cB7PaW4ASLY1XIML+EVVSCyiYQKAPGkLDk/H91muZG3gcP4CmrnyhPJIPtgMXwiBNKasWJHIcyIKHsk3HFU020dy6B6z+dG1PxEOpNCpr8s4yxMTwx24lfRD2urbAPHNgSdNjwz2ctAgMBAAECggEAOlPIn8Q5aaebec+EuuMvzGZ1vsnI9syxb92+c+NS9zlgTC7Z46P7YaUHB1RtDQHp5vAqI+xlNR4A9oVfPGpmkUo/CtJJ2Y6UolOWU7CPacyubATLGk0Q2ojFb7i3XSGAXu7PMn0xJFjQNPmfBQFuwgDhRcDTc+8FmuFOwpOi0OEPHx6SgEKT3mpihQtls0Nkm6jdB7wcVrmH5SqEIJ9l36Te4c0Mffx8RkxqfYbK99bROi3Mwp1WRT2qRmOoFJFrWRobeKqkARLU+zP188hj+B2YiI8IuYzN8keUoKnluoVZLyZKD1nI1xnB+SO9kuBiBdGChpUIvr2V4Tm7iE45wQKBgQDinSM8EPtyt2S1ZBcGR5q4kbJEcC+OdcuNbI4qqTISYwomaT5ozhklnU6E3cKDWDtdB0thMUOUjouCNPJCESEoGO0wBg323FBVEcoTWdkz3cDTVkL6c12vB7UDQJDzk7vORNyfeujefHzu+gu7sXGkykQaclNS8zfjrpgzVep79QKBgQC0CKaSpChjL0khSork9rmz/Z/PkyAeVlsUKzwTFUft2yH6rqyDdoHqxg33zB15ySP8ccGxokLyJhVDALvCKRvVG+yGp/cEfTrm0g0ZVIdxSy3JcPFFg6LYo293OOpgez3MiQ2qMphU24uq5cTMRcb45qysBMB9NyY+qi3WkmazWQKBgHImHT2AgGiEoKIOTNrI7tLR8S4wJIRqKaKPb/1lROemI9K9aMZ6Koib+JVM71Ih+6Ucq4fFvPjz5Kot6WoGHuGHzNJH+njC+WQgld2xgItzxFGwPgTSX/pus2tz9o/7drqsTUW4L90CcBwgsBBXjZsol62KsRYzlY+Gvvv85ySRAoGAWwpI2bzjo4yE6a3dYP2XWPq3duuKgWzf7+oRHgFT92cp3q+4E165QfTXmWiBu1VIul74ORC50+QIOnLDf+Lz7SeKy2li08VUJOcxso55U4RV6hkQGTwA/JZhRqPkede4n5bq7xdYywier1Qz2ayrerCDvxVEFc3urB+4wFoWTzkCgYEAqFwASHWf2PuLxSJInsvAeE2r+U6lltjoW/4WP/Hucl/pVwxVowhmQxcD+HDMc7TevKj4JV4RpuLt6WF63OlFuo7oTN6YYYJCZD4pZREc9MrxxAs8CO8umrT5DYCjNftVLsQjfylnjy1PNUHQKpGdx5oYTcDzWQ3Qsa4w5cJrOX0=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。，这些就是我们刚才设置的
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoByDozjA4sOcD+6ESzycmoqqRK9DG69KSsxF6vaGp7v5NrIOqZiiMeCQtf//h2Oj0HtsV7mTW7WMLOwfZIeaoNlXqtLXDpy0CDIB4/bsVG6EEkV3W/Irw/NCWRvCpb2HIfE2INz3wzYu3lRI29ZqNV0cJ2FPdIJFA/fbTeKP+GHuK5FXb144vnxNXuYPRiOcUtkW3Q40PVUsCRie0vUypygpOP/TuYNnDUv2ecNrtshjJRw4501QQlrDPNTQCJnl+kgfBipQGSuVSNB5CVsCO3PYn7WizlAZvArvReAOP28+BHZO+FUBra5YL4mvf8fECxE4vtXkuJwZC1YzlBODewIDAQAB";

    //异步通知，再这里我们设计自己的后台代码
    public static String notify_url = "http://localhost:8080/alipaynotify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/alipay/return_url";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关
//    public static String GATEWAYURL = "https://openapi.alipay.com/gateway.do";
    // 沙箱应用(测试)
    public static String GATEWAYURL = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String LOG_PATH = "E:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(LOG_PATH + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

