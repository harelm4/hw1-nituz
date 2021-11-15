public class LineItem {
    public int quantity;
    public int price;

    private ShoppingCart shoppingCart;
    private Product product;
    private Order order;

    @Override
    public String toString() {
        return "LineItem{" +
                "quantity=" + quantity +
                ", price=" + price +
                ", shoppingCart=" + shoppingCart+
                ", product=" + product.getId() +
                ", order=" + order.getNumber() +
                '}';
    }

    public LineItem(ShoppingCart shoppingCart, Order order, Product product)
    {
        this.shoppingCart=shoppingCart;
        shoppingCart.addLineItem(this);
        this.product=product;
        product.addLineItem(this);
        this.order=order;
        order.addLineItem(this);
    }

    public void shoppingCartWasDeleted() {
        product.delLineItem(this);
        product=null;
        shoppingCart=null;
        order.delLineItem(this);
        order=null;
    }

    public void productWasDeleted() {
        product=null;
        shoppingCart.delLineItem(this);
        shoppingCart=null;
        order.delLineItem(this);
        order=null;
    }

    public void orderWasDeleted(){
        product.delLineItem(this);
        product=null;
        shoppingCart.delLineItem(this);
        shoppingCart=null;
        order=null;
    }

    public void remove(){
        product.delLineItem(this);
        product=null;
        shoppingCart.delLineItem(this);
        shoppingCart=null;
        order.delLineItem(this);
        order=null;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
