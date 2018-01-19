package DAO.implementations;

import DAO.models.DAOLayer;
import DAO.models.DAOLayer_ContactItems;
import Model.Address;
import Model.Phone;

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
    public ArrayList<Address> getAll(int id_Contact) {

        try { Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException e) {}

        ArrayList<Address> a = new ArrayList<Address>();

        try{
            cx = DriverManager.getConnection(url , uid, passwd);
            stmt = cx.prepareStatement("SELECT * FROM addresses where refContact = ?");
            stmt.setObject(1, id_Contact);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int refContact = rs.getInt("refContact");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String zip = rs.getString("zip");
                String country = rs.getString("country");
                a.add(new Address(id, refContact, street, city, zip, country));
            }
            stmt.close();
            cx.close();
        }catch(SQLException e){ throw new RuntimeException(e);
        }finally {
            try{
                stmt.close();
                cx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return a;
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

    @Override
    public void delete(int id) {
        try {Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        // Request
        try {
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("DELETE FROM addresses WHERE refContact = ?");
            stmt.setObject (1, id);
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


    public void DeleteAddress(int idAddress) {
        try {Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        // Request
        try {
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("DELETE FROM addresses WHERE id = ?");
            stmt.setObject (1, idAddress);
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
