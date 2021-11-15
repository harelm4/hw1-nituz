public class Customer {
    private String id;
    private Address address;

    private Account account;
    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", account=" + account+
                ", user=" + user.getLogin_id() +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public User getUser() {
        return user;
    }

    private String phone;
    private String email;

    private User user;


    public Customer(String id, Address address, String phone, String email) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public void accountWasDeleted(Account account) {
    }

    public void remove()
    {
        user.customerWasDeleted();
        user=null;
        account.customerWasDeleted();
        account=null;
    }
    public boolean setUser(User user) {
        if (this.user==null || user.getCustomer()!=this)
            this.user=user;
        else
            return false;
        return true;
    }

    public void removeUser() {
        user=null;
    }

    public Account getAccount() {
        return account;
    }

    public boolean setAccount(Account account) {
        if (this.account==null || account.getCustomer()!=this)
            this.account=account;
        else
            return false;
        return true;

    }
}
