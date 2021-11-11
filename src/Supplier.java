import java.util.ArrayList;
import java.util.List;

public class Supplier {
    private String id;
    private String name;
    private ArrayList<Product> products;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Supplier(String id, String name) {
        this.id = id;
        this.name = name;
        products = new ArrayList<>();
    }

    public Supplier(String id, String name, ArrayList<Product> prods) {
        this.id = id;
        this.name = name;
        products = prods;
    }

    public boolean addProduct(Product p)
    {
        if (p==null)
            return false;
        if (products.contains(p))
            return false;
        if (p.getSupplier()!=this)
            return false;
        products.add(p);
        return true;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void remove()
    {
        int i;
        int length = products.size();
        while(products.isEmpty()==false)
        {
            products.get(0).remove();
        }
    }
}
