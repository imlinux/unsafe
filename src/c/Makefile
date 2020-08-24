JAVA_SRC=../../src/main/java/

com_study_mem_dummy_DummyLibJni.h:
	${JAVA_HOME}/bin/javah -cp ${JAVA_SRC} -d ./ com.study.mem.dummy.DummyLibJni

main: com_study_mem_dummy_DummyLibJni.h
	gcc -D_REENTRANT -fPIC -shared \
		-I ${JAVA_HOME}/include/ \
		-I ${JAVA_HOME}/include/linux/ \
		dummy_struct.c -o libdummy.so
