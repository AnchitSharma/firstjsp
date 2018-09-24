/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import database.DatabaseHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author user
 */
public class UserDaoImpl implements EntityDao<User>{

    private Connection conn;

    public UserDaoImpl() {
        conn = DatabaseHelper.getConnection();
    }
    
    
    @Override
    public void save(User obj) {
        if (conn!=null) {
            String sql = "INSERT INTO employee.user(name,dob,doj,address,city_id,age) values(?,?,?,?,?,?)";
            java.sql.Date dob = new java.sql.Date(EntityDao.getStrDate(obj.getDob()).getTime());
            java.sql.Date doj = new java.sql.Date(EntityDao.getStrDate(obj.getDoj()).getTime());
            try(PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, obj.getName());
                pst.setDate(2, dob);
                pst.setDate(3, doj);
                pst.setString(4, obj.getAddress());
                pst.setInt(5, obj.getCity());
                pst.setInt(6, obj.getAge());
                int id = pst.executeUpdate();
                if(id>0){
                    System.out.println("INSERT SUUCESS");
                }else{
                    System.out.println("INSERT FAILED");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public User get(String id) {
        if (conn!=null) {
            String sql = "select * from employee.user where user_id = ?";
            try(PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, id);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    User u = new User();
                    u.setAddress(rs.getString("address"));
                    u.setAge(rs.getInt("age"));
                    u.setCity(rs.getInt("city_id"));
                    u.setDob(rs.getString("dob"));
                    u.setDoj(rs.getString("doj"));
                    u.setName(rs.getString("name"));
                    u.setUser_id(rs.getInt("user_id"));
                    return u;
                    
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public List<User> getList() {
        if (conn!=null) {
            List<User> ulist = new ArrayList<>();
            String sql = "SELECT * FROM employee.user";
            try(PreparedStatement pst = conn.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    User u = new User();
                    u.setUser_id(rs.getInt("user_id"));
                    u.setAddress(rs.getString("address"));
                    u.setAge(rs.getInt("age"));
                    u.setCity(rs.getInt("city_id"));
                    u.setDob(rs.getString("dob"));
                    u.setDoj(rs.getString("doj"));
                    u.setName(rs.getString("name"));
                    ulist.add(u);
                }
                
                return ulist;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
