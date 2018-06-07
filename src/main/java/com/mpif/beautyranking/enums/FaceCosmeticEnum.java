package com.mpif.beautyranking.enums;

/**
 * @Author: mpif
 * @Date: 2018-06-07 23:29
 */

public enum FaceCosmeticEnum {

    RXZ_BBF(1, "日系妆", "芭比粉"),
    RXZ_QT(2, "日系妆", "清透"),
    RXZ_YH(3, "日系妆", "烟灰"),
    RXZ_ZR(4, "日系妆", "自然"),
    RXZ_YHF(5, "日系妆", "樱花粉"),
    RXZ_YSH(6, "日系妆", "原宿红"),
    HZ_SL(7, "韩妆", "闪亮"),
    HZ_FZ(8, "韩妆", "粉紫"),
    HZ_FN(9, "韩妆", "粉嫩"),
    HZ_ZR(10, "韩妆", "自然"),
    HZ_QT(11, "韩妆", "清透"),
    HZ_DDS(12, "韩妆", "大地色"),
    HZ_MG(13, "韩妆", "玫瑰"),
    LZ_ZR(14, "裸妆", "自然"),
    LZ_QT(15, "裸妆", "清透"),
    LZ_TF(16, "裸妆", "桃粉"),
    LZ_JF(17, "裸妆", "橘粉"),
    LZ_CX(18, "裸妆", "春夏"),
    LZ_QD(19, "裸妆", "秋冬"),
    ZTZ_JDFG(20, "主题妆", "经典复古"),
    ZTZ_XGHX(21, "主题妆", "性感混血"),
    ZTZ_XCMM(22, "主题妆", "炫彩明眸"),
    ZTZ_ZSMH(23, "主题妆", "紫色魅惑");

    /**
     * 美妆编码
     */
    private int cosmetic;

    /**
     * 美妆类型
     */
    private String cosmeticType;

    /**
     * 美妆名称
     */
    private String cosmeticName;

    FaceCosmeticEnum(int cosmetic, String cosmeticType, String cosmeticName) {
        this.cosmetic = cosmetic;
        this.cosmeticType = cosmeticType;
        this.cosmeticName = cosmeticName;
    }

    public int getCosmetic() {
        return cosmetic;
    }

    public void setCosmetic(int cosmetic) {
        this.cosmetic = cosmetic;
    }

    public String getCosmeticType() {
        return cosmeticType;
    }

    public void setCosmeticType(String cosmeticType) {
        this.cosmeticType = cosmeticType;
    }

    public String getCosmeticName() {
        return cosmeticName;
    }

    public void setCosmeticName(String cosmeticName) {
        this.cosmeticName = cosmeticName;
    }
}
