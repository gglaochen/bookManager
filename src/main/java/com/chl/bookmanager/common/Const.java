package com.chl.bookmanager.common;

/**
 * @author:Administrator
 * @date:2018/12/25/025
 */
public final class Const {

    //图书所属国家:0中国1英国2美国3北欧4其他地区
    public static final int BOOK_COUNTRY_CHINA = 0;
    public static final int BOOK_COUNTRY_UK = 1;
    public static final int BOOK_COUNTRY_USA = 2;
    public static final int BOOK_COUNTRY_EU = 3;
    public static final int BOOK_COUNTRY_OTHER = 4;
    public static final String[] bookCountryArray = {"中国", "英国", "美国",
            "北欧", "其他地区"};

    //读者账户状态:0正常1过期2挂失3注销
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_OVERDUE = 1;
    public static final int STATUS_REPORT = 2;
    public static final int STATUS_CANCEL = 3;
    public static final String[] readerStatusArray = {"正常", "过期", "挂失", "注销"};

    //查询排序编号:
//    public static final int ORDER_BY_ID = 1;
//    public static final int ORDER_BY_BOOKNAME = 2;
//    public static final int ORDER_BY_AUTHORNAME = 3;
//    public static final int ORDER_BY_PUBLISHDATE_ASC = 4;
//    public static final int ORDER_BY_PUBLISHDATE_DESC = 5;
//    public static final String[] orderByArray={""}

    public static String getBookCountryName(int countryNum) {
        return bookCountryArray[countryNum];
    }

    public static String getReaderStatusName(int status) {
        return readerStatusArray[status];
    }
}
