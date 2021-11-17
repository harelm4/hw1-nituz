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
        protected Stack<Order> orders=new Stack<Order>();
        protected ArrayList<Payment> payments = new ArrayList<Payment>();
        protected Customer customer;
        protected ShoppingCart shoppingCart;


        @Override
        public String toString() {
                String p="";
                for (Payment payment: payments)
                        p+=payment.getId()+",";

                return "Account{" +
                        "id='" + id + '\'' +
                        ", billing_address='" + billing_address + '\'' +
                        ", is_closed=" + is_closed +
                        ", open=" + open +
                        ", closed=" + closed +
                        ", balance=" + balance +
                        ", orders=" + orders +
                        ", payments=" + p+
                        ", customer=" + customer.getId() +
                        ", shoppingCart=" + shoppingCart +
                        '}';
        }


        /**
         * no need for orders & payments.
         * no need to get customer & shoppingCart because of the 1:1 associations, based on the instructions given
         * @param id
         * @param billing_address
         * @param is_closed
         * @param open
         * @param closed
         * @param balance
         */
        public Account(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance) {
                this.id = id;
                this.billing_address = billing_address;
                this.is_closed = is_closed;
                this.open = open;
                this.closed = closed;
                this.balance = balance;
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

        public void remove()
        {
                customer.accountWasDeleted(this);
                customer=null;
                shoppingCart.delAccount(this);
                shoppingCart=null;

                int i=0;
                for (i=0; i<orders.size(); i++)
                {
                        orders.get(i).accountWasDeleted();
                }
                orders=null;

                int j=0;
                for (j=0; j<payments.size(); j++)
                {
                        payments.get(i).accountWasDeleted();
                }
                payments=null;

        }

        public void shoppingCartWasDeleted() {
                customer.accountWasDeleted(this);
                customer=null;
                shoppingCart=null;

                int j=0;
                for (j=0; j<payments.size(); j++)
                {
                        payments.get(j).removeAccount();
                }
                payments=null;

                int i=0;
                for (i=0; i<orders.size(); i++)
                {
                        orders.get(i).removeAccount();
                }
                orders=null;



        }

        public void customerWasDeleted(){
                customer=null;
                shoppingCart.delAccount(this);
                shoppingCart=null;

                int j=0;
                for (j=0; j<payments.size(); j++)
                {
                        payments.get(j).accountWasDeleted();
                }
                payments=null;

                int i=0;
                for (i=0; i<orders.size(); i++)
                {
                        orders.get(i).accountWasDeleted();
                }
                orders=null;



        }

        public void deleteOrder(Order o){
                orders.remove(o);
        }

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


}
