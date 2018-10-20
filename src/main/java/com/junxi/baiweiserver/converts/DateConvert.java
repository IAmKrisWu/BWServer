package com.junxi.baiweiserver.converts;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//时间类型转换器
@Component
public class DateConvert implements Converter<String, Date> {
    SimpleDateFormat simpleDateFormat = null;

    @Override
    public Date convert(String s) {
        if(s.contains(":")){
            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        }else{

            simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        }
        try {
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
}
