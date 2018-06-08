package com.mpif.beautyranking.enums;

/**
 * @author: mpif
 * @date: 2018-06-08 10:36
 */
public enum ErrorCodeEnum {

    PARAM_INVALID(4096, "参数非法", "请检查请求参数是否符合要求"),
    APP_NOT_EXISTS(12289, "应用不存在", "请检查app_id是否有效的应用标识（AppId）"),
    TEMPLATE_ID_NOT_EXISTS(12801, "素材不存在", "请检查app_id对应的素材模版id"),
    APPID_NOT_MATCH_TEMPLATEID(12802, "素材ID与应用ID不匹配", "请检查app_id对应的素材模版id"),
    APPID_NOT_EXISTS(16385, "缺少app_id参数", "请检查请求中是否包含有效的app_id参数"),
    TIMESTAMP_NOT_EXISTS(16386, "缺少time_stamp参数", "请检查请求中是否包含有效的time_stamp参数"),
    NONCE_NOT_EXISTS(16387, "缺少nonce_str参数", "请检查请求中是否包含有效的nonce_str参数"),
    SIGN_INVALID(16388, "请求签名无效", "请检查请求中的签名信息（sign）是否有效"),
    UNAUTHORITY(16389, "缺失API权限", "请检查应用是否勾选当前API所属接口的权限"),
    TIMESTAMP_INVALID(16390, "time_stamp参数无效", "请检查time_stamp距离当前时间是否超过5分钟"),
    SYNONYM_RECOGNIZE_EMPTY(16391, "同义词识别结果为空", "系统识别结果为空"),
    PROPER_NOUN_RECOGNIZE_EMPTY(16392, "专有名词识别结果为空", "系统识别结果为空"),
    INTENTION_RECOGNIZE_EMPTY(16393, "意图识别结果为空", "系统识别结果为空"),
    CHAT_RETURN_EMPTY(16394, "闲聊返回结果为空", "系统处理结果为空"),
    PIC_FORMAT_ILLEGAL(16396, "图片格式非法", "请检查图片格式是否符合API要求"),
    PIC_TOO_LAGER(16397, "图片体积过大", "请检查图片大小是否超过API限制"),
    PIC_NO_FACE(16402, "图片没有人脸", "请检查图片是否包含人脸"),
    SIMILARITY_ERROR(16403, "相似度错误", "请联系客服反馈问题"),
    FACE_DETECT_FAIL(16404, "人脸检测失败", "请联系客服反馈问题"),
    PIC_DECODE_FAIL(16405, "图片解码失败", "请联系客服反馈问题"),
    FEATURE_PROCESS_FAIL(16406, "特征处理失败", "请联系客服反馈问题"),
    EXTRACT_OUTLINE_ERROR(16407, "提取轮廓错误", "请联系客服反馈问题"),
    EXTRACT_SEX_ERROR(16408, "提取性别错误", "请联系客服反馈问题"),
    EXTRACT_EXPRESSION_ERROR(16409, "提取表情错误", "请联系客服反馈问题"),
    EXTRACT_AGE_ERROR(16410, "提取年龄错误", "请联系客服反馈问题"),
    EXTRACT_ATTITUDE_ERROR(16411, "提取姿态错误", "请联系客服反馈问题"),
    EXTRACT_GLASS_ERROR(16412, "提取眼镜错误", "请联系客服反馈问题"),
    EXTRACT_GLAMOUR_ERROR(16413, "提取魅力值错误", "请联系客服反馈问题"),
    SPEECH_SYNTHESIS_FAIL(16414, "语音合成失败", "请联系客服反馈问题"),
    PIC_EMPTY(16415, "图片为空", "请检查图片是否正常"),
    MAN_EXISTS(16416, "个体已存在", "请检查个体是否已存在"),
    MAN_NOT_EXISTS(16417, "个体不存在", "请检查个体是否已存在"),
    FACE_NOT_EXISTS(16418, "人脸不存在", "请检查人脸是否不存在"),
    GROUP_NOT_EXISTS(16419, "分组不存在", "请检查分组是否不存在"),
    GROUP_LIST_NOT_EXISTS(16420, "分组列表不存在", "请检查分组列表是否不存在"),
    TOO_MANY_FACE(16421, "人脸个数超过限制", "请检查是否超过系统限制"),
    TOO_MANY_MAN(16422, "个体个数超过限制", "请检查是否超过系统限制"),
    TOO_MANY_GROUP(16423, "组个数超过限制", "请检查是否超过系统限制"),
    SAME_FACE_IN_MAN(16424, "对个体添加了几乎相同的人脸", "请检查个体已添加的人脸"),
    PIC_FORMAT_INVALID(16425, "无效的图片格式", "请检查图片格式是否符号API要求"),
    PICTURE_BLUR_DETECT_FAIL(16426, "图片模糊度检测失败", "请联系客服反馈问题"),
    FOOD_PIC_DETECT_FAIL(16427, "美食图片检测失败", "请联系客服反馈问题"),
    EXTRACT_PIC_FINGERPRINT_FAIL(16428, "提取图像指纹失败", "请联系客服反馈问题"),
    PIC_FEATURE_CONTRAST_FAIL(16429, "图像特征比对失败", "请联系客服反馈问题"),
    OCR_PIC_EMPTY(16430, "OCR照片为空", "请检查待处理图片是否为空"),
    OCR_RECOGNIZE_FAIL(16431, "OCR识别失败", "请联系客服反馈问题"),
    NOT_IDCARD_PIC(16432, "输入图片不是身份证", "请检查图片是否为身份证"),
    NO_ENOUGH_TXT_IN_CARD(16433, "名片无足够文本", "请检查名片是否正常"),
    CARD_TEXT_TOO_INCLINED(16434, "名片文本行倾斜角度太大", "请检查名片是否正常"),
    BUSINESS_CARD_BLUR(16435, "名片模糊", "请检查名片是否正常"),
    CARD_NAME_RECOGNIZE_FAIL(16436, "名片姓名识别失败", "请联系客服反馈问题"),
    CARD_PHONE_RECOGNIZE_FAIL(16437, "名片电话识别失败", "请联系客服反馈问题"),
    PIC_NOT_BUSINESS_CARD(16438, "图像为非名片图像", "请检查图片是否为名片"),
    DETECT_OR_RECOGNIZE_FAIL(16439, "检测或者识别失败", "请联系客服反馈问题"),
    UNDETECT_IDCARD(16440, "未检测到身份证", "请对准边框(避免拍摄时倾角和旋转角过大、摄像头)"),
    NONE_SECONDARY_IDCARD(16441, "请使用第二代身份证件进行扫描", "请使用第二代身份证进行处理"),
    NOT_POSITIVE_IDCARD(16442, "不是身份证正面照片", "请使用带证件照的一面进行处理"),
    NOT_OPPOSITE_IDCARD(16443, "不是身份证反面照片", "请使用身份证反面进行进行处理"),
    CERTIFICATE_PIC_BLUR(16444, "证件图片模糊", "请确保证件图片清晰"),
    DONT_DIRECT_LIGHT(16445, "请避开灯光直射在证件表面", "请避开灯光直射在证件表面"),
    DRIVING_LICENSE_RECOGNIZE_FAIL(16446, "行驾驶证OCR识别失败", "请联系客服反馈问题"),
    COMMON_OCR_RECOGNIZE_FAIL(16447, "通用OCR识别失败", "请联系客服反馈问题"),
    BANKCARD_PREPROCESS_FAIL(16448, "银行卡OCR预处理错误", "请联系客服反馈问题"),
    BANKCARD_RECOGNIZE_FAIL(16449, "银行卡OCR识别失败", "请联系客服反馈问题"),
    BUSINESS_LICENSE_PREPROCESS_FAIL(16450, "营业执照OCR预处理失败", "请联系客服反馈问题"),
    BUSINESS_LICENSE_RECOGNIZE_FAIL(16451, "营业执照OCR识别失败", "请联系客服反馈问题"),
    INTENTION_RECOGNIZE_TIMEOUT(16452, "意图识别超时", "请联系客服反馈问题"),
    CHAT_PROCESS_TIMEOUT(16453, "闲聊处理超时", "请联系客服反馈问题"),
    SPEECH_REGONITION_DECODE_FAIL(16454, "语音识别解码失败", "请检查语音参数是否正确编码"),
    SPEECH_TOO_LONG_OR_EMPTY(16455, "语音过长或空", "请检查语音参数是否正确编码或者长度是否合法"),
    TRANSLATE_ENGINE_FAIL(16456, "翻译引擎失败", "请联系客服反馈问题"),
    UNSUPPORT_TRANSLATE_TYPE(16457, "不支持的翻译类型", "请检查翻译类型参数是否合法"),
    PIC_SENSE_NOT_MATCH(16460, "输入图片与识别场景不匹配", "请检查场景参数是否正确，所传图片与场景是否匹配"),
    RECOGNIZE_RESULT_EMPTY(16461, "识别结果为空", "当前图片无法匹配已收录的标签，请尝试更换图片");

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误码描述
     */
    private String desc;

    /**
     * 错误处理意见
     */
    private String handleAdvice;

    ErrorCodeEnum(int code, String desc, String handleAdvice) {
        this.code = code;
        this.desc = desc;
        this.handleAdvice = handleAdvice;
    }

    public static ErrorCodeEnum valueOf(int code) {
        for(ErrorCodeEnum errorCodeEnum : values()) {
            if(errorCodeEnum.code == code) {
                return errorCodeEnum;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHandleAdvice() {
        return handleAdvice;
    }

    public void setHandleAdvice(String handleAdvice) {
        this.handleAdvice = handleAdvice;
    }
}
