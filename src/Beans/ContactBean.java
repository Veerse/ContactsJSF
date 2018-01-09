package Beans;


import Model.Address;
import Model.Contact;
import Model.Phone;
import Services.implementations.ContactServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="contact")
@ViewScoped
public class ContactBean {

    ContactServices c_s;

    private List<Contact> contacts;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Address> addresses;
    private List<Phone> phones;

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

    public List<Address> getAddresses() { return addresses; }

    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    public List<Phone> getPhones() { return phones; }

    public void setPhones(List<Phone> phones) { this.phones = phones; }


    // METHODS

    public String Create () {
        //Contact c = new Contact (firstName, lastName, email, addresses);
        System.out.println("creation de " + firstName + " avec les numeros " + phones.get(0).getPhoneKind() + " " + phones.get(0).getPhoneNumber());
        return null;
    }

    // AJAX METHODS

    public void addPhone() { phones.add(new Phone()); }

    public void removePhone(Phone item) {
        phones.remove(item);
    }

    public void addAddress() { addresses.add(new Address()); }

    public void removeAddress(Address item) {
        addresses.remove(item);
    }

    public void save() {
        System.out.println("items: " + phones);
    }

}