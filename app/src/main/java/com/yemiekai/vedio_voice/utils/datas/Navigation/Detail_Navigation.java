package com.yemiekai.vedio_voice.utils.datas.Navigation;

import java.util.List;

public class Detail_Navigation {
    private List<ResBean> res;

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        /**
         * id : 1
         * name : 门诊楼
         * photoUrl : \public\image\hosptial\building\building1.jpg
         * isMain : false
         * floors : [{"id":"4f17ecc0ccac11e9bd775b558181f131","name":1,"photoUrl":"\\public\\image\\hosptial\\building\\floors\\floor1.jpg","buildingId":"1"},{"id":"4f18b010ccac11e9bd775b558181f131","name":2,"photoUrl":"\\public\\image\\hosptial\\building\\floors\\floor2.jpg","buildingId":"1"}]
         */

        private String id;
        private String name;
        private String photoUrl;
        private boolean isMain;
        private List<FloorsBean> floors;

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

        public List<FloorsBean> getFloors() {
            return floors;
        }

        public void setFloors(List<FloorsBean> floors) {
            this.floors = floors;
        }

        public static class FloorsBean {
            /**
             * id : 4f17ecc0ccac11e9bd775b558181f131
             * name : 1
             * photoUrl : \public\image\hosptial\building\floors\floor1.jpg
             * buildingId : 1
             */

            private String id;
            private int name;
            private String photoUrl;
            private String buildingId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getName() {
                return name;
            }

            public void setName(int name) {
                this.name = name;
            }

            public String getPhotoUrl() {
                return photoUrl;
            }

            public void setPhotoUrl(String photoUrl) {
                this.photoUrl = photoUrl;
            }

            public String getBuildingId() {
                return buildingId;
            }

            public void setBuildingId(String buildingId) {
                this.buildingId = buildingId;
            }
        }
    }
}
