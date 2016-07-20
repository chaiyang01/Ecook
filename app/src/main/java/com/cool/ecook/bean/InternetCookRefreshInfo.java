package com.cool.ecook.bean;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/14.
 */
public class InternetCookRefreshInfo {


    private DataBean data;
    /**
     * data : {"list":[{"image":"224335623","collectionNum":153,"title":"烤鸡米花","type":1,"userId":31622903,"likeNum":0,"userImage":"224333760","totalNum":"","userNick":"百.合","sortId":1599,"id":224336864,"desc":"鲜嫩多汁 美味"},{"image":"224335996","collectionNum":142,"title":"炒馒头片","type":1,"userId":6231556,"likeNum":0,"userImage":"14482500","totalNum":"","userNick":"月牙小厨","sortId":1598,"id":224336169,"desc":""},{"image":"224324378","collectionNum":29,"title":"「梅子三文鱼茶泡饭」","type":1,"userId":23961091,"likeNum":0,"userImage":"224324217","totalNum":"","userNick":"自然卷小姐","sortId":1597,"id":224324628,"desc":"看深夜食堂开始就馋上了，\n每次看\u201c茶泡饭三姐妹\u201d捧着精致的饭碗，\n用木筷轻轻掏一掏碗中的泡饭，\n然后一小口一小口喂进嘴里，幸福感超强。\n\n夏天总想吃一点清爽的食物，\n从炒荞麦米开始到煎三文鱼，\n全凭感觉去放料理，\n再加一点芥末，味道好极了！"},{"image":"7268066","collectionNum":2714,"title":"水果可丽饼","type":3,"userId":5586332,"likeNum":0,"userImage":"5586447","totalNum":"","userNick":"芙芙姓liu","sortId":1596,"id":7268113,"desc":"材料：低筋面粉100克糖10克鸡蛋3个牛奶200克黄油20克黄桃火龙果腰果酸奶1�"},{"image":"224323948","collectionNum":110,"title":"剁椒鱼头","type":1,"userId":14556372,"likeNum":0,"userImage":"223325265","totalNum":"","userNick":"海底世界","sortId":1595,"id":224324209,"desc":"我做的实际是剁椒鱼，因为小渔民，不舍得扔掉其余部分。头和身子都留着了。"},{"image":"19211368","collectionNum":0,"title":"走在以食养【颜】的道路上","type":2,"userId":3153313,"likeNum":0,"userImage":"3363968","totalNum":"一共39菜","userNick":"CTT的私人小厨","sortId":1594,"id":11747341,"desc":"以食养颜，做最美的自己。"},{"image":"224333057","collectionNum":230,"title":"鲜虾蛋卷","type":1,"userId":31599636,"likeNum":0,"userImage":"224022519","totalNum":"","userNick":"闹闹","sortId":1593,"id":224329277,"desc":"给宝宝，最好的"},{"image":"224339328","collectionNum":80,"title":"莲子百合清粥","type":1,"userId":24628527,"likeNum":0,"userImage":"224023475","totalNum":"","userNick":"祝宝贝","sortId":1592,"id":224339077,"desc":"家人要求清粥，我就做了这个，希望大家喜欢。"},{"image":"224333067","collectionNum":86,"title":"全麦苏打面包","type":1,"userId":24260526,"likeNum":0,"userImage":"223322988","totalNum":"","userNick":"无忧de小时光","sortId":1591,"id":224301973,"desc":"炎热的夏天到了，我又加入了轰轰烈烈的减脂大军！说来惭愧，冬天总是馋，又懒得动，攒得一身五花肉都要留到夏天来消耗！甜点是不敢做了，长肉真的是高高的！终于尝试了这款不用发酵的方子，简直太适合我了，没有额外添加的油和糖，比花式面包减少了许多热量，一半分量的全麦粉加入，迈进了低GI食物的门槛，各种果干和果仁的加入，丰富了口感和层次！为了当做健身代餐我还加了两大勺蛋白粉，锻炼以后切一片嚼一嚼！且不说能有多少用，至少心里是非常满足的！口感软中带韧，越嚼越香，也是一款非常不错的磨牙小食！不用发酵，不用揉面，超级快手！"},{"image":"4802696","collectionNum":7142,"title":"男神张亮推荐--水煮牛肉'老公出品","type":3,"userId":1305158,"likeNum":0,"userImage":"1305159","totalNum":"","userNick":"紫滢儿","sortId":1590,"id":4802838,"desc":"材料：牛肉豆瓣酱大蒜子黄豆芽或生菜八角'�"}]}
     * state : 200
     * message : 获取成功！
     */

    private String state;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * image : 224335623
         * collectionNum : 153
         * title : 烤鸡米花
         * type : 1
         * userId : 31622903
         * likeNum : 0
         * userImage : 224333760
         * totalNum :
         * userNick : 百.合
         * sortId : 1599
         * id : 224336864
         * desc : 鲜嫩多汁 美味
         */

        private List<ContentListBean> list;

        public List<ContentListBean> getList() {
            return list;
        }

        public void setList(List<ContentListBean> list) {
            this.list = list;
        }

    }
}
