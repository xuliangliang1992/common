package com.icloudwhale.cloudpos.base;

import com.iwhalecloud.common.base.BaseApplication;
import com.umeng.commonsdk.UMConfigure;

/**
 * MainApplication
 *
 * @author xuliangliang
 * @date 2019/9/4
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class MainApplication extends BaseApplication {
    //    private User mUser;
    //    private AbstractScreenDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

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


    //    public User getUser() {
    //        return mUser == null ? new User() : mUser;
    //    }
    //
    //    public void setUser(User user) {
    //        mUser = user;
    //    }
}
