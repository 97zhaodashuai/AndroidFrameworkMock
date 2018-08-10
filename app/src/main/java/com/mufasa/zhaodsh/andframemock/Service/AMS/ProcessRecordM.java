package com.mufasa.zhaodsh.andframemock.Service.AMS;

import com.mufasa.zhaodsh.andframemock.App.IApplicationThreadM;

import java.util.List;

public class ProcessRecordM {
    public IApplicationThreadM  thread;
    public String ProcessName;
    public boolean persistent;
    public int  pid;

    public List<ReceiverListM> receivers;


}
