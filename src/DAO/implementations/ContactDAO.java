package DAO.implementations;

import DAO.models.DAOLayer;
import Model.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements DAOLayer <Contact> {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/j2e";
    private String uid = "root";
    private String passwd = "root";
    private Connection cx;
    private PreparedStatement stmt;
    ResultSet rs;

    // This method returns max(id) + 1
    public int getAvailableId() {

        int max = 0;

        try { Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException e) {}

        try{
            cx = DriverManager.getConnection(url , uid, passwd);
            stmt = cx.prepareStatement("SELECT MAX(id) FROM contacts");
            rs = stmt.executeQuery();
            if(rs.next())   max = rs.getInt(1);
            stmt.close();
            cx.close();
        }catch(SQLException e){ throw new RuntimeException(e);
        }finally {
            try{
                if (stmt != null) stmt.close();
                if (cx != null) cx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return max + 1;
    }

    @Override
    public ArrayList<Contact> getAll() {
        return null;
    }

    @Override
    public void create(Contact c) {

        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        try {
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("INSERT INTO contacts (id, firstName, lastName, email) VALUES (?, ?, ?, ?)");

            stmt.setObject (1, c.getId());
            stmt.setObject (2, c.getFirstName());
            stmt.setObject (3, c.getLastName());
            stmt.setObject (4, c.getEmail());

            stmt.executeUpdate();
        }
        catch (SQLException e){throw new RuntimeException(e);
        }finally{
            try{
                if (stmt != null) stmt.close();
                if (cx != null) cx.close();
            }catch (Exception e){throw new RuntimeException(e);}
        }
    }

    @Override
    public Contact read(int id) {
        return null;
    }

    @Override
    public void update(int id, Contact element) {

    }

    @Override
    public void delete(int id) {

    }
}
