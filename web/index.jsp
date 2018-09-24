<%-- 
    Document   : index.jsp
    Created on : Sep 21, 2018, 10:54:18 PM
    Author     : user
--%>

<%@page import="dao.UserDaoImpl"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="dao.CityDaoImpl"%>
<%@page import="model.City"%>
<%@page import="dao.EntityDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%!
    EntityDao<City> cityDao;
    List<City> clist;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>JSP Page</title>
        <style>
            .disabled{
                pointer-events: none;
                cursor: default;
            }
            .disLink{
                text-decoration: none;
            }
            
            #data_entry{
                font-family: "Trebuchet MS",Arial,Helvetica,sans-serif;
                border-collapse: collapse;
                width: 50%;
            }
            
            #data_entry td, th{
                padding: 8px;
            }
            
            
        </style>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/custom.js"></script>
        
    </head>
    <body>
        <% cityDao = new CityDaoImpl(); %>

        <form action="savedata?type=save" method="POST" onsubmit="return checkform()">
            <div class="container" >
                <center>
                <table border="1" cellpadding="3" cellspacing="1" style="margin-top: 15px">

                    <tbody>
                        <tr>
                            <td><input type="submit" value="Save" class="btn btn-md"/></td>
                            <td><button type="button" onclick="editUser()" disabled="disabled" class="rec btn btn-md">Edit</button></td>
                            <td><button type="button" id="delete" class="rec btn btn-md" disabled="disabled">Delete</button></td>
                            <td><button type="button" class="btn btn-md">Find</button></td>
                            
                        </tr>
                    </tbody>
                </table>
            </center>
            </div>
            
            <hr/>

            <center>
                <label style="color: red">* Fields are mandatory</label>
                <table id="data_entry" cellspacing="5" cellpadding="5">
                    <tbody>
                        <tr>
                            <td>Name* : </td>
                            <td><input type="text" name="name" value="" size="20" required/></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>DOB* : </td>
                            <td><input type="date" name="dob"  id ="dob" onchange="onDateSubmit(this)" required/></td>
                            <td>Age : </td>
                            <td><input type="text" name="age" value="" size="10" id="age"/></td>
                        </tr>
                        <tr>
                            <td>Date of Joining : </td>
                            <td><input type="date" name="doj" value="" size="20" id="doj"/></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Address : </td>
                            <td><textarea name="address" rows="4" cols="20">
                                </textarea></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>City : </td>
                            <td><select class="form-control" name="city" id ="city" onchange="onCitySelect(this)" >
                                    <option>Select City</option>
                                    <% clist = cityDao.getList();
                                        if (clist != null) {
                                            for (City c : clist) {
                                    %>
                                    <option><%= c.getCity()%></option>
                                    <%
                                            }
                                        }
                                    %>


                                </select></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>State : </td>
                            <td><input type="text" name="state" value="" size="20" id="state" readonly></td>
                            <td>Country : </td>
                            <td><input type="text" name="country" value="" size="20" id="country" readonly></td>
                        </tr>
                    </tbody>
                </table>

            </center>
        </form>
    <center>
<br/>
        <table  class="table table-bordered table-striped"border="1" cellpadding="3" cellspacing="1">
                <tr>
                    <th></th>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>DOB</th>
                    <th>DOJ</th>
                    <th>Address</th>
                    <th>City</th>
                </tr>
            <%
                EntityDao<User> udao = new UserDaoImpl();
                List<User> ulist = udao.getList();
                for(User u:ulist){
            %>
            
                <tr>
                    <td><input class="chbk" type="checkbox" onclick="selectUser()" /></td>
                    <td><%= u.getUser_id() %></td>
                    <td><%= u.getName() %></td>
                    <td><%= u.getDob() %></td>
                    <td><%= u.getDoj() %></td>
                    <td><%= u.getAddress() %></td>
                    <td><%= cityDao.get(u.getCity()).getCity() %></td>
                </tr>
            <%}%>
        </table>



    </center>






</body>
</html>