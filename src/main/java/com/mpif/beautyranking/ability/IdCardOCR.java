package com.mpif.beautyranking.ability;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mpif.beautyranking.AbstractComputeVision;
import com.mpif.beautyranking.enums.ErrorCodeEnum;
import com.mpif.beautyranking.util.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author: mpif
 * @date: 2018-06-07 15:39
 */
public class IdCardOCR extends AbstractComputeVision {

    protected String cardTypeKey = "card_type";

    /**
     * card_type 是	int	整数	0/1	身份证图片类型，0-正面，1-反面
     */
    protected int cardType;

    public IdCardOCR() {
        apiUrl = "https://api.ai.qq.com/fcgi-bin/ocr/ocr_idcardocr";
        picPath = picRoot + "正面.jpg";
//        picPath = picRoot + "反面.jpg";
//        picPath = picRoot + "彭于晏.jpeg";
        try {
            image = getFileBase64Str(picPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cardType = 0;
    }

    public static void main(String[] args) {

        IdCardOCR idCardOCR = new IdCardOCR();
        idCardOCR.ocr();

    }

    public void ocr() {
        try {
            //计算签名是map中不包括sign, 共5个参数, 且map中的value都是进过URL编码的
            Map<String, Object> paramsMap = getParamsMapForSign();
            paramsMap.put(cardTypeKey, urlEncode(cardType));
            String sortedDictStr = new StringsSortByDict().sortedDictStr(paramsMap);
            sortedDictStr = sortedDictStr + "&" + appKeyStr + "=" + appKey;
            System.out.println("sortedDictStr:");
            System.out.println(sortedDictStr);

            String signatureStr = DigestUtils.md5Hex(sortedDictStr).toUpperCase();
            System.out.println("md5Str:");
            System.out.println(signatureStr);

            //提交的时候map中包括sign, 共6个参数, 且map中的value都是没有进过URL编码的
            //之前出错就是因为提交了进过URL编码的image, 估计是验证签名时又对image的内容进行URL编码, 所以算出来的sign不匹配。
            Map<String, Object> postParamsMap = getPostParamsMap();
            postParamsMap.put(cardTypeKey, cardType);
            postParamsMap.put(signKey, signatureStr);
            ResponseContent responseContent = HttpClientV455.doPost(apiUrl, postParamsMap);

            if(responseContent != null) {
                String jsonContent = responseContent.getResponseContentString();

                System.out.println("接口调用返回内容为:");
                System.out.println(jsonContent);

                this.parseImage(jsonContent);

            } else {
                System.out.println("responseContent is null.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseImage(String jsonContent) throws IOException {
        JSONObject jsonObject = (JSONObject) JSON.parseObject(jsonContent);
        int ret = jsonObject.getInteger("ret");
        String msg = jsonObject.getString("msg");

        ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.valueOf(ret);
        if(errorCodeEnum != null) {
            System.out.println("返回code=" + ret + ", " + errorCodeEnum.getDesc());
            System.out.println("返回msg：" + msg);

        }
        JSONObject dataObj = jsonObject.getJSONObject("data");


        String imageBase64Str = "";

        if(this.cardType == 0) {
            imageBase64Str = dataObj.getString("frontimage");
        } else if(this.cardType == 1) {
            imageBase64Str = dataObj.getString("backimage");
        }

        if(StringUtils.isNotEmpty(imageBase64Str)) {
            System.out.println("image:");
            System.out.println(imageBase64Str);
            String picOut = getOutFilePath(picPath, "IdCardOCR", -1);
            FileUtils.base64StrToFile(imageBase64Str, picOut);
        } else {

            System.out.println("image 字段为空!");
        }

    }


}
