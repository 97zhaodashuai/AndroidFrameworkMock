package com.mufasa.zhaodsh.andframemock.App;

import com.mufasa.zhaodsh.andframemock.IntentM;
import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public abstract class IApplicationThreadM  extends IBinderM {
    public abstract void scheduleLaunchActivity(IBinderM  binder );
    public abstract void schedulePauseActivity(IBinderM  binder );
    public abstract void scheduleCreateService(IBinderM binder);
    public abstract void scheduleBindService(IBinderM binder);
    public abstract  void scheduleRegisteredReceiver(IBinderM binder);
}
