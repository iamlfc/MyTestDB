package com.lfc.mysql;

import android.app.Application;

import com.lfc.mysql.DBUtils.DatabaseContext;
import com.lfc.mysql.DBUtils.MySQLiteOpenHelper;
import com.lfc.mysql.greenDao.DaoMaster;
import com.lfc.mysql.greenDao.DaoSession;
import com.lfc.mysql.utils.Const;

import org.greenrobot.greendao.database.Database;

/**
 * Created by LFC
 * on 2020/2/5.
 */
public class MyApp extends Application {
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        // regular SQLite database
        DatabaseContext databaseContext = new DatabaseContext(this);
        MySQLiteOpenHelper devOpenHelper = new MySQLiteOpenHelper(databaseContext, Const.DB_OneToMany, null);
        Database db = devOpenHelper.getEncryptedWritableDb("123456");
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();


    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
