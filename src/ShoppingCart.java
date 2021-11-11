import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart {
    private Date created;

    private User user;
    private Account account;
    private ArrayList<LineItem> lineItems;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
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
}
