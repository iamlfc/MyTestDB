package com.lfc.mysql.DataBeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by LFC
 * on 2020/2/3.
 */

/**
 * 创建数据库实体类
 *
 * @Entity 表示这个实体类一会会在数据库中生成对应的表，
 * @Id 表示该字段是id，注意该字段的数据类型为包装类型Long
 * @Property 则表示该属性将作为表的一个字段，其中nameInDb看名字就知道这个属性在数据库中对应的数据名称。
 * @Transient 该注解表示这个属性将不会作为数据表中的一个字段。
 * @NotNull 表示该字段不可以为空
 * @Unique 表示该字段唯一
 */

@Entity
public class UserD {

    @Id(autoincrement = true)//自增
    private Long id;
    @Property(nameInDb = "NAME")
    private String strName;
    @Transient
    private int tempUsageCount;
    @Index(unique = true)//设置唯一性
    private String strPerNo;
    @Generated(hash = 2066100318)
    public UserD(Long id, String strName, String strPerNo) {
        this.id = id;
        this.strName = strName;
        this.strPerNo = strPerNo;
    }
    @Generated(hash = 1142584133)
    public UserD() {
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
    public String getStrPerNo() {
        return this.strPerNo;
    }
    public void setStrPerNo(String strPerNo) {
        this.strPerNo = strPerNo;
    }


}
