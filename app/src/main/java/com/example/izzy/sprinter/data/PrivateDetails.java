package com.example.izzy.sprinter.data;

/**
 * Created by izzy on 22/05/15.
 */
public class PrivateDetails {
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean[] isSportChosenArray = new Boolean[Constants.SPORTS_NUMBER];
    private String uniqueKey;

//   UUID uniqueKey1;
    public PrivateDetails(String userName1,String password1,String email1,String firstName1,
    String lastName1, Boolean[] isSportChosenArray1,String uniqueKey1)
    {
        userName = userName1;
        password = password1;
        email = email1;
        firstName = firstName1;
        lastName = lastName1;
        isSportChosenArray = isSportChosenArray1;
        uniqueKey =uniqueKey1;

    }

   // TODO need to create getters and setters for all private fields

   public String getUsername()
   {
       return userName;

   }
    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;

    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return  lastName;
    }

    public Boolean[] getIsSportChosenArray()
    {
        return  isSportChosenArray;
    }

    public String getUniqueKey()
    {
        return uniqueKey;
    }


}
