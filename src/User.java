public class User {
    private String login_id;
    private String password;
    private UserState state;

    private Customer customer;
    private ShoppingCart shoppingCart;

    public User(String login_id, String password, UserState state, Customer customer) {
        this.login_id = login_id;
        this.password = password;
        this.state = state;
        this.customer = customer;
        customer.setUser(this);
    }

    public User(String login_id, String password, UserState state, Customer customer, ShoppingCart shoppingCart) {
        this.login_id = login_id;
        this.password = password;
        this.state = state;
        this.customer = customer;
        this.shoppingCart = shoppingCart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void remove()
    {
        customer.remove();
        customer=null;
    }

    public void delCustomer() {
        customer=null;
    }
}
