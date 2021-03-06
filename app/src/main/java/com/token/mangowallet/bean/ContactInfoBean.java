package com.token.mangowallet.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactInfoBean {

    /**
     * code : 0
     * msg : success
     * data : {"weixin":"123456","mail":"156533@qq.com","phone":"5555555123456","mgpName":"uuuuuuuu1234","updateAt":"2020-12-31 16:02:04","id":1,"createAt":"2020-12-31 16:02:02"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * weixin : 123456
         * mail : 156533@qq.com
         * phone : 5555555123456
         * mgpName : uuuuuuuu1234
         * updateAt : 2020-12-31 16:02:04
         * id : 1
         * createAt : 2020-12-31 16:02:02
         */

        private String weixin;
        private String mail;
        private String phone;
        private String mgpName;
        private String updateAt;
        private int id;
        private String createAt;

        protected DataBean(Parcel in) {
            weixin = in.readString();
            mail = in.readString();
            phone = in.readString();
            mgpName = in.readString();
            updateAt = in.readString();
            id = in.readInt();
            createAt = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(weixin);
            dest.writeString(mail);
            dest.writeString(phone);
            dest.writeString(mgpName);
            dest.writeString(updateAt);
            dest.writeInt(id);
            dest.writeString(createAt);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getMgpName() {
            return mgpName;
        }

        public void setMgpName(String mgpName) {
            this.mgpName = mgpName;
        }

        public String getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(String updateAt) {
            this.updateAt = updateAt;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateAt() {
            return createAt;
        }

        public void setCreateAt(String createAt) {
            this.createAt = createAt;
        }
    }
}
