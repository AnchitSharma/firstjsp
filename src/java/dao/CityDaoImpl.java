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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.City;

/**
 *
 * @author user
 */
public class CityDaoImpl implements EntityDao<City> {

    private Connection conn;

    public CityDaoImpl() {
        conn = DatabaseHelper.getConnection();
    }

    @Override
    public void save(City obj) {

    }

    @Override
    public City get(String id) {
        if (conn!=null&&id!=null) {
            String sql = "SELECT * FROM employee.city where city = ?";
            try(PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, id);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    City c = new City();
                    c.setCity_id(rs.getInt("city_id"));
                    c.setCity(rs.getString("city"));
                    c.setState(rs.getString("state"));
                    c.setCountry(rs.getString("country"));
                    return c;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<City> getList() {
        if (conn != null) {
            List<City> cityList = new ArrayList<>();
            String sql = "SELECT * FROM employee.city";
            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    City c = new City();
                    c.setCity_id(rs.getInt("city_id"));
                    c.setCity(rs.getString("city"));
                    c.setState(rs.getString("state"));
                    c.setCountry(rs.getString("country"));
                    cityList.add(c);
                }
                return cityList;
            } catch (SQLException ex) {
                Logger.getLogger(CityDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public City get(int id) {
        if (conn!=null&&id!=0) {
            String sql = "SELECT * FROM employee.city where city_id = ?";
            try(PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, id);
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    City c = new City();
                    c.setCity_id(rs.getInt("city_id"));
                    c.setCity(rs.getString("city"));
                    c.setState(rs.getString("state"));
                    c.setCountry(rs.getString("country"));
                    return c;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
