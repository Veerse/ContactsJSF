package DAO.implementations;

import DAO.models.DAOLayer;
import DAO.models.DAOLayer_ContactItems;
import Model.Address;

import java.sql.*;
import java.util.ArrayList;

public class AddressDAO implements DAOLayer_ContactItems <Address> {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/j2e";
    private String uid = "root";
    private String passwd = "root";
    private Connection cx;
    private PreparedStatement stmt;
    ResultSet rs;

    @Override
    public ArrayList<Address> getAll(int id) {
        return null;
    }

    @Override
    public void create(Address a, int idContact) {
        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        try {
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("INSERT INTO addresses (id, refContact, street, city, zip, country) VALUES (?, ?, ?, ?, ?, ?)");

            stmt.setObject (1, a.getId());
            stmt.setObject (2, idContact);
            stmt.setObject (3, a.getStreet());
            stmt.setObject (4, a.getCity());
            stmt.setObject (5, a.getZip());
            stmt.setObject (6, a.getCountry());

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
}
