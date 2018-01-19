package Beans;

import DAO.implementations.PhoneDAO;
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

    private int id;
    private String phoneKind;
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneKind() {
        return phoneKind;
    }

    public void setPhoneKind(String phoneKind) {
        this.phoneKind = phoneKind;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneBean(){ phones = new ArrayList<>(); }


    public ArrayList<Phone> getPhones() { return phones; }

    public void setPhones(ArrayList<Phone> phones) { this.phones = phones; }


    public void addPhone() { phones.add(new Phone()); }

    public void removePhone(Phone item) {
        phones.remove(item);
    }

    public ArrayList<Phone> getPhonesOf(int idC) {
        return new PhoneDAO().getAll(idC);
    }

    public String DeletePhone(int idAddress) {
        new PhoneDAO().deleteAddress (idAddress);
        return "ContactEdit";
    }

    public String addPhoneTo(int idC) {
        System.out.println("Adding " + phoneNumber + " to " + idC);
        new PhoneDAO().create(new Phone(0, idC, phoneKind, phoneNumber), idC);
        return "ContactEdit";
    }
}
