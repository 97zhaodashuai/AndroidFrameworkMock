package com.mufasa.zhaodsh.andframemock.Service.AMS;

import com.mufasa.zhaodsh.andframemock.App.IApplicationThreadM;
import com.mufasa.zhaodsh.andframemock.IntentM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public abstract  class IActivityManagerServiceM extends IBinderM {

    public abstract void attachApplication(IApplicationThreadM applicationThreadM);

    public abstract void startActivity(IApplicationThreadM callingPid, IBinderM resultTo, IntentM intent);


}
