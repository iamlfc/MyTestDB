package com.lfc.mysql.DataBeans;

import com.google.gson.Gson;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by LFC
 * on 2020/2/6.
 */
public class OrdersD_Converter implements PropertyConverter<List<OrdersD>, String> {
    @Override
    public List<OrdersD> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null)
            return null;
        List<String> list_str = Arrays.asList(databaseValue.split(","));
        List<OrdersD> list_transport = new ArrayList<>();
        for (String s : list_str) {
            list_transport.add(new Gson().fromJson(s, OrdersD.class));
        }
        return list_transport;
    }

    @Override
    public String convertToDatabaseValue(List<OrdersD> entityProperty) {
        if (entityProperty == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (OrdersD array : entityProperty) {
                String str = new Gson().toJson(array);
                sb.append(str);
                sb.append(",");
            }
            return sb.toString();

        }
    }
}
