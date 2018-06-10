package com.mpif.beautyranking;

import com.mpif.beautyranking.util.DateUtils;
import com.mpif.beautyranking.util.Md5Utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: mpif
 * @date: 2018-06-07 15:44
 *
 * 返回码
 * https://ai.qq.com/doc/returncode.shtml
 *
 */
public class AbstractComputeVision {

    protected String apiUrl;

    /**
     * 	是	int	正整数	1000001	应用标识（AppId）
     */
    protected int appId;

    protected String appIdKey = "app_id";

    protected String appKey;

    protected String appKeyStr = "app_key";

    /**
     * 	是	int	正整数	1493468759	请求时间戳（秒级）
     */
    protected int timestamp;

    protected String timestampKey = "time_stamp";

    /**
     * 	是	string	非空且长度上限32字节	fa577ce340859f9fe	随机字符串
     */
    protected String nonceStr;

    protected String nonceKey = "nonce_str";

    /**
     * 	是	string	非空且长度固定32字节	B250148B284956EC5218D4B0503E7F8A	签名信息，详见接口鉴权
     */
    protected String sign;

    protected String signKey = "sign";

    /**
     * 	是	string	原始图片的base64编码数据（原图大小上限1MB，支持JPG、PNG、BMP格式）	...	待识别图片
     */
    protected String image;

    protected String imageKey = "image";

    protected File imageFile;

    protected long maxImageFileLength = 500 * FileUtils.ONE_KB;

    protected static final String defaultCharset = "UTF-8";

    protected static String txtRoot;

    protected static String picRoot;
    protected static String picPath;

    public AbstractComputeVision() {
        appId = 1106879537;
        appKey = "HxbQwzb3CsnMbTjU";
//        timestamp = DateUtils.getSecondTimestamp(new Date());
        timestamp = Integer.parseInt(String.valueOf(System.currentTimeMillis()/1000));
        nonceStr = Md5Utils.getUUID();
        txtRoot = System.getProperty("user.dir") + "/src/main/resources/";
        picRoot = System.getProperty("user.dir") + "/src/main/resources/imgs/";

    }

    public Map<String, Object> getParamsMapForSign() throws UnsupportedEncodingException {
        String encodeAppId = urlEncode(appId);
        String encodeTimestamp = urlEncode(timestamp);
        String encodeNonce = urlEncode(nonceStr);
        String encodeImage = urlEncode(image);

        System.out.println("appId:" + appId + ", encode:" + encodeAppId);
        System.out.println("timestamp:" + timestamp + ", encode:" + encodeTimestamp);
        System.out.println("nonce: " + nonceStr + ", encode:" + encodeNonce);
        System.out.println("image:");
        System.out.println(image);
        System.out.println("encodeImage:");
        System.out.println(encodeImage);

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put(appIdKey, encodeAppId);
        paramsMap.put(timestampKey, encodeTimestamp);
        paramsMap.put(nonceKey, encodeNonce);
        paramsMap.put(imageKey, encodeImage);

        return paramsMap;
    }

    public Map<String, Object> getPostParamsMap() {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put(appIdKey, appId);
        paramsMap.put(timestampKey, timestamp);
        paramsMap.put(nonceKey, nonceStr);
        paramsMap.put(imageKey, image);
        return paramsMap;
    }

    public String urlEncode(Object obj) throws UnsupportedEncodingException {
        return URLEncoder.encode(String.valueOf(obj), defaultCharset);//.toUpperCase();
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public long getMaxImageFileLength() {
        return maxImageFileLength;
    }

    public void setMaxImageFileLength(long maxImageFileLength) {
        this.maxImageFileLength = maxImageFileLength;
    }
}
