package com.mufasa.zhaodsh.andframemock.Service.WMS;

public class WindowStateM {

    IWindowM mClient;
    AppWindowToken windowToken;
    public WindowStateM( IWindowM c, AppWindowToken token) {
        mClient = c;
        windowToken = token;
    }


}
