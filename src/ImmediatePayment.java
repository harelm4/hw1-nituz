import java.util.Date;

public class ImmediatePayment extends Payment {
    public boolean phoneConfirmation;


    @Override
    public String toString() {
        return "ImmediatePayment{" +
                "phoneConfirmation=" + phoneConfirmation +
                ", id='" + id + '\'' +
                ", paid=" + paid +
                ", total=" + total +
                ", details='" + details + '\'' +
                ", account='"+super.getAccount().getId()+'\'' +
                '}';
    }


    public ImmediatePayment(String id, Date paid, float total, String details, Account account, Order order) {
        super(id, paid, total, details, account, order);
    }
}
