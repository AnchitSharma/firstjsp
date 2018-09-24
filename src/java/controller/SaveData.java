/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.CityDaoImpl;
import dao.EntityDao;
import dao.UserDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.City;
import model.User;

/**
 *
 * @author user
 */
@WebServlet(name = "SaveData", urlPatterns = {"/savedata"})
public class SaveData extends HttpServlet {

    public static final String TYPE_1 = "save";
    public static final String TYPE_2 = "get";
    /*
        type=save,get;
     */
    EntityDao<User> udao;

    public SaveData() {
        udao = new UserDaoImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String type = request.getParameter("type");
        
        if (type.equalsIgnoreCase(TYPE_1)) {
            String name = request.getParameter("name");
            String dob = request.getParameter("dob");
            int age = Integer.parseInt(request.getParameter("age"));
            String doj = request.getParameter("doj");
            String address = request.getParameter("address").trim();
            String city = request.getParameter("city");
            EntityDao<City> cdao = new CityDaoImpl();
            City c = cdao.get(city);
            User u = new User();
            u.setAddress(address);
            u.setAge(age);
            u.setCity(c.getCity_id());
            u.setDob(dob);
            u.setDoj(doj);
            u.setName(name);
            saveData(u);
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }else if (type.equalsIgnoreCase(TYPE_2)) {
            String id = request.getParameter("id");
            User u = udao.get(id);
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(u, User.class);
            PrintWriter out = response.getWriter();
            out.print(json);
        }

    }

    private void saveData(User u) {
        if (u != null) {
            EntityDao<User> udao = new UserDaoImpl();
            udao.save(u);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
