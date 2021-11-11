import javax.sound.sampled.Line;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    static ArrayList<User> users = new ArrayList<User>();
    static ArrayList<Order> orders = new ArrayList<Order>();
    static ArrayList<Product> products = new ArrayList<Product>();
    static  ArrayList<Object> allInstances = new ArrayList();
    static Order lastOrder;
    static  User userLoggedIn=null;
    static int nextOrderNumber=1;


    public static void main(String[] args) {
        /**
         * todo:supported commends:
            1. Add user *Login_id* - and after should ask from the user to define : password , is the account a premium one,and define the rest of the user account attributes
            2.Remove user *Login_id*
            3.Login user *Login_id*   *password* - should be only one user logged in (remember to update user state)
            4.Logout user *Login_id*
            5.Create new order *address* - update "ordered" time, **display order id as output to user**
            6.Add product to order *Order_ID* *Login_ID* *Product Name*
            7.Display order-
             	הפקודה תציג את הפרטים המופיעים בתרשים המחלקות:
             	מספר הזמנה
             	תאריך ביצוע ההזמנה
             	תאריך השילוח
             	כתובת למשלוח
             	סטטוס ההזמנה
             	סכום כולל לתשלום
            8.Link Product *Product_Name* *Price* *Quantity*
            9. Add Product *Product_Name* *Supplier_Name*
            10. Delete Product *Product_name* -the Product_name is unique
            11(10).ShowAllObjects -show all object with a unique id
            12(11).ShowObjectId *id* - display all object attributes and all the names of the objects that the *id* object connected to

         *todo:when the system inits the folowing object should be created:
             	id: Osem
             	name: Osem
             o	Supplier עם הפרטים הבאים:
             	id: EastWest
             	name: EastWest
             o	Product עם הפרטים הבאים:
             	id: Bamba
             	name: Bamba
             	יקושר ל-Supplier בעל ה-id: ‘Osem’.
             o	Product עם הפרטים הבאים:
             	id: Ramen
             	name: Ramen
             	יקושר ל-Supplier בעל ה-id: ‘EastWest’.
             o	Account רגיל המשויך ל-user עם הפרטים הבאים:
             	Login_id: Dani
             	Password: Dani123
             o	Premium Account המשויך ל-user עם הפרטים הבאים:
             	Login_id: Dana
             	Password: Dana123
             ה-Premium Account הנ"ל יהיה מקושר עם עליית המערכת ל-Product בשם Bamba.
         **/

        Supplier Osem=new Supplier("Osem","Osem");
        Supplier EastWest=new Supplier("EastWest","EastWest");
        Product Bambaa=new Product("Bamba","Bamba");
        Bambaa.setSupplier(Osem);
        Osem.addProduct(Bambaa);
        Product Ramen=new Product("Ramen","Ramen");
        Ramen.setSupplier(EastWest);
        EastWest.addProduct(Ramen);
        User user=addUser("Dani","Dani123",false);
        Customer customer=new Customer();
        customer.setUser(user);
        Account normalAccount=new Account();
        customer.setAccount(normalAccount);
        normalAccount.setCustomer(customer);
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setAccount(normalAccount);
        normalAccount.setShoppingCart(shoppingCart);
        PremiumAccount premiumAccount=new PremiumAccount();
        premiumAccount.addProduct(Bambaa);
        premiumAccount.setCustomer(customer);
        User userPremium=addUser("Dana","Dana123",true);
        Customer customerPremium=new Customer();
        customerPremium.setUser(user);
        customerPremium.setAccount(premiumAccount);
        premiumAccount.setCustomer(customerPremium);
        ShoppingCart PremiumShoppingCart=new ShoppingCart();
        PremiumShoppingCart.setUser(userPremium);
        PremiumShoppingCart.setAccount(premiumAccount);
        premiumAccount.setShoppingCart(PremiumShoppingCart);

        int inputValue=-1;
        while (inputValue !=0){
            System.out.println(
                    "choose action:\n" +
                            "1.Add user\n"+
                            "2.Remove user\n"+
                            "3.Login user\n "+
                            "4.Logout user\n"+
                            "5."
            );

            //if choose add user
        }


    }
    public static User addUser(String id,String password,boolean isPremiumAccount){
        ShoppingCart shoppingCart=new ShoppingCart();

        Account account;
        if(isPremiumAccount) account=new PremiumAccount();
        else account=new Account();

        Customer customer=new Customer();

        allInstances.add(shoppingCart);
        allInstances.add(customer);
        allInstances.add(account);
        //shopping cart->account
        shoppingCart.setAccount(account);
        //account->customer
        account.setCustomer(customer);
        //customer->account
        customer.account=account;
        //account->shopping cart
        account.shoppingCart=shoppingCart;
        //creating a new user
        User user = new User(id,password,UserState.New);
        //user->customer
        user.customer=customer;

        allInstances.add(user);
        users.add(user);
        return user;
    }
    public static Status loginUser(String id,String password){
        if(userLoggedIn==null){
            User u=findUser(id);
            if (u!=null && users.contains(u) && u.getPassword()==password){
                userLoggedIn=u;
                return Status.success;
            }
        }
        return Status.failure;
    }
    public static Status logOut(String id){
        if(id== userLoggedIn.getLogin_id()){
            userLoggedIn=null;
            return Status.success;
        }
        return Status.failure;
    }
    public static int createNewOrder(String address){
        if(userLoggedIn==null){
            return -1;
        }
        Address addressObj=new Address(address);
        allInstances.add(addressObj);
        Order order=new Order(String.valueOf(nextOrderNumber), LocalDateTime.now(),addressObj,OrderStatus.New);
        allInstances.add(order);
        orders.add(order);
        userLoggedIn.getCustomer().account.addOrder(order);
        nextOrderNumber+=1;
        return nextOrderNumber-1;
    }
    //Add product to order *Order_ID* *Login_ID* *Product Name*


    public static Status addProductToOrder(String orderId,String userId,String productName){
        Order order=findOrder(orderId);
        ArrayList<LineItem> productsLineItems= findProductByName(productName).getLineItems();
        //order<->line items
        for(LineItem li:productsLineItems){
            order.addLineItem(li);
            li.setOrder(order);
        }
        return Status.success



    }

    public static Status DisplayOrder(){
        if(userLoggedIn.getState()==UserState.Closed)
            return Status.failure;
        Order lastOrder=userLoggedIn.getCustomer().getAccount().getOrders().peek();
        System.out.println(
                "Order Number: "+ lastOrder.getNumber()
                        +"Order Date:"+ lastOrder.getOrdered()
                        +"Order Shipping Date:"+lastOrder.getShipped()
                        +"Order Adress:"+lastOrder.getShip_to().getAdress()
                        +"Order Status:"+lastOrder.getStatus().name()
                        +"Order total Payment"+lastOrder.getSumOfPayments()
        );
        return Status.success;
    }

    //Add Product *Product_Name* *Supplier_Name*
    public static Status addProduct(String productName,String supplierName){
        //
    }


//    public void removeUser(String id){
//        //todo:to complete at the end
//        User user=findUser(id);
//        if(user!=null){
//            allInstances.remove(user);
//            allInstances.remove(user.getCustomer());
//            allInstances.remove(user.getCustomer().getAccont());
//            if(user.getCustomer().getAccont().getOrders().leangth != 0){
//                allInstances.removeAll(user.getCustomer().getAccont().getOrders());
//            }
//            if(user.getShoppingCart() != null){
//                allInstances.remove(user.getShoppingCart());
//                if(user.getShoppingCart.leangth != 0){
//                    allInstances.removeAll(user.getCustomer().getAccont().getOrders());
//                }
//            }
//
//        }
//    }

    private static User findUser(String id){
        for(User u:users){
            if(u.getLogin_id()==id){
                return u;

            }
        }
        return null;
    }
    private static  Order findOrder(String id){
        for(Order o:orders){
            if(o.getNumber()==id){
                return o;

            }
        }
        return null;
    }
    private static  Product findProductByName(String n){
        for(Product p:products){
            if(p.getName()==n){
                return p;

            }
        }
        return null;
    }

}
