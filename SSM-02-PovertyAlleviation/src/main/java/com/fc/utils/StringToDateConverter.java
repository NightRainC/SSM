//package com.fc.utils;
//
//
//import org.springframework.core.convert.converter.Converter;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
///**
// * 字符串转换日期格式工具
// */
//public class StringToDateConverter implements Converter<String, Date> {
//    @Override
//    public Date convert(String source) {
//        // 默认格式
//        String format = "yyyy/MM/dd";
//
//        // 如果日期格式中间包含- 则替换格式
//        if (source.contains("-")) {
//            format = "yyyy-MM-dd";
//        }
//
//        DateFormat simpleDateFormat = new SimpleDateFormat(format);
//
//        try {
//            return simpleDateFormat.parse(source);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}