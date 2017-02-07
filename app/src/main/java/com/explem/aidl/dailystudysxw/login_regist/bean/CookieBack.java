package com.explem.aidl.dailystudysxw.login_regist.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/22 0022.
 */

public class CookieBack {

    /**
     * data : [{"cid":"5615","course_pic":"http://img.dianfu.net/img/20161128/bf307f6af18e5654078a147d1a10697a.jpg","course_price":"0.00","course_name":"实用编发教程（28）","course_hour":"9","status":"0","studycount":"0","comment_status":0},{"cid":"4295","course_pic":"http://img.dianfu.net/img/20151109/61cc20935af0dce86f60b412830de555.jpg","course_price":"0.00","course_name":"制作高大上的手工包包（一）","course_hour":"4","status":"0","studycount":"0","comment_status":0}]
     * count : 2
     */

    private String count;
    private List<DataBean> data;
    private String msg;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cid : 5615
         * course_pic : http://img.dianfu.net/img/20161128/bf307f6af18e5654078a147d1a10697a.jpg
         * course_price : 0.00
         * course_name : 实用编发教程（28）
         * course_hour : 9
         * status : 0
         * studycount : 0
         * comment_status : 0
         */

        private String cid;
        private String course_pic;
        private String course_price;
        private String course_name;
        private String course_hour;
        private String status;
        private String studycount;
        private int comment_status;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getCourse_pic() {
            return course_pic;
        }

        public void setCourse_pic(String course_pic) {
            this.course_pic = course_pic;
        }

        public String getCourse_price() {
            return course_price;
        }

        public void setCourse_price(String course_price) {
            this.course_price = course_price;
        }

        public String getCourse_name() {
            return course_name;
        }

        public void setCourse_name(String course_name) {
            this.course_name = course_name;
        }

        public String getCourse_hour() {
            return course_hour;
        }

        public void setCourse_hour(String course_hour) {
            this.course_hour = course_hour;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStudycount() {
            return studycount;
        }

        public void setStudycount(String studycount) {
            this.studycount = studycount;
        }

        public int getComment_status() {
            return comment_status;
        }

        public void setComment_status(int comment_status) {
            this.comment_status = comment_status;
        }
    }
}
