package com.lfc.mysql.DataBeans;

import com.lfc.mysql.greenDao.CardInfoDDao;
import com.lfc.mysql.greenDao.DaoSession;
import com.lfc.mysql.greenDao.PersonInfoDDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by LFC
 * on 2020/2/6.
 */
@Entity
public class CardInfoD {
    @Id(autoincrement = true)
    private Long id;
    private Long PId;
    private String strFromName;//所属银行
    private double d_Money; //金额
    @ToOne(joinProperty = "PId")
    private PersonInfoD personInfoD;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 310423685)
    private transient CardInfoDDao myDao;
    @Generated(hash = 348855193)
    public CardInfoD(Long id, Long PId, String strFromName, double d_Money) {
        this.id = id;
        this.PId = PId;
        this.strFromName = strFromName;
        this.d_Money = d_Money;
    }
    @Generated(hash = 980832551)
    public CardInfoD() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getPId() {
        return this.PId;
    }
    public void setPId(Long PId) {
        this.PId = PId;
    }
    public String getStrFromName() {
        return this.strFromName;
    }
    public void setStrFromName(String strFromName) {
        this.strFromName = strFromName;
    }
    public double getD_Money() {
        return this.d_Money;
    }
    public void setD_Money(double d_Money) {
        this.d_Money = d_Money;
    }
    @Generated(hash = 22963568)
    private transient Long personInfoD__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 116210344)
    public PersonInfoD getPersonInfoD() {
        Long __key = this.PId;
        if (personInfoD__resolvedKey == null
                || !personInfoD__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PersonInfoDDao targetDao = daoSession.getPersonInfoDDao();
            PersonInfoD personInfoDNew = targetDao.load(__key);
            synchronized (this) {
                personInfoD = personInfoDNew;
                personInfoD__resolvedKey = __key;
            }
        }
        return personInfoD;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1497771175)
    public void setPersonInfoD(PersonInfoD personInfoD) {
        synchronized (this) {
            this.personInfoD = personInfoD;
            PId = personInfoD == null ? null : personInfoD.getId();
            personInfoD__resolvedKey = PId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1693812164)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCardInfoDDao() : null;
    }

}
