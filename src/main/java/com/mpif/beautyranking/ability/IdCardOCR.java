package com.mpif.beautyranking.ability;

import com.mpif.beautyranking.AbstractComputeVision;
import com.mpif.beautyranking.util.FileUtils;
import com.mpif.beautyranking.util.HttpClientV455;
import com.mpif.beautyranking.util.ResponseContent;
import com.mpif.beautyranking.util.StringsSortByDict;
import org.apache.commons.codec.digest.DigestUtils;

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
        picPath = picRoot + "idcard.jpeg";
        picPath = picRoot + "彭于晏.jpeg";
        image = FileUtils.fileToBase64Str(picPath);
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
            paramsMap.put(cardTypeKey, cardType);
            postParamsMap.put(signKey, signatureStr);
            ResponseContent responseContent = HttpClientV455.doPost(apiUrl, postParamsMap);
            System.out.println("接口调用返回内容为:");
            System.out.println(responseContent.getResponseContentString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
