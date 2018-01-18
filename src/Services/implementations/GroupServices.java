package Services.implementations;

import DAO.implementations.GroupDAO;
import Model.Contact;
import Model.Group;
import Services.models.ServiceLayer;

import java.util.ArrayList;

public class GroupServices implements ServiceLayer <Group> {

    @Override
    public ArrayList<Group> getAll() {
        GroupDAO g_d = new GroupDAO();
        return g_d.getAll();
    }

    @Override
    public void create(Group element) {
        GroupDAO g_d = new GroupDAO();
        element.setId(g_d.getAvailableId());
        g_d.create(element);
    }

    public void create(Group group, ArrayList<String> contactsToAdd) {
        GroupDAO g_d = new GroupDAO();
        group.setId(g_d.getAvailableId());
        g_d.create(group);
        g_d.subscribe(contactsToAdd, group.getId());
    }

    @Override
    public Group read(int id) {
        return null;
    }

    @Override
    public void update(Group element) {

    }

    @Override
    public void delete(int id) {
        GroupDAO g_d = new GroupDAO();
        g_d.delete(id);
    }

    public void remove(int refContact, int refGroup) {
        GroupDAO g_d = new GroupDAO();
        g_d.remove(refContact, refGroup);
    }

    public int CountMembers(int id){
        return new GroupDAO().CountMembers(id);
    }

    public ArrayList<Contact> getContacts(int idGroup) {
        return new GroupDAO().getContacts(idGroup);
    }

    public ArrayList<Contact> getUnaffectedContacts(int idGroup) {
        return new GroupDAO().getUnaffectedContacts(idGroup);
    }

    public void subscribe(ArrayList<String> contactsToAdd, int idGroup) {
        System.out.println("contactsToAdd = [" + contactsToAdd + "], idGroup = [" + idGroup + "] (services)");
        new GroupDAO().subscribe(contactsToAdd, idGroup);
    }


}
