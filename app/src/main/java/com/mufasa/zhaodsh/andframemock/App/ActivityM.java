package com.mufasa.zhaodsh.andframemock.App;

public class ActivityM {

    PhoneWindowM mWindow;
    public void attach(){
        mWindow = new PhoneWindowM();

    }


    public void onCreate(){
        ViewM v = new ViewM();
        setContentView(v);
    }




    public void setContentView(ViewM v){
        mWindow.setContentView(v);
    }


    public void onResume() {

    }

}
