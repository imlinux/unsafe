package com.study.mem.dummy;

/**
 * @version 1.0
 * @since 2020/8/24
 **/
public class DummyLibJni {

    static {
        //改为自己环境的路径
        System.load("/path/libdummy.so");
    }

    public native static int offset_meta1();
    public native static int offset_meta2();
    public native static int offset_meta3();
    public native static int offset_data();
    public native static int offset_data_len();
    public native static void freeMem(long addr);

    /**
     * 调用dummy api
     * @return 内存指针
     */
    public native static long callDummyApi();
}
