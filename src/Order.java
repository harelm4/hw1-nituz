import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private String number;
    private LocalDateTime ordered;
    private LocalDateTime shipped;
    private Address ship_to;
    private OrderStatus status;
    private float total;

    private Account account;
    private List<LineItem> lineItems;
    private List<Payment>  payments;

    public Order(String number, LocalDateTime ordered, Address ship_to, OrderStatus status, Account account) {
        this.number = number;
        this.ordered = ordered;
        this.ship_to = ship_to;
        this.status = status;
        this.account=account;
        this.account.addOrder(this);
    }

    public Status addLineItem(LineItem li){
        if (li==null)
            return Status.failure;
        if (lineItems.contains(li))
            return Status.failure;
        lineItems.add(li);
        return Status.success;
    }

    public void delLineItem(LineItem li){
        lineItems.remove(li);
    }

    public void remove()
    {
        int i=0;
        for (i=0; i<lineItems.size(); i++)
        {
            lineItems.get(i).orderWasDeleted();
        }
        lineItems=null;
        account.deleteOrder(this);
        account=null;

        int j=0;
        for (j=0; j<payments.size(); j++)
        {
            payments.get(i).orderWasDeleted();
        }
        payments=null;
    }

    public void accountWasDeleted() {
        int i=0;
        for (i=0; i<lineItems.size(); i++)
        {
            lineItems.get(i).orderWasDeleted();
        }
        lineItems=null;
        //account.deleteOrder(this);
        account=null;

        int j=0;
        for (j=0; j<payments.size(); j++)
        {
            payments.get(i).orderWasDeleted();
        }
        payments=null;

    }


    public void addPayment(Payment p){
        payments.add(p);
    }
    public void deletePayment(Payment p){
        payments.remove(p);
    }
    public float getSumOfPayments()
    {
        float sum=0;
        for (Payment p:
                payments) {
            sum+=p.total;
        }
        return sum;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getOrdered() {
        return ordered;
    }

    public void setOrdered(LocalDateTime ordered) {
        this.ordered = ordered;
    }

    public LocalDateTime getShipped() {
        return shipped;
    }

    public void setShipped(LocalDateTime shipped) {
        this.shipped = shipped;
    }

    public Address getShip_to() {
        return ship_to;
    }

    public void setShip_to(Address ship_to) {
        this.ship_to = ship_to;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }


}
