import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart {
    private Date created;
    private User user;
    private Account account;
    private ArrayList<LineItem> lineItems;

    public ShoppingCart(Date created) {
        this.created = created;
    }

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

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "created=" + created +
                ", user=" + user.getLogin_id() +
                ", account=" + account.getId() +
                ", lineItems=" + lineItems +
                '}';
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

    public void userwasdeleted() {
        //remove all connected
        this.user=null;
        this.account.shoppingCartWasDeleted();
        this.account=null;
        int i=0;
        for (i=0; i<lineItems.size(); i++)
        {
            lineItems.get(i).shoppingCartWasDeleted();
        }
        lineItems=null;
    }

    public void delAccount(Account account) {
        this.user.shoppingCartWasDeleted();
        this.user=null;
        this.account=null;
        int i=0;
        for (i=0; i<lineItems.size(); i++)
        {
            lineItems.get(i).shoppingCartWasDeleted();
        }
        lineItems=null;

    }
}
