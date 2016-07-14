package com.cool.ecook.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/7/14.
 */
public class SquareHeaderBean {

    private DataBean data;
    /**
     * data : {"followList":[{"image":"224217782","startTime":0,"endTime":24,"id":20,"type":1,"url":"http://pic.xiaochushuo.com/minisite/oneweek01/mall.ecook.cn.minisite12.html?f=ecook_sharehttp://pic."}],"squareList":[{"image":"223377268","startTime":0,"endTime":11,"id":7,"type":0,"url":"ecook://talktopic?topicname=早餐"},{"image":"223530694","startTime":11,"endTime":15,"id":8,"type":0,"url":"ecook://talktopic?topicname=午餐"},{"image":"223677139","startTime":16,"endTime":24,"id":9,"type":0,"url":"ecook://talktopic?topicname=晚餐"},{"image":"224058963","startTime":0,"endTime":24,"id":30,"type":0,"url":"http://tiyan.qiaohu.com/apply.php?supplier_id=129&ql_id=2280"},{"image":"224355925","startTime":0,"endTime":24,"id":32,"type":0,"url":"http://mall.ecook.cn/good/getGoodDetail?goodId=31700947"}]}
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
         * image : 224217782
         * startTime : 0
         * endTime : 24
         * id : 20
         * type : 1
         * url : http://pic.xiaochushuo.com/minisite/oneweek01/mall.ecook.cn.minisite12.html?f=ecook_sharehttp://pic.
         */

        private List<FollowListBean> followList;
        /**
         * image : 223377268
         * startTime : 0
         * endTime : 11
         * id : 7
         * type : 0
         * url : ecook://talktopic?topicname=早餐
         */

        private List<SquareListBean> squareList;

        public List<FollowListBean> getFollowList() {
            return followList;
        }

        public void setFollowList(List<FollowListBean> followList) {
            this.followList = followList;
        }

        public List<SquareListBean> getSquareList() {
            return squareList;
        }

        public void setSquareList(List<SquareListBean> squareList) {
            this.squareList = squareList;
        }

        public static class FollowListBean {
            private String image;
            private int startTime;
            private int endTime;
            private int id;
            private int type;
            private String url;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class SquareListBean {
            private String image;
            private int startTime;
            private int endTime;
            private int id;
            private int type;
            private String url;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getStartTime() {
                return startTime;
            }

            public void setStartTime(int startTime) {
                this.startTime = startTime;
            }

            public int getEndTime() {
                return endTime;
            }

            public void setEndTime(int endTime) {
                this.endTime = endTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
