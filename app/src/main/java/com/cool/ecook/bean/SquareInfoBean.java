package com.cool.ecook.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/7/15.
 */
public class SquareInfoBean {

    /**
     * collectionSortCount : 3
     * imageid : 223661719
     * recipeList : [{"imageid":"224044135","name":"鲜虾番茄炒黄瓜丁","id":"224046445"},{"imageid":"223641757","name":"丝瓜炒肾球","id":"223642764"},{"imageid":"223494601","name":"陈皮蒸猪腰","id":"223496349"},{"imageid":"223478442","name":"鲐鱼肉丸汤","id":"223478803"},{"imageid":"223472990","name":"南乳白肉","id":"223474089"},{"imageid":"223449346","name":"清拌白茄子","id":"223450418"}]
     * profile : 为家人用心做美食。
     * sex : 0
     * fansCount : 856
     * title : Moon
     * type : Normal
     * collectionSortList : [{"imageid":"223837430","name":"厨友的作品","id":"223838839"},{"imageid":"223418066","name":"面食","id":"223564746"}]
     * talkCount : 248
     * medal : gold
     * followsCount : 24
     * recipeCount : 15
     * id : 24347411
     */

    private DataBean data;
    /**
     * data : {"collectionSortCount":"3","imageid":"223661719","recipeList":[{"imageid":"224044135","name":"鲜虾番茄炒黄瓜丁","id":"224046445"},{"imageid":"223641757","name":"丝瓜炒肾球","id":"223642764"},{"imageid":"223494601","name":"陈皮蒸猪腰","id":"223496349"},{"imageid":"223478442","name":"鲐鱼肉丸汤","id":"223478803"},{"imageid":"223472990","name":"南乳白肉","id":"223474089"},{"imageid":"223449346","name":"清拌白茄子","id":"223450418"}],"profile":"为家人用心做美食。","sex":"0","fansCount":"856","title":"Moon","type":"Normal","collectionSortList":[{"imageid":"223837430","name":"厨友的作品","id":"223838839"},{"imageid":"223418066","name":"面食","id":"223564746"}],"talkCount":"248","medal":"gold","followsCount":"24","recipeCount":"15","id":"24347411"}
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
        private String collectionSortCount;
        private String imageid;
        private String profile;
        private String sex;
        private String fansCount;
        private String title;
        private String type;
        private String talkCount;
        private String medal;
        private String followsCount;
        private String recipeCount;
        private String id;
        /**
         * imageid : 224044135
         * name : 鲜虾番茄炒黄瓜丁
         * id : 224046445
         */

        private List<RecipeListBean> recipeList;
        /**
         * imageid : 223837430
         * name : 厨友的作品
         * id : 223838839
         */

        private List<CollectionSortListBean> collectionSortList;

        public String getCollectionSortCount() {
            return collectionSortCount;
        }

        public void setCollectionSortCount(String collectionSortCount) {
            this.collectionSortCount = collectionSortCount;
        }

        public String getImageid() {
            return imageid;
        }

        public void setImageid(String imageid) {
            this.imageid = imageid;
        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getFansCount() {
            return fansCount;
        }

        public void setFansCount(String fansCount) {
            this.fansCount = fansCount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTalkCount() {
            return talkCount;
        }

        public void setTalkCount(String talkCount) {
            this.talkCount = talkCount;
        }

        public String getMedal() {
            return medal;
        }

        public void setMedal(String medal) {
            this.medal = medal;
        }

        public String getFollowsCount() {
            return followsCount;
        }

        public void setFollowsCount(String followsCount) {
            this.followsCount = followsCount;
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

        public List<RecipeListBean> getRecipeList() {
            return recipeList;
        }

        public void setRecipeList(List<RecipeListBean> recipeList) {
            this.recipeList = recipeList;
        }

        public List<CollectionSortListBean> getCollectionSortList() {
            return collectionSortList;
        }

        public void setCollectionSortList(List<CollectionSortListBean> collectionSortList) {
            this.collectionSortList = collectionSortList;
        }

        public static class RecipeListBean {
            private String imageid;
            private String name;
            private String id;

            public String getImageid() {
                return imageid;
            }

            public void setImageid(String imageid) {
                this.imageid = imageid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class CollectionSortListBean {
            private String imageid;
            private String name;
            private String id;

            public String getImageid() {
                return imageid;
            }

            public void setImageid(String imageid) {
                this.imageid = imageid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
