package com.lfc.mysql.DBUtils

import com.lfc.mysql.greenDao.DaoMaster
import com.lfc.mysql.greenDao.DaoSession
import com.lfc.mysql.utils.Const

/**
 * Created by LFC
on 2020/2/6.
 */
class MyDBUtils {
    companion object {
        private var daoSession: DaoSession? = null
        fun getDaoSession(
            context: DatabaseContext,
            strDBName: String = Const.DB_OneToMany
        ): DaoSession? {
            if (daoSession == null) {
                val databaseContext = DatabaseContext(context)
                val devOpenHelper = MySQLiteOpenHelper(databaseContext, strDBName, null)
                val db = devOpenHelper.getEncryptedWritableDb("123456")
                val daoMaster = DaoMaster(db)
                daoSession = daoMaster.newSession()
            }
            return daoSession
        }
    }
}