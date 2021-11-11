import java.util.ArrayList;

public class PremiumAccount extends Account {
    ArrayList<Product> products=new ArrayList<Product>();

    public ArrayList<Product> getProducts() {
        return products;
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



}
