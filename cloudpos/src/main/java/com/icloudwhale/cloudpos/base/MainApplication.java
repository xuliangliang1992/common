package com.icloudwhale.cloudpos.base;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.iwhalecloud.common.base.APP;
import com.umeng.commonsdk.UMConfigure;

import java.util.logging.Logger;


/**
 * @author xll
 * @date 2019-05-16
 */
public class MainApplication extends APP {
    private static MainApplication instance;
//    private User mUser;
//    private AbstractScreenDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(true);

//        initRoom();
    }

//    private void initRoom() {
//        mAppDatabase = Room.databaseBuilder(this, AbstractScreenDatabase.class, Constant.DATA_BASE_NAME)
//                .allowMainThreadQueries()
////                .addMigrations(sMigration)
//                .build();
//    }
//
//    static Migration sMigration = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            Logger.d("migrate=====>" + database.getVersion());
//            database.execSQL("ALTER TABLE users" + " ADD COLUMN shop_name TEXT");
//            database.execSQL("ALTER TABLE users" + " ADD COLUMN shop_adress TEXT");
//        }
//    };
//
//    public AbstractScreenDatabase getAppDatabase() {
//        return mAppDatabase;
//    }

    public static MainApplication getInstance() {
        return instance;
    }

//    public User getUser() {
//        return mUser == null ? new User() : mUser;
//    }
//
//    public void setUser(User user) {
//        mUser = user;
//    }
}
