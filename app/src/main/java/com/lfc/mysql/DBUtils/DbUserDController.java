package com.lfc.mysql.DBUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lfc.mysql.DataBeans.UserD;
import com.lfc.mysql.greenDao.DaoMaster;
import com.lfc.mysql.greenDao.DaoSession;
import com.lfc.mysql.greenDao.UserDDao;

import java.util.List;

public class DbUserDController {
    private static final String strDBName = "person";
    /**
     * Helper
     */
    private DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;
    /**
     * dao
     */
    private UserDDao personInforDao;

    private static DbUserDController mDbController;

    /**
     * 获取单例
     */
    public static DbUserDController getInstance(Context context) {
        if (mDbController == null) {
            synchronized (DbUserDController.class) {
                if (mDbController == null) {
                    mDbController = new DbUserDController(context);
                }
            }
        }
        return mDbController;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public DbUserDController(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, strDBName + ".db", null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        personInforDao = mDaoSession.getUserDDao();
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, strDBName + ".db", null);
        }
        SQLiteDatabase db = mHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, strDBName + ".db", null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }

    /**
     * 会自动判定是插入还是替换
     *
     * @param personInfor
     */
    public void insertOrReplace(UserD personInfor) {
        personInforDao.insertOrReplace(personInfor);
    }

    /**
     * 插入一条记录，表里面要没有与之相同的记录
     *
     * @param personInfor
     */
    public long insert(UserD personInfor) {
        return personInforDao.insert(personInfor);
    }

    /**
     * 更新数据
     *
     * @param personInfor
     */
    public void update(UserD personInfor) {
        UserD mOldPersonInfor = personInforDao.queryBuilder().where(UserDDao.Properties.Id.eq(personInfor.getId())).build().unique();//拿到之前的记录
        if (mOldPersonInfor != null) {
            mOldPersonInfor.setStrName("张三");
            personInforDao.update(mOldPersonInfor);
        }
    }

    /**
     * 按条件查询数据
     */
    public List<UserD> searchByWhere(String wherecluse) {
        List<UserD> personInfors = (List<UserD>) personInforDao.queryBuilder().where(UserDDao.Properties.StrName.eq(wherecluse)).build().unique();
        return personInfors;
    }

    /**
     * 查询所有数据
     */
    public List<UserD> searchAll() {
        List<UserD> personInfors = personInforDao.queryBuilder().list();
        return personInfors;
    }

    /**
     * 删除数据
     */
    public void delete(String wherecluse) {
        personInforDao.queryBuilder().where(UserDDao.Properties.StrName.eq(wherecluse)).buildDelete().executeDeleteWithoutDetachingEntities();
    }
}