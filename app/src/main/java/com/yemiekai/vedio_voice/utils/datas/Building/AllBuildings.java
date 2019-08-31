package com.yemiekai.vedio_voice.utils.datas.Building;

import java.util.List;

public class AllBuildings {
    /**
     * res : {"count":3,"rows":[{"id":"1","name":"门诊楼","photoUrl":"\\public\\image\\hosptial\\building\\building1.jpg","isMain":false},{"id":"639c4460c79911e99b845b23dde801b5","name":"医院总览图","photoUrl":null,"isMain":true},{"id":"66877d70c79911e99b845b23dde801b5","name":"急诊楼","photoUrl":"\\public\\image\\hosptial\\building\\building1.jpg","isMain":false}]}
     */

    private ResBean res;

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * count : 3
         * rows : [{"id":"1","name":"门诊楼","photoUrl":"\\public\\image\\hosptial\\building\\building1.jpg","isMain":false},{"id":"639c4460c79911e99b845b23dde801b5","name":"医院总览图","photoUrl":null,"isMain":true},{"id":"66877d70c79911e99b845b23dde801b5","name":"急诊楼","photoUrl":"\\public\\image\\hosptial\\building\\building1.jpg","isMain":false}]
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
             * name : 门诊楼
             * photoUrl : \public\image\hosptial\building\building1.jpg
             * isMain : false
             */

            private String id;
            private String name;
            private String photoUrl;
            private boolean isMain;

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

            public String getPhotoUrl() {
                return photoUrl;
            }

            public void setPhotoUrl(String photoUrl) {
                this.photoUrl = photoUrl;
            }

            public boolean isIsMain() {
                return isMain;
            }

            public void setIsMain(boolean isMain) {
                this.isMain = isMain;
            }
        }
    }
}
