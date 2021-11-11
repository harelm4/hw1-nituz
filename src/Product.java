import java.util.List;

public class Product {
    private String id;
    private String name;

    private Supplier supplier;
    private List<LineItem> lineItems;

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

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Supplier getSupplier() {
        return supplier;
    }


    public Product(String id, String name, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        supplier.addProduct(this);
    }

    public void remove()
    {
        supplier.removeProduct(this);
        supplier=null;
    }
}
