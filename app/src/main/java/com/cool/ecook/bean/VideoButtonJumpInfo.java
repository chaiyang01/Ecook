package com.cool.ecook.bean;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/16.
 */
public class VideoButtonJumpInfo {


    /**
     * state : 200
     * list : [{"imageid":"223462576","authorname":"杨桃美食","collectCount":"373","name":"蒸蛋","description":"","likeCount":"108","id":"223462596","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"30"},{"imageid":"223462950","authorname":"杨桃美食","collectCount":"184","name":"芝麻烤雞腿","description":"","likeCount":"72","id":"223462984","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"29"},{"imageid":"223465254","authorname":"杨桃美食","collectCount":"334","name":"自製雞蛋豆腐","description":"","likeCount":"89","id":"223465277","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"23"},{"imageid":"223423482","authorname":"杨桃美食","collectCount":"155","name":"用烤箱做粉蒸肉","description":"","likeCount":"74","id":"223423500","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"20"},{"imageid":"223424378","authorname":"杨桃美食","collectCount":"71","name":"用烤箱做鹹蛋瓜仔肉","description":"","likeCount":"62","id":"223424427","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"24"},{"imageid":"223424958","authorname":"杨桃美食","collectCount":"87","name":"魷魚螺肉蒜","description":"","likeCount":"63","id":"223424977","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"25"},{"imageid":"223424720","authorname":"杨桃美食","collectCount":"284","name":"油蔥雞","description":"","likeCount":"73","id":"223424756","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"20"},{"imageid":"223418293","authorname":"杨桃美食","collectCount":"161","name":"雞蓉玉米濃湯","description":"","likeCount":"80","id":"26650582","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"19"},{"imageid":"223414341","authorname":"杨桃美食","collectCount":"178","name":"銀芽雞絲(素食)","description":"","likeCount":"102","id":"27994465","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"23"},{"imageid":"223414465","authorname":"杨桃美食","collectCount":"108","name":"用電鍋作簡易佛跳牆","description":"","likeCount":"73","id":"27998233","authorid":"25924808","type":"","authorimageid":"223373106","commentCount":"21"}]
     * message : 获取成功！
     */

    private String state;
    private String message;
    /**
     * imageid : 223462576
     * authorname : 杨桃美食
     * collectCount : 373
     * name : 蒸蛋
     * description :
     * likeCount : 108
     * id : 223462596
     * authorid : 25924808
     * type :
     * authorimageid : 223373106
     * commentCount : 30
     */

    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String imageid;
        private String authorname;
        private String collectCount;
        private String name;
        private String description;
        private String likeCount;
        private String id;
        private String authorid;
        private String type;
        private String authorimageid;
        private String commentCount;

        public String getImageid() {
            return imageid;
        }

        public void setImageid(String imageid) {
            this.imageid = imageid;
        }

        public String getAuthorname() {
            return authorname;
        }

        public void setAuthorname(String authorname) {
            this.authorname = authorname;
        }

        public String getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(String collectCount) {
            this.collectCount = collectCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAuthorimageid() {
            return authorimageid;
        }

        public void setAuthorimageid(String authorimageid) {
            this.authorimageid = authorimageid;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }
    }
}
