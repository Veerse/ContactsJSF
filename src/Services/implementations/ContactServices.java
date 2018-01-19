package Services.implementations;

import DAO.implementations.AddressDAO;
import DAO.implementations.ContactDAO;
import DAO.implementations.PhoneDAO;
import DAO.models.DAOLayer;
import Model.Address;
import Model.Contact;
import Model.Group;
import Model.Phone;
import Services.models.ServiceLayer;

import java.sql.SQLException;
import java.util.ArrayList;

public class ContactServices implements ServiceLayer <Contact> {

    @Override
    public ArrayList<Contact> getAll() {
        ContactDAO c_d = new ContactDAO();
        return c_d.getAll();
    }

    @Override
    public void create(Contact c) {
        ContactDAO c_d = new ContactDAO();
        PhoneDAO p_d = new PhoneDAO();
        AddressDAO a_d = new AddressDAO();

        c.setId(c_d.getAvailableId());

        c_d.create(c);

        for (Phone p : c.getPhones())   p_d.create(p, c.getId());
        for (Address a : c.getAddresses())   a_d.create(a, c.getId());
    }

    @Override
    public Contact read(int id) {
        ContactDAO c_d = new ContactDAO();
        return c_d.read(id);
    }

    @Override
    public void update(Contact element) {
        new ContactDAO().update(element);
    }

    @Override
    public void delete(int id) {
        ContactDAO c_d = new ContactDAO();
        PhoneDAO p_d = new PhoneDAO();
        AddressDAO a_d = new AddressDAO();

        p_d.delete(id);
        a_d.delete(id);
        c_d.delete(id);
    }

    public ArrayList<Group> getGroups(int id) {
        ContactDAO c_d = new ContactDAO();
        return c_d.getGroups(id);
    }
}
