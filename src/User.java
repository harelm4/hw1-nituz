public class User {
    private String login_id;
    private String password;
    private UserState state;

    private Customer customer;
    private ShoppingCart shoppingCart;

    /**
     * When creating new user, need to attach a customer.
     * According to the directions given, we can attach it with outside function because of 1:1 association
     * @param login_id
     * @param password
     * @param state
     */
    public User(String login_id, String password, UserState state) {
        this.login_id = login_id;
        this.password = password;
        this.state = state;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void remove()
    {
        customer.removeUser();
        customer=null;
        shoppingCart.userwasdeleted(); //if no user -> no shopping cart
        shoppingCart=null;
    }

    public void shoppingCartWasDeleted() {
        shoppingCart=null;
    }

    public void customerWasDeleted() {
        customer=null;
        shoppingCart.userwasdeleted(); //if no user -> no shopping cart
        shoppingCart=null;
    }


    public Customer getCustomer() {
        return customer;
    }
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
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


    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(UserState state) {
        this.state = state;
    }

}
