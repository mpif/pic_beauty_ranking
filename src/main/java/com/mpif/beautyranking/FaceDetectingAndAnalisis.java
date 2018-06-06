package com.mpif.beautyranking;

import com.mpif.beautyranking.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import sun.security.provider.MD5;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mpif
 * @Date: 2018-06-06 6:32
 * 人脸检测与分析
 * https://ai.qq.com/doc/detectface.shtml
 */

public class FaceDetectingAndAnalisis {

    private String apiUrl = "https://api.ai.qq.com/fcgi-bin/face/face_detectface";

    /**
     * 	是	int	正整数	1000001	应用标识（AppId）
     */
    private int appId;

    private String appIdKey = "app_id";

    private String appKey;

    private String appKeyStr = "app_key";

    /**
     * 	是	int	正整数	1493468759	请求时间戳（秒级）
     */
    private int timestamp;

    private String timestampKey = "time_stamp";

    /**
     * 	是	string	非空且长度上限32字节	fa577ce340859f9fe	随机字符串
     */
    private String nonceStr;

    private String nonceKey = "nonce_str";

    /**
     * 	是	string	非空且长度固定32字节	B250148B284956EC5218D4B0503E7F8A	签名信息，详见接口鉴权
     */
    private String sign;

    private String signKey = "sign";

    /**
     * 	是	string	原始图片的base64编码数据（原图大小上限1MB，支持JPG、PNG、BMP格式）	...	待识别图片
     */
    private String image;

    private String imageKey = "image";


    /**
     * 	是	int	整数	0/1	检测模式，0-正常，1-大脸模式（默认1）
     */
    private int mode;

    private String modeKey = "mode";

    private static final String defaultCharset = "UTF-8";

    public FaceDetectingAndAnalisis() {
        appId = 1106879537;
        appKey = "HxbQwzb3CsnMbTjU";
        timestamp = DateUtils.getSecondTimestamp(new Date());
        nonceStr = Md5Utils.getUUID();
//        img01.jpg:
        image = "/9j/4AAQSkZJRgABAQEAYABgAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a" +
                        "HBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy" +
                        "MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wgARCAIJAUwDASIA" +
                        "AhEBAxEB/8QAGwAAAQUBAQAAAAAAAAAAAAAAAQACAwQFBgf/xAAZAQADAQEBAAAAAAAAAAAAAAAA" +
                        "AQIDBAX/2gAMAwEAAhADEAAAAe2SGdFBAUEBQQFBAUEBQQFBAUCxJIEQgKSEkENEARQQyEAKCBJI" +
                        "EEAaxzJbUhLugi5SCAoICgQSSBJIEkgSSBEEEkGEKMJVzXOo9GXk7g9WXluuHdrH1mOBAEJAkgCa" +
                        "5oNY9ktrS2HeBGkpJAkkCSQJJAUEBQIJJAiECS5QNHiakY2tfGIta1qR0CZY6HAtI9Iu+RdijrEk" +
                        "2gkABCGxyRptaRDvBLSUkgSSBJIEkgSSBEICkgQNAMniZYFQaoWnxAuWteGNRAp9bI2E8WWBzXqG" +
                        "v5p6SmUgmmuaNsb2SNBEu6ktJQIBIECkgSSBJIEkgKCBvn29xM0WuqsajLcRNe1Aa4NNTgEu7ibs" +
                        "Xzriy4Pp3lvTJ+hhJNMc0bY5IpACId5IayQkhJIEkgKCApJiSQKvPxyeDXDJuOtLW0zllPRj5272" +
                        "2jFcMu6bN8QO2jT4sdTzzXOFDXCC1Wez2GSjelhrmDEUkcNqCl3whpBQQFBAUEBQQFJAQgGVwmni" +
                        "TbqklWkb8XoaZ0myzaBDTQWzSa5oQZevXiuDzOw5LbCFjhpHr1uKWWGuaNkb44YSUu8ENIISBJAH" +
                        "IICgQRBAY+ty4+OLqiqOWvbpdR1efp5bU4MfLqezt8Bvp9CIHy5BXiC1E0S6vDd7xtxhOuVd8fWr" +
                        "mVqyNaQnGx8ctICXfCGkFBAiENEEEkgITBMydjMT85pSR0TWImqvUb9K9npi4XZ02clobUqbormZ" +
                        "Lz8bV5K897SwdFPXwOk4UMuepc3x9O06N6G0JoMjcyGghL0AhrJCQIhAUCCIQJrmgM63yqfImfNp" +
                        "SQuFT6xp4O5j0FrmA0semc+5VluzdZoc/q2WJ5vnnSc1vg25RsXHrlnnd2G9jowZG5sMICXogjWU" +
                        "kgKCAoECEANaDlgs0M2uqZTswXL4Xsa7frvM/SctXxkTbJoIxyV608VZQTUdC5xwsMR3OjnpHqsi" +
                        "XHr4l0Oxu8Bel9mzPgh6NXCiT9ECW2aSQJJAkkCaQHOcd3/no4p3RDptcLhydaTqencV0+Wu1WnZ" +
                        "GlZ+TTXb0cnL75zWo25ZnFyDxtjUndBcbOFO5HWdD51sZ69lmVNma5A9bzAo3ukh9qEujBIICggK" +
                        "BAYW7wqeIgwbq807UU3VxRpi29m2qz9aSREcsaTjo6sJdMmvKr8l0/O6ZyVIJ9IgPT0RYTq/TOcG" +
                        "1HAPpbvM9HlrtUL9eayJI3wdoEOnnKCAoMCRVJQl5jpo1WW/YdGmP596xg3GZ0fmfqAB0immJwTZ" +
                        "FMyXBFYaqqqykV4NEM5wdO+p4+p3PI3GB2uH16M3ju3zFXKbGVLcddNnaGOuU4Pk7BJdPOkkCrWQ" +
                        "HH39jNVU+k53o07Dg8bY5GB5zq7nnd5+rGN8aNa9ibWPbNRpyTa4kAXFyg9NRcD6B55c7PR5mllp" +
                        "WZK8fn8F7M1y393muky0oEiDsEh04EJAkABQQFATVl0Tyi0gB5v6RzjkdF576ENNc2aYx7ZpqKTT" +
                        "gWi4OaSIE3gu/wDPanrVJlZa6skaa5Tnut5vXN/XcL0kO2gstOvCXTzpBAggBSQFpCp8kMkW9BNK" +
                        "GWvL8x9U887vSLALYsJJMApMFFhe1zkoqkzz70Dz8XaZ1yrltdkc5zU43uc1nBS6+VplsXOZ2Mr7" +
                        "wIbZJIAkkCSQJApiSOTPV5CqW0bmXF8x2nM9hpmGuEW1EIAIG5wdST2uaILBRef9fwDXYWcXaz10" +
                        "y0tNilhTp5WnmSZFDo8DSPUEhpkkkASSEkAKCBSRPjWVBMix9bm4s9Ty/UVmkUqDXtQ1rq6dp8ct" +
                        "olGpbHJWl89wu9g6Zb3T+e9/Gmq4GbZBPVVUqU1WU7D1sS59PCGmKQAFBAkEMlpBECbnSQ6/Idbw" +
                        "YbHXcj2KQTkNoICOjfz4vQmhnuCiKlmXqc0nxVeRbYt9B8/9Cy10klGkdWzUmsmN+aTDeFkOuaht" +
                        "igkhJJiQQJBA9pbNWECqqee+hec1O13fl/pQrAIVAOYmzP0M6a0Z4ZrlwIcx8B3fmbWa6OTTOT0D" +
                        "ge9y20whGkVK3Sl5kE6lNIQdc0jfBJBhASEggSDAcIkqvSQTq6vnvonm9RJvc+649RWfejRNcyWz" +
                        "M0Mua1bOTqXMjDG1k+edVz9xVkdVcy99xHbZa6TXMjSDE2OVDHsUjpnrT4amvVQheSCAIJqEkwDG" +
                        "GJlRpO/Zz7qscP3PN0uRBh1w67rvL+rjTp468eej8W1zQaPV+W9jc9LDlcshZ9S1plFo6O5GsUtx" +
                        "2eskaroh4np+K0yeA7SWlFHq7SEg1NQg1ibmKMZjbCiRlfOT39bge3V2s6xA3wdPpsLXCN1YVG3b" +
                        "5cK9rKvWjfFk2tJTzdvrLszg694zcTixMhkSHZ8udLzObsQdXKej5v0dPhIe64ab9UBCljHRg0CN" +
                        "NQNxlWjl0RNInUTk6U2abIL6aw4ehLXJHrQ1zDOko1vzOnsW1tRuvZPIkyNEkcUEueKrlhsVeXzb" +
                        "z6HGpu0zDUri93/I6uXp7WLoy5c+kwx6YCF0abc3Q5ZVGyF8XIgU1sZFgfV6kUmilMZcyGMNSNja" +
                        "guznp3I6OWnvRclRa7SnxUFT1OfitqLdV0jmBPjqWrRurTDl3kdRv17S9LUmaMfLtxujvnYx8acX" +
                        "P7uCqy3NSp5YZJp6rlXa6PIdLRbEbaUjYIE7Vapop0J9ZNYPM95ni80GvkdHMi0tPc2VNrLcyedN" +
                        "oPRr7HGzRp14FyNcRu3WrpRjdOVyN0dcrY3xJxYuzjDyCFNOCQFzSh8tU6R2ljknq9/Cyq1T3up5" +
                        "v1010bqtgG1b0aeNwvpNKp82n0VplVntSTVWaeQI3yPTgiuNDD7nlomu4jdFlsyB9UNSN8V4ticx" +
                        "NuPr5I8VJTRQQFBArFXUuLBuSl50OvEGBQ6LJqNrqPLNYfpJzNCKFa6wMjmO2zWc9KpHLXAA50ZB" +
                        "wDUNxNjKqe3VCfPYZNjmg9BhkjrJjXMQ3N0soeGQkymobg1MW/hdI5t2IrSuFlmJFHI3MprAjm6L" +
                        "XKv0+fXy26qXA1R2IJ2NY2V09IMONrXD1E1E7YowdmWaVT18bMCNTXjkc9+xzFLWKMDSgyBwgBjk" +
                        "EMpJE3Tc90rVuw2UpkM0IVMrSyAp9Lk7rThOJrLuRZQ+0fFOKGleol4Gb0PLPKZpqtWIo67k2KMj" +
                        "UsDyqD06X3UJy0WsWsxMNeE4A9Woy4NEsnDU3KN5VcdVI5IRVFHh3cSo6jVy9FVabG1VXxdbLDsJ" +
                        "qk6p2fq5KqDkO2xqVrj+74RkAJvlJRTLmmW5zCq1qLhnSDgMBwFECLECgGlnbNToyMerCjaMUpaT" +
                        "ipmW6emPV6uDuRrJFLFGkdG/Va3nQWi7daWFPNrtxC+s4vuuBedMg6cznMllpxcmwSAP/8QAMRAA" +
                        "AgICAQIFBAMAAAYDAAAAAQIAAwQREgUQEyAhMTIUIjAzI0BBFSQ0QkNgNURQ/9oACAEBAAEFAv8A" +
                        "1E/+hMwQX9WRJb1HIeHIcz6m+Ln5KynrNqzH6jReAQR/+BldQSiZGXZc2yZqa7bWDU/3AzDjsmTU" +
                        "5/vZ/UuMJJ7b77E3PSL6wp/GLNTpvUfE/u9SzfCXuT5kn/1z7ofXp+X9VR/ayb1x6rLC79ifOnuP" +
                        "WgwTp+R4OQP7JOhn5Xj2gQxjshfJ6d09wp+nPv7wNo9OyBkYn9jqeTwT5MTCYJvvqampqVj7nPGh" +
                        "vWAxp0a7w7f69tgqryLDbd7KxjfLt7xMOx4OnPP+HmDAMOE2hjcZkk8d+rAT2mM/B0blX3P9PqGT" +
                        "znuWM3F9ZXU9rU9L3KcGquCsCcROInAQpHrmRX6N6H/JV+7DO8T+rmXaF7839gxjeleJhvknHxK6" +
                        "EA8xhEur2MiruDp8cccf+rk5Posdp7muk32Y1QqrH4DGG5kVS0cW3F14i+39TqGX9LQwbkToMYo0" +
                        "Om0/bx9DVPFesploZyBm/Nau1yRqw+yjTYzc6P6e51VWdGblGaD1MwV40N7ZN2ZysbMx4mZzevkI" +
                        "vtDYqzx654imExpnrqywivFH3TB34P8AUyPWtzqH1iSscmxhqqZFLFs5GulONWlGJvgvs7alpEa3" +
                        "H3Vahn8ggJIy/wBjnbU/LE9E/pGN7XrtX+QnsEtNduP61QiNVueAItQWa9G+5mqFl7rxtvw+FWGb" +
                        "NewzLvEslPyq9/6ZmS2kuH8vokZu2Gd43lb0Vfdl2Ho9Ti2O1dfBcyzw8du1H7K/XyH+hZYKlvse" +
                        "9sheFg7GdMfnhd99rPivkJnUr+TntU3B8PKXwwwP9K21ake76l7b0rW6AT2h9+i2fZ2MWCWmDyX2" +
                        "iutnNlnhloa2gUxWesUdRKxM1WC2q03NwtqHIrB/FlZIx6rLOTWXbgG5adv2926fd4OaO7JueqRn" +
                        "bY9e7GdRv5Msp+QxUltyK3i2NFxMiweDk4xp6gYjhlsygoe1rJr8W51Bdi1uQE+K+5nsq+kAPLEu" +
                        "8bHJjXiufXVQZFbAFW7kzLyRUH+5v9B1DkscaYGMtSA9rsKqyWUWYh1NTX48m2mtG0bFWWMJ79hK" +
                        "6eYWmYS+FXHUOr1qC1QlXi1shbRMyckVI5aw75BkgMB4wjcrudSmXlLKs/cS5HjIHBrNTfj6lca6" +
                        "dkz2nL04lycdtDElWODEpZZVTFXUHZ6+UbGWLWqTcvv8JPEa61ZYqwklfCeCe095TkvUQEyBUvKJ" +
                        "Z6ZKen486wtcWmiY9bquJYos8FeIxyYlHGCuBe4PYmEwyyrlLcZkO7N1UtY6YKBHuFaj3GEfBtpa" +
                        "vtRaa28TTezZPw/Gcbxr/oUn0aCHEVlyqGxb+mXC9eM1NeQz1nqZqanGNUDPpWBbHsaDCtMzMVMe" +
                        "jFq8XI4+CtlKsl9BpYe+O8x35V3j7POWCz6msEWK3ZUAbU1NTqWJ9Rj0WtRbWy2V6/BqampxnGcZ" +
                        "qdZ9ulr9+vSgaTKp5VanMqcNgbHg891fiV1W3Y7jqdLBuoVVys8q/J1LG+nyuj5Hp5D5ddtdzOqN" +
                        "zy+mJqqe2Rascam/swSfEb0UfgsprtlnTanP/B64g4r5M/H+pxaLTTcjB0/KZn0eHl4ScKI/yf2y" +
                        "V1evtgn+W34fjEHl6nR4GZ0i7xMb82WfE6yn22FvVvgZmrrNX0bFbhfcfT8f+jy9Yp8TE6Vb4eZ+" +
                        "av8Ak61cuxTbu1/1s6zOrZq71/m3pq7+afkHlsUWVJunJB2v5DMH16pZ7Yo2hqTXECOnJbquII0V" +
                        "bRqt5D8Y8u9N1BOHUMU8sX8je3T/AP5Gw/bi/r7344tluA0sosqiPqLbtfxDyGWH16l69QxRrF/I" +
                        "50uCddRyrONWP+sdzHj+pupm/wAg7mXH1yh4nVfOPNmWcKcZ+GZnN9uP8B3MfsZYvGz8X+92l5+7" +
                        "HHi9W848pnUn/hMezxacf4jsY0sOlhl37Pxju0zW0vSzuDzMdQeVzoZ1ni2NFdhMX4djGmQdIJY/" +
                        "FdM/4z7Du0z29Ome4813xXy5b8KbG2fef+TG+HYxpk+va1vFcLofi/wd39s1t3dMMHmv+KwdzOpt" +
                        "rH16r8x+7G+HYxpb+y12Y11CsfkEHaz45R/5jprBXX28uR8V9u5nU7eVs/7P/LjfHs0bcPy46s3+" +
                        "X/R2t+OV++g8TQ62VeXI+K+3exuKXuXecfsH/UY/xhhlh4rvf5twGDtZ8btfVOAJhX/TWb35cn9d" +
                        "Z2vfOvFVbbLeFqXD1r9cmj2hjTOfWPsiDIsEXKn1Ff4tzc3F7WfF9rkBvEU2NrEfni9zMk/ZiPyo" +
                        "HYzqg5Pa3Ky33Zi7V0MrY/tDGnUX+7c3+dD2b26kn8nrWxs23TcgIQexMYzIf7enZEUzcdgFyckv" +
                        "kL6sx2cfHJKVDVa+G0MczLfllfj35NyswQzqFfKleXBfcfbMPN8QeIIbJZZMu/0rco2Pmq62Za1j" +
                        "Jy2shbcrptslGDxiU6gWa7MZbZxVzsj8u5ublbjmO1w2LV8G5ho8hoPwZeoOJ9YCLMrcbkxHuC0C" +
                        "2tEwmeU4KrEoAgTXkYxzuZ9nFIJi0V5WPZU1T/g3NwmFwJZkyuwraGniCOwMvo8SPRZVP9Pv2rxH" +
                        "ZRVWIKA8qw/RcYCLUBNa7bm5uFozS25alssNrnti1JVTmYwvrIKnzEwmWWqgfKJnqe1GBZZEr4rx" +
                        "jVAxg6w1Foem+JB0mtZd09Bd9PVUGLXtXikirGWsce+4TNwtC0e5UFmeIXNra7VY7OQft3L8Nbbf" +
                        "M7BRZlEw+p7YVQe7nFE1NTjOM1NSyvkr4oeJjJXNdtzc5QtC8NoEtzUSWdQdoXZjC2+9K+JMViJy" +
                        "8p7ZGSK4ztYfJRfXXVjIYPPs63NzlOcLw2iPkosfqCRs1zHuZpvc4wjXfRMFZExYv7UpAh7GHsfQ" +
                        "ZZ/nm5vvSvK7vubm5uFo1h3zjWgSzqFKRuqCN1Gxo2RY85TlNsYE7b1N7leIzQYBn0mguOzOo4XM" +
                        "vqjbQww92mZrygxT64t/ipvtubnKF41k4WOfBj0CZmMR5dzlOc2TOJlf2NRkU2QLOE4SzFLOFcSt" +
                        "uSw9zDMz282FZwdLFdd9iYdzkztXUBOM1GWWVzMxfBbuFgSCuCuCueHDSDKsq7FNF6ZCTUevcrrF" +
                        "Q8hhmYPt83MrMe9qjRkLYC0e5UDZ62WUleKnuRGEsrDrlYrY9gWBIEgScYFgWcZxjICFazDux71y" +
                        "KuxhMPkMzPbzH41z1hvu1ZzsPhynIsxzjZaXBW7kRlltS2pbjGiwLAs4zjNTXfUsTkMC40ZMMJhP" +
                        "r5c34+Y+1I9Ak8OeFDXHrn3VtidQDRLNwHsRCsuqFqNWa2E152mR9r1vzqJjGWXhXh7mZnw8x9qB" +
                        "9qrOM4wrHSWJCJi9Qao03rYoPbUZZfQLF1o9999xpkfDEP8AyhMts4KTyJ8uV+nz0j0E1NQiOssE" +
                        "f0ldRtagPjyjIFgDTc1GWXU8ofQ99zc3GMuPpV9lDvoW2+I0Ply/1+YfKr2UeuuxjS2MOdlFIReM" +
                        "NZU05HIq3YwiXVCxCZubm5ubjNP2XM4AuvNh7Hy5GvCPmr+dfsg7mNLvbFXlaggEIlqTFu5gdiPQ" +
                        "TLThbubm5uFo7SnVIstaw+Umbl2VxjWuZy81A26D0UdzGl5+3DTVS9jLPZG4ZC+3b/uzRvH3LENU" +
                        "3Nxng9++uxMJj2KgtyGfsfJvvjJFizc3CY7TIb7ccfxjsZb7N8gYp3G9JvZvINNTfzZWOLITqctz" +
                        "Xm3CZblBYzFz59SpdtSNDcVpuFozSx5a2zQfsBgMJjx4PWqs7jRgYqqsOGhyT+m77n/BdkNYfxUr" +
                        "pE9oDOULR3ljwzGb+MHsTDLBMNuWMWCWb2GUGX2eEvjrF/VmBRf59fkr+C+3Ywx48MxP1r2PaydO" +
                        "/Rb7j4/71L2xf+rs+OV/1PceX//EACURAAIBBAMBAAICAwAAAAAAAAABAhARIDEDITASBEETFCJR" +
                        "YP/aAAgBAwEBPwH/AI1e6HivZUYosUT5R8ocfZDIxvlNW9FRUbSI96LV5NFvNUXQzprsSSETkRdy" +
                        "ehejxjqvI+xejpHWLdkLscbCdLFvFK5FWI2v2f14NdE4fLpJ3ND7IteX77PlFqxlJaG7k7mxxF2a" +
                        "IvOJcl2RdVjLRFEldEehY3FXTqsZ6FWOso1kiOs5Co0ReS3WxHNn6o0WaE/BC3m9rFrJaohZIm+y" +
                        "Dvg8o0b6FqqHhx4JZRpPQn1VDpLVOPyVJYIdJU411SXRc+vBrqkXRuwmN2NkYf7rysXgqSiXFNi/" +
                        "G5ZkuBx2fCxm7sgNYpFjRcuWRwSjHZyflX6gNt7pYvYfKv0ObdP6yjD6Raqquh1sJGj7ifyj5Gbp" +
                        "HjbIfjxXbYu4DXeV8bklcatWx8ilbYmcXNbqROzldZy2KQ5P9ClelyUfo+S2C/x8XssWGhT/AE6I" +
                        "axloWh5/urLXYui1LYMvbwVWRwiSVmNNbLi8YotRsWEScW30csbx+vJioxsjoQyKEi9jl1j/AP/E" +
                        "ACURAAIBBAMAAgEFAAAAAAAAAAABAhARIDESITADUUETIkBQYf/aAAgBAgEBPwH+gv4P+K/S3i/N" +
                        "Yujq/F0Rc5F2XYn7MSHK2UHg/HY6JMfRejPj3g/BiNiND7GQiNHx7H6LWL3X410P0VJbxXbNClca" +
                        "9G7EpXpeqjY2LokmJj8Pwc2XxgaE/obsbJIvkxIj0SXhHZJ9kXZkux5Ou14Q2OsspCpFjo8Yjon0" +
                        "TWT0Klx94Kq06p2Lpko+DL9ZrTFhB38XgqMirElarIZOiXY94LCddj/zJ0jsa7qxUjSfk6RxVI0n" +
                        "SPbOJwWaE+6SVEhoSuaHKvxLziyxwQkkORyeMVZE2LG9EixY7E/sbw7Yvif5FC1PkE8riwubOLOB" +
                        "+mjpU5I5G14Kl6WE7Cd63ORx+qRkPecF0WFFDjasZWORfB/u8VqlxMcPrwjsZbP8VRexsvlEtfG2" +
                        "bqhipat6Xpelh1ih4MiR9kh4MsRx/8QANhAAAQMBBQcCBQMDBQAAAAAAAQACESEQEiAwMQMiMkBB" +
                        "UWFxgRMzQmKRUnKxI5KhBFBjcIL/2gAIAQEABj8C/wCk5cYCjZC+e50XzLv7V8139y+e/wDuXz3+" +
                        "5X9Voe3uFR109ipBn/YYG8/spe72GRBMs7KLwnn/AIWxNepw1K6rS2+vPdfB2pr0PO/CYd8/4yuy" +
                        "M97AW6hV42682XlF7uIqtnjIPizyg7oaHm6cA4VJydLHEW1TT1bQ8z8Jp3jqvFk5QsrZHR1DzBcU" +
                        "53dRZHbBRn5WtuilXbJFrXdxy9Duj/Ns9rLrAt4yqNywtl+0ct8JpgniPYKmg0Fvqo0b3V0DO2Y+" +
                        "3lnfre4wpUWAdAgBlm0eqHpytON2il3GbIUq9ZIKqqqhyZTZFE0nWOVa4DhUnXAFREMaEPiQZGih" +
                        "7bvkKhpbUriCocAZ9T6lNX+eUkp5wiy81N6OAj1T/iul5FB2UWy40XEfwtx6oqq8iUEz9nKuHi2E" +
                        "CE0+Mi7tqbP+UWXahbNzabSN4KHA2EN0Frf28r9x0RA7rzbs/wBoyZAr3W88xY42hSO3KXnK6NXd" +
                        "uyIHTBs/FMwbIdKlCxruyA7rXkrzzAUk+jUW7Orjq44dps+xnLLj0TnFUs0XVb1fVTVUcLariy70" +
                        "Sei+Jt316dh6Ld0xM7O3Ti0w/DGg1shVUbIafUtSVPwVIa9q/qtB8q8x9FTVVOZJmPp8IEuJf1sk" +
                        "62myU1/XrZvgjyqVV4mFIrggcRwFo17+LLzvmfxbN2D3ap12Z7ZxvbQBFzbO+CeimPRXbIK3m+6o" +
                        "8hbpDvC3rJ69Ai9x3l/KpZ4UhU2hBVahb0Khr2TmnhKOzPTT0zID4JWhVbKIW3bkt6KTioLJUu1R" +
                        "lTMFUKqLKWayFLN13ZU426tUBplA5hk6GBbJbRXX9eqqo+nMkLqomD5UFO2RqRo4WCinp3tbtR7r" +
                        "1GbtW/8AJVcIWiIIoUWHToVcdxt/jO3PwVXZNKgvIHaVqSZogDorw4RqF4KBGnSwsKYOraKmuRVR" +
                        "NVR1jndXa4JA321CbtW9DVB7dDyGyCJsdsz9Bp6Jw7VFgctp+UfTILevRO3b/cdVD2OHqFOze79p" +
                        "TXdxhkfL2id/p3dKjkCB9IRPkWfubYfBsHlqdk77ZXE5fMcg3thc36hUJm06tNUHDQ591sSaojzY" +
                        "w+bNs33RTRyZjhfUL4Z1ZngdnALaN90AvcWA/rbCKCHJX+rEB0fTPn7yrzTDhonX6FFdyvi9WGYR" +
                        "I0cJUpo7ck5h0IhR1Y5A50+thnuuELREFXTqzT05U/cJWzP25x98XlUctJ9FPIj1WnRbMeM53uih" +
                        "iKvN9xyLfVbFnpOc4ph8prfKGQRyDfVPf0ZnFSEzaYjyZW0d1JzoGllzpOL3slXuecVAUJqGFrfN" +
                        "lwaBQOQjONa2NQ9cfw2deSKidcfviIB0pZe6ymIYNUUXd+Scm10Qc3LJTidbPVNGEk8o+Uzyrp+W" +
                        "cRQwR1K0hCephR+lDCR3Wq1W8FryD7/lMZSiDTqCmemEppwMdeEaJojRU6WB6GBjfflGu6aKQpR2" +
                        "U01GEo7M+1pqtRdbor1l42R0wO/HKO8VR6tskSrp1/nBCBCrQrVVcY7KgVApNTic7sr3IxgP6SpG" +
                        "ikErd9lUBTKopNlFofdbxWmOENmOutrmRDx9SLXCuZUqGflB5PXBQLS83DedRqNwXvJR3APRaLTJ" +
                        "vORcbAhcGq+4aKDrk1K3Qq2S/dCAs0VBIXCq0V4E3grzqyFfcF9vQKuVUrcqpebWmkIWX8cuMBQz" +
                        "8qTbedo1UyGyqkwFQZOq3BClxnCB4TmnIhtSpcZw/cr7tTpkeJx1KpVUoquxybCzTtnMHnJcOlLN" +
                        "VxSfC3WlUgKryq21Xa2TSzT8KooiED2U4weuKhVdRkE6BVcTZfb75UubKgGD2OC8HQqu08ZDcbp0" +
                        "hS0yMMN/K84bzeA5cO3mK8w2nytZxtxlbv4XZ3ayXFXZhvdUxFrhQr7TocqFfYg9uU3K4lvEmylW" +
                        "9lQ17Yixworp06HKhfDPC6mUM6WmCru0o7Fdcrpyg4Jru4tjEOQu7SrVLTIw+QoOVs/SyVOI8hRb" +
                        "v4XnthkDeymN8WeM4ZAaFFl5pgq66jsMjiyGt82QNMgg4xkF2CRqoPENcEKejsd93EvGRDFU5xQP" +
                        "fC13scBU9rIdxdcE5EuKgUHIjD74JTweyZ6ov6wq5UNqVJ5IYmuHa27Z8QcMzdW0PYUyqUbygxNU" +
                        "d8NVDREc6MX/AKwNWzRT8n//xAAqEAEAAgEDBAEEAgMBAQAAAAABABEhEDFBIFFhcYEwkaGxQMHR" +
                        "4fDxUP/aAAgBAQABPyF/j30XL+hcuLH/AOO6PQ5/k39Zjpz/ABVpS5ZdP9BC1rHbCJZ+6lWx1tSj" +
                        "NjfJFw5BgZ7MmGCgB5H6Trz/ABLvxgi6322iO3xK8uZgf8RTufEuxf4g4fiUkXToQbPiL1ujrz/D" +
                        "uvZB/qW6rnd5lBHjtLeI1/gngUr3QbWNRIz94ovOp5/sjoxu/wA9btH+IxtlOXsjjPM5j/1Sjav3" +
                        "Fuf9iPqV5JXplxrMBie2EuW4iiSckDK4j/fU9D/Bzi1sd2N/a36jjP2RwW4Ir4j1KxnErtK7sxW8" +
                        "r3ElLgfmsyw4am/GOzvLk/SRWdDox05/gEi4lvbhH9z3XEfLFo57dou7NtPbPlPiEC+E8CNobw3g" +
                        "bHD+o/7ksBxfQxjHTn69z42Pgh8CJXv8T43EfqNto17jfE99S5wkClWzm2YbKSjlj8Tfc8yP8b9+" +
                        "hjHXn6+3mfmOBtcr3Jko5xFteEPuy73b8EC1G/iZtK7xSyD4j7fME7wEN2xKjhCkIOD9QvBiuHs9" +
                        "mZVHA6uh/grRKdZqiAbO8zteibngJazM7Xd4hguf2IDgubMTxTxRTiDUFHEe0G9F3RzDjeWolvfo" +
                        "mOrv9azoy/8AmyzCCsXYi+ycnHEWB3UIgeeBFioJK6dkuJcYlCoZIKXGrs2lSzEFUOhjrz9Vii70" +
                        "DsbXMn8JuD5YbfEAnYghbEQYsho9DorMx0itUy4plgvZeuOjHV+ozIG8Xj5lnbu54Js/xP8AInmT" +
                        "LQjfM4IJavmPaswKPiGYDouXoxhJGQqBwGCXLMSL2cPs0dGOrv8AUQFsNs3t9S5Znu+Jg/UMauiD" +
                        "6MuXgFU1uMava7nRCAqYXBthrlLnGGlG2WWRWTZN2AZyesNolDttHhfA+T/N6Mdox+stFs+L+oxQ" +
                        "wl5hJIrQ1bExz4nExCFIniWU3acCUBUQEYybrEGiUEB5TDfbhOhfcMVpinPiEutjE92T2MwPkD8f" +
                        "+6sY/WyZwd4onNPxLWvvBbOBMwIbneLygYQkgqd7MyQVCklWExTMeUvirI1zMm0EEj2zEtWXorB7" +
                        "0F/B/UNV7H+EvRi9D9TKGwPYTKFmxJQV8p2Jsks/eBomqiLTBrgNADYbyxN7IAEqZzVEWfbOZWt2" +
                        "uMB2Bq6HV3+okTHbvPff7PD/ADCQ3TeWcyswZJ6kt8Q0WOUI66FqVE4VfcTdHM8qLlwOMvUBsDLi" +
                        "x0uXH6bYoQ19qaK7vj9xW33t68RoF7pPgJRTE2SZi5o/OlxYmSwZ0nUNoxjrYE3AmAR4EE7Tv0xW" +
                        "Kc/WZewDvuQPMj5RkxaolYh+jc3ELQlYR8P2HL5gtCi5crLmd4VPBLpxLv7wyTOn+4ilzeUd09RS" +
                        "otj+eXpioRZQSsXJ8mbmPfaHFy3f3LHZ/J/UKoseJlAjy4jWU7m0CgJ2GjkpGXjOx3bTz9C4w6uh" +
                        "ntXmW/G6OxBQXl4Jz0Ko6HsQUuMA3vE8PK9tBfAO1LnHd/aUW+LMhgeZiXK5ebjPHmFyb1DEXbmY" +
                        "hlUjdohwxUFN5a/l6Zi2K7IY3pMZz9HeEd1wt38TNSrwsI8v7gsbvxO5Zz/UCjEsYigdjaFsQb0y" +
                        "VXNHADiKuYxwC/EpImWfuJvKd0yhJioqL+Eow7RH3g8zPeVbtplS+9UZULdjeY33m8LcQyd5kcFl" +
                        "34QgY+iyx7Do7RQtjKBebG6rHnxCjGITgIwWpUct2I23W2h/cVQTGxKUwgWQRTtOynCEaEFN7wTN" +
                        "d2uUxpY3mXKeAQEbGJWWtd4qxK3Y+GYPr8T7BLtKnA58oZ4bg7+SB8CsYfuNfEBmfosqERgivecE" +
                        "fRLzIL1Bs7IvQYg+Xz3ZW2D1M+cyqVEnDL1tLObmMr679o8jslij4EfZHvKnwPhdvcs0N2IsK1ub" +
                        "x/B7IM++SEUcceJX7afkihpDOl8QlsfoMxmVRfNVdQywNgZdWCoY97u8RW97N8xjxA6arRgiiJB1" +
                        "5GSKSiAYOd7I49jl5uTaBceZX6xlju181R3IiwJzeAbofuifgXOeEVf4gXgddZbfBFV4tytoVYOh" +
                        "/Mgr1iGgxd/lb4m4JUI61nZorVjElaTXB7QBExnc2s+FkC5bM8En9EJfeApjvAC3IoHIAmw+2vnp" +
                        "zZW5HDLAbvNsiRb2smUA7NkxxVTCVEiS4OsvpiWef92ESJ0qlQitASpsl8uKtcys/H5B/wBwHLs4" +
                        "ZiOaY29J7wH5mTd01c9LNl6bPMtFfhzFXPwEM7YBDRjBBOR8we9gin4LNWMdKlQlaETRSJ5LOWWF" +
                        "uVYuYvQr9yC4SpztisIb2wywvLWvP0d0WrpexX++l6mSvjjR0Y9J0VboIR87j8y0m7DQeyfmHETh" +
                        "oLDdyWPswGvZZzo/RuFL6KwmW/jmXk4r++h6SENGVRPmG+1xy5Dd/DC2iaLiHIQVg+Ag2lUHhKeC" +
                        "WGA33hOMXo7/AEWKD0Z9lKD/AKAMqnZL0eohrUwGfenFI0w29/cR/ShsBC2AlMTlqvLQGeFIdbvo" +
                        "7/RdBqxxe8Jx3Etbsj1kIa4KbrsRWmKw986MSyBZwOYBwVEc0d4sUxDC47/TENFKV8IkB4w8zxYY" +
                        "9RoasuLgG71J75iYj46l9whllTwfuDMR+kb6DR4n27+4FdZX5IYKPojVZnysY9zNOGn3lngegGOL" +
                        "CxywVKy2vE5+mIaLEEd7Z2DK/rrvQQ0YsSg++JikAQcqD7js9AqJcJvNkzXgr6bvFDR4lq7QLfO6" +
                        "TGVL0mrLiW6FGhgolTzBmxDbU5SeQR4gPF17nqvrBGbZSjuzB+YMdDGYv3pNWUg01ib+tTceVQ+1" +
                        "iH7ENSn5pOInvRlaFB9Im6NJGOlpkqrszZ0MZ+8mzU6DDbsDmPdqoj90zbvGxDaOoxs8EEbvKGDd" +
                        "5fP1CYM2R2jyl0zK8PuZdBjP0P3MxDRmyPg3aHmW5by8QoQ2QYq0RHRubIC6rfMwjP6Qi479V9N6" +
                        "DNyfly9KBXZFEsehjFXzNI0dp4gJdC6DdWlYIb7Kptw1RRUBACxHStHf6NxjJp4m5LuMZlv2DFu7" +
                        "5f7gBY4dGMY53ruQ0WZ27MancxtLZMhYnYBH8qbcNtCnmjUNoid5+5/pYMcJz1XL0dAyi03JwK7r" +
                        "j5hweSM3GX5gtEUow0YpYfiZa4qLRT1gf9yp605i3dmokeWN8s5iuRtoUx3yp6wGvPS6rFlwZgg4" +
                        "mcGcxkzAyVzE47zXM3abb/UFN5ehTK4EXObg00qCDyxC2YKzIXE2TvKy+iEJUTvu3iXHo+PEbkrS" +
                        "2PQy5cYWLL0X4ixNs2dnBETKTe+IgolyzGCbkzDjzA3MLvBDebA5Y4dIwwLwy+uXBCH0GWDFUwig" +
                        "8sCgeRhcZVExwSkgK7G5ZLdbjxXQ6OlxYsWLGHTAE5Y8R2hrcxX1wuc7QAWXaCjYd1e0ALneYND4" +
                        "K4ogOFkVi3xMHA9f2pRYzhIHCUaLMMXBCUZV+kWOXUvsirsPzo6rFlxYwzlm35FtgQQpaYLnEsNQ" +
                        "ZlCN8y1v1JjY4mFBuKjlqCu1sPVc6zMnipRDssdlSkbdMcBAibaGXT4YwT15m8Us3Q/aYXKWzFqN" +
                        "5HJ0Gk1Yxi6raqvEtyryxVtK+YEAu/ew7haKt0F5hWv2U5tDV19Cd1QLzDFIOvcvA0bEzQTgwkYH" +
                        "jiYYfeACVLjTRdJ5ZeDDzCWD2do5tNYiLwy+85KpuyoDYI2jq1KZjosWLF03C8GjulrhXzKgSoFl" +
                        "fzKbZMUM76KSkpKkpGcrBolKC2CEfkYAlkYZdCBOYltvexMZQ7u8vRXmXneOczaXmUtqQ15NoTS5" +
                        "jGLFLnM7z2lwalStVA2LiBM/CeCKDoaLLjf7dBlHeInlhOZsifMJz9ZtYjcJnguflFy02hsC5xjL" +
                        "i+CoTNs5XiZNtfMoMYoox2SxFUBBqEgdKnmkwKhL6Czggy8lNkAtB8xhPUE5g9s2pDhlMt1yvE4M" +
                        "7iUB2ROyL9IXsGdwPuWlCMB3ueQqgvPf4ldyibiijGKypxefPiXL0FNp3SCBoSL8uhFx0WRnlmZL" +
                        "q9y9QkDj7y9LbZpcuYgDjTbg0FnEQdHCAEAuNDaFrQO0Es+6Bw8Rc6GOhwZfelTMGDCDacrCRU5N" +
                        "Cx5gu4bHBvGXc9zAVHSA3ZiPXf0OtMs0Q7QJ4J6Txz3II+WmeDuckqMMwNG6OQdu8d4xixRTNeeo" +
                        "UgRHapd1h3WzqMLsAEyi/MlZcqDBvS7RtgbhKhSyLOgE6IGXaMMAJjt38Tkq3OzoxTJoYxm6Nq4v" +
                        "r3obhSkUe8xh+MykxczZ3FPlEW5McGby2US1EpvR+41kggroDRgnUvblt7i9CzGLFjFj89e/L7Sw" +
                        "mUpW0PtPHNzA5JWt5OGBTMBjqSwPT2jXdIYRWvGtag5FPAY9C6LvFHeOgZvPXuTHmCEOGnglUoYS" +
                        "R7vJAJU5Jfo30Owtpl2CkgR0IGXoU2veqc1UdVu6GMYyuTfjpvRziVmGGPQDfhsyiDHLMq2uVzME" +
                        "wN+yXwEbTsS7gftAsJTLiy66Q318z1AwGVoj4tu2ijHRl/v1uXqbDzBRl0EJBBDvH3AwCCVSYDic" +
                        "cHHeXy8TZLC+Y6wDZlGHfmMOpZqIWBGdNBP/AGBhpuYxixZYhUwly+gXIYzBKia6zgJcYNHDo4dg" +
                        "bM9MAjsiTIiq4xE5PmOPV5tt/sdplWOyBKhtGLoNC2BbM94C5LlmWdOd0FQaLHccrSXBvlDiBBpO" +
                        "ZssHhOIscSF+TuZse2uDtpZ+5A5N5l3lEohFSjQxISx0HZKlTbQJpQHeCiLEZdNVM4lfplahtoUj" +
                        "H00q0xLGwIlRlyp3tgb3+9ibFe4rZCC5cNCWm8sSYF9bdXbSpUrRv2xB1A56nu5lYedIwam7Id5f" +
                        "eFGHs+YLIJRg7zbD2sFtT+RHXJSHk8dFSpSS+8sjM/zNK1Y9Oc5ZjCzdjXTwzcjtuX+iYpdaeUwT" +
                        "0YqAxvu0DPmE44XEwAPqG8dLuMqWA41NKlSpTqY9OzpO87zZp5Tfq2yOvazd9tWd8/Gn5k/Cn5Wn" +
                        "EOp//9oADAMBAAIAAwAAABCY4444447jAYMacJY74i+EL4577776oRQOv6xZqbCF9aLLL5576qEA" +
                        "FLyHdD6NEsoIIILa7723xlxq30kZBsGpRwAJL572Lj5UHK055hVexIJI4BKaNcTdPISMSIRL44oY" +
                        "47Jps897mYWv9rrGbAZrcb6pT26BjA9TaHijqYx4++pLtN5YZ3U3MvGjop9MdO8uPfYb0STJtctw" +
                        "S2FmH/8Af/fRVVMZ6QKky8/uRG5LDDzzMRZVkne5e5tvJTuALPPLWNLFoooERw0tP2rsAfP/ABXD" +
                        "leOXj1d5Ct7gpS7L81fPcjFpBSIZF01WtPEnSEDM71OBsAlhpOvvrTRb4OLPw0M5hmc6E9L7atS3" +
                        "nYSJ6cath9aYpl5bR2kR/Gcp8e9XUQAs+lh1CJjw8ZAYT/6fKyfbcQzdlxdSb+8mQkO+afd95hz5" +
                        "tcCc/LOBF+0wS6/eKK7BIn/RVYK4qIFHeYcaUT8elUkUqqzFBKAK0TbtHfS2JtvG9lWHPWnbB+7Z" +
                        "fRNX2pdKaRfJTaUPXEjWFPhAfGtS5GrjJQsdPbIOcTpE7UkdjsDlrinLycK2Loljlh/x525creZu" +
                        "TwSR7UXyljmnwXfJGSfR6pPfSBGB8gyjKzh2AJuKQ0FpUmAiw9B/Nx8AePr3M+ofUO1XwKeimdEg" +
                        "NfQTCYMjH10Y811IzGtEOOV3c11dLtgWqgf/xAAgEQEBAQADAQEBAQEBAQAAAAABABEQITEgQTBR" +
                        "YXGR/9oACAEDAQE/EP4H8iP65efR/QL/AMmW2LeQ/mG2ZblvwPeSP4kMNll7wxDg4I/iNZwlsiH9" +
                        "v+MSYdSZHBH8TPvC1a+T15HBH+W7rkj4yyzgW4Q12Our2IHeGLIh6vWcfkQcHGTBwXXJGN45ink3" +
                        "ZlHAk6JPMcUiIs+gujLboMQQhBaYJScEfRGT1mewvYMM42GGu4O7f4WEfwMw4WLBgeY3P/q6oZcN" +
                        "vHZwMGSaQWfIbZP4h7WC2L1WSLXg3GZAHcXxaq1LP369cTjS3MbeQ2yzl0SWRwii0+Cw+zOCfWIe" +
                        "TLyHDh9l9Pc8aEthHDz+C8cZd35fcnBrq6KcETNt6I6m2hwNvfk9nzgw7fDMTyN3rg74y7PpaOOh" +
                        "H95O56nhpMXBw/jOPPGTvC2OPvjcNl7vDwS5J6x8ruTj2mxx9cLMR7eHgs376sz6hx3g4iCN0dSH" +
                        "aJYj/qF8hx+WylgemCwW5jDNV1dmzYsAJCWchBwGw6gkG0eXlidzCbxQXb3HXhHHhrdtHXbDkg3h" +
                        "P8SY4oZ/xvP2bLCV2ZGJHqBPZg1nbv8A2eBCMOEx77ZZwWQ9SH7I/CRK+pv3che9ljL8SQQxZEQx" +
                        "GyC6OAnGRY8A2mO14podTrbqaPHk9+EgyDO7VA6/smRCEYeGWWSVn5ycHvwNoJF+5xqf5K0+M4O4" +
                        "lHB7byHwFOrJHpE7852f5IFq98g28+l4GXUMLYcv29cQ7kZ62O+2CCDOMsZUujg2ReIJvzbLCGPo" +
                        "fOoP2Ichgsk59XSZW0oLraFlLQE3pwc//8QAIBEBAQEAAwEBAAMBAQAAAAAAAQARECExIEEwUWFA" +
                        "cf/aAAgBAgEBPxD/AI3+Tb/Frbb9H+Jcv/Ysskjfk/wsRDqHhg6szh4LztvyuuQ3k4UQ/flvzvCw" +
                        "iGEg9lfnE1q9xyzP3t2v2s2HsdzwzakHD/CfINSzqe9Zh1Pw8PeCg9fyBw2R8cRV1N93SBNWI72G" +
                        "6vHKy/b3DYGR0s82v5O2HaHUMzL9bPkcIhmuPJddtj0T06k6kH7Iy2/Rm3rnAeoBxu2Nei9sMH4t" +
                        "GyEett+WI/RPhafbIkLyZ5ZrdlYHqwFg5eM+dl1wLXdi7ZwnGQRslm+LpPy6IY/OMMjzg4EnkOMR" +
                        "dsMHW3uz58S64wY4zyOf0wXyLaN7b9StnHd1Dwv0B7ZaLBli9c7bwWcXomeCJ8u8DreGerxPG/Hj" +
                        "wmss64SX4MndumeA/fr1xtB24ZLzxmsPV6JmDWS3rxPxt5jiy1JPDzwO949ks2sXvG8rqI9ybPDw" +
                        "26EK9yF64EP+JjOdlnhlCw7JbRkHSSYC68LJbsWRH423hYbYUsSnCAS3l2+zaXa4XWWBLT4WYdwc" +
                        "RFo/YSK+cLLB4SIHjjSbJ+cLPk7wY4bbbMF8Q8P9YCDwjWQ6kZZDh8OSST6t4dvHbsA04Usk/iSz" +
                        "E9sOnyzF+WDU/wBbB3J29h2SZzrhvGwB/v089Vby9O4P9yQ/R6Y18ZZZHBJZdE9tunXA5wxDvYSk" +
                        "zrn3hnH5BwGs++E3gupaWQmcDLQ6Ji5MImkavVvGjerYjggHbv7b/UslktsMcHnBB3ZR74CTqUbN" +
                        "j3w2cf/EACgQAQACAgEEAgEFAQEBAAAAAAEAESExQRBRYXGBkaEgscHR4fAw8f/aAAgBAQABPxBZ" +
                        "db7S/X1L9fUv19S/X1L9fUv19S/X1L9fUv19S/X1L9fUv19S/X1L9fUv19S/X1L9fUv19S/X1Bzx" +
                        "9S3sfUv19T4fUv19S/X1Lb4+ph2+o+n1L9fUv19S3x9TPj6l+D6lvj6grjH1MO31H0+o2dfUb4+o" +
                        "qdvqW+PqO33+q/8AyP0j1ucRnE5lzcyS+/QzL6HUy6GXHb3F63BmL/8AM/Rc4nEcELczfQhOel1N" +
                        "9LjGLM3oUuO3uPU6GJcv/wA7qXM9al6CPaE5vgNsfI5Vx+e5bHr3T+ZUoLQOoBE3qj+ZZwHAfjTK" +
                        "YR5SUh9qssDLuV0ub6MeY9DHcVt+g1+q5cvP61AYTS1qweXsROi4WDwRJEh4vMS5OVzQp+cP7gSn" +
                        "4CwyAHm6TkaHlX+IBZpSrOYkVq76eE7eYYMgoBntM10rq7ZyjqLMXLLjt7/9Of1KBa0RwhzylePL" +
                        "zGckZG0+WFp0fvELBTsbm+ue/wDsV3M9nco17kdKMPdL1UMb04EPLS106dsS1i4WDctxFm5PZYdL" +
                        "jGOoui76O3/1vH6FQvEQV8Y5MfCwXUqt2/li2rymg1EVW35QcKHttGvavuVndwLvD5bgf8GDbD7J" +
                        "cMR12Zbje67xqJlHyI4ALBxBlBT3/b5QSobejqOppFi76bPv/wBTE304ikgNX5bR/wB2joUV2tqC" +
                        "trfHL/7GKtLrv/bLUvxG2KhQofzLmQH5hjQ+WO9BE1L4qAvD9SEtC8wKxWKlkCW1elwxVc8joRdi" +
                        "qjumyfZ/NTKjZ36MdRUR1NmaS47e+r0P/Ex0dAAWrwR3ta3NvPymjyA/vDFfXDx/sGgt6gkyvuVn" +
                        "Ve5lcfbG6cJ2gdii5wflg8A9xRL31cIRSgRzncsChbTzK9bwvMUfYag82IuZE0/JXV6NI6Op299W" +
                        "cwr/AMlEYK42l8PzKIq8rLSqHTwme/Kj+7CKWltvn3OSsMW6ngYtwKPo+4ZWj4zMe7LrQfcWWwYw" +
                        "G+OJVwFNc+oaivNYfcGuCwKb/sQuAs8cksGtHNgsX9p89e/Q5tHo7e+r1uD+o6OdhsDa4JfJVHsS" +
                        "gDn9kvs0qkHkgAQ1v+IhRucBQTlngC2BBK5aPqVS1yDxFJmi1Qi7au5LplzRGwF3kSmDVoYqIgl3" +
                        "s/yWoLFPeAzp/EvcVCvhAfswmGCfkvpom3Qsxc9HbLl/quXD9Ao3qEtJtbvy+uPXuNnhX0QrHr/a" +
                        "AKfaveBEdtwg/vWh7Ykt91h9cvzDOC2pAqGuzEBw+o9hGLpcOqJiZHIGTHqPWFp549TIDama5/2M" +
                        "WFfFwlTCKmsIxia8j0RjqPWuXFl1uXLl9L6XBvosuEp/u3/A+YSaWHDt/csS8jK8QLeEod4xNVh9" +
                        "QGQcxO9aF5fMAwB6JQI4IxIkEFRUJpayJUiZrmZQJ3O8NA3ylcFo6YJKBwdmJuMdxxblSo7e49L/" +
                        "AFkItRVtyzGq3Oa1C+Cg4y8wrXsGk8+ZxPELGcjc4yJMKOUC1FrXe5pdxYossl4iuC4IpYg2aqVU" +
                        "wOSJ7xhrB9QrNCC8XKgBQCjtDUVRnOaddn3Ll9blwZfW4qyxSu1PjDPwlqaNnmZuE5qFRQ0bTK8z" +
                        "MDkh/wDXOJVSsNYaitg8rmxPnZKUJ7qC0B7MQkcIQp3jnO5SJxEVYHjccm2BYaezCYrrPJMiSNXo" +
                        "fyM8dDqY9VxZdb6XDofosT4A5ZdTMLyOhfxERDs+zRNz6MUhzbbGweP5mMcp/aFmwmlkv3FPMW1g" +
                        "dttW9pyQyMHxLnW1mX3JjXc1n7S0r7mE/loIURO5LHmVKUyNdoIxMO8g9LiiQwKHbvOA7DeCv8I4" +
                        "izMI5xS6mI7ff6D9Iw1AZMEVDWeIogOXjUBjedR8mV3Mo+WOqK2MoPj+0slUmLza/fiHAXcp3Snu" +
                        "ZlKs2Tjkb73LMwco4bOIJS8x4tcqDV9OUogDBcavqJEbfmAaDnwYwwad6uUs2aepiyUA087hm997" +
                        "p/aNx3Hio9xZiy47ffS5cuXDqS48HadkNqYRl2gTyqokuHdHpLV/MaPZWWVzopNC4YRRQeyZEZCR" +
                        "g1XqW707VL8NuxAf1CA0RLEKuDhVNEvTISVV4SAFyk7f4ZXkmLYVYALXtFtxuHJ2xwV3ZSG8/wA2" +
                        "CGY5Z+R/Edo5jlUXcWXNn3Hf6zXRhtXmYvUu6KAc9/iswtkBoJe4U5Hk59SyxZe3EGxPOq/BHZEu" +
                        "F04lx6l0d42QN7EsYI0wBT0ygsuEkHJo2zA4vmLUZbef/pF+pG7WwuvZKoqLB9/yRaOjyx3FFxLi" +
                        "y99Ll/qLjLDDomEVyDa4DvGhtGehuRcHflxiDDGpnJhqWVK3CyxeamAqCsbW8Sq/ao8RSpi3LKEz" +
                        "CYy5ifM2RDtB2SzqXR5w5pg+I7u3Vv3LyiCg0UdMt0GqLbbjzWp8daylTEx4i2RBK95s+5b1HP6l" +
                        "nd4E7exKPZdU99k/L0iVc4WHA4HBFVOY3EBts79paps7dvxxMey3B1QH4FP7H3BoxHymZUA5YBtq" +
                        "GgS8Ecco6GBqLnt4Ihq3rfmMeYhI2zU2EWA/TCeBGymk+I6optwvkxCvJ1PzEs9rwMDyJwEbB7ix" +
                        "VQ+ps++l9Sc9LqMIEswatjPDLsk+35RiOZZVbPdYw3ywLuUg4az2JmKpvbtlsziizKbz+Jph9JSv" +
                        "zUwVGKyguZHdUBthxbmVe1HXaXkoHeKphgpmczopr/CYk+pipfccRSNxyc+Ye5alrt8O0WwIFRX8" +
                        "TGWLwuVCLgX5ViW8tScT3Up+aXS2f5E5HnYj1IdqATUdvcv9Q1EDmBS0gd5TuRB9Yee/xEcAGKA4" +
                        "qOoXvhf8l32Fr55/iNrlysSjv5gReDUFV8y6EAV2hvNKh2GGVGIJO18P1Fm3xj+UoDLp2nqdkpCD" +
                        "XFdAhzBJXA3pDNbZNveJFr3Pef3hD4Sme75hhD4dwJ22rmnATuFvtKpnSYO8uUNwB8jT6jUtXc3e" +
                        "uHxLKMjkYT20dvf6L63HKxDlUU9FKi8O6VcsVSqJcWWecr+CBIA+XR8QG2EQRs57Q41lxLouwYYC" +
                        "QUd2cOMPzzKMQFg09ouP2DC/MeYRo0y80soS5SIL2OyBsiJ2lC5X9TGU3dg4IlpT5ZzuoLNRymZX" +
                        "wvtL5avbiURhZpp/pnhwjZEqDa5SA0l4B8MxY7sIlWEbcHGPJ3jYNl3z/h8QXKIXLLuXL6EGpcSH" +
                        "uI6UWKXlAJY8zNR2OCVtUcP7EvgNC+YA2Khrvyy7VW7q8RxkS3xEFbxgo5oOqh/xpth5vlgABMtc" +
                        "QEiyi3DFO/wpAWhQ5NIrMfLM+tdwBoIEiWCww8/vC57+SMArId5QlrVIrfwdMslMt6mCuxvLz/Yh" +
                        "S6GHOvZGpR3WejGyecFHubPXESBBZbp99okYsSd2T+ZwoFNR2+//AAqlXVxHRobA3HE07BRBFIeA" +
                        "h8G9XX7wDcGnLs+IiMs559TuKYDXsriUAKOz+JsL5agAxGkxXKG0qmWGDkmdqWdXGuCA0pzTcJyV" +
                        "rKtk7rZHaVDOfFlLLFBumzFIjZRTuJYcqr9jn4ljdja9X/ESVqiYOTuSh7a13QpaBryP6MnoiFlX" +
                        "Cd7/ANlVAaDvs/8A2NHcJbPvpeZcOtxXLnp4LQ1DwqfmJLbsNSsbhzUyZoEob3a36fcorLNX2H4g" +
                        "P5IriAOIARBIaxL7eYdRN04ij3hbRKJrMRgt94WaXYNh6ZU2FGt6bEihZt9DzwS1odhJ2q9TjMV3" +
                        "CKXLaq7g5xyQkM3Jkb0nnmO4539whI7UrwxYIVMdL4/qG82D7Dl8VLcBCqnamG24kXL7i31uFupd" +
                        "Hd7RC3AJ+VQMK91ElNPvipT9pWT0nBDsoVDY5+Vfcu4POJyPs/aC7LbwynzLGGV3idIlykZbbng6" +
                        "ArmXtIBQQXH4gi7L9YIa0uiv3FABCki3Rsu5Wf6fEyiZI4/7+YeZwLHtG2z0wMPTFO3+JSrdCWdW" +
                        "YhwHIUyo7foqVOnLak9Izm4TUnL5mvgmAs8nP+Bcn5jo2Inay/5ho6MEJaYWdAaMX5PvPzL4Mgrx" +
                        "eH3n5hOYLKYwRI71PSEhUrChqUEYEOaodTnVv7xFTYX8f7AAzL0MI+h/EYQsH0SjQWmQr7zgy9NI" +
                        "DnYBP5gtl5z+CDbFyxMul9LgEzAAT4FD0mZQheECfLn8xU0uwn7sUlRq7QKh6KZQ3lXjHj5yfMAl" +
                        "DFmUMI+zHxL9R3ORLIuIs26BKhBSCEVBZMEpSuiK8lLWspSjzxic2t7Cpn5o7Tc+68IfvU4D3ENQ" +
                        "IHhz/M8k/wCxEtKd+mPQAX8oAWRc6iZe+t9Ll56K6jpX0lxGcpRK+8ACgeH3+8yNV19+f5Hx1Mp0" +
                        "VZAgQIIFwIk5hg/eIC2Cnql/mWXgZ5QU/k/MtBaU+JcP+ZgWtuMMyvnk/klE6Wk9MtZKcTvcFVdg" +
                        "r1NL102Zd9LlxZctg4h0HKLiLLmTbP8AN8fw+ojIJ/IPyfmeY6jqC4kDcOrSCBHRjeiGBFWsnwg/" +
                        "pBvGbdB34Gj1HEFZLFm6dMpBrBEfMuGDzsbRBJlLZzBLYlPnL+YgPQTCUKUZHj/JZSXNnvpcuX+g" +
                        "ZaxndlhLixxARAx7I2dh2BOMIfMWKXGfErE4g6DiHM2GO14ZUlx+E/3KGHaHJqUFm0QJlrISD4no" +
                        "qFNbgeRxKv0hHm1Lhr4jhQOe3vxAiFd5mGLL3L6r0uY6LFxYjxB6LEpm4LXuBZQQecn8RFVra/HQ" +
                        "srMZcYEEEECLFb8Mz/6NhvuAnBt37twLOqDL2QnESr+FlR73TZUY17E4/wDkchbrc2e5cuXLtl1+" +
                        "jhimk0iSgmTqT8zUJQpaumc/mWPv9l0JNSow6AroIRYgpWiCRxYfN/xEeOaF94lQNAlEjqMxGC2U" +
                        "tacIJXudROyC0WvcWX3H9Fwi9DJ0FiasXEoigRqzisAGFXZZr6gCCgwBFjAuOI7moLIF3BDE2Sgh" +
                        "jXXF5YJauwW8Cv5jhO1fuEo8EwOjmJlfYCwth+Y4urOfUABh9EVt+i4fouXVI4sRYlqiIUDv3Lay" +
                        "kvkD+TBx1cGIxiByzWCCulpUswrm8KPPP8RXikpHVMNQhnYNM+ITQ6iSeN8QQDh7QCjhIjGGiHb1" +
                        "XrcuXLlwMxRRaJQqncTIneT3pz/MyJUqVBNGZQ4uZg+OgJVTSWyUKsgWYW3/ALxOcHLk8TIGq9nu" +
                        "Rq/g6yzN0pzNXF4nIKse4AV5M1uLl/RfRfUeJzdmOwmnRvuVu5AisF0JiSpUrE0ZziXHZ+8yD1Bi" +
                        "Go6iojAAUT3cEqa5AK2nj8wLWx0e4LHlC+GVF4TSotR4wzdEsObfiFFntmW5rXQzggBWXzHb0uX5" +
                        "iy+ly4onkCOazWfEomfgbYgWtqYOMqVEyzSbx/8AbuQ4TSXNIsQhDhY7s34qGpKhVhS3+0YCAqK8" +
                        "mp41g+4MPggroPcdrGqUEeoYKbtaIBwDPK909RcsXPRZd9WXiDFxMk7MUOKPTCQuqmHsbbqjr6io" +
                        "TTE30YOnA/8AOEdjxNemkdOI0W4Dus/7UuApgRL6NQrsQnBKP5i+qKyLDFhqF0weDMe1XbVwZcIq" +
                        "lz6Swov5mMWUuL1voWXBg1D7IsTSC/RC3PBPxDBKcg9PEpQ4zz8+YnRi6Mz2/eIaHrpvERGRgsRf" +
                        "UC6peUXNfioNb2yhUg8x3iIGckwHxNIsR7gmFlWV7ryMxzE2t5lM2RZfS4vTiDiUDo4MVkYPDxDt" +
                        "N9PIXMt0tga8yj9I2xVtAP39QCBCxOYzDoW5SnbMCy0McuYnMtlBVQuiwX4uLXV2A+f5+ZoAaHNH" +
                        "ecdDnfMBV3GA+OhozZMtZE+tv7R+85wzFY+wuYQy8rl4UuyR26suXKTaKVOKMY8tqcQOHdY9xDhU" +
                        "HsGocUBehKxiANhO9PK8wgpKnSYSKyOSOpWuZ5BULKEu9mICblzbFqBOZyOV9KhuQEqg58nFVMOj" +
                        "Ki+dxQGUwc9oiEW/E7wHDYhnoPcrHMdS0A94P2Z5/SK5+4PmWR26XFuXFBYsr5jvPTk3LDLoFhFu" +
                        "UiLjt/MXDxocE1lhZAmHxRAWvDCLtHEuYGJ7wu8zsw03sYhyO7tyS1XEVuUMAclBMzB37Huve5am" +
                        "1ZViG2XJrnxB1fPojAGu0rO778nb1ChMbmUiXHXd1j43+ZRZE1KTTPPNmLnouYqOhw6F/M4Y+cCQ" +
                        "U+U0ILR4l6Lh8hBP0TkgRd8UPMNCAsMh3O8sLZ7cO55gnKcMRZY+4SuERYNoJAGgUv8AiZSkWPMo" +
                        "jPS0HuECiKoyvll1umFEYojg16ggYQBqoopItsNMxUM32YvPE+YSlu8S+On1LW9FUYxbPLFlLO9C" +
                        "OY1huTCF5uYUzUF9KjVlo+l8QQt5Hf4laQcvjvKI1QclDLgFaL8TWjuPEJULxFIOe8NjPPEY2vG2" +
                        "AhU4VqGWU8CWou7pbA9cTTAlRqIEAIAo5ZlnCOR2f7GpOIPOI8Oy0b/jxGbN3wO50WWXFtXoYWKf" +
                        "XQMuEcy/aE2H5ZUvBi38QrAkr5zBqXCWMDyD2QPDDbzk8CaJG3JPie4vyRXlB4zGJicJmMJU8EbS" +
                        "paaoj9nCxZexLMElNIe0JFO4CLGGPEN0lQAjQxEECDzAOY1swvo6HK7ETPsL0cENYHxEXYxtBmib" +
                        "LaqRWAO6+ez4iOGkGmO2MFRtxbuYZVYSu+ZRH2coBTdG6P3rapY1uC7FkEv4uIKaMEziITIfUWUf" +
                        "GJhLsVVD+4JQS9yqiFAd7vuOG3F0s8cwb7IOCmK+qjv59OD13gUIOHxBoxYp+HnzBoyjd5ZlWCVH" +
                        "UQQopXM88rvMEG4Sm3KhSq4xQ/1lTQeiBT6aluEx8rUil5YTAoBUtVBBSqnLnP7TZjKmVkFlYtxc" +
                        "BOXmdpC5InKfKvrNVabMPCXoVmAmYb5Mp4j2iHaGBmiPCgRVQE1IdhP8ndFowPeVSx9iwUqglBuU" +
                        "8wL3D7wq3CLuHKk9sPD8tlmTizQ27qq4ttwgoBYVZAMnPiDDYOZgig3HcWObUGzeIQ7gGnM2YsPT" +
                        "2R5SmQFtqO1SrcHogSUlE+JZYdIMAaCJaAd15/LKZYQSoi8ywgVERJtV/f8A2YmDnMNBjZDzhBHH" +
                        "EBX28WAk4pR9y/rXvll1nuBoikglBjXLg4g1kfUW5j2imd+CJA+BzGumCGNWy/Linys1FTyJXFQr" +
                        "UeXoZGZsJDxBAqIeYujH5LnJVAvUFDWLCZ1uaRxiHNwrAVA3HynlgG8QYqlvLeH9o97EVDPMO2hh" +
                        "Q5l98JiJZAb2tZdZjgaIhKq7rcSxdmaF+ITTc9iNEV4IJWB35Zj7rsILXYA3LZXoIVwrOVHlV2t2" +
                        "HgAKruRJ28DduT1EY0wtb8xjzMjMEW168Ecgr0Qvsh2PRDKeW8kuTKx0jKN23n2YNQvBJ7wRq5nU" +
                        "xjG35mbgQdlcz+XE/tQhuk0Rs7xd9GUO6aIfMKbVPEqEq8xVm1hlCYwc+L1PUxcxHCmAjlLgJays" +
                        "rlbC9U28S/u7Uw+EWWPc1ixLrI5xrEDoKcwRyQk3BHZy70GYeQLElWwYRzAFDEUh9x0grB+xMYfM" +
                        "MNpCqqlolQsCoqmLEqcfV6lBzKgnEWLHU5kcAlpiAG4JsO3wS/q+geQ8MK15h9vZJW5QwGRqXN+I" +
                        "gyVrDyjyxbmKYJkTxPR2QxB6hmaBuYm1fwSnp2yK+vcTn47wi+8z50tZSlrV8H9CEdCMI4ZUMytK" +
                        "IAupeIkNYSkiq5tX4fMamIzxM+SWEqaqdk6RbCSgsMSxFwMQXnngeVFFwKvzzDFIzNzdFvqPKRYY" +
                        "9zNgbdp5xHodLuEEpiPhjpahpZZA0kLe+0LBiB0Lj6g+IBEuysfHaA2kdhAnaGlxKQhrMsLUGQGt" +
                        "ZHueYrBRvgH9w8YgdoXBCvJNqoYY3Ldyi6lykG7KYezLiSoujiwNxYZgYAo7X3HRMT0DuBh5zuGo" +
                        "dbg4i+mali4hKxPC5mWJ4yADCiceIVPaxY/pYXTCAFMAwkSZFCCo78i7kB+k33O5Lcw4Ucwol1mX" +
                        "cPeOvMyacxVtKm/JBIf62XDMTmOL2gLUoegUbTK5qGqw63Llkf1ynDxcu0hjqBoSwcQLYlxaxLpM" +
                        "VB2LBv8A2kIYNiQSZiCS8zsbl/Ktf1PiF6ApOzKQcXKEWoCJMFXKauI1BBqVU7Rk3dCY3MXUwYO7" +
                        "FHtbYtxWxUxXNZaPIMDLly+i8sD7EJS60VAsCFlUa8QHeISMuLEAEuArBoU31v6MBL5FtQhlIogZ" +
                        "MtzWyBh1sdCJGDCMQjNpeJVYcs13HHca2zGz3UQ5QEjBjhlYzs24d/MJmfcU2jtmkNCaw6X1Le8u" +
                        "NdbEHClBcxzbPDCR7SsRCjy16ILjBl7sNRcKI0k58PeCAVtteRLI8SrblgbJPEHeFCAsufDHaCBq" +
                        "KDxMrGXC7m2chCCLYsPcVoA2x06FjFgWYiWpmnRVqWEZktkiVUJy6kr/AJuVyFCG4YS0xuGrxFQ2" +
                        "koXioL92/uloFQTKGlVCylthsYNiH9IfmUTcsJabyRQ14ajYkWRo5QML1Hyjg5jc3KLCbwbXBEgi" +
                        "OPYleSLgyiEGNmVEohIlBKY17eiXl8lx2AZfvECyxuNhBx0fEwTEPEJ0aCVQQx8y+HiAAyXfcoGJ" +
                        "g1ASVvFVo4dxa6BY90KmMDxc4Fo+tMUoWq0BzCFpB7RYR7mGG5kofQgAmGvEtFSrtYdj6g2mvcSF" +
                        "CXE6CMHYcsy57XL0FuJi+JlYxBm8xblqVjFMvSZZZRwcbuVDGcQwYFswkTvEIpoJCBEODiIreIVi" +
                        "UK8J+ZWBzVxmH1NV0ZjpMOoJ9oc6h1BB8+yMpngGgGmbsVXhUaTTvKG7t7xByDA84iHTAmHMC7fJ" +
                        "AFAB5hJUc8ETvTu6lSsQZRIBlO0S6lgi3DlYJazxABUSquW7QwywQW5UAjLuKfvMAPBAQwa3CDDL" +
                        "PzHBVkl+S3sYiF4N0PMFB0lRSKmn+sEa1e6X2zJUxTCO3YvMa8ULuGtyttvKBRRiBZAvoJNBv3Cm" +
                        "n1BuYg7fgNjzK6KlQYYMvTiV00PLcFJ2IgygF1TNyV3bGtTFoXmPY7yyjsQGR6NRL5irGtRiBml3" +
                        "fjEQRsqhiBvWJUfe40ZTHzKRTVAHKxEKAwLSls7m3+ZUCbwLhJOs8BOX9TZ/RxPxZqn7U5jfOXQ3" +
                        "9H4s0T+c2Zx0H/J8T9yafXQf8vkn/J4eifi/2I6hO036Gpx0/9k=";
        mode = 0;
    }

