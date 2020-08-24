package com.study.mem.dummy;

import com.study.mem.Uns;

import java.nio.ByteBuffer;

/**
 * @version 1.0
 * @since 2020/8/24
 **/
public class DummyStruct {

    //记录c端结构体里各个字段的偏移量
    private final static int OffsetMeta1;
    private final static int OffsetMeta2;
    private final static int OffsetMeta3;
    private final static int OffsetDataLen;
    private final static int OffsetData;

    private final long addr;

    static {
        OffsetMeta1 = DummyLibJni.offset_meta1();
        OffsetMeta2 = DummyLibJni.offset_meta1();
        OffsetMeta3 = DummyLibJni.offset_meta1();
        OffsetDataLen = DummyLibJni.offset_data_len();
        OffsetData = DummyLibJni.offset_data();
    }

    DummyStruct(long addr) {
        this.addr = addr;
    }

    public int getMeta1() {
        return Uns.getInt(addr, OffsetMeta1);
    }

    public int getMeta2() {
        return Uns.getInt(addr, OffsetMeta2);
    }

    public int getMeta3() {
        return Uns.getInt(addr, OffsetMeta3);
    }

    public int getDataLen() {
        return Uns.getInt(addr, OffsetDataLen);
    }

    public ByteBuffer data() {
        return Uns.directBufferFor(Uns.getLong(addr, OffsetData), 0, getDataLen(), true);
    }

    public void release() {
        DummyLibJni.freeMem(addr);
    }
}
