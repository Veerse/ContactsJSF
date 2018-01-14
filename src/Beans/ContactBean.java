package Beans;

import Model.Address;
import Model.Contact;
import Model.Phone;
import Services.implementations.ContactServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="contact")
@SessionScoped
public class ContactBean {

    ContactServices c_s;

    private List<Contact> contacts;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<Address> addresses;
    private ArrayList<Phone> phones;

    public ContactBean() {
        contacts = new ArrayList<Contact>();
        phones = new ArrayList<Phone>();
        addresses = new ArrayList<Address>();

        c_s = new ContactServices();

        contacts = c_s.getAll();

    }

    // GETTERS AND SETTERS

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public ArrayList<Address> getAddresses() { return addresses; }

    public void setAddresses(ArrayList<Address> addresses) { this.addresses = addresses; }

    public ArrayList<Phone> getPhones() { return phones; }

    public void setPhones(ArrayList<Phone> phones) { this.phones = phones; }

    public List<Contact> getContacts() { return contacts; }

    public void setContacts(List<Contact> contacts) { this.contacts = contacts; }


    // METHODS

    public String Create () {
        Contact c = new Contact (0, firstName, lastName, email, addresses, phones);
        c_s.create(c);

        contacts = c_s.getAll();

        return "ContactRead";
    }

    public String Edit(int id) {
        System.out.println("edit " + id);
        return "error";
    }

    public String Delete (int id) {
        c_s.delete(id);

        contacts = c_s.getAll();

        return "ContactRead";
    }



    // AJAX METHODS

    public void addPhone() { phones.add(new Phone()); }

    public void removePhone(Phone item) {
        phones.remove(item);
    }

    public void addAddress() { addresses.add(new Address()); }

    public void removeAddress(Address item) { addresses.remove(item); }



}