public class User {

    private int id;
    private String name;
    private long nic;
    private long contact;
    private int pin;
    private long balance;
    private long account_number;

    public User(int id, String name, long nic, long contact, int pin, long balance, long account_number) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.contact = contact;
        this.pin = pin;
        this.balance = balance;
        this.account_number = account_number;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNic() {
        return nic;
    }

    public void setNic(long nic) {
        this.nic = nic;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(long account_number) {
        this.account_number = account_number;
    }
}
