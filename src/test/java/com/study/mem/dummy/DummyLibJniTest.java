package com.study.mem.dummy;

import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 * @author 董帅阳
 * @version 1.0
 * @since 2020/8/24
 **/
public class DummyLibJniTest {

    @Test
    public void t() {
        long addr = DummyLibJni.callDummyApi();

        DummyStruct dummyStruct = new DummyStruct(addr);

        System.out.println(dummyStruct.getMeta1());

        ByteBuffer byteBuffer = dummyStruct.data();

        byte[] data = new byte[6];
        byteBuffer.get(data);
        System.out.println(new String(data));

        dummyStruct.release();
    }
}