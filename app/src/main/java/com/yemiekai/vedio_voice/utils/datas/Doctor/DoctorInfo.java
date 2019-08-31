package com.yemiekai.vedio_voice.utils.datas.Doctor;

import java.util.List;

public class DoctorInfo {


    private List<DoctorBean> doctor;

    public List<DoctorBean> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<DoctorBean> doctor) {
        this.doctor = doctor;
    }

    public static class DoctorBean {
        /**
         * id : 1
         * name : 曾康华
         * avatarUrl : \public\image\doctor\face\doctor1.jpg
         * gender : 2
         * post : 副院长
         * title : 主任医师 、教授
         * resume : 南昌大学硕士生导师、心脏康复学科带头人、江西省政府特殊津贴专家、赣州市政府特殊津贴专家、赣南医学院兼职教授、全国“五一”劳动奖章获得者、江西省人大代表、赣州市“十佳”医务工作者。
         * concurrent : 中国心脏联盟委员、江西省康复医学会常委、赣州市康复医学会会长、赣州市康复医学会心血管专业委员会主委、赣州市医学会心血管专业委员会副主委。
         * achievement : 完成省市级课题多项，多项成果获市科技进步奖。
         * speciality : 在冠心病、高血压、心衰的诊断治疗上积累了丰富经验，率先在市级医院开展心脏康复，创建赣州市人民医院心脏康复亚专业。
         * secondOfficeId : 1
         * workInformations : [{"id":"1","department":"心血管内一科门诊（南院）","registerType":"正高号","date":"2019-08-26T00:36:05.000Z","classes":"上午","remaining":20,"doctorId":"1"},{"id":"2","department":"心血管内一科门诊（南院）","registerType":"正高号","date":"2019-08-26T00:36:09.000Z","classes":"下午","remaining":19,"doctorId":"1"}]
         */

        private String id;
        private String name;
        private String avatarUrl;
        private int gender;
        private String post;
        private String title;
        private String resume;
        private String concurrent;
        private String achievement;
        private String speciality;
        private String secondOfficeId;
        private List<WorkInformationsBean> workInformations;

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

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getResume() {
            return resume;
        }

        public void setResume(String resume) {
            this.resume = resume;
        }

        public String getConcurrent() {
            return concurrent;
        }

        public void setConcurrent(String concurrent) {
            this.concurrent = concurrent;
        }

        public String getAchievement() {
            return achievement;
        }

        public void setAchievement(String achievement) {
            this.achievement = achievement;
        }

        public String getSpeciality() {
            return speciality;
        }

        public void setSpeciality(String speciality) {
            this.speciality = speciality;
        }

        public String getSecondOfficeId() {
            return secondOfficeId;
        }

        public void setSecondOfficeId(String secondOfficeId) {
            this.secondOfficeId = secondOfficeId;
        }

        public List<WorkInformationsBean> getWorkInformations() {
            return workInformations;
        }

        public void setWorkInformations(List<WorkInformationsBean> workInformations) {
            this.workInformations = workInformations;
        }

        public static class WorkInformationsBean {
            /**
             * id : 1
             * department : 心血管内一科门诊（南院）
             * registerType : 正高号
             * date : 2019-08-26T00:36:05.000Z
             * classes : 上午
             * remaining : 20
             * doctorId : 1
             */

            private String id;
            private String department;
            private String registerType;
            private String date;
            private String classes;
            private int remaining;
            private String doctorId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public String getRegisterType() {
                return registerType;
            }

            public void setRegisterType(String registerType) {
                this.registerType = registerType;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getClasses() {
                return classes;
            }

            public void setClasses(String classes) {
                this.classes = classes;
            }

            public int getRemaining() {
                return remaining;
            }

            public void setRemaining(int remaining) {
                this.remaining = remaining;
            }

            public String getDoctorId() {
                return doctorId;
            }

            public void setDoctorId(String doctorId) {
                this.doctorId = doctorId;
            }
        }
    }
}
