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

        public void addPayment(Payment p){

        }
        public void deletePayment(Payment p){

        }


        }
