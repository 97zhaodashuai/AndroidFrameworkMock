package com.mufasa.zhaodsh.andframemock.Service.WMS;

import com.mufasa.zhaodsh.andframemock.App.IWindowSeesionM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public abstract  class IWindowManagerServiceM extends IBinderM {
    public abstract IWindowSeesionM openSession();

}
