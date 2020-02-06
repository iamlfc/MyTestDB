package com.lfc.mysql.DataBeans;

import com.lfc.mysql.greenDao.CardInfoDDao;
import com.lfc.mysql.greenDao.DaoSession;
import com.lfc.mysql.greenDao.PersonInfoDDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by LFC
 * on 2020/2/6.
 */
@Entity
public class PersonInfoD {
    @Id(autoincrement = true)
    private Long id;
    private String strName;
    private String strValue;

    @ToMany(referencedJoinProperty = "PId")
    private List<CardInfoD> listCard;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 120824062)
    private transient PersonInfoDDao myDao;

    @Generated(hash = 1040906613)
    public PersonInfoD(Long id, String strName, String strValue) {
        this.id = id;
        this.strName = strName;
        this.strValue = strValue;
    }

    @Generated(hash = 915103499)
    public PersonInfoD() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrName() {
        return this.strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrValue() {
        return this.strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1809175186)
    public List<CardInfoD> getListCard() {
        if (listCard == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CardInfoDDao targetDao = daoSession.getCardInfoDDao();
            List<CardInfoD> listCardNew = targetDao._queryPersonInfoD_ListCard(id);
            synchronized (this) {
                if (listCard == null) {
                    listCard = listCardNew;
                }
            }
        }
        return listCard;
    }

    public void setListCard(List<CardInfoD> listCard) {
        this.listCard = listCard;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1317046587)
    public synchronized void resetListCard() {
        listCard = null;
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
    @Generated(hash = 1125169564)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonInfoDDao() : null;
    }

}
