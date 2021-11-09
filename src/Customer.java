public class Customer {
    private String id;
    private Address address;
    private String phone;
    private String email;

    private User user;

    public boolean setUser(User user) {
        if (this.user==null || user.getCustomer()!=this)
            this.user=user;
        else
            return false;
        return true;
    }

    public void remove() {
        if (user.getCustomer()==this)
            user.delCustomer();
        user=null;
    }
}
