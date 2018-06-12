package com.mufasa.zhaodsh.andframemock.App;

import com.mufasa.zhaodsh.andframemock.Service.IBinderM;

public abstract class IApplicationThreadM  extends IBinderM {
    public abstract void scheduleLaunchActivity(IBinderM  binder );
    public abstract void schedulePauseActivity(IBinderM  binder );
}
