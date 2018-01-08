package Model;

import java.util.List;

public class Contact {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Address> addresses;
    private List<Phone> phones;

    public Contact(String firstName, String lastName, String email, List<Address> addresses, List<Phone> phones) {
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

    public List<Address> getAddresses() { return addresses; }

    public void setAddresses(List<Address> addresses) { this.addresses = addresses; }

    public List<Phone> getPhones() { return phones; }

    public void setPhones(List<Phone> phones) { this.phones = phones; }
}
