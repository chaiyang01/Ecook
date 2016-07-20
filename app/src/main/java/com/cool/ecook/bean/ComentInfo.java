package com.cool.ecook.bean;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/18.
 */
public class ComentInfo {

    /**
     * state : 200
     * message : 获取成功！
     * info : {"work":[{"imageid":"224464293","userimageid":"224464218","nickname":"apptest","id":"224464297","userid":"34161753"},{"imageid":"223409264","userimageid":"223478530","nickname":"吃货菇凉","id":"27776208","userid":"23736418"}],"collectionCount":"5362","comment":[{"contentid":"89686","type":"recipe","userid":"19430633","targetusername":"","targetuserid":"","displaytime":"2小时前","userimageid":"11998015","addtime":"2016-07-18 16:49:00.0","targetUserImageid":"","id":"224464808","text":"赞","talkid":"27776208","username":"心意合一c"},{"contentid":"89686","type":"recipe","userid":"19430633","targetusername":"","targetuserid":"","displaytime":"2小时前","userimageid":"11998015","addtime":"2016-07-18 16:48:53.0","targetUserImageid":"","id":"224464805","text":"赞","talkid":"27776208","username":"心意合一c"},{"contentid":"89686","type":"recipe","userid":"34161753","targetusername":"","targetuserid":"","displaytime":"2小时前","userimageid":"224464218","addtime":"2016-07-18 16:27:37.0","targetUserImageid":"","id":"224464298","text":"xk","talkid":"27776208","username":"apptest"},{"contentid":"89686","type":"recipe","userid":"33846702","targetusername":"","targetuserid":"","displaytime":"2小时前","userimageid":"11998015","addtime":"2016-07-18 16:15:11.0","targetUserImageid":"","id":"224464063","text":"招大字员，又意这╋口口2\u20e33\u20e38\u20e32\u20e38\u20e39\u20e34\u20e39\u20e37\u20e31\u20e3","talkid":"27776208","username":"诚招打字员"},{"contentid":"89686","type":"album","userid":"24890379","targetusername":"","targetuserid":"","displaytime":"05-26 07:06","userimageid":"11998015","addtime":"2016-05-26 07:06:10.0","targetUserImageid":"","id":"27142922","text":"我看图觉的饺子皮有点厚，薄皮大馅饺子更好吃。","talkid":"","username":"雷明芯语"}],"likeCount":"73","id":"89686","workCount":"2","commentCount":"22"}
     */

    private String state;
    private String message;
    /**
     * work : [{"imageid":"224464293","userimageid":"224464218","nickname":"apptest","id":"224464297","userid":"34161753"},{"imageid":"223409264","userimageid":"223478530","nickname":"吃货菇凉","id":"27776208","userid":"23736418"}]
     * collectionCount : 5362
     * comment : [{"contentid":"89686","type":"recipe","userid":"19430633","targetusername":"","targetuserid":"","displaytime":"2小时前","userimageid":"11998015","addtime":"2016-07-18 16:49:00.0","targetUserImageid":"","id":"224464808","text":"赞","talkid":"27776208","username":"心意合一c"},{"contentid":"89686","type":"recipe","userid":"19430633","targetusername":"","targetuserid":"","displaytime":"2小时前","userimageid":"11998015","addtime":"2016-07-18 16:48:53.0","targetUserImageid":"","id":"224464805","text":"赞","talkid":"27776208","username":"心意合一c"},{"contentid":"89686","type":"recipe","userid":"34161753","targetusername":"","targetuserid":"","displaytime":"2小时前","userimageid":"224464218","addtime":"2016-07-18 16:27:37.0","targetUserImageid":"","id":"224464298","text":"xk","talkid":"27776208","username":"apptest"},{"contentid":"89686","type":"recipe","userid":"33846702","targetusername":"","targetuserid":"","displaytime":"2小时前","userimageid":"11998015","addtime":"2016-07-18 16:15:11.0","targetUserImageid":"","id":"224464063","text":"招大字员，又意这╋口口2\u20e33\u20e38\u20e32\u20e38\u20e39\u20e34\u20e39\u20e37\u20e31\u20e3","talkid":"27776208","username":"诚招打字员"},{"contentid":"89686","type":"album","userid":"24890379","targetusername":"","targetuserid":"","displaytime":"05-26 07:06","userimageid":"11998015","addtime":"2016-05-26 07:06:10.0","targetUserImageid":"","id":"27142922","text":"我看图觉的饺子皮有点厚，薄皮大馅饺子更好吃。","talkid":"","username":"雷明芯语"}]
     * likeCount : 73
     * id : 89686
     * workCount : 2
     * commentCount : 22
     */

    private InfoBean info;

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

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class InfoBean {
        private String collectionCount;
        private String likeCount;
        private String id;
        private String workCount;
        private String commentCount;
        /**
         * imageid : 224464293
         * userimageid : 224464218
         * nickname : apptest
         * id : 224464297
         * userid : 34161753
         */

        private List<WorkBean> work;
        /**
         * contentid : 89686
         * type : recipe
         * userid : 19430633
         * targetusername :
         * targetuserid :
         * displaytime : 2小时前
         * userimageid : 11998015
         * addtime : 2016-07-18 16:49:00.0
         * targetUserImageid :
         * id : 224464808
         * text : 赞
         * talkid : 27776208
         * username : 心意合一c
         */

        private List<CommentBean> comment;

        public String getCollectionCount() {
            return collectionCount;
        }

        public void setCollectionCount(String collectionCount) {
            this.collectionCount = collectionCount;
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

        public String getWorkCount() {
            return workCount;
        }

        public void setWorkCount(String workCount) {
            this.workCount = workCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public List<WorkBean> getWork() {
            return work;
        }

        public void setWork(List<WorkBean> work) {
            this.work = work;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public static class WorkBean {
            private String imageid;
            private String userimageid;
            private String nickname;
            private String id;
            private String userid;

            public String getImageid() {
                return imageid;
            }

            public void setImageid(String imageid) {
                this.imageid = imageid;
            }

            public String getUserimageid() {
                return userimageid;
            }

            public void setUserimageid(String userimageid) {
                this.userimageid = userimageid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }
        }

        public static class CommentBean {
            private String contentid;
            private String type;
            private String userid;
            private String targetusername;
            private String targetuserid;
            private String displaytime;
            private String userimageid;
            private String addtime;
            private String targetUserImageid;
            private String id;
            private String text;
            private String talkid;
            private String username;

            public String getContentid() {
                return contentid;
            }

            public void setContentid(String contentid) {
                this.contentid = contentid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getTargetusername() {
                return targetusername;
            }

            public void setTargetusername(String targetusername) {
                this.targetusername = targetusername;
            }

            public String getTargetuserid() {
                return targetuserid;
            }

            public void setTargetuserid(String targetuserid) {
                this.targetuserid = targetuserid;
            }

            public String getDisplaytime() {
                return displaytime;
            }

            public void setDisplaytime(String displaytime) {
                this.displaytime = displaytime;
            }

            public String getUserimageid() {
                return userimageid;
            }

            public void setUserimageid(String userimageid) {
                this.userimageid = userimageid;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getTargetUserImageid() {
                return targetUserImageid;
            }

            public void setTargetUserImageid(String targetUserImageid) {
                this.targetUserImageid = targetUserImageid;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getTalkid() {
                return talkid;
            }

            public void setTalkid(String talkid) {
                this.talkid = talkid;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
