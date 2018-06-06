package com.mpif.beautyranking.util;

import java.util.*;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-02-12 6:37
 *
 * 字典序，即按照字段名的ASCII码从小到大排序
 *
 * 给出一组字符串, 然后输出这组字符串按字典序排序
 * 例如：
 *      abbcd abadc abbef
 * 排序后顺序为：
 *      abadc abbcd abbef
 *
 * 参考资料：
 * ASCII-百度百科
 * https://baike.baidu.com/item/ASCII/309296
 *
 * 微信JS-SDK说明文档
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141115
 * 在这个说明文档页面搜索"字典序"三个字即可查看相关说明
 *
 */

public class StringsSortByDict {

    public static void main(String[] args) {
        StringsSortByDict sortByDict = new StringsSortByDict();
//        sortByDict.printAllASCII();
//        sortByDict.compare();
        sortByDict.sortByDict();
    }

    public void printAllASCII() {

        int asciiCount = 2<<6; //即128
        System.out.println("asciiCount:" + asciiCount);

        for(int i = 0; i < asciiCount; i ++) {
            System.out.println((char)i);
        }

    }

    public void compare() {

        String[] arr01 = new String[]{
                "abcdefg",
                "abcABC",
                "efg"
        };

        String[] arr02 = new String[]{
                "abcefgd",
                "abcABCDD",
                "efg"
        };

        String str01 = "";
        String str02 = "";
        for(int i = 0; i < arr01.length; i ++) {
            str01 = arr01[i];
            str02 = arr02[i];
            System.out.println("[" + str01.compareTo(str02) + "]<--->[" + compareTo(str01, str02) + "]");
        }

    }

    public void sortByDict() {

        String[] arr = new String[]{
                "abcdefg",
                "abcABC",
                "efg"
        };

//        this.sort(Arrays.asList(arr));

//        this.sort(arr);
//        输出如下：
//        sort before:
//        abcdefg, abcABC, efg
//        sort after:
//        abcABC, abcdefg, efg

//        this.collectionsSort(arr);
//        输出如下：
//        Collections.sort before:
//        abcdefg, abcABC, efg
//        Collections.sort after:
//        abcABC, abcdefg, efg
//

        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("abcdefg", "张三");
        datas.put("abcABC", "李四");
        datas.put("efg", "王五");
        String sortedDictStr = this.sortedDictStr(datas);
        System.out.println("sortedDictStr:");
        System.out.println(sortedDictStr);
//        输出如下：
//        abcABC=李四&abcdefg=张三&efg=王五

    }

    public void sort(String[] arr) {

        String temp = "";
        int n = 0;
        System.out.println("sort before:");
        this.print(arr);

        for(int i = 0; i < arr.length - 1; i ++) {
            for(int j = i + 1; j < arr.length; j ++) {
                if(compareTo(arr[i], arr[j]) > 0) {
//                if(arr[i].compareTo(arr[j]) > 0) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("sort after:");
        this.print(arr);

    }

    public List<String> sort(List<String> list) {
        String[] arr = list.toArray(new String[]{});
        this.sort(arr);
        return Arrays.asList(arr);
    }

    public void collectionsSort(String[] arr) {

        System.out.println("Collections.sort before:");
        this.print(arr);

        Collections.sort(Arrays.asList(arr));

        System.out.println("Collections.sort after:");
        this.print(arr);

    }

    public String sortedDictStr(Map<String, Object> datas) {
        StringBuilder sb = new StringBuilder();

        List<String> keyList = new ArrayList<String>();
        Set<String> keys = datas.keySet();
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()) {
            keyList.add(iter.next());
        }

//        System.out.println("--->keyList:");
//        this.print(keyList);

        keyList = this.sort(keyList);

//        System.out.println("--->keyList after sort:");
//        this.print(keyList);

        String key = "";
        Object val = "";
        for(int i = 0; i < keyList.size(); i ++) {
            key = keyList.get(i);
            val = datas.get(key);
            if(i != keyList.size() - 1) {
                sb.append(key).append("=").append(val).append("&");
            } else {
                sb.append(key).append("=").append(val);
            }
        }

        return sb.toString();
    }

    /**
     * 如果first>second，则return 1
     * 如果first=second，则return 0
     * 如果first<second，则return -1
     * 否则return -2
     * @param first
     * @param second
     * @return
     */
    public int compareTo(String first, String second) {
        int result = -2;
        if(first == null) {
            throw new IllegalArgumentException("first can't be null");
        }
        if(second == null) {
            throw new IllegalArgumentException("second can't be null");
        }

        int fstLen = first.length();
        int secLen = second.length();

        int minLen = Math.min(fstLen, secLen);

        if(fstLen == secLen) {
            for(int i = 0; i < minLen; i ++) {
                if(first.charAt(i) != second.charAt(i)) {
                    result = first.charAt(i) - second.charAt(i);
                    return result;
                }
            }
        } else {
            for(int i = 0; i < minLen; i ++) {
                if(first.charAt(i) != second.charAt(i)) {
                    result = first.charAt(i) - second.charAt(i);
                    return result;
                }
            }
        }

        return fstLen - secLen;
    }

    public void print(String[] arr) {
        for(int i = 0; i < arr.length; i ++) {
            if(i != arr.length - 1) {
                System.out.print(arr[i] + ", ");
            } else {
                System.out.println(arr[i]);
            }
        }
    }

    public void print(List<String> arr) {
        for(int i = 0; i < arr.size(); i ++) {
            if(i != arr.size() - 1) {
                System.out.print(arr.get(i) + ", ");
            } else {
                System.out.println(arr.get(i));
            }
        }
    }

}



