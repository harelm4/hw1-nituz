import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private String name;

    private Supplier supplier;
    private ArrayList<LineItem> lineItems;
    private PremiumAccount premiumAccount;

    public Product(String id, String name, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        supplier.addProduct(this);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", supplier=" + supplier.getId() +
                ", lineItems=" + lineItems +
                ", premiumAccount=" + premiumAccount+
                '}';
    }

    public void deleteProduct()
    {
        supplier.delProduct(this);
        supplier=null;
        premiumAccount.delProduct(this);
        premiumAccount=null;

        int i=0;
        for (i=0; i<lineItems.size(); i++)
        {
            lineItems.get(i).productWasDeleted();
        }
        lineItems=null;
    }

    public void supplierWasDeleted() {
        supplier=null;
        premiumAccount.delProduct(this);
        premiumAccount=null;

        int i=0;
        for (i=0; i<lineItems.size(); i++)
        {
            lineItems.get(i).productWasDeleted();
        }
        lineItems=null;

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

    public void deletePremiumAccount() {
        this.premiumAccount=null;
    }

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

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Supplier getSupplier() {
        return supplier;
    }

}
