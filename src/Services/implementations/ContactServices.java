package Services.implementations;

import Model.Contact;
import Services.models.ServiceLayer;

import java.util.ArrayList;

public class ContactServices implements ServiceLayer <Contact> {

    @Override
    public ArrayList<Contact> getAll() {
        return null;
    }

    @Override
    public void create(Contact element) {

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
