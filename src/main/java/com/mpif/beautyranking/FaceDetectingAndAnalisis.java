package com.mpif.beautyranking;

/**
 * @Author: mpif
 * @Date: 2018-06-06 6:32
 * 人脸检测与分析
 * https://ai.qq.com/doc/detectface.shtml
 */

public class FaceDetectingAndAnalisis {

    /**
     * 	是	int	正整数	1000001	应用标识（AppId）
     */
    private int appId;

    /**
     * 	是	int	正整数	1493468759	请求时间戳（秒级）
     */
    private int timeStamp;

    /**
     * 	是	string	非空且长度上限32字节	fa577ce340859f9fe	随机字符串
     */
    private int nonceStr;

    /**
     * 	是	string	非空且长度固定32字节	B250148B284956EC5218D4B0503E7F8A	签名信息，详见接口鉴权
     */
    private String sign;

    /**
     * 	是	string	原始图片的base64编码数据（原图大小上限1MB，支持JPG、PNG、BMP格式）	...	待识别图片
     */
    private String image;

    /**
     * 	是	int	整数	0/1	检测模式，0-正常，1-大脸模式（默认1
     */
    private int mode;



}