    public void detectAndAnalisis() {

        try {
            Map<String, Object> paramsMap = getParamsMap();
            String sortedDictStr = new StringsSortByDict().sortedDictStr(paramsMap);
            sortedDictStr = sortedDictStr + "&" + appKeyStr + "=" + appKey;
            System.out.println("sortedDictStr:");
            System.out.println(sortedDictStr);

//            String signatureStr = new String(Md5Utils.getBytes(new ByteArrayInputStream(sortedDictStr.getBytes())), defaultCharset).toUpperCase();
            String signatureStr = DigestUtils.md5Hex(sortedDictStr).toUpperCase();
            System.out.println("md5Str:");
            System.out.println(signatureStr);

            paramsMap.put(signKey, signatureStr);

            ResponseContent responseContent = HttpClientV455.doPost(apiUrl, paramsMap);
            System.out.println("接口调用返回内容为:");
            System.out.println(responseContent.getResponseContentString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Map<String, Object> getParamsMap() throws UnsupportedEncodingException {
        String encodeAppId = urlEncode(appId);
        String encodeTimestamp = urlEncode(timestamp);
        String encodeNonce = urlEncode(nonceStr);
        String encodeImage = urlEncode(image);
        String encodeMode = urlEncode(mode);
        System.out.println(appId + ", encode:" + encodeAppId);
        System.out.println(timestamp + ", encode:" + encodeTimestamp);
        System.out.println(nonceStr + ", encode:" + encodeNonce);
        System.out.println("image:");
        System.out.println(image);
        System.out.println("encodeImage:");
        System.out.println(encodeImage);
        System.out.println(mode + ", encode:" + encodeMode);

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put(appIdKey, encodeAppId);
        paramsMap.put(timestampKey, encodeTimestamp);
        paramsMap.put(nonceKey, encodeNonce);
        paramsMap.put(imageKey, encodeImage);
        paramsMap.put(modeKey, encodeMode);
        return paramsMap;
    }

    public String urlEncode(Object obj) throws UnsupportedEncodingException {
        return URLEncoder.encode(String.valueOf(obj), defaultCharset);//.toUpperCase();
    }

    public static void main(String[] args) {

        FaceDetectingAndAnalisis faceDetectingAndAnalisis = new FaceDetectingAndAnalisis();
        faceDetectingAndAnalisis.detectAndAnalisis();

    }


}
