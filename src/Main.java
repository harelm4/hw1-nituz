import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    ArrayList<User> users = new ArrayList<User>();
    ArrayList<Order> orders = new ArrayList<Order>();
    ArrayList<Product> products = new ArrayList<Product>();
    ArrayList allInstances = new ArrayList();
    Order lastOrder;
    User userLoggedIn=null;
    int nextOrderNumber=1;


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
    public User addUser(String id,String password,boolean isPremiumAccount){
        ShoppingCart shoppingCart=new ShoppingCart();

        Account account;
        if(isPremiumAccount) account=new PremiumAccount();
        else account=new Account();

        Customer customer=new Customer();

        allInstances.add(shoppingCart);
        allInstances.add(customer);
        allInstances.add(account);
        //shopping cart->account
        shoppingCart.account=account;
        //account->customer
        account.customer=customer;
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
    public Status loginUser(String id,String password){
        if(userLoggedIn==null){
            User u=findUser(id);
            if (u!=null && users.contains(u) && u.getPassword()==password){
                userLoggedIn=u;
                return Status.success;
            }
        }
        return Status.failure
    }
    public Status logOut(String id){
        if(id== userLoggedIn.getLoginId()){
            userLoggedIn=null;
            return Status.success;
        }
        return Status.failure;
    }
    public int createNewOrder(String address){
        Order order=new Order(nextOrderNumber, LocalDateTime.now(),address,OrderStatus.New);
        allInstances.add(order);
        orders.add(order);
        userLoggedIn.getCustomer().account.addOrder(order);
        nextOrderNumber+=1;
        return nextOrderNumber-1;
    }
    public Status addProductToOrder(String orderID,String userId,String productName){

    }
    public void removeUser(String id){
        //todo:to complete at the end
        User user=findUser(id);
        if(user!=null){
            allInstances.remove(user);
            allInstances.remove(user.getCustomer());
            allInstances.remove(user.getCustomer().getAccont());
            if(user.getCustomer().getAccont().getOrders().leangth != 0){
                allInstances.removeAll(user.getCustomer().getAccont().getOrders());
            }
            if(user.getShoppingCart() != null){
                allInstances.remove(user.getShoppingCart());
                if(user.getShoppingCart.leangth != 0){
                    allInstances.removeAll(user.getCustomer().getAccont().getOrders());
                }
            }

        }
    }

    private User findUser(String id){
        for(User u:users){
            if(u.getLoginId()==id){
                return u;

            }
        }
    }
}
