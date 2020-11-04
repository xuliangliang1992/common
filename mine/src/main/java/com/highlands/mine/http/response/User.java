package com.highlands.mine.http.response;


import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author xll
 * @date 2019-05-27
 */

@Entity(tableName = "users")
public class User implements Serializable {
    /**
     * session_id : 259d717f60d514b37273ff25060946d80b33969f
     * login_success : true
     * shop_db : csclient002.iwhalecloud.com
     * shop_user_id : 7
     * shop_id : 1
     * error :
     */
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private long shop_user_id;

    @ColumnInfo(name = "session_id")
    private String session_id;

    @ColumnInfo(name = "db_name")
    private String shop_db;

    @ColumnInfo(name = "shop_id")
    private long shop_id;

    @ColumnInfo(name = "shop_name")
    private String shop_name;

    @ColumnInfo(name = "shop_address")
    private String shop_address;

    public long getShop_user_id() {
        return shop_user_id;
    }

    public void setShop_user_id(long shop_user_id) {
        this.shop_user_id = shop_user_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getShop_db() {
        return shop_db;
    }

    public void setShop_db(String shop_db) {
        this.shop_db = shop_db;
    }

    public long getShop_id() {
        return shop_id;
    }

    public void setShop_id(long shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }
}
