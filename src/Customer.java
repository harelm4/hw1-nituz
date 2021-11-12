public class Customer {
    private String id;
    private Address address;
    private String phone;
    private String email;

    private User user;
    private Account account;

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
