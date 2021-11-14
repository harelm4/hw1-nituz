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
                ", product=" + product +
                ", order=" + order +
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

}
