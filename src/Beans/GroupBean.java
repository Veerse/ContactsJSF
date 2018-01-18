package Beans;

import Model.Contact;
import Model.Group;
import Services.implementations.ContactServices;
import Services.implementations.GroupServices;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import net.bootsfaces.C;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ManagedBean(name = "group")
@RequestScoped
public class GroupBean {

    private GroupServices g_s;

    private ArrayList<Group> groups;

    private ArrayList<String> contactsToAdd;

    private String groupName;

    public GroupBean(){
        g_s = new GroupServices();

        groups = g_s.getAll();
        contactsToAdd = new ArrayList<>();
    }

    public String getGroupName() { return groupName; }

    public void setGroupName(String groupName) { this.groupName = groupName; }

    public ArrayList<Group> getGroups() { return groups; }

    public void setGroups(ArrayList<Group> groups) { this.groups = groups; }

    public ArrayList<String> getContactsToAdd() { return contactsToAdd; }

    public void setContactsToAdd(ArrayList<String> contactsToAdd) { this.contactsToAdd = contactsToAdd; }


    public String Create(){
        g_s.create(new Group(0, getGroupName()), contactsToAdd);
        groups = g_s.getAll();
        System.out.println("creating group " + groupName + " with members " + contactsToAdd);
        return "GroupRead";
    }

    public String Delete(int id){
        g_s.delete(id);
        groups = g_s.getAll();
        return "GroupRead";
    }

    // Returns unaffected contacts on the group refGroup
    public Map<String, Integer> getUnaffectedContacts(int refGroup){
        ArrayList<Contact> c = new GroupServices().getUnaffectedContacts(refGroup);

        Map<String, Integer> items = new HashMap<>();
        for (Contact tmp : c)   items.put(tmp.getFirstName(), tmp.getId());
        return items;
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
        System.out.println("adding  " + contactsToAdd + " to idGroup = [" + idGroup + "]");
        //new GroupServices().subscribe(contactsToAdd, idGroup);
        return "error";
    }


    public Map<String, Integer> getAllContacts() {
        ArrayList<Contact> contacts = new ContactServices().getAll();
        Map<String, Integer> items = new HashMap<>();
        for (Contact tmp : contacts)   items.put(tmp.getFirstName(), tmp.getId());
        System.out.println(items);
        return items;
    }

}
