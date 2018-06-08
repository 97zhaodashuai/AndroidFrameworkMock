package com.mufasa.zhaodsh.andframemock.App;

public class PhoneWindowM {

    public ViewM mDecor;

    public void setContentView(ViewM v){
        if(mDecor == null){
            installDecorView();
        }
    }

    private void installDecorView() {
        mDecor = new ViewM();
    }






}
