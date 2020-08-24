package core;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * @author dong
 * @since 18-10-30
 */
public class Tools {


    public static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);

            return unsafe;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static long sizeOf(Object o) throws Exception {
        Unsafe u = getUnsafe();
        HashSet<Field> fields = new HashSet<Field>();
        Class c = o.getClass();
        while (c != Object.class) {
            for (Field f : c.getDeclaredFields()) {
                if ((f.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(f);
                }
            }
            c = c.getSuperclass();
        }

        // get offset
        long maxSize = 0;
        for (Field f : fields) {
            long offset = u.objectFieldOffset(f);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }

        return ((maxSize/8) + 1) * 8;   // padding
    }

    private static long normalize(int value) {
        if(value >= 0) return value;
        return (~0L >>> 32) & value;
    }


    public static long toAddress(Object obj) throws Exception {
        Object[] array = new Object[] {obj};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        return normalize(getUnsafe().getInt(array, baseOffset));
    }

    public static Object fromAddress(long address) throws Exception {
        Object[] array = new Object[] {null};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        getUnsafe().putLong(array, baseOffset, address);
        return array[0];
    }

}
