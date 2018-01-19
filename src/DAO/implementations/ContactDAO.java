package DAO.implementations;

import DAO.models.DAOLayer;
import Model.Contact;
import Model.Group;

import java.sql.*;
import java.util.ArrayList;

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

        PhoneDAO p_d = new PhoneDAO();
        AddressDAO a_d = new AddressDAO();

        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        ArrayList<Contact> l = new ArrayList<Contact>();

        try{
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("SELECT * FROM contacts");
            rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");

                l.add(new Contact(id, firstName, lastName, email, a_d.getAll(id), p_d.getAll(id)));
            }

        }catch(SQLException e){throw new RuntimeException(e);
        }finally{
            try{
                stmt.close();
                cx.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        return l;
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
            }catch (SQLException e){throw new RuntimeException(e);}
        }
    }

    @Override
    public Contact read(int id_Contact) {

        PhoneDAO p_d = new PhoneDAO();
        AddressDAO a_d = new AddressDAO();
        Contact c = new Contact();

        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        try{
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("SELECT * FROM contacts WHERE id = ?");
            stmt.setObject(1, id_Contact);
            rs = stmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");


                c = new Contact(id, firstName, lastName, email, a_d.getAll(id_Contact), p_d.getAll(id_Contact));
            }

        }catch(SQLException e){throw new RuntimeException(e);
        }finally{
            try{
                stmt.close();
                if(stmt!=null) cx.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        return c;
    }

    @Override
    public void update(Contact element) {
        try {Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        // Request
        try {
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("UPDATE contacts SET firstName = ?, lastName = ?, email = ? WHERE id = ?");
            stmt.setObject(1, element.getFirstName());
            stmt.setObject(2, element.getLastName());
            stmt.setObject(3, element.getEmail());
            stmt.setObject(4, element.getId());
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
            stmt = cx.prepareStatement("DELETE FROM contacts WHERE id = ?");
            stmt.setObject (1, id);
            stmt.executeUpdate();

            new MappingDAO().deleteContact(id);
        }
        catch (SQLException e){throw new RuntimeException(e);
        }finally{
            try{
                if (stmt != null) stmt.close();
                if (cx != null) cx.close();
            }catch (Exception e){throw new RuntimeException(e);}
        }
    }

    public ArrayList<Group> getGroups(int refContact) {

        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        ArrayList<Group> groups = new ArrayList<>();

        try{
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("SELECT * FROM contactgroupmapping INNER JOIN groups ON (groups.id = contactgroupmapping.refGroup) WHERE refContact = ?");
            stmt.setObject(1, refContact);
            rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("refGroup");
                String groupName = rs.getString("groupName");

                groups.add(new Group(id, groupName));
            }

        }catch(SQLException e){throw new RuntimeException(e);
        }finally{
            try{
                if(stmt!=null) stmt.close();
                if(cx!=null) cx.close();
            }catch (SQLException e){ throw new RuntimeException(e); }
        }

        return groups;
    }

}
