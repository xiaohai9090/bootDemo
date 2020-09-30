package com.springboot.bootdemo.controller;

import com.springboot.bootdemo.domain.Config;
import com.springboot.bootdemo.wxPaySDK.HttpsClientUtil;
import com.springboot.bootdemo.wxPaySDK.WXPayUtil;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payItems")
public class PayRequest {

    private static final Logger LOG = Logger.getLogger(PayRequest.class);

    @RequestMapping("/orders")
    public String orders(int totalFee, String clientIp){

        try {

            // 必填字段
            Map<String, String> data = new HashMap<>(11);
            String money = String.valueOf(totalFee);
            data.put("appid",Config.APP_ID);
            data.put("mch_id",Config.MCH_ID);
            data.put("nonce_str",WXPayUtil.generateNonceStr());
            data.put("body",Config.BODY);
            String orderId = "生成";
            data.put("out_trade_no",orderId);
            data.put("total_fee",money);
            data.put("spbill_create_ip",clientIp);
            data.put("notify_url",Config.NOTITY_URL);
            data.put("trade_type","MWEB");
            data.put("scene_info","json");
            String sign = WXPayUtil.generateSignature(data,Config.WX_PARTNER_KEY);
            data.put("sign",sign);
            String requestXML = WXPayUtil.mapToXml(data);

            // 微信统一下单链接地址
            String unifiedorder_url  = "https://api.mch.weixin.qq.com/pay/unifiedorder";

            String xmlStr = HttpsClientUtil.httpsRequest(unifiedorder_url, "POST", requestXML);

//            <xml>
//            <return_code><![CDATA[FAIL]]></return_code>
//            <return_msg><![CDATA[商户号该产品权限预开通中，请等待产品开通后重试]]></return_msg>
//            </xml>

            Map<String, String> map = WXPayUtil.xmlToMap(xmlStr);
            String returnCode = (String) map.get("return_code");

            //以下内容是返回前端页面的json数据
            String mweb_url = "";//跳转链接
            if ("SUCCESS".equals(returnCode)) {
                mweb_url = (String) map.get("mweb_url");

                //支付完返回浏览器跳转的地址，如跳到查看订单页面
                String redirect_url = "https://www.xxxxx.com/orders";
                String redirect_urlEncode = URLEncoder.encode(redirect_url,"utf-8");//对上面地址urlencode
                mweb_url = mweb_url + "&redirect_url=" + redirect_urlEncode;//拼接返回地址
            }else {

            }

            return  "redirect:https://www.baidu.com";
        } catch (Exception e) {
            e.printStackTrace();
            // 支付异常
        }

        return null;
    }
}
