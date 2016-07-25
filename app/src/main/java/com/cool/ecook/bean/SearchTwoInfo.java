package com.cool.ecook.bean;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/22.
 */
public class SearchTwoInfo {


    /**
     * total : 2000
     * userList : [{"recipeCount":"0","fansCount":"0","id":"2449438","pic":"2449439","title":"楼拉dance"},{"recipeCount":"0","fansCount":"0","id":"24630627","pic":"11998013","title":"信仰"},{"recipeCount":"0","fansCount":"0","id":"3824773","pic":"3824774","title":"25"},{"recipeCount":"0","fansCount":"0","id":"33107837","pic":"11998015","title":"25"},{"recipeCount":"0","fansCount":"0","id":"11601918","pic":"11601919","title":"╅.25"},{"recipeCount":"0","fansCount":"0","id":"9123424","pic":"9123425","title":"25。"},{"recipeCount":"0","fansCount":"0","id":"670213","pic":"670214","title":" - 25"},{"recipeCount":"0","fansCount":"0","id":"8037789","pic":"8037790","title":"____________25"},{"recipeCount":"0","fansCount":"23","id":"559942","pic":"559943","title":"叁又叁Thr3e"},{"recipeCount":"0","fansCount":"0","id":"13295549","pic":"13295550","title":".﹎6月25號."}]
     * from : 0
     */

    private int total;
    private int from;
    /**
     * recipeCount : 0
     * fansCount : 0
     * id : 2449438
     * pic : 2449439
     * title : 楼拉dance
     */

    private List<UserListBean> userList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public List<UserListBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserListBean> userList) {
        this.userList = userList;
    }

    public static class UserListBean {
        private String recipeCount;
        private String fansCount;
        private String id;
        private String pic;
        private String title;

        public String getRecipeCount() {
            return recipeCount;
        }

        public void setRecipeCount(String recipeCount) {
            this.recipeCount = recipeCount;
        }

        public String getFansCount() {
            return fansCount;
        }

        public void setFansCount(String fansCount) {
            this.fansCount = fansCount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
