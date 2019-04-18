package mybus.com.myeventbus.bus;

/**
 * author: wyj
 * Date:2019/4/18
 * Description
 * 线程模式
 */
public enum  ThreadMode {
    POSTING,    //事件的处理和事件的发送在相同的进程
    MAIN, //主线程
    BACKROUND, //后台线程
    ASYNC //异步
}
