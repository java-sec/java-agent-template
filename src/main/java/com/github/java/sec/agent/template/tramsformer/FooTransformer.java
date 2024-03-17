package com.github.java.sec.agent.template.tramsformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 这里是一个Transformer的例子
 *
 * @author CC11001100
 */
public class FooTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        // TODO 在这里编写你的agent transformer逻辑
        System.out.println("Load class " + className);

        return classfileBuffer;
    }

}
