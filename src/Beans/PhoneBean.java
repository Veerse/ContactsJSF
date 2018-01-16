package Beans;

import Model.Address;
import Model.Phone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;

@ManagedBean(name="phone")
@ViewScoped
public class PhoneBean {

    private ArrayList<Phone> phones;

    public PhoneBean(){ phones = new ArrayList<>(); }


    public ArrayList<Phone> getPhones() { return phones; }

    public void setPhones(ArrayList<Phone> phones) { this.phones = phones; }


    public void addPhone() { phones.add(new Phone()); }

    public void removePhone(Phone item) {
        phones.remove(item);
    }
}
