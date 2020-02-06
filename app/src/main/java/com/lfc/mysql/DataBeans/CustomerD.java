package com.lfc.mysql.DataBeans;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LFC
 * on 2020/2/5.
 */
@Entity
public class CustomerD {

    @Id(autoincrement = true)
    private Long id;
    //    @ToMany(referencedJoinProperty = "customerID")
    @Convert(columnType = String.class, converter = OrdersD_Converter.class)
    private List<OrdersD> orders;
    @Generated(hash = 362296294)
    public CustomerD(Long id, List<OrdersD> orders) {
        this.id = id;
        this.orders = orders;
    }

    @Generated(hash = 1209736645)
    public CustomerD() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrders(List<OrdersD> orders) {
        this.orders = orders;
    }

    public List<OrdersD> getOrders() {
        if (orders == null) {
            return new ArrayList<>();
        }
        return orders;
    }
}
