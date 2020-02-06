package com.lfc.mysql.DataBeans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by LFC
 * on 2020/2/5.
 */
@Entity
public class OrdersD {
    @Id(autoincrement = true)
    private Long id;
    private Long customerID;
    private String strTime;
    private String strNote;

    @Generated(hash = 971617850)
    public OrdersD(Long id, Long customerID, String strTime, String strNote) {
        this.id = id;
        this.customerID = customerID;
        this.strTime = strTime;
        this.strNote = strNote;
    }

    @Generated(hash = 828195521)
    public OrdersD() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public String getStrTime() {
        return this.strTime;
    }

    public void setStrTime(String strTime) {
        this.strTime = strTime;
    }

    public String getStrNote() {
        return this.strNote;
    }

    public void setStrNote(String strNote) {
        this.strNote = strNote;
    }
}
