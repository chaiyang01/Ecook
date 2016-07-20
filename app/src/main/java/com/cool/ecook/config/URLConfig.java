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
    //图片契合地址,中间数字为替换的ID
    public static final String PIC_ADDR = "http://pic.ecook.cn/web/14516602.jpg!s1";
    //菜谱界面的listview数据
    public static final String  COOKBOOK_LISTVIEW = "http://api.ecook.cn/public/getRecipeHomeData.shtml";

    //第一个主界面签到跳转
    public static final String  INTERNET_COOK_SIGN_JUMP="http://api.ecook.cn/public/signinConfig.shtml?machine=868b83db44bafe15546203bbddc50360&version=12.4.6&device=GT-P5210";
    //第一个界面商城跳转
    public static final String INTERNET_COOK_SHOPPING_JUMP="http://mall.ecook.cn/category/list?f=ecook_show_mine&machine=868b83db44bafe15546203bbddc50360";
    //第一个界面活动跳转
    public static final String INTERNET_COOK_ACTIVITY_JUMP="http://api.ecook.cn/public/getActList.shtml";
}
