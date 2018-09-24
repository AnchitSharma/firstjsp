/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public interface EntityDao<T> {
    public void save(T obj);
    public T get(String id);
    public T get(int id);
    public List<T> getList();
    public void delete(String id);
    public  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public static Date getStrDate(String datestr){
        if (datestr == null||datestr.isEmpty()) {
            return null;
        }
        try {
            Date date = dateFormat.parse(datestr);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(EntityDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
