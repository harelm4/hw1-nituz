import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

public class Account {
        public String id;
        public String billing_address;
        public boolean is_closed;
        public Date open;
        public Date closed;
        public int balance;
        //shold be stack to support command no.7
        Stack<Order> orders=new Stack<Order>();
        ArrayList<Payment> payments = new ArrayList<Payment>();

        private Customer customer;
        private ShoppingCart shoppingCart;

        public ShoppingCart getShoppingCart() {
                return shoppingCart;
        }

        public void setShoppingCart(ShoppingCart shoppingCart) {
                this.shoppingCart = shoppingCart;
        }

        public Customer getCustomer() {
                return customer;
        }

        public void setCustomer(Customer customer) {
                this.customer = customer;
        }

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getBilling_address() {
                return billing_address;
        }

        public void setBilling_address(String billing_address) {
                this.billing_address = billing_address;
        }

        public boolean isIs_closed() {
                return is_closed;
        }

        public void setIs_closed(boolean is_closed) {
                this.is_closed = is_closed;
        }

        public Date getOpen() {
                return open;
        }

        public void setOpen(Date open) {
                this.open = open;
        }

        public Date getClosed() {
                return closed;
        }

        public void setClosed(Date closed) {
                this.closed = closed;
        }

        public int getBalance() {
                return balance;
        }

        public void setBalance(int balance) {
                this.balance = balance;
        }

        public Stack<Order> getOrders() {
                return orders;
        }

        public void setOrders(Stack<Order> orders) {
                this.orders = orders;
        }

        public ArrayList<Payment> getPayments() {
                return payments;
        }

        public void setPayments(ArrayList<Payment> payments) {
                this.payments = payments;
        }

        public Status addPayment(Payment p){
                if (p==null)
                        return Status.failure;
                if (payments.contains(p))
                        return Status.failure;
                payments.add(p);
                return Status.success;

        }
        public void deletePayment(Payment p){
                payments.remove(p);
        }
        public Status addOrder(Order o){

                if (o==null)
                        return Status.failure;
                if (orders.contains(o))
                        return Status.failure;
                orders.add(o);
                return Status.success;

        }
        public void deleteOrder(Order o){
                orders.remove(o);
        }




        }
