import java.util.Date;

public class DelayedPayment extends Payment {
    public Date paymentDate;

    public DelayedPayment(String id, Date paid, float total, String details, Account account, Order order) {
        super(id, paid, total, details, account, order);
    }

    @Override
    public String toString() {
        return "DelayedPayment{" +
                "paymentDate=" + paymentDate+
                ", id='" + id + '\'' +
                ", paid=" + paid +
                ", total=" + total +
                ", details='" + details + '\'' +
                ", account='"+super.getAccount().getId()+'\'' +
                '}';
    }
}
