package com.cool.ecook.bean;

import java.util.List;

/**
 * Created by HP on 2016/7/18.
 */
public class CookBookDetailsInfo {

    /**
     * commentList : []
     * imageid : 18097642
     * recipeList : [{"imageid":"18097642","authorname":"贪吃鱼~祸害猫","collectCount":"851","name":"彩虹慕斯","description":"好吃不腻，不需进烤箱的慕斯蛋糕！","likeCount":"527","id":"18097638","authorid":"17704175","type":"","authorimageid":"17200214","commentCount":"14"},{"imageid":"18136732","authorname":"还好吧","collectCount":"128","name":"经典重芝士","description":"最爱的蛋糕，没有之一","likeCount":"26","id":"18136727","authorid":"16177164","type":"","authorimageid":"14594536","commentCount":"5"},{"imageid":"16619847","authorname":"Miss苒","collectCount":"599","name":"日式奶酪蛋糕","description":"曾经做过几次奶酪蛋糕，可是家里没人爱吃，做的一个都是自己吃，我哪吃得了那么多啊，更不敢多吃啊，怕胖的说...不断的找合适的方子，正好前几天朋友要舒芙蕾，我也找到一个感觉比较不错的方子，就试着给她做了一个，没想到她反馈说非常好吃，她老公还说每","likeCount":"1","id":"16619840","authorid":"14687802","type":"","authorimageid":"16640492","commentCount":"0"},{"imageid":"18181532","authorname":"婷\u2026","collectCount":"269","name":"轻乳酪蛋糕","description":"清爽绵软的轻乳酪，搭配自制芒果卡仕达酱，别有一番小清新。","likeCount":"0","id":"18181521","authorid":"19291941","type":"","authorimageid":"1875838","commentCount":"0"},{"imageid":"7388494","authorname":"简单好。","collectCount":"50","name":"轻乳酪蛋糕","description":"轻乳酪蛋糕","likeCount":"130","id":"7388555","authorid":"6807203","type":"","authorimageid":"6839615","commentCount":"0"}]
     * description : 这个人很懒，什么都没写
     * userid : 33410706
     * commentCount : 0
     * tags :
     * usernickname : Yi Wen
     * userimageid : 224337969
     * name : 蛋糕
     * recipeCount : 5
     * id : 224338048
     * time : 创建于2016-07-18
     */

    private DetailsBean details;
    /**
     * details : {"commentList":[],"imageid":"18097642","recipeList":[{"imageid":"18097642","authorname":"贪吃鱼~祸害猫","collectCount":"851","name":"彩虹慕斯","description":"好吃不腻，不需进烤箱的慕斯蛋糕！","likeCount":"527","id":"18097638","authorid":"17704175","type":"","authorimageid":"17200214","commentCount":"14"},{"imageid":"18136732","authorname":"还好吧","collectCount":"128","name":"经典重芝士","description":"最爱的蛋糕，没有之一","likeCount":"26","id":"18136727","authorid":"16177164","type":"","authorimageid":"14594536","commentCount":"5"},{"imageid":"16619847","authorname":"Miss苒","collectCount":"599","name":"日式奶酪蛋糕","description":"曾经做过几次奶酪蛋糕，可是家里没人爱吃，做的一个都是自己吃，我哪吃得了那么多啊，更不敢多吃啊，怕胖的说...不断的找合适的方子，正好前几天朋友要舒芙蕾，我也找到一个感觉比较不错的方子，就试着给她做了一个，没想到她反馈说非常好吃，她老公还说每","likeCount":"1","id":"16619840","authorid":"14687802","type":"","authorimageid":"16640492","commentCount":"0"},{"imageid":"18181532","authorname":"婷\u2026","collectCount":"269","name":"轻乳酪蛋糕","description":"清爽绵软的轻乳酪，搭配自制芒果卡仕达酱，别有一番小清新。","likeCount":"0","id":"18181521","authorid":"19291941","type":"","authorimageid":"1875838","commentCount":"0"},{"imageid":"7388494","authorname":"简单好。","collectCount":"50","name":"轻乳酪蛋糕","description":"轻乳酪蛋糕","likeCount":"130","id":"7388555","authorid":"6807203","type":"","authorimageid":"6839615","commentCount":"0"}],"description":"这个人很懒，什么都没写","userid":"33410706","commentCount":"0","tags":"","usernickname":"Yi Wen","userimageid":"224337969","name":"蛋糕","recipeCount":"5","id":"224338048","time":"创建于2016-07-18"}
     * state : 200
     * message : 获取成功！
     */

    private String state;
    private String message;

    public DetailsBean getDetails() {
        return details;
    }

    public void setDetails(DetailsBean details) {
        this.details = details;
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

    public static class DetailsBean {
        private String imageid;
        private String description;
        private String userid;
        private String commentCount;
        private String tags;
        private String usernickname;
        private String userimageid;
        private String name;
        private String recipeCount;
        private String id;
        private String time;
        private List<CommentListBean> commentList;
        /**
         * imageid : 18097642
         * authorname : 贪吃鱼~祸害猫
         * collectCount : 851
         * name : 彩虹慕斯
         * description : 好吃不腻，不需进烤箱的慕斯蛋糕！
         * likeCount : 527
         * id : 18097638
         * authorid : 17704175
         * type :
         * authorimageid : 17200214
         * commentCount : 14
         */

        private List<RecipeListBean> recipeList;

        public String getImageid() {
            return imageid;
        }

        public void setImageid(String imageid) {
            this.imageid = imageid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getUsernickname() {
            return usernickname;
        }

        public void setUsernickname(String usernickname) {
            this.usernickname = usernickname;
        }

        public String getUserimageid() {
            return userimageid;
        }

        public void setUserimageid(String userimageid) {
            this.userimageid = userimageid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRecipeCount() {
            return recipeCount;
        }

        public void setRecipeCount(String recipeCount) {
            this.recipeCount = recipeCount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public List<RecipeListBean> getRecipeList() {
            return recipeList;
        }

        public void setRecipeList(List<RecipeListBean> recipeList) {
            this.recipeList = recipeList;
        }

        public static class CommentListBean{
            private String displaytime;
            private String userimageid;
            private String id;
            private String text;
            private String userid;
            private String usernickname;

            public CommentListBean(String displaytime, String userimageid, String id, String text, String userid, String usernickname) {
                this.displaytime = displaytime;
                this.userimageid = userimageid;
                this.id = id;
                this.text = text;
                this.userid = userid;
                this.usernickname = usernickname;
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

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getUsernickname() {
                return usernickname;
            }

            public void setUsernickname(String usernickname) {
                this.usernickname = usernickname;
            }
        }


        public static class RecipeListBean {
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
}
