package com.shop.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String YMDHMS = "yyyyMMddHHmmss";
	
	public static String getString(Date date,String fmt){
		return (new SimpleDateFormat(fmt).format(date));
	}
}
