package com.lfc.mysql.greenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lfc.mysql.DataBeans.CustomerD;
import com.lfc.mysql.DataBeans.OrdersD_Converter;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CUSTOMER_D".
*/
public class CustomerDDao extends AbstractDao<CustomerD, Long> {

    public static final String TABLENAME = "CUSTOMER_D";

    /**
     * Properties of entity CustomerD.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Orders = new Property(1, String.class, "orders", false, "ORDERS");
    }

    private final OrdersD_Converter ordersConverter = new OrdersD_Converter();

    public CustomerDDao(DaoConfig config) {
        super(config);
    }
    
    public CustomerDDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CUSTOMER_D\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ORDERS\" TEXT);"); // 1: orders
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CUSTOMER_D\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CustomerD entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        List orders = entity.getOrders();
        if (orders != null) {
            stmt.bindString(2, ordersConverter.convertToDatabaseValue(orders));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CustomerD entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        List orders = entity.getOrders();
        if (orders != null) {
            stmt.bindString(2, ordersConverter.convertToDatabaseValue(orders));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CustomerD readEntity(Cursor cursor, int offset) {
        CustomerD entity = new CustomerD( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : ordersConverter.convertToEntityProperty(cursor.getString(offset + 1)) // orders
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CustomerD entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOrders(cursor.isNull(offset + 1) ? null : ordersConverter.convertToEntityProperty(cursor.getString(offset + 1)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CustomerD entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CustomerD entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CustomerD entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}