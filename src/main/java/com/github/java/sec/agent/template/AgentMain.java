package com.github.java.sec.agent.template;

import com.github.java.sec.agent.template.tramsformer.FooTransformer;
import com.sun.tools.attach.VirtualMachine;

import java.lang.instrument.Instrumentation;

/**
 * agent的入口
 *
 * @author CC11001100
 */
public class AgentMain {

    /**
     * jvm启动的时候以 -javaagent:xxx.jar 方式启动时的agent main入口
     *
     * @param agentArgs
     * @param instrumentation
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("premain");
        initTransformer(instrumentation);
    }

    /**
     * jvm启动后以attach方式启动时的agent main入口
     *
     * @param agentArgs
     * @param instrumentation
     */
    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("agentmain");
        initTransformer(instrumentation);
    }

    private static void initTransformer(Instrumentation instrumentation) {
        instrumentation.addTransformer(new FooTransformer());
    }

    /**
     * java -jar foo-agent.jar 这种方式直接运行的时候执行的入口方法
     *
     * @param args pid 传入要attach的jvm pid
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            printUsage();
            return;
        }

        try {
            VirtualMachine vm = VirtualMachine.attach(args[0]);
            String agentJarPath = AgentMain.class.getProtectionDomain().getCodeSource().getLocation().toString();
            vm.loadAgent(agentJarPath);
            vm.detach();
            System.out.println("Attach done.");
        } catch (Exception e) {
            e.printStackTrace();
            printUsage();
        }

    }

    /**
     * 打印jar方式启动的时候的用法
     */
    private static void printUsage() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usage: java -jar xxx.jar [pid]\n");
        sb.append("Example: java -jar xxx.jar 10086\n");
        System.out.println(sb);
    }

}
