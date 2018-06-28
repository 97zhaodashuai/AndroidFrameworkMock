package com.mufasa.zhaodsh.andframemock.App;

import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public class ReceiverDispatcher  extends IBinderM{

    public BroadcastReceiverM  receiverM;
    public String action;
    public void performReceive(){
        receiverM.onReceive();
    }
}
