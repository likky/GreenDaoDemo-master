package com.meitu.lyz.greendaodemo.db;

import android.database.sqlite.SQLiteDatabase;

import com.meitu.lyz.greendaodemo.MyApplication;
import com.meitu.lyz.greendaodemo.dao.DaoMaster;
import com.meitu.lyz.greendaodemo.dao.DaoSession;
import com.meitu.lyz.greendaodemo.dao.UserDao;

/**
 * @author LYZ 2018.08.06
 */
public class DaoHelper {

    private static final String DB_NAME = "test_db";

    private static DaoSession daoSession;

    private static DaoSession getDaoSession() {
        if (daoSession == null) {
            synchronized (DaoSession.class) {
                if (daoSession == null) {
                    DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getInstance(), DB_NAME, null);
                    SQLiteDatabase db = helper.getWritableDatabase();
                    DaoMaster daoMaster = new DaoMaster(db);
                    daoSession = daoMaster.newSession();
                }
            }
        }
        return daoSession;
    }

    public static UserDao getUserDao() {
        return getDaoSession().getUserDao();
    }
}
