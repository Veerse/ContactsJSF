package Beans;

import Model.Address;
import Model.Phone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;

@ManagedBean(name="address")
@ViewScoped
public class AddressBean {

    private ArrayList<Address> addresses;

    public AddressBean(){ addresses = new ArrayList<>(); }


    public ArrayList<Address> getAddresses() { return addresses; }

    public void setAddresses(ArrayList<Address> addresses) { this.addresses = addresses; }


    public void addAddress() { addresses.add(new Address()); }

    public void removeAddress(Address item) { addresses.remove(item); }
}
