package com.cool.ecook.config;

/**
 * Created by lenovo on 2016/7/13.
 */
public class URLConfig {
    //图片前缀网址
    public static final String URL_PIC1="http://pic.ecook.cn/web/" ;
    public static final String URL_PIC2=".jpg";
    //图片前缀网址
    //第三页面广场的网址
    public static final String URL_SQUARE="http://api.ecook.cn/public/getHotTalkList.shtml";
    //第三页面广告
    public static final String URL_SQUARE2="http://api.ecook.cn/public/getTalkSquareAds.shtml";
    //第三页面转跳的内容
    public static final String URL_SQUARE3="http://api.ecook.cn/public/getTopicReferTalksWithoutLogin.shtml";
    //第三页面个人信息
    public static final String URL_SQUARE4 = "http://api.ecook.cn/public/getUserPageData.shtml";
    //图片契合地址,中间数字为替换的ID
    public static final String PIC_ADDR = "http://pic.ecook.cn/web/";
    public static final String PIC_ADDR2 = ".jpg!s1";
    //菜谱界面的listview数据
    public static final String  COOKBOOK_LISTVIEW = "http://api.ecook.cn/public/getRecipeHomeData.shtml";
    //最新专辑界面数据（get）
    public static final String COOKBOOK_SPECIAL = "http://api.ecook.cn/public/getCollectionSortListByType.shtml";
    //最热专辑界面数据（post）
    public static final String COOKBOOK_HOT = "http://api.ecook.cn/public/getCollectionSortListByType.shtml";

}
