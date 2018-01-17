package Beans;

import DAO.implementations.GroupDAO;
import Model.Address;
import Model.Contact;
import Model.Group;
import Model.Phone;
import Services.implementations.ContactServices;
import Services.implementations.GroupServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="contact")
@RequestScoped

public class ContactBean {

    // Contact service
    ContactServices c_s;



    // All the contacts
    private List<Contact> contacts;

    // For the contact form
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<Address> addresses;
    private ArrayList<Phone> phones;

    public ContactBean() {
        contacts = new ArrayList<>();
        phones = new ArrayList<>();
        addresses = new ArrayList<>();

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

    public String Create (ArrayList<Address> a, ArrayList<Phone> p) {

        Contact c = new Contact (0, firstName, lastName, email, a, p);
        c_s.create(c);

        contacts = c_s.getAll();

        return "ContactRead";
    }

    public String Edit(int id) {
        Contact c = c_s.read(id);
        setId(c.getId());
        setFirstName(c.getFirstName());
        setLastName(c.getLastName());
        setEmail(c.getEmail());
        setPhones(c.getPhones());
        System.out.println("retrieved phone size " + c.getPhones().size());
        setAddresses(c.getAddresses());
        return "ContactEdit";
    }

    public String EditSubmit(){
        System.out.println("editiing to " + firstName + "phone1 = " + phones.size());
        return "ContactRead";
    }

    public String Delete (int id) {
        c_s.delete(id);
        contacts = c_s.getAll();
        return "ContactRead";
    }

    public ArrayList<Group> getGroups(int id){
        return c_s.getGroups(id);
    }

    public String DeleteAffectation(int refContact, int refGroup) {
        GroupServices g_s = new GroupServices();
        g_s.remove(refContact, refGroup);
        return "ContactRead";
    }
}