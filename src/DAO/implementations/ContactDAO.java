package DAO.implementations;

import DAO.models.DAOLayer;
import Model.Contact;

import java.util.ArrayList;

public class ContactDAO implements DAOLayer <Contact> {
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
