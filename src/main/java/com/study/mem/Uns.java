package com.study.mem;

import core.Tools;
import sun.misc.Unsafe;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @version 1.0
 * @since 2020/8/24
 **/
public class Uns {

    private static final Unsafe unsafe = Tools.getUnsafe();

    private static final Class<?> DIRECT_BYTE_BUFFER_CLASS;
    private static final Class<?> DIRECT_BYTE_BUFFER_CLASS_R;
    private static final long DIRECT_BYTE_BUFFER_ADDRESS_OFFSET;
    private static final long DIRECT_BYTE_BUFFER_CAPACITY_OFFSET;
    private static final long DIRECT_BYTE_BUFFER_LIMIT_OFFSET;

    static {
        try
        {
            ByteBuffer directBuffer = ByteBuffer.allocateDirect(0);
            ByteBuffer directReadOnly = directBuffer.asReadOnlyBuffer();
            Class<?> clazz = directBuffer.getClass();
            Class<?> clazzReadOnly = directReadOnly.getClass();
            DIRECT_BYTE_BUFFER_ADDRESS_OFFSET = unsafe.objectFieldOffset(Buffer.class.getDeclaredField("address"));
            DIRECT_BYTE_BUFFER_CAPACITY_OFFSET = unsafe.objectFieldOffset(Buffer.class.getDeclaredField("capacity"));
            DIRECT_BYTE_BUFFER_LIMIT_OFFSET = unsafe.objectFieldOffset(Buffer.class.getDeclaredField("limit"));
            DIRECT_BYTE_BUFFER_CLASS = clazz;
            DIRECT_BYTE_BUFFER_CLASS_R = clazzReadOnly;
        }
        catch (NoSuchFieldException e)
        {
            throw new Error(e);
        }
    }

    public static ByteBuffer directBufferFor(long address, long offset, long len, boolean readOnly) {
        if (len > Integer.MAX_VALUE || len < 0L)
            throw new IllegalArgumentException();
        try
        {
            ByteBuffer bb = (ByteBuffer) unsafe.allocateInstance(readOnly ? DIRECT_BYTE_BUFFER_CLASS_R : DIRECT_BYTE_BUFFER_CLASS);
            unsafe.putLong(bb, DIRECT_BYTE_BUFFER_ADDRESS_OFFSET, address + offset);
            unsafe.putInt(bb, DIRECT_BYTE_BUFFER_CAPACITY_OFFSET, (int) len);
            unsafe.putInt(bb, DIRECT_BYTE_BUFFER_LIMIT_OFFSET, (int) len);
            bb.order(ByteOrder.BIG_ENDIAN);
            return bb;
        }
        catch (Error e)
        {
            throw e;
        }
        catch (Throwable t)
        {
            throw new RuntimeException(t);
        }
    }


    public static ByteBuffer valueBufferR(long hashEntryAdr, long valueLen) {
        return Uns.directBufferFor(hashEntryAdr , 0, valueLen, true);
    }

    public static ByteBuffer valueBuffer(long hashEntryAdr, long valueLen)
    {
        return Uns.directBufferFor(hashEntryAdr , 0, valueLen, false);
    }

    public static int getInt(long address, long offset) {
        return unsafe.getInt(null, address + offset);
    }

    public static long getLong(long address, long offset) {
        return unsafe.getLong(null, address + offset);
    }
}
