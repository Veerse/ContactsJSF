package Model;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private ArrayList<Address> addresses;
    private ArrayList<Phone> phones;

    public Contact(int id, String firstName, String lastName, String email, ArrayList<Address> addresses, ArrayList<Phone> phones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addresses = addresses;
        this.phones = phones;
    }

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
}
