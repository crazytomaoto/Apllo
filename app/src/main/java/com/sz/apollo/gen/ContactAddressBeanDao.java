package com.sz.apollo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.sz.apollo.ui.models.ContactAddressBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CONTACT_ADDRESS_BEAN".
*/
public class ContactAddressBeanDao extends AbstractDao<ContactAddressBean, Long> {

    public static final String TABLENAME = "CONTACT_ADDRESS_BEAN";

    /**
     * Properties of entity ContactAddressBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "Id", true, "_id");
        public final static Property Address = new Property(1, String.class, "address", false, "ADDRESS");
        public final static Property Remark = new Property(2, String.class, "remark", false, "REMARK");
    }


    public ContactAddressBeanDao(DaoConfig config) {
        super(config);
    }
    
    public ContactAddressBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CONTACT_ADDRESS_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: Id
                "\"ADDRESS\" TEXT NOT NULL ," + // 1: address
                "\"REMARK\" TEXT NOT NULL );"); // 2: remark
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CONTACT_ADDRESS_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ContactAddressBean entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
        stmt.bindString(2, entity.getAddress());
        stmt.bindString(3, entity.getRemark());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ContactAddressBean entity) {
        stmt.clearBindings();
 
        Long Id = entity.getId();
        if (Id != null) {
            stmt.bindLong(1, Id);
        }
        stmt.bindString(2, entity.getAddress());
        stmt.bindString(3, entity.getRemark());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ContactAddressBean readEntity(Cursor cursor, int offset) {
        ContactAddressBean entity = new ContactAddressBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // Id
            cursor.getString(offset + 1), // address
            cursor.getString(offset + 2) // remark
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ContactAddressBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAddress(cursor.getString(offset + 1));
        entity.setRemark(cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ContactAddressBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ContactAddressBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ContactAddressBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
