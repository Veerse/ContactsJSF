package Beans;

import DAO.implementations.ContactDAO;
import Model.Contact;
import Model.Group;
import Services.implementations.GroupServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;

@ManagedBean(name = "group")
@RequestScoped
public class GroupBean {

    private GroupServices g_s;

    private ArrayList<Contact> contacts;
    private ArrayList<Group> groups;

    private String groupName;
    private int userIdToAdd;

    public GroupBean(){
        g_s = new GroupServices();

        groups = g_s.getAll();
    }

    public ArrayList<Contact> getContacts() { return contacts; }

    public void setContacts(ArrayList<Contact> contacts) { this.contacts = contacts; }

    public String getGroupName() { return groupName; }

    public void setGroupName(String groupName) { this.groupName = groupName; }

    public ArrayList<Group> getGroups() { return groups; }

    public void setGroups(ArrayList<Group> groups) { this.groups = groups; }

    public int getUserIdToAdd() { return userIdToAdd; }

    public void setUserIdToAdd(int userIdToAdd) { this.userIdToAdd = userIdToAdd; }


    public String Create(){
        g_s.create(new Group(0, getGroupName()));
        groups = g_s.getAll();
        return "GroupRead";
    }

    public String Delete(int id){
        g_s.delete(id);
        groups = g_s.getAll();
        return "GroupRead";
    }

    public ArrayList<Contact> getUnaffectedContacts(int id){
        return new GroupServices().getUnaffectedContacts(id);
    }

    public ArrayList<Contact> getContactsFrom(int idGroup){
        return new GroupServices().getContacts(idGroup);
    }

    public int CountMembers(int idGroup){
        return new GroupServices().CountMembers(idGroup);
    }

    public String DeleteAffectation(int refContact, int refGroup) {
        GroupServices g_s = new GroupServices();
        g_s.remove(refContact, refGroup);
        return "GroupRead";
    }

    public String subscribe(int idGroup) {
        System.out.println("adding  " + userIdToAdd + " idGroup = [" + idGroup + "]");
        return "GroupRead";
    }
}
