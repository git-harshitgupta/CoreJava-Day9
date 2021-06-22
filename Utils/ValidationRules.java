package Utils;

import java.text.ParseException;
import java.util.Date;
import com.app.core.Customer;
import static com.app.core.Customer.sdf;
import exeception.CustomerHandlingException;
public class ValidationRules {
    private static Date tempDate;
    static {
        try {
            tempDate = sdf.parse("1-1-1995");
        }
        catch (ParseException e) {
            System.out.println("Error in static date block " + e);
        }
    }
    public static Date convertDate(String date) throws ParseException, CustomerHandlingException {
       Date d1 = sdf.parse(date);
        if(d1.before(tempDate)){
            throw new CustomerHandlingException("Your age is not valid");
        }
        return d1;
    }
    public static String validateEmail(String email, Customer[] customer) throws CustomerHandlingException {

        for(Customer test : customer){
            if(test!=null)
                if(email.equals(test.getEmailAddress())){
                    throw new CustomerHandlingException("Email address already present");
                }
        }

        if(email.contains("@")&&email.contains(".com")){
            return email;
        }
        else
            throw new CustomerHandlingException("Invaild email entered");
    }

    public static String validatePassword(String password) throws CustomerHandlingException {
        if(password.length()>=3&&password.length()<=9){
            return password;
        }
        else
            throw new CustomerHandlingException("Entered password is to short");
    }
    public static boolean validateEmailAndPassword(String email, String password,Customer[] arr) throws CustomerHandlingException {
        boolean emailAndPassword = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                if (arr[i].getEmailAddress().equals(email) && arr[i].getPassword().equals(password))
                   return true;
                emailAndPassword = true;
            }
        }
        if (emailAndPassword) {
            throw new CustomerHandlingException("Invalid email and password");
        }
        return false;
    }
    public static boolean validateAdhar(String email, String password,Customer[] arr) throws CustomerHandlingException {
        for (Customer temp : arr){
            if(temp!=null) {
                if (temp.getEmailAddress().equals(email) && temp.getPassword().equals(password)) {
                    if (temp.getAdharNumber() == null)
                        throw new CustomerHandlingException("Aadhar not linked");
                    else
                        return true;
                }
            }
        }
        return false;
    }

    public static void changePassword(String email, String password,String newPassword,Customer[] customer) throws CustomerHandlingException {
        for(Customer temp : customer){
            if(temp!=null) {
                if (temp.getEmailAddress().equals(email) && temp.getPassword().equals(password)) {
                    temp.setPassword(validatePassword(newPassword));
                }
            }
        }
    }

    public static void setAadhar(String email, String password,Customer[] arr,String aadharNumber){
        for(Customer temp : arr){
            if(temp!=null) {
                if (temp.getEmailAddress().equals(email) && temp.getPassword().equals(password)) {
                    temp.setAdharNumber(aadharNumber);
                }
            }

        }
    }




}
