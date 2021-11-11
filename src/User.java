public class User {
    private String login_id;
    private String password;
    private UserState state;

    private Customer customer;
    private ShoppingCart shoppingCart;

    public User(String login_id, String password, UserState state) {
        this.login_id = login_id;
        this.password = password;
        this.state = state;
        this.customer = customer;
        customer.setUser(this);
    }


    public Customer getCustomer() {
        return customer;
    }

    public void remove()
    {
        customer.remove();
        customer=null;
    }

    public String getLogin_id() {
        return login_id;
    }

    public String getPassword() {
        return password;
    }

    public UserState getState() {
        return state;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void delCustomer() {
        customer=null;
    }
}
