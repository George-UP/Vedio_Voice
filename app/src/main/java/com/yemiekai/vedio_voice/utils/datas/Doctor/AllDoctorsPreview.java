package com.yemiekai.vedio_voice.utils.datas.Doctor;

import java.util.List;

public class AllDoctorsPreview {

    /**
     * count : 6
     * rows : [
     *          {
     *              "id":"1",
     *              "name":"曾康华",
     *              "post":"副院长",
     *              "avatarUrl":"\\public\\image\\doctor\\face\\doctor1.jpg",
     *              "title":"主任医师 、教授",
     *              "secondOfficeId":"1"
     *           },
     *           {
     *              "id":"2",
     *              "name":"罗骏",
     *              "post":"党委委员、副院长、心血管内一科主任",
     *              "avatarUrl":"\\public\\image\\doctor\\face\\doctor2.jpg",
     *              "title":"主任医师 、教授",
     *              "secondOfficeId":"1"
     *           },
     *           {
     *              "id":"f70b5cc0c73211e99f72a3fae2d0354e",
     *              "name":"陈仁华",
     *              "post":null,
     *              "avatarUrl":"\\public\\image\\doctor\\face\\doctor5.jpg",
     *              "title":"副主任医师",
     *              "secondOfficeId":"1"
     *           },
     *           {
     *              "id":"f70c4720c73211e99f72a3fae2d0354e",
     *              "name":"袁文金",
     *              "post":null,
     *              "avatarUrl":"\\public\\image\\doctor\\face\\doctor6.jpg",
     *              "title":"主任医师",
     *              "secondOfficeId":"1"
     *           },
     *           {
     *              "id":"f70d3180c73211e99f72a3fae2d0354e",
     *              "name":"王诚高",
     *              "post":null,
     *              "avatarUrl":"\\public\\image\\doctor\\face\\doctor7.jpg",
     *              "title":"副主任医师",
     *              "secondOfficeId":"1"
     *           },
     *           {
     *              "id":"f70df4d0c73211e99f72a3fae2d0354e",
     *              "name":"谢绍峰",
     *              "post":null,
     *              "avatarUrl":"\\public\\image\\doctor\\face\\doctor8.jpg",
     *              "title":"副主任医师","secondOfficeId":"1"
     *           }
     *        ]
     */

    private int count;
    private List<RowsBean> rows;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 1
         * name : 曾康华
         * post : 副院长
         * avatarUrl : \public\image\doctor\face\doctor1.jpg
         * title : 主任医师 、教授
         * secondOfficeId : 1
         */

        private String id;
        private String name;
        private String post;
        private String avatarUrl;
        private String title;
        private String secondOfficeId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSecondOfficeId() {
            return secondOfficeId;
        }

        public void setSecondOfficeId(String secondOfficeId) {
            this.secondOfficeId = secondOfficeId;
        }
    }
}
