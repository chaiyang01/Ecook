package com.cool.ecook.bean;

import java.util.List;

/**
 * Created by ningyachao on 2016/7/22.
 */
public class SearchInfoListOne {


    /**
     * state : 200
     * list : ["酸奶","酸奶\n"]
     * message : 获取成功！
     */

    private String state;
    private String message;
    private List<String> list;

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

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
