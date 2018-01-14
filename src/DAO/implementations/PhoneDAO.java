package DAO.implementations;

import DAO.models.DAOLayer_ContactItems;
import Model.Phone;

import java.sql.*;
import java.util.ArrayList;

public class PhoneDAO implements DAOLayer_ContactItems<Phone> {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/j2e";
    private String uid = "root";
    private String passwd = "root";
    private Connection cx;
    private PreparedStatement stmt;
    ResultSet rs;

    @Override
    public ArrayList<Phone> getAll(int id_Contact) {

        try { Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException e) {}

        ArrayList<Phone> p = new ArrayList<Phone>();

        try{
            cx = DriverManager.getConnection(url , uid, passwd);
            stmt = cx.prepareStatement("SELECT * FROM phones where refContact = ?");
            stmt.setObject(1, id_Contact);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int refContact = rs.getInt("refContact");
                String phoneKind = rs.getString("phoneKind");
                String phoneNumber = rs.getString("phoneNumber");
                p.add(new Phone(id, refContact, phoneKind, phoneNumber));
            }
            stmt.close();
            cx.close();
        }catch(SQLException e){ throw new RuntimeException(e);
        }finally {
            try{
                stmt.close();
                if (cx != null) cx.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return p;

    }

    @Override
    public void create(Phone p, int idContact) {
        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        try {
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("INSERT INTO phones (id, refContact, phoneKind, phoneNumber) VALUES (?, ?, ?, ?)");

            stmt.setObject (1, p.getId());
            stmt.setObject (2, idContact);
            stmt.setObject (3, p.getPhoneKind());
            stmt.setObject (4, p.getPhoneNumber());

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
            stmt = cx.prepareStatement("DELETE FROM phones WHERE refContact = ?");
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
}