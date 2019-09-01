package com.yemiekai.vedio_voice.utils.datas.Introduction;

import java.util.List;

//医院介绍————初始
public class Introduction {

    /**
     * res : {"count":3,"rows":[{"id":"1","message":"医院"},{"id":"2","message":"门诊大厅"},{"id":"4f0ffd80ccac11e9bd775b558181f131","message":"血液净化中心"}]}
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
         * rows : [{"id":"1","message":"医院"},{"id":"2","message":"门诊大厅"},{"id":"4f0ffd80ccac11e9bd775b558181f131","message":"血液净化中心"}]
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
             * message : 医院
             */

            private String id;
            private String message;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
}
