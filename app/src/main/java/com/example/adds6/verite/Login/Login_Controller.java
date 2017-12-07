package com.example.adds6.verite.Login;

import com.example.adds6.verite.Main_Activity;

/**
 * Created by adds6 on 2017-12-06.
 */

public class Login_Controller {

    // Main_Activity 객체 상속 받기
    protected static Main_Activity main = new Main_Activity();

    public Login_Controller(Main_Activity main_activity) {
        this.main = main_activity;
    }

    public void loadUserInfo(){
        UserAdapter userAdapter = new UserAdapter();
        userAdapter.Init(main);
        userAdapter.execute("http://13.125.66.109/Real_Usertable.php");
    }

}
