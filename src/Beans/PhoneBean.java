package Beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="phone")
public class PhoneBean {

    private int id;
    private String phoneKind;
    private String phoneNumber;

    public PhoneBean() {

    }

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

}
