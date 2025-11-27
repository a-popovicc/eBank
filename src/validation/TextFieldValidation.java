package validation;

import user.User;

public class TextFieldValidation {

   public static boolean validateName(String name) {
       if(name.isEmpty() || name==null){
           return false;
       }
       if(name.length()<3){
           return false;
       }
       return true;
   }
   public static String turnFirstLetterToUpperCase(String name){

       return name.substring(0, 1).toUpperCase() + name.substring(1);
   }
   public static boolean validateEmail(String email){
       if(email.isEmpty() || email==null){
           return false;
       }
       String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

       return email.matches(emailRegex);
   }
   public static boolean validatePassword(String password){
       if(password.isEmpty() || password==null){
           return false;
       }
       if(password.length()<6 || notDigits(password)<1){
           return false;
       }
       return true;
   }
   public static int notDigits(String string){
       if(string.isEmpty() || string==null){
           return 0;
       }
       int counter = 0;
       for(int i=0; i<string.length(); i++){
           if(!Character.isDigit(string.charAt(i))){
               counter++;
           }
       }
       return counter;
   }
   public static boolean confirmPassword(String password, String confirmPassword){
       if(password.equals(confirmPassword)){
           return true;
       }
       return false;
   }
   public static boolean validateAmount(String amount){
       if(amount==null || amount.isEmpty()){
           return false;
       }
      for(int i=0; i<amount.length(); i++){
          if(!Character.isDigit(amount.charAt(i))){
              return false;
          }
      }
      return true;
   }

}
