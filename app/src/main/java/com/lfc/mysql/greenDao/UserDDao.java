package com.lfc.mysql.greenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lfc.mysql.DataBeans.UserD;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_D".
*/
public class UserDDao extends AbstractDao<UserD, Long> {

    public static final String TABLENAME = "USER_D";

    /**
     * Properties of entity UserD.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StrName = new Property(1, String.class, "strName", false, "NAME");
        public final static Property StrPerNo = new Property(2, String.class, "strPerNo", false, "STR_PER_NO");
    }


    public UserDDao(DaoConfig config) {
        super(config);
    }
    
    public UserDDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_D\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: strName
                "\"STR_PER_NO\" TEXT);"); // 2: strPerNo
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_USER_D_STR_PER_NO ON \"USER_D\"" +
                " (\"STR_PER_NO\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_D\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserD entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String strName = entity.getStrName();
        if (strName != null) {
            stmt.bindString(2, strName);
        }
 
        String strPerNo = entity.getStrPerNo();
        if (strPerNo != null) {
            stmt.bindString(3, strPerNo);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserD entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String strName = entity.getStrName();
        if (strName != null) {
            stmt.bindString(2, strName);
        }
 
        String strPerNo = entity.getStrPerNo();
        if (strPerNo != null) {
            stmt.bindString(3, strPerNo);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserD readEntity(Cursor cursor, int offset) {
        UserD entity = new UserD( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // strName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // strPerNo
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserD entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStrName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStrPerNo(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserD entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserD entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserD entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
