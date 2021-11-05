public class Main {
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
    }
}
