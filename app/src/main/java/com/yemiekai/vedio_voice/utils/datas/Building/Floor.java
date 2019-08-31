package com.yemiekai.vedio_voice.utils.datas.Building;

public class Floor {
    /**
     * res : {"id":"66892b20c79911e99b845b23dde801b5","name":1,"photoUrl":"\\public\\image\\hosptial\\building\\floors\\floor1.jpg","buildingId":"1"}
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
         * id : 66892b20c79911e99b845b23dde801b5
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
