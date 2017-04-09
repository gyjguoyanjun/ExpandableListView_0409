package com.gyj.expandablelistview_0409.bean;

import java.util.ArrayList;

/**
 * data:2017/4/9
 * author:郭彦君(Administrator)
 * function:
 */
public class Info {
    public ArrayList<Result> result;

    public Info(ArrayList<Result> result) {
        this.result = result;
    }

    public class Result {
        public String city;
        public String cityid;
        public String parentid;

        public Result(String city, String cityid, String parentid) {
            this.city = city;
            this.cityid = cityid;
            this.parentid = parentid;
        }
    }

}
