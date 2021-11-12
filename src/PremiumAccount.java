import java.util.ArrayList;
import java.util.Date;

public class PremiumAccount extends Account {
    ArrayList<Product> products=new ArrayList<Product>();

    /**
     * no need for orders & payments.
     * no need to get customer & shoppingCart because of the 1:1 associations, based on the instructions given
     *
     * @param id
     * @param billing_address
     * @param is_closed
     * @param open
     * @param closed
     * @param balance
     */
    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance) {
        super(id, billing_address, is_closed, open, closed, balance);
    }

    public Status addProduct(Product p){
        if (p==null)
            return Status.failure;
        if (products.contains(p))
            return Status.failure;
        products.add(p);
        return Status.success;
    }
    public void delProduct(Product product) {
        products.remove(product);
    }

    public void remove(){
        int i=0;
        for (i=0; i<products.size(); i++)
        {
            products.get(i).deletePremiumAccount();
        }
    }
    public ArrayList<Product> getProducts() {
        return products;
    }



}
