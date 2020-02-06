package com.lfc.mysql.DataBeans;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by LFC
 * on 2020/2/6.
 */
public class UsersInfoD {

    @Id(autoincrement = true)//自增
    private Long id;
    @Property(nameInDb = "NAME")
    private String strName;
}

