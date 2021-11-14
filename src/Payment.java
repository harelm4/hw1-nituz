import java.util.Date;

public abstract class Payment {
    public String id;
    public Date paid;
    public float total;
    public String details;

    private Account account;
    private Order order;

    public Payment(String id, Date paid, float total, String details, Account account, Order order) {
        this.id = id;
        this.paid = paid;
        this.total = total;
        this.details = details;
        this.account = account;
        this.order = order;
        this.account.addPayment(this);
        this.order.addPayment(this);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", paid=" + paid +
                ", total=" + total +
                ", details='" + details + '\'' +
                ", account=" + account +
                ", order=" + order +
                '}';
    }

    public void orderWasDeleted() {
        order=null;
        account.deletePayment(this);
        account=null;
    }

    public void accountWasDeleted(){
        account=null;
        order.deletePayment(this);
        order=null;

    }

    public void remove()
    {
        order.deletePayment(this);
        order=null;
        account.deletePayment(this);
        account=null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPaid() {
        return paid;
    }

    public void setPaid(Date paid) {
        this.paid = paid;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
