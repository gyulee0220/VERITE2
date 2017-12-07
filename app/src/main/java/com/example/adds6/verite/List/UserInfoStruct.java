package com.example.adds6.verite.List;

/**
 * Created by adds6 on 2017-12-07.
 */

public class UserInfoStruct {
    public static String UserEmail = null;
    public static int UserNumber = 0;
    public static String UserPassword = null;
    public static String UserName = null;

    public UserInfoStruct(String email, int number, String password, String name){
        this.UserEmail = email;
        this.UserNumber =  number;
        this.UserPassword = password;
        this.UserName = name;
    }
}
