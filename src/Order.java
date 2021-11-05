import java.util.Date;

public class Order {
    public String number;
    public Date ordered;
    public Date shipped;
    public Address ship_to;
    public OrderStatus status;
    public float total;
}
