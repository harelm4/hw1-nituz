public class Product {
    private String id;
    private String name;

    private Supplier supplier;
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
