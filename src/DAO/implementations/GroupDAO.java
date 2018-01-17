package DAO.implementations;

import DAO.models.DAOLayer;
import DAO.models.DAOLayer_ContactItems;
import Model.Contact;
import Model.Group;

import java.sql.*;
import java.util.ArrayList;

public class GroupDAO implements DAOLayer <Group> {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/j2e";
    private String uid = "root";
    private String passwd = "root";
    private Connection cx;
    private PreparedStatement stmt;
    ResultSet rs;

    public int getAvailableId() {

        int max = 0;

        try { Class.forName("com.mysql.jdbc.Driver"); } catch (ClassNotFoundException e) {}

        try{
            cx = DriverManager.getConnection(url , uid, passwd);
            stmt = cx.prepareStatement("SELECT MAX(id) FROM groups");
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
    public ArrayList<Group> getAll() {

        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        ArrayList<Group> groups = new ArrayList<>();

        try{
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("SELECT * FROM groups");
            rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String groupName = rs.getString("groupName");
                groups.add(new Group(id, groupName));
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

        return groups;
    }

    @Override
    public void create(Group g) {
        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        try {
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("INSERT INTO groups (id, groupName) VALUES (?, ?)");

            stmt.setObject (1, g.getId());
            stmt.setObject (2, g.getGroupName());

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
    public Group read(int id) {
        return null;
    }

    @Override
    public void update(int id, Group element) {

    }

    @Override
    public void delete(int id) {
        try {Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        // Request
        try {
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("DELETE FROM groups WHERE id = ?");
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

    public void remove(int refContact, int refGroup) {
        try {Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        // Request
        try {
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("DELETE FROM contactgroupmapping WHERE refContact = ? AND refGroup = ?");
            stmt.setObject (1, refContact);
            stmt.setObject(2, refGroup);
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

    public int CountMembers(int id) {

        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}
        int nb = -1;

        try{
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("SELECT COUNT(*) FROM contactgroupmapping WHERE refGroup = ?");
            stmt.setObject(1, id);
            rs = stmt.executeQuery();

            if(rs.next()) nb = rs.getInt(1);

        }catch(SQLException e){throw new RuntimeException(e);
        }finally{
            try{
                stmt.close();
                cx.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        return nb;
    }

    public ArrayList<Contact> getContacts(int refGroup) {
        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        ArrayList<Contact> contacts = new ArrayList<>();

        try{
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("SELECT * FROM contactgroupmapping INNER JOIN contacts ON (contacts.id = contactgroupmapping.refContact) WHERE refGroup = ?");
            stmt.setObject(1, refGroup);
            rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("refContact");
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                String email = rs.getString("email");

                contacts.add(new Contact(id, firstName, lastName, email, null, null));
            }

        }catch(SQLException e){throw new RuntimeException(e);
        }finally{
            try{
                if(stmt!=null) stmt.close();
                if(cx!=null) cx.close();
            }catch (SQLException e){ throw new RuntimeException(e); }
        }

        return contacts;
    }

    public ArrayList<Contact> getUnaffectedContacts(int idGroup) {
        try { Class.forName(driver); }catch (ClassNotFoundException e1) {e1.printStackTrace();}

        ArrayList<Contact> contacts = new ArrayList<>();

        try{
            cx = DriverManager.getConnection(url, uid, passwd);
            stmt = cx.prepareStatement("SELECT * FROM contacts WHERE contacts.id NOT IN (SELECT refContact FROM contactgroupmapping INNER JOIN groups ON (groups.id = contactgroupmapping.refGroup) WHERE refContact = ?)");
            stmt.setObject(1, idGroup);
            rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");

                contacts.add(new Contact(id, firstName, lastName, email, null, null));
            }

        }catch(SQLException e){throw new RuntimeException(e);
        }finally{
            try{
                if(stmt!=null) stmt.close();
                if(cx!=null) cx.close();
            }catch (SQLException e){ throw new RuntimeException(e); }
        }

        return contacts;
    }
}
