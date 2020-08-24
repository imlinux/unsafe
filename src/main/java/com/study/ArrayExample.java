package com.study;

import core.Tools;
import sun.misc.Unsafe;

import java.util.Arrays;

/**
 * @version 1.0
 * @since 2020/8/24
 **/
public class ArrayExample {

    public static void main(String[] args) throws Exception {

        Unsafe unsafe = Tools.getUnsafe();

        int[] data = new int[10];
        //获取数组的偏移量
        int baseOffset = unsafe.arrayBaseOffset(int[].class);
        //获取数组每一个元素的大小
        int scale = unsafe.arrayIndexScale(int[].class);

        //设置数组第一个元素的值
        unsafe.putOrderedInt(data, baseOffset + scale, 10000);

        System.out.println(Arrays.toString(data));
    }
}
