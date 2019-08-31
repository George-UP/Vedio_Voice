package com.yemiekai.vedio_voice.utils.datas.Introduction;

import java.util.List;

//医院介绍————初始
public class Introduction {
    /**
     * res : {"count":4,"rows":[{"id":"1","message":"医院","description":"赣州市人民医院始建于1939年7月，位于有着千年宋城美誉的赣州中心城区，现已形成\u201c一院两区\u201d格局，集医疗、教学、科研、保健、康复于一体，是南昌大学附属医院，国家支持的区域性医疗中心，江西省首家第三周期三级甲等综合医院，赣州市规模最大、技术力量最雄厚、医疗设备和管理及服务理念最先进的现代化医院。在江西省推行的DRGs综合评价排名中，取得了综合服务能力全省第三、地市级医院第一的优异成绩，牢固确立了在全省地市级医院中的领军地位。","introducePhotos":[{"id":"6681ff30c79911e99b845b23dde801b5","photoUrl":"\\public\\image\\hosptial\\message\\hospital1.jpg","introductionId":"1"},{"id":"668337b0c79911e99b845b23dde801b5","photoUrl":"\\public\\image\\hosptial\\message\\hospital2.jpg","introductionId":"1"}]},{"id":"2","message":"门诊大厅","description":"test","introducePhotos":[{"id":"66842210c79911e99b845b23dde801b5","photoUrl":"\\public\\image\\hosptial\\message\\hospital3.jpg","introductionId":"2"}]},{"id":"668114d0c79911e99b845b23dde801b5","message":"血液净化中心","description":"test","introducePhotos":[]}]}
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
         * count : 4
         * rows : [{"id":"1","message":"医院","description":"赣州市人民医院始建于1939年7月，位于有着千年宋城美誉的赣州中心城区，现已形成\u201c一院两区\u201d格局，集医疗、教学、科研、保健、康复于一体，是南昌大学附属医院，国家支持的区域性医疗中心，江西省首家第三周期三级甲等综合医院，赣州市规模最大、技术力量最雄厚、医疗设备和管理及服务理念最先进的现代化医院。在江西省推行的DRGs综合评价排名中，取得了综合服务能力全省第三、地市级医院第一的优异成绩，牢固确立了在全省地市级医院中的领军地位。","introducePhotos":[{"id":"6681ff30c79911e99b845b23dde801b5","photoUrl":"\\public\\image\\hosptial\\message\\hospital1.jpg","introductionId":"1"},{"id":"668337b0c79911e99b845b23dde801b5","photoUrl":"\\public\\image\\hosptial\\message\\hospital2.jpg","introductionId":"1"}]},{"id":"2","message":"门诊大厅","description":"test","introducePhotos":[{"id":"66842210c79911e99b845b23dde801b5","photoUrl":"\\public\\image\\hosptial\\message\\hospital3.jpg","introductionId":"2"}]},{"id":"668114d0c79911e99b845b23dde801b5","message":"血液净化中心","description":"test","introducePhotos":[]}]
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
             * description : 赣州市人民医院始建于1939年7月，位于有着千年宋城美誉的赣州中心城区，现已形成“一院两区”格局，集医疗、教学、科研、保健、康复于一体，是南昌大学附属医院，国家支持的区域性医疗中心，江西省首家第三周期三级甲等综合医院，赣州市规模最大、技术力量最雄厚、医疗设备和管理及服务理念最先进的现代化医院。在江西省推行的DRGs综合评价排名中，取得了综合服务能力全省第三、地市级医院第一的优异成绩，牢固确立了在全省地市级医院中的领军地位。
             * introducePhotos : [{"id":"6681ff30c79911e99b845b23dde801b5","photoUrl":"\\public\\image\\hosptial\\message\\hospital1.jpg","introductionId":"1"},{"id":"668337b0c79911e99b845b23dde801b5","photoUrl":"\\public\\image\\hosptial\\message\\hospital2.jpg","introductionId":"1"}]
             */

            private String id;
            private String message;
            private String description;
            private List<IntroducePhotosBean> introducePhotos;

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

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<IntroducePhotosBean> getIntroducePhotos() {
                return introducePhotos;
            }

            public void setIntroducePhotos(List<IntroducePhotosBean> introducePhotos) {
                this.introducePhotos = introducePhotos;
            }

            public static class IntroducePhotosBean {
                /**
                 * id : 6681ff30c79911e99b845b23dde801b5
                 * photoUrl : \public\image\hosptial\message\hospital1.jpg
                 * introductionId : 1
                 */

                private String id;
                private String photoUrl;
                private String introductionId;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPhotoUrl() {
                    return photoUrl;
                }

                public void setPhotoUrl(String photoUrl) {
                    this.photoUrl = photoUrl;
                }

                public String getIntroductionId() {
                    return introductionId;
                }

                public void setIntroductionId(String introductionId) {
                    this.introductionId = introductionId;
                }
            }
        }
    }
}
