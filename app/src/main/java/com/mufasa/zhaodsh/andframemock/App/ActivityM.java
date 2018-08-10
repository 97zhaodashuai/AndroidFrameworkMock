package com.mufasa.zhaodsh.andframemock.App;

import android.os.Bundle;

import com.mufasa.zhaodsh.andframemock.IntentM;
import com.mufasa.zhaodsh.andframemock.Service.AMS.IActivityManagerServiceM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;
import com.mufasa.zhaodsh.andframemock.Service.ServiceManager;

public class ActivityM {

    private IBinderM  mToken;
    PhoneWindowM mWindow;
    public void attach(IBinderM token){
        mWindow = new PhoneWindowM();
        mToken = token;
    }

    public void onCreate(Bundle instanceState){
        ViewM v = new ViewM();
        setContentView(v);
    }

    public void onStart(){

    }

    public void onResume() {
    }

    public void onPause(){

    }

    public void onStop(){

    }

    public void onRetoreInstanceState(Bundle state){

    }

    public void onSaveInstanceState(Bundle state){

    }



    public void setContentView(ViewM v){
        mWindow.setContentView(v);
    }

    public void startActivity(IntentM it){
        IActivityManagerServiceM  AMS = (IActivityManagerServiceM)ServiceManager.getService("ActivityManagerService");
        AMS.startActivity(mToken, it);

    }


    public void registerReceiver(BroadcastReceiverM  receiverM, String action){




    }


}
