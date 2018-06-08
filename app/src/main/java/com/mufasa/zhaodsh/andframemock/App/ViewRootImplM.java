package com.mufasa.zhaodsh.andframemock.App;

import com.mufasa.zhaodsh.andframemock.Service.IBinderM;
import com.mufasa.zhaodsh.andframemock.Service.ServiceManager;
import com.mufasa.zhaodsh.andframemock.Service.WMS.IWindowManagerServiceM;

public class ViewRootImplM {

    private W mWindow;
    private ViewM view;

    public static IWindowSeesionM sWindowSeesion;

    public ViewRootImplM(){
        mWindow  = new W();
        getWindowSeesion();
    }

    private void getWindowSeesion(){
        sWindowSeesion = ((IWindowManagerServiceM)ServiceManager
                .getService("WindowManagerServie"))
                .openSession();
    }

    public  class W  extends IBinderM {

    }

    public  void setView(ViewM view ){
        sWindowSeesion.addWindow(mWindow);
    }






}
