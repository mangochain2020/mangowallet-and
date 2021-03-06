package com.token.mangowallet.bean;

import java.util.List;

public class A {

    /**
     * rows : [{"account":"mgpchain2222","id":1,"scheme_title":"铁路网","scheme_content":"来咯巫山烤鱼兔子窝借款","created_at":"2021-02-24T09:01:48","updated_at":"2021-02-24T09:01:48","vote_count":"2.0000 MGP","is_del":0,"cash_money":"1.0000 MGP","is_remit":0},{"account":"mgpchain2222","id":2,"scheme_title":"安慕希","scheme_content":"2222222222","created_at":"2021-02-24T09:01:56","updated_at":"2021-02-24T09:01:56","vote_count":"0.0000 MGP","is_del":0,"cash_money":"1.0000 MGP","is_remit":0}]
     * more : false
     * next_key :
     */

    private boolean more;
    private String next_key;
    private List<RowsBean> rows;

    public boolean isMore() {
        return more;
    }

    public void setMore(boolean more) {
        this.more = more;
    }

    public String getNext_key() {
        return next_key;
    }

    public void setNext_key(String next_key) {
        this.next_key = next_key;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * account : mgpchain2222
         * id : 1
         * scheme_title : 铁路网
         * scheme_content : 来咯巫山烤鱼兔子窝借款
         * created_at : 2021-02-24T09:01:48
         * updated_at : 2021-02-24T09:01:48
         * vote_count : 2.0000 MGP
         * is_del : 0
         * cash_money : 1.0000 MGP
         * is_remit : 0
         */

        private String account;
        private int id;
        private String scheme_title;
        private String scheme_content;
        private String created_at;
        private String updated_at;
        private String vote_count;
        private int is_del;
        private String cash_money;
        private int is_remit;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getScheme_title() {
            return scheme_title;
        }

        public void setScheme_title(String scheme_title) {
            this.scheme_title = scheme_title;
        }

        public String getScheme_content() {
            return scheme_content;
        }

        public void setScheme_content(String scheme_content) {
            this.scheme_content = scheme_content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getVote_count() {
            return vote_count;
        }

        public void setVote_count(String vote_count) {
            this.vote_count = vote_count;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public String getCash_money() {
            return cash_money;
        }

        public void setCash_money(String cash_money) {
            this.cash_money = cash_money;
        }

        public int getIs_remit() {
            return is_remit;
        }

        public void setIs_remit(int is_remit) {
            this.is_remit = is_remit;
        }
    }
}
