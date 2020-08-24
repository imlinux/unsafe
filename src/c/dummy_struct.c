#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>
#include <string.h>
#include "com_study_mem_dummy_DummyLibJni.h"

typedef struct {

    //一些元数据或键值对
    int meta1;
    int meta2;
    int meta3;

    //大块的数据
    int data_len;
    char *data;

} DummyStruct;


JNIEXPORT jlong JNICALL Java_com_study_mem_dummy_DummyLibJni_callDummyApi
  (JNIEnv *env, jclass class) {

    int struct_size = sizeof(DummyStruct);

    DummyStruct *ret = (DummyStruct*)malloc(struct_size + 100);

    ret->meta1 = 100;
    ret->meta2 = 200;
    ret->meta3 = 300;
    ret->data_len = 100;
    ret->data = ((char*)ret) + 100;

    strncpy(ret->data, "hello", 6);

    return (jlong)ret;
}


JNIEXPORT jint JNICALL Java_com_study_mem_dummy_DummyLibJni_offset_1meta1
  (JNIEnv *env, jclass class) {
    return (jint)offsetof(DummyStruct, meta1);
}

JNIEXPORT jint JNICALL Java_com_study_mem_dummy_DummyLibJni_offset_1meta2
  (JNIEnv *env, jclass class) {
    return (jint)offsetof(DummyStruct, meta1);
}

JNIEXPORT jint JNICALL Java_com_study_mem_dummy_DummyLibJni_offset_1meta3
  (JNIEnv *env, jclass class) {
    return (jint)offsetof(DummyStruct, meta1);
}

JNIEXPORT jint JNICALL Java_com_study_mem_dummy_DummyLibJni_offset_1data_1len
  (JNIEnv *env, jclass class) {
    return (jint)offsetof(DummyStruct, data_len);
}

JNIEXPORT jint JNICALL Java_com_study_mem_dummy_DummyLibJni_offset_1data
  (JNIEnv *env, jclass clazz) {
  return (jint)offsetof(DummyStruct, data);
}

JNIEXPORT void JNICALL Java_com_study_mem_dummy_DummyLibJni_freeMem
  (JNIEnv *env, jclass class, jlong addr) {
  free((void*)addr);
}