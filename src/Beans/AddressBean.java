package Beans;

import DAO.implementations.AddressDAO;
import Model.Address;
import Model.Phone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;

@ManagedBean(name="address")
@ViewScoped
public class AddressBean {

    private ArrayList<Address> addresses;

    private int id;
    private String street;
    private String city;
    private String zip;
    private String country;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public AddressBean(){ addresses = new ArrayList<>(); }


    public ArrayList<Address> getAddresses() { return addresses; }

    public void setAddresses(ArrayList<Address> addresses) { this.addresses = addresses; }

    public void addAddress() { addresses.add(new Address()); }

    public void removeAddress(Address item) { addresses.remove(item); }


    public ArrayList<Address> getAddressesOf(int idContact){
        return new AddressDAO().getAll(idContact);
    }

    public String DeleteAddress(int idAddress){
        new AddressDAO().DeleteAddress(idAddress);
        return "ContactEdit";
    }

    public String addAddressTo(int idC) {
        new AddressDAO().create(new Address(0, idC, city, street, zip, country), idC);
        return "ContactEdit";
    }
}
