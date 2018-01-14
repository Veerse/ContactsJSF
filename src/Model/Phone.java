package Model;

public class Phone {

    public Phone(){

    }

    public Phone(int id, int refContact, String phoneKind, String phoneNumber) {
        this.id = id;
        this.refContact = refContact;
        this.phoneKind = phoneKind;
        this.phoneNumber = phoneNumber;
    }

    private int id;
    private int refContact;
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

    public int getRefContact() { return refContact; }

    public void setRefContact(int refContact) { this.refContact = refContact; }
}
