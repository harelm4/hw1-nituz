import javax.sound.sampled.Line;
import java.rmi.dgc.VMID;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    static ArrayList<User> users = new ArrayList<User>();
    static ArrayList<Order> orders = new ArrayList<Order>();
    static ArrayList<Product> products = new ArrayList<Product>();
    static HashMap<Integer,Object> allInstances = new HashMap<Integer,Object>();
    static Order lastOrder;
    static  User userLoggedIn=null;
    static int nextOrderNumber=1;
    static int nextSystemId=0;

    public static void main(String[] args) {


        Supplier Osem=new Supplier("Osem","Osem");
        Supplier EastWest=new Supplier("EastWest","EastWest");
        Product Bambaa=new Product("Bamba","Bamba", Osem);
        //Bambaa.setSupplier(Osem);
        //Osem.addProduct(Bambaa);
        Product Ramen=new Product("Ramen","Ramen", EastWest);
        //Ramen.setSupplier(EastWest);
        //EastWest.addProduct(Ramen);

        User user=addUser("Dani","Dani123",false,"BEER SHEVA 123");
        /**
         Customer customer=new Customer();
         customer.setUser(user);
         Account normalAccount=new Account();
         customer.setAccount(normalAccount);
         normalAccount.setCustomer(customer);
         ShoppingCart shoppingCart=new ShoppingCart();
         shoppingCart.setUser(user);
         shoppingCart.setAccount(normalAccount);
         normalAccount.setShoppingCart(shoppingCart);
         **/

        User userPremium=addUser("Dana","Dana123",true, "BEER SHEVA 124");
        PremiumAccount p = (PremiumAccount) userPremium.getCustomer().getAccount();
        p.addProduct(Bambaa);
        //(PremiumAccount (userPremium.getCustomer().getAccount()).addProduct(Bambaa);
        //premiumAccount.setCustomer(customer);

        /**
         Customer customerPremium=new Customer();
         customerPremium.setUser(user);
         customerPremium.setAccount(premiumAccount);
         premiumAccount.setCustomer(customerPremium);
         ShoppingCart PremiumShoppingCart=new ShoppingCart();
         PremiumShoppingCart.setUser(userPremium);
         PremiumShoppingCart.setAccount(premiumAccount);
         premiumAccount.setShoppingCart(PremiumShoppingCart);

         **/


        Scanner myObj = new Scanner(System.in);
        int inputValue=-1;
        while (inputValue !=0){
            System.out.println(
                    "choose action:\n" +
                            "1.Add user\n"+
                            "2.Remove user\n"+
                            "3.Login user\n"+
                            "4.Logout user\n"+
                            "5.Create new order\n"+
                            "6.Add product to order\n"+
                            "7.Display order\n"+
                            "8.Link Product\n"+
                            "9.Add Product\n"+
                            "10.Delete Product\n"+
                            "11.ShowAllObject\n"+
                            "12.ShowObjectId\n"+
                            "0.Exit\n"
            );

            System.out.println("Please select an operation:\n");
            String OpNumber = myObj.nextLine();
            inputValue = Integer.parseInt(OpNumber);
            switch(inputValue){
                case 1:{ //Add user
                    System.out.println("Please enter a User Id you wish to add:\n");
                    String UserID = myObj.nextLine();
                    if (findUser(UserID) != null){
                        System.out.println("User ID already taken!\n");
                        break;
                    }
                    System.out.println("Please enter a Password: (If you wish to have no password, please type '.')\n");
                    String UserPassword = myObj.nextLine();//fix add user function
                    System.out.println("Does the User have a Premium Account?: (Y/N)\n");
                    String UserPremium = myObj.nextLine();
                    boolean UserPremiumBool = false;
                    if (UserPremium.equals("Y")) {UserPremiumBool= true; }
                    if (UserPremium.equals("N")) {UserPremiumBool= false; }
                    addUser(UserID,UserPassword,UserPremiumBool,"TEL AVIV 1234");
                    break;
                }
                case 2:{ //Remove user
                    System.out.println("Please enter a User Id you wish to remove:\n");
                    String UserID = myObj.nextLine();
                    if (findUser(UserID) != null) {
                        if (removeUser(UserID) == Status.success){
                            System.out.println("User removed successfully.\n");
                            break;
                        }
                    }
                    else {
                        System.out.println("No user found with id "+UserID+" \n" );
                        break;
                    }
                    break;
                }
                case 3:{ //Login user
                    System.out.println("Please enter a User Id you wish to log in to:\n");
                    String UserID = myObj.nextLine();
                    if (findUser(UserID) == null){
                        break;
                    }
                    System.out.println("Please enter a Password: (If User has no password, please type '.')\n");
                    String UserPassword = myObj.nextLine();//fix loginUser function
                    if (loginUser(UserID,UserPassword) == Status.success){
                        System.out.println("User successfully logged in!\n");
                        break;
                    }
                    System.out.println("Another User is already logged in!\n");
                    break;
                }
                case 4:{ //Logout user
                    System.out.println("Please enter a User Id you wish to log in to:\n");
                    String UserID = myObj.nextLine();
                    if (findUser(UserID) == null){
                        break;
                    }
                    if (logOut(UserID) == Status.success){
                        System.out.println("User successfully logged out!\n");
                        break;
                    }
                    System.out.println("Current User id isn't "+ UserID +" !\n");
                    break;
                }
                case 5:{ //Create new order
                    System.out.println("Please enter an address:\n");
                    String UserAddress= myObj.nextLine();

                    int OrderId = createNewOrder(UserAddress);
                    System.out.println("Order Number is: "+ OrderId+"\n");
                    break;
                }
                case 6:{ //Add product to order
                    System.out.println("Please enter an Order Id:\n");
                    String OrderID = myObj.nextLine();
                    if (findOrder(OrderID) == null){
                        System.out.println("Order doesn't exist!\n");
                        break;
                    }
                    System.out.println("Please enter User login Id:\n");
                    String UserID = myObj.nextLine();//fix add user function
                    if (findUser(UserID) == null){
                        System.out.println("User ID doesn't exist!\n");
                        break;
                    }
                    System.out.println("Enter a product name the User wishes to buy:\n");
                    String ProductName = myObj.nextLine();
                    if (addProductToOrder(OrderID,UserID,ProductName)== Status.success){
                        System.out.println(ProductName+" has been successfully added to Order " + OrderID +" !\n");
                        break;
                    }
                    System.out.println(ProductName+" Doesn't exist!\n");
                    break;
                }
                case 7:{ //Display order
                    if (DisplayOrder() == Status.failure){
                        System.out.println("User state is currently closed!\n");
                        break;
                    }
                }
                case 8:{ //Link Product


                }
                case 9:{ //Add Product
                    System.out.println("Please enter a product name you wish to add:\n");
                    String ProductName = myObj.nextLine();
                    if (findProductByName(ProductName) != null){
                        System.out.println("Product name already taken!\n"); /////////////////////////////
                        break;
                    }
                    System.out.println("Please enter a Supplier name:\n");
                    String SupplierName = myObj.nextLine();//fix add user function
                    if (findSupplierByName(SupplierName) == null){ /////////////////
                        System.out.println("Supplier doesn't exist!\n");
                        break;
                    }
                    if (addProduct(ProductName,SupplierName) == Status.success){
                        System.out.println("Product added successfully!\n");
                        break;
                    }
                    System.out.println("Product wasn't added successfully!\n");
                    break;

                }
                case 10:{ //Delete Product
                    System.out.println("Please enter a product name you wish to delete:\n");
                    String ProductName = myObj.nextLine();
                    if (findProductByName(ProductName) == null){
                        System.out.println("Product name doesn't exist!\n");
                        break;
                    }
                    if(deleteProduct(ProductName) == Status.success){
                        System.out.println("Product "+ProductName +" successfully deleted!\n");
                        break;
                    }
                    System.out.println("Failed to delete product "+ProductName +"!\n");
                    break;
                }
                case 11:{ //ShowAllObject
                    showAllObjects();
                    break;
                }
                case 12:{ //ShowObjectId
                    System.out.println("Please enter an Object Id:\n");
                    String ObjectId= myObj.nextLine();
                    showObjectId(ObjectId);
                    break;

                }
                case 0:{ //Exit
                    System.out.println("Thank you for visiting our online store!\n");
                    System.out.println("We hope to see you again!\n");
                    break;
                }
            }
        }
    }



    public static User addUser(String id,String password,boolean isPremiumAccount, String address){
        ShoppingCart shoppingCart=new ShoppingCart(new Date(2021,1,1));

        Account account;
        if(isPremiumAccount) account=new PremiumAccount(id,"Beer Sheva",false,new Date(2020,1,1),null,5);
        else account=new Account(id,"Beer Sheva",false,new Date(2020,1,1),null,5);

        Customer customer=new Customer(id,new Address(address),"054-1234567","bgu@post.com");

        allInstances.put(getSystemId(),shoppingCart);
        allInstances.put(getSystemId(),customer);
        allInstances.put(getSystemId(),account);
        //shopping cart->account
        shoppingCart.setAccount(account);
        //account->customer
        account.setCustomer(customer);
        //customer->account
        customer.setAccount(account);
        //account->shopping cart
        account.setShoppingCart(shoppingCart);
        //creating a new user
        User user = new User(id,password,UserState.New);
        //user->customer
        user.setCustomer(customer);
        //customer->user
        customer.setUser(user);
        //shopping cart-> user
        shoppingCart.setUser(user);
        //todo-decide if it should be here
        //user->shopping cart
        user.setShoppingCart(shoppingCart);

        allInstances.put(getSystemId(),user);
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
        allInstances.put(getSystemId(),addressObj);
        Order order=new Order(String.valueOf(nextOrderNumber), LocalDateTime.now(),addressObj,OrderStatus.New, userLoggedIn.getCustomer().getAccount());
        allInstances.put(getSystemId(),order);
        orders.add(order);
        //userLoggedIn.getCustomer().getAccount().addOrder(order);
        nextOrderNumber+=1;
        return nextOrderNumber-1;
    }
    //Add product to order *Order_ID* *Login_ID* *Product Name*



    public static Status DisplayOrder(){
        if(userLoggedIn.getState()==UserState.Closed)
            return Status.failure;
        Order lastOrder=userLoggedIn.getCustomer().getAccount().getOrders().peek();
        System.out.println(
                "Order Number: "+ lastOrder.getNumber()
                        +"Order Date:"+ lastOrder.getOrdered()
                        +"Order Shipping Date:"+lastOrder.getShipped()
                        +"Order Adress:"+lastOrder.getShip_to().getAddress()
                        +"Order Status:"+lastOrder.getStatus().name()
                        +"Order total Payment"+lastOrder.getSumOfPayments()
        );
        return Status.success;
    }

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

    private static Status findSupplierByName(String supplierName) {
        //todo
        return null;
    }

    private static Status deleteProduct(String productName) {
        //todo
        return null;
    }

    //Add Product *Product_Name* *Supplier_Name*
    public static Status addProduct(String productName,String supplierName){
        //todo
        //
        return null;
    }


    public static Status removeUser(String id) {
        return null;
    }
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

    private static Status showAllObjects() {
        //printing the memory address of each object
        System.out.println(allInstances.keySet());
        return Status.success;
    }

    private static Status showObjectId(String objectId) {
        //int key=Integer.parseInt(objectId);
        if(allInstances.keySet().contains(objectId))
            System.out.println(allInstances.get(objectId));
        return Status.success;
    }

    //Add product to order *Order_ID* *Login_ID* *Product Name*
    public static Status addProductToOrder(String orderId,String userId,String productName){
        //todo
        Order order=findOrder(orderId);
        //delete order from userId product list
        Account acc=findUser(userId).getCustomer().getAccount();
        if(acc instanceof PremiumAccount){
            ((PremiumAccount) acc).delProduct(findProductByName(productName));
        }
        else{
            return Status.failure;
        }
        //add product to logged in order
        //how can i know what LineItem should i add to Order?????
//        LineItem productLineItem=findProductByName(productName).getLineItems();
        return Status.success;



    }
    private static int getSystemId(){
        nextSystemId+=1;
        return nextSystemId-1;
    }

}
