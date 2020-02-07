/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author user
 */
public  class DateTime {
    
    public static String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	Date date = new Date();
        return dateFormat.format(date);
    }
    
    public static String getDateTimeForDatabase(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	Date date = new Date();
        return dateFormat.format(date);
    }
    
    
    public static Date getNowDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	Date date = new Date();
        return date;
    }
    
    public static Date getNowDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
        return date;
    }
}
