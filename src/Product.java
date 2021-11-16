import java.util.ArrayList;
import java.util.List;

public class Product {
    private String id;
    private String name;

    private Supplier supplier;
    private ArrayList<LineItem> lineItems;
    private PremiumAccount premiumAccount;
    private int price;
    private int totalAvailableQuantity;

    public Product(String id, String name, Supplier supplier) {
        lineItems=new ArrayList<>();
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        supplier.addProduct(this);
        this.price=10; //default
        this.totalAvailableQuantity=1000; //default
    }

    public Product(String id, String name, Supplier supplier, int Price, int totalAvailableQuantity) {
        lineItems=new ArrayList<>();
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        supplier.addProduct(this);
        this.price=Price;
        this.totalAvailableQuantity=totalAvailableQuantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalAvailableQuantity() {
        return totalAvailableQuantity;
    }

    public void setTotalAvailableQuantity(int totalAvailableQuantity) {
        this.totalAvailableQuantity = totalAvailableQuantity;
    }

    @Override
    public String toString() {
        String l="";
        for (LineItem lineItem: lineItems)
            l+="["+lineItem.getProduct().getId()+", q:"+lineItem.getQuantity()+", p:"+lineItem.getPrice()+"],";

        String pra="";
        if (premiumAccount!=null)
            pra=premiumAccount.getId();
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", supplier=" + supplier.getId() +
                ", lineItems=" + l +
                ", premiumAccount=" + pra+
                ", price=" +price+
                ", totalAvailableQuantity="+totalAvailableQuantity+
                '}';
    }


    public void deleteProduct()
    {
        supplier.delProduct(this);
        supplier=null;
        if(premiumAccount!=null){
            premiumAccount.delProduct(this);
            premiumAccount=null;
        }
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

    public PremiumAccount getPremiumAccount() {
        return premiumAccount;
    }

    public void setPremiumAccount(PremiumAccount premiumAccount) {
        if (this.premiumAccount!=null && premiumAccount!=null)
            this.premiumAccount.delProduct(this); //when setting new premium account when old one exists -> delete the previus association. this is described in our pdf assumptions
        this.premiumAccount=premiumAccount;
    }
}
