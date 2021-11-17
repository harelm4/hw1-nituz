import javax.sound.sampled.Line;
import java.rmi.dgc.VMID;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    static ArrayList<User> users = new ArrayList<User>();
    static HashMap<Integer,Order> orders = new HashMap<Integer, Order>();
    static ArrayList<Product> products = new ArrayList<Product>();
    static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
    static HashMap<Integer,Object> allInstances = new HashMap<Integer,Object>();
    static Order lastOrder;
    static  User userLoggedIn=null;
    static int nextOrderNumber=1;
    static int nextSystemId=0;

    public static void main(String[] args) {


        Supplier Osem=new Supplier("Osem","Osem");
        Supplier EastWest=new Supplier("EastWest","EastWest");
        suppliers.add(Osem);
        suppliers.add(EastWest);
        allInstances.put(getSystemId(),Osem);
        allInstances.put(getSystemId(),EastWest);

        Product Bambaa=new Product("Bamba","Bamba", Osem);
        products.add(Bambaa);
        allInstances.put(getSystemId(),Bambaa);

        Product Ramen=new Product("Ramen","Ramen", EastWest);
        products.add(Ramen);
        allInstances.put(getSystemId(),Ramen);

        User user=addUser("Dani","Dani123",false,"BEER SHEVA 123","054-1234567","DANI@POST.BGU.AC.IL");
        User userPremium=addUser("Dana","Dana123",true, "BEER SHEVA 124","050-1234567","DANA@POST.BGU.AC.IL");
        PremiumAccount p = (PremiumAccount) userPremium.getCustomer().getAccount();
        p.addProduct(Bambaa);



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
                    if (userLoggedIn!=null) {
                        System.out.println("User logged in. Logout first");
                        break;
                    }
                    System.out.println("Please enter a User Id you wish to add:\n");
                    String UserID = myObj.nextLine();
                    if (findUser(UserID) != null){
                        System.out.println("User ID already taken!\n");
                        break;
                    }
                    System.out.println("Please enter a Password: (If you wish to have no password, please type '.')\n");
                    String UserPassword = myObj.nextLine();//fix add user function
                    System.out.println("Does the User have a Premium Account?: (Y/N) answer with capital letter, no spaces. If you type incorrectly, it won't be a premium account\n");
                    String UserPremium = myObj.nextLine().toLowerCase();
                    boolean UserPremiumBool = false;
                    if (UserPremium.equals("y")) {UserPremiumBool= true; }
                    else if((UserPremium.equals("n"))) {UserPremiumBool= false; }
                    else{
                        System.out.println("invalid entry: "+UserPremium);
                        break;
                    }
                    System.out.println("Your address please:\n");
                    String address = myObj.nextLine();
                    System.out.println("Your phone number please:\n");
                    String phone = myObj.nextLine();
                    System.out.println("Your email please:\n");
                    String email = myObj.nextLine();

                    addUser(UserID,UserPassword,UserPremiumBool,address, phone, email);
                    System.out.println("user "+UserID+ " has been created successfully\n");
                    break;
                }
                case 2:{ //Remove user
                    System.out.println("Please enter a User Id you wish to remove:\n");
                    String UserID = myObj.nextLine();
                    User userToRemove = findUser(UserID);
                    if (userToRemove != null) {
                        if (removeUser(userToRemove) == Status.success){
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
                    if(userLoggedIn!=null){
                        System.out.println("Another User is already logged in!\n");
                        break;
                    }
                    System.out.println("Please enter a User Id you wish to log in to:\n");
                    String UserID = myObj.nextLine();
                    if (findUser(UserID) == null){
                        System.out.println("User doesn't exist\n");
                        break;
                    }
                    System.out.println("Please enter a Password: (If User has no password, please type '.')\n");
                    String UserPassword = myObj.nextLine();//fix loginUser function
                    if (loginUser(UserID,UserPassword) == Status.success){
                        System.out.println("User successfully logged in!\n");
                        break;
                    }
                    System.out.println("log in failed");
                    break;

                }
                case 4:{ //Logout user
                    if(userLoggedIn==null){
                        System.out.println("there is no user currently logged in");
                        break;
                    }
                    System.out.println("Please enter a User Id you wish to log in to:\n");
                    String UserID = myObj.nextLine();
                    if (findUser(UserID) == null){
                        break;
                    }
                    if (logOut(UserID) == Status.success){
                        System.out.println("User successfully logged out!\n");
                        break;
                    }
                    else
                    {
                        System.out.println("Current User id isn't "+ UserID +" !\n");
                        break;
                    }
                }
                case 5:{ //Create new order
                    if(userLoggedIn==null){
                        System.out.println("Order creation failed,there is no logged in user\n");
                        break;
                    }
                    System.out.println("Please enter an address:\n");
                    String UserAddress= myObj.nextLine();

                    int OrderId = createNewOrder(UserAddress);

                    System.out.println("Would you like delayed payment?[Y/N]\n");
                    String pay = myObj.nextLine();
                    if(pay.equals("Y"))
                        CreatePayment(user,OrderId,true);
                    else
                        CreatePayment(user,OrderId,false);

                    if(OrderId==-1){
                        break;
                    }
                    System.out.println("Order Number is: "+ OrderId+"\n");
                    break;
                }
                case 6:{ //Add product to order
                    if (userLoggedIn==null) {
                        System.out.println("No user logged in. Login first");
                        break;
                    }
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
                    if(!(findUser(UserID).getCustomer().getAccount() instanceof PremiumAccount)){
                        System.out.println(UserID+" is not a premium account\n");
                        break;
                    }
                    //todo: see if we should do that
                    if(UserID.equals(userLoggedIn.getLogin_id())){
                        System.out.println("you cant order products from yourself");
                        break;
                    }
                    System.out.println("Enter a product name the User wishes to buy:\n");
                    String ProductName = myObj.nextLine();
                    if(findProductByName(ProductName)==null){
                        System.out.println(ProductName+" Doesn't exist!\n");
                        break;
                    }
                    if (addProductToOrder(OrderID,UserID,ProductName)== Status.success){
                        System.out.println(ProductName+" has been successfully added to Order " + OrderID +" !\n");
                        break;
                    }


                    break;
                }
                case 7:{ //Display order
                    if (userLoggedIn==null) {
                        System.out.println("No user logged in. Login first");
                        break;
                    }
                    if (DisplayOrder() == Status.failure){
                        System.out.println("User state is currently closed!\n");
                        break;
                    }
                    break;

                }
                case 8:{ //Link Product
                    if(userLoggedIn==null){
                        System.out.println("User should be logged in:\n");
                        break;
                    }
                    if (userLoggedIn.getCustomer().getAccount() instanceof PremiumAccount) {
                        System.out.println("User currently logged in is not a PremiumAccount");
                        break;
                    }
                    System.out.println("Please enter product name:\n");
                    String productName = myObj.nextLine();
                    System.out.println("Please enter price:\n");
                    String price = myObj.nextLine();
                    System.out.println("Please enter quantity:\n");
                    String quantity = myObj.nextLine();
                    if (userLoggedIn.getCustomer().getAccount() instanceof PremiumAccount)
                    {
                        try{
                            if(linkProduct(productName,Integer.parseInt(price),Integer.parseInt(quantity))==Status.success){
                                System.out.println(productName+" linked successfully to"+userLoggedIn+"'s account");
                                break;
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Enter integer values for price & quantity");
                            break;
                        }


                    }
                    else
                    {
                        System.out.println("Currently not a premium account. Link failed");
                        break;
                    }
                    System.out.println("link failed");
                    break;
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

    private static void CreatePayment(User user, int orderId,boolean isDelayed) {
            Payment payment;
            Date date=java.util.Calendar.getInstance().getTime();
            int totalpayment=0;
            int id=getSystemId();
            for(int i=0;i<orders.get(orderId).getLineItems().size();i++){
                totalpayment=orders.get(orderId).getLineItems().get(i).price;
            }
            if(isDelayed){
                payment=new DelayedPayment(""+id,date,totalpayment,orders.get(orderId).getNumber(),user.getCustomer().getAccount(),orders.get(orderId));
                orders.get(orderId).addPayment(payment);
            }
            else{
                payment=new ImmediatePayment(""+id,date,totalpayment,orders.get(orderId).getNumber(),user.getCustomer().getAccount(),orders.get(orderId));
                orders.get(orderId).addPayment(payment);
            }
            allInstances.put(id,payment);


    }

    private static Status linkProduct(String productName, int price, int quantity) {
        Product p = findProductByName(productName);
        p.setPrice(price);
        p.setTotalAvailableQuantity(quantity);
        PremiumAccount pa = (PremiumAccount) (userLoggedIn.getCustomer().getAccount());
        pa.addProduct(p);
        return Status.success;
    }


    public static User addUser(String id,String password,boolean isPremiumAccount, String address, String phone, String email){
        ShoppingCart shoppingCart=new ShoppingCart(new Date());
        Account account;

        if(isPremiumAccount) account=new PremiumAccount(id,address,false,new Date(),null,5);
        else account=new Account(id,address,false,new Date(),null,5);

        Customer customer=new Customer(id,new Address(address),phone,email);
        User user = new User(id,password,UserState.New);

        allInstances.put(getSystemId(),shoppingCart);
        allInstances.put(getSystemId(),customer);
        allInstances.put(getSystemId(),account);
        allInstances.put(getSystemId(),user);
        users.add(user);

        //shopping cart->account
        shoppingCart.setAccount(account);
        //account->customer
        account.setCustomer(customer);
        //customer->account
        customer.setAccount(account);
        //account->shopping cart
        account.setShoppingCart(shoppingCart);
        //creating a new user
        //user->customer
        user.setCustomer(customer);
        //customer->user
        customer.setUser(user);
        //shopping cart-> user
        shoppingCart.setUser(user);
        //todo-decide if it should be here
        //user->shopping cart
        user.setShoppingCart(shoppingCart);

        return user;
    }
    public static Status loginUser(String id,String password){
        if(userLoggedIn==null){
            User u=findUser(id);
            if (u!=null && u.getPassword().equals(password)){
                userLoggedIn=u;
                return Status.success;
            }
        }
        return Status.failure;
    }
    public static Status logOut(String id){
        if(id.equals(userLoggedIn.getLogin_id())){
            userLoggedIn=null;
            return Status.success;
        }
        return Status.failure;
    }
    public static int createNewOrder(String address){

        Address addressObj=new Address(address);
        Order order=new Order(String.valueOf(nextOrderNumber), LocalDateTime.now(),addressObj,OrderStatus.New, userLoggedIn.getCustomer().getAccount());
        allInstances.put(getSystemId(),order);
        orders.put(nextOrderNumber,order);
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
                "Order Number: "+ lastOrder.getNumber()+"\n"
                        +"Order Date:"+ lastOrder.getOrdered()+"\n"
                        +"Order Shipping Date:"+lastOrder.getShipped()+"\n"
                        +"Order Address:"+lastOrder.getShip_to().getAddress()+"\n"
                        +"Order Status:"+lastOrder.getStatus().name()+"\n"
                        +"Order total: "+lastOrder.getTotal()+"\n"
        );
        return Status.success;
    }

    private static User findUser(String id){
        for(User u:users){
            if(u.getLogin_id().equals(id)){
                return u;

            }
        }
        return null;
    }
    private static  Order findOrder(String id){
        for(Order o:orders.values()){
            if(o.getNumber().equals(id)){
                return o;

            }
        }
        return null;
    }
    private static  Product findProductByName(String n){
        for(Product p:products){
            if(p.getName().equals(n)){
                return p;

            }
        }
        return null;
    }

    private static Supplier findSupplierByName(String supplierName) {
        for(Supplier s: suppliers){
            if(s.getName().equals(supplierName)){
                return s;

            }
        }
        return null;
    }

    private static Status deleteProduct(String productName) {
        Product p = findProductByName(productName);
        ArrayList<LineItem> lineItems = p.getLineItems();
        p.deleteProduct();


        //removal of all products & lineItems from system

        int size = allInstances.size();
        for (int i = 0; i < size; i++) {
            try {
                allInstances.get(i).toString(); //if null pointer exception means that there is no printing because of deleting all associations
            } catch (NullPointerException e) {
                allInstances.remove(i);
            }
//todo:ask adi why did he do next line (caused a bug)
//            return Status.failure;
        }
        return Status.success;
    }


    //Add Product *Product_Name* *Supplier_Name*
    public static Status addProduct(String productName,String supplierName){
        Supplier supplier = findSupplierByName(supplierName);
        Product p = new Product(productName,productName,supplier);
        products.add(p);
        allInstances.put(getSystemId(),p);
        return Status.success;
    }



    public static Status removeUser(User user) {
        if(userLoggedIn!=null && user.getLogin_id().equals(userLoggedIn.getLogin_id())){
            System.out.println("cant remove user while logged in to same user");//assumption
            return Status.failure;
        }
        ShoppingCart shoppingCart = user.getShoppingCart();
        Customer customer = user.getCustomer();
        Account account = customer.getAccount();
        ArrayList<LineItem> lineItems = shoppingCart.getLineItems();
        user.remove();
        users.remove(user);
        int size = allInstances.size();
        for (int i = 0; i < size; i++) {
            try {
                allInstances.get(i).toString();
            } catch (NullPointerException e) {
                allInstances.remove(i);
            }


        }
        return Status.success;
    }
    private static Status showAllObjects() {
        //printing the memory address of each object
        for (Integer name: allInstances.keySet()) {
            String key = name.toString();
            String value = allInstances.get(name).toString();
            System.out.println("ID: "+key + ", value: " + value);
        }

        return Status.success;
    }

    private static Status showObjectId(String objectId) {
        try {
            int key = Integer.parseInt(objectId);
            if (allInstances.containsKey(key)) {
                System.out.println(allInstances.get(key));
                return Status.success;
            }


            System.out.println(objectId + " does not exist in the system\n");
            return Status.failure;
        }catch (NumberFormatException e){
            System.out.println(objectId + " is not a number id in the system\n");
            return Status.failure;

        }


    }

    //Add product to order *Order_ID* *Login_ID* *Product Name*
    public static Status addProductToOrder(String orderId,String userId,String productName){

        Order order=findOrder(orderId);
        //delete order from userId product list
        Account acc=findUser(userId).getCustomer().getAccount();
        Product p = findProductByName(productName);
        if(acc instanceof PremiumAccount){
            //((PremiumAccount) acc).delProduct(findProductByName(productName));

            LineItem newLineItem = new LineItem(userLoggedIn.getShoppingCart(), order,p,1,10);
            allInstances.put(getSystemId(),newLineItem);

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
