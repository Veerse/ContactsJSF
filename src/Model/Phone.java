package Model;

public class Phone {

    private int id;
    private String phoneKind;
    private String phoneNumber;



    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getPhoneKind() {
        return phoneKind;
    }

    public void setPhoneKind(String phoneKind) {
        this.phoneKind = phoneKind;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
