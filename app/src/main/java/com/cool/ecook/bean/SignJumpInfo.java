package com.cool.ecook.bean;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/15.
 */
public class SignJumpInfo {

    /**
     * imageid : 224312699
     * month : 7月 July
     * description : 再连续签到7天就可以获得额外100厨币哦~
     * mallUrl : http://mall.ecook.cn/recommend/good
     * adList : [{"imageid":"224312699","type":"","url":"http://www.ecook.cn/public/postoutservlet?platform=4&type=qiaohu"},{"imageid":"224312616","type":"","url":"http://www.ecook.cn/public/postoutservlet?platform=4&type=waiguoren"}]
     * list : [{"date":"2016-07-14 00:00:00.0","isbig":"0","coin":"50"},{"date":"2016-07-15 00:00:00.0","isbig":"0","coin":"50"},{"date":"2016-07-16 00:00:00.0","isbig":"0","coin":"50"},{"date":"2016-07-17 00:00:00.0","isbig":"0","coin":"77"},{"date":"2016-07-18 00:00:00.0","isbig":"0","coin":"60"},{"date":"2016-07-19 00:00:00.0","isbig":"0","coin":"60"},{"date":"2016-07-20 00:00:00.0","isbig":"0","coin":"50"}]
     * url : http://www.ecook.cn/public/postoutservlet?platform=4&type=qiaohu
     */

    private DetailBean detail;
    /**
     * detail : {"imageid":"224312699","month":"7月 July","description":"再连续签到7天就可以获得额外100厨币哦~","mallUrl":"http://mall.ecook.cn/recommend/good","adList":[{"imageid":"224312699","type":"","url":"http://www.ecook.cn/public/postoutservlet?platform=4&type=qiaohu"},{"imageid":"224312616","type":"","url":"http://www.ecook.cn/public/postoutservlet?platform=4&type=waiguoren"}],"list":[{"date":"2016-07-14 00:00:00.0","isbig":"0","coin":"50"},{"date":"2016-07-15 00:00:00.0","isbig":"0","coin":"50"},{"date":"2016-07-16 00:00:00.0","isbig":"0","coin":"50"},{"date":"2016-07-17 00:00:00.0","isbig":"0","coin":"77"},{"date":"2016-07-18 00:00:00.0","isbig":"0","coin":"60"},{"date":"2016-07-19 00:00:00.0","isbig":"0","coin":"60"},{"date":"2016-07-20 00:00:00.0","isbig":"0","coin":"50"}],"url":"http://www.ecook.cn/public/postoutservlet?platform=4&type=qiaohu"}
     * state : 200
     * message : 获取成功！
     */

    private String state;
    private String message;

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
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

    public static class DetailBean {
        private String imageid;
        private String month;
        private String description;
        private String mallUrl;
        private String url;
        /**
         * imageid : 224312699
         * type :
         * url : http://www.ecook.cn/public/postoutservlet?platform=4&type=qiaohu
         */

        private List<AdListBean> adList;
        /**
         * date : 2016-07-14 00:00:00.0
         * isbig : 0
         * coin : 50
         */

        private List<ListBean> list;

        public String getImageid() {
            return imageid;
        }

        public void setImageid(String imageid) {
            this.imageid = imageid;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMallUrl() {
            return mallUrl;
        }

        public void setMallUrl(String mallUrl) {
            this.mallUrl = mallUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<AdListBean> getAdList() {
            return adList;
        }

        public void setAdList(List<AdListBean> adList) {
            this.adList = adList;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class AdListBean {
            private String imageid;
            private String type;
            private String url;

            public String getImageid() {
                return imageid;
            }

            public void setImageid(String imageid) {
                this.imageid = imageid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class ListBean {
            private String date;
            private String isbig;
            private String coin;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getIsbig() {
                return isbig;
            }

            public void setIsbig(String isbig) {
                this.isbig = isbig;
            }

            public String getCoin() {
                return coin;
            }

            public void setCoin(String coin) {
                this.coin = coin;
            }
        }
    }
}
