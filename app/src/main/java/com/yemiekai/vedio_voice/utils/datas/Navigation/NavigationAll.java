package com.yemiekai.vedio_voice.utils.datas.Navigation;

import java.util.List;

public class NavigationAll {

    /**
     * res : {"count":3,"rows":[{"id":"4cbdd020ccac11e9bd775b558181f131","name":"医院总览图"},{"id":"1","name":"门诊楼"},{"id":"4f16db50ccac11e9bd775b558181f131","name":"急诊楼"}]}
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
         * rows : [{"id":"4cbdd020ccac11e9bd775b558181f131","name":"医院总览图"},{"id":"1","name":"门诊楼"},{"id":"4f16db50ccac11e9bd775b558181f131","name":"急诊楼"}]
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
             * id : 4cbdd020ccac11e9bd775b558181f131
             * name : 医院总览图
             */

            private String id;
            private String name;

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
        }
    }
}
