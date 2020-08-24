package com.study;

import sun.misc.Unsafe;

/**
 * @version 1.0
 * @since 2020/8/20
 **/
public class UnsafeClient {

    public static void main(String[] args) throws Exception {

        Unsafe unsafe = Unsafe.getUnsafe();

        System.out.println(unsafe);
    }
}
