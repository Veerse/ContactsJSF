package Beans;


import Model.Address;
import Model.Contact;
import Services.implementations.ContactServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="contact")

public class ContactBean {

    @ManagedProperty(value="#{phone}")
    private PhoneBean phone;

    public void setPhone(PhoneBean phone) { this.phone = phone; }

    public PhoneBean getPhone() { return phone; }



    ContactServices c_s;

    private List<Contact> contacts;

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Address> addresses;

    public ContactBean() {
        contacts = new ArrayList<Contact>();

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


    // METHODS

    public String Create () {
        //Contact c = new Contact (firstName, lastName, email, addresses);
        System.out.println("dedans" + phone.getPhoneNumber());
        
        return null;
    }


}
