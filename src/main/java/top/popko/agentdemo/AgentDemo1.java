package top.popko.agentdemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.instrument.Instrumentation;

public class AgentDemo1 {
//    public static void premain(String agentArgs, Instrumentation inst) {
//        System.out.println("static agent,,,");
//        init(agentArgs, inst);
//    }
    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new PatchTransformer());
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("dynamic agent,,,");
        init(agentArgs, inst);
    }

    public static void init(String agentArgs, Instrumentation inst) {
        System.out.println("agentArgs : " + agentArgs);

        Thread t = new Thread(new MyRunnable(agentArgs));
        t.start(); // 启动新线程
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(new FlagServer().getFlag());
//        System.exit(1);//强制退出,防止hook卡住

//        DefineTransformer defineTransformer = new DefineTransformer();
//        inst.addTransformer(defineTransformer, true);
//        defineTransformer.retransform(inst);//添加DefineTransformer后retransform重新加载类会经过DefineTransformer
    }


}
class MyRunnable implements Runnable {
    private String jsScriptName;
    MyRunnable(String s){
        jsScriptName = s;
    }
    public static String getpid() {
        String name = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        return pid;
    }

    @Override
    public void run() {
        try {
            //linux
//            String cmd = "python3 exp.py " + getpid() + jsScriptName;
//            String cmd = String.format("python3 exp.py %s %s",getpid(),jsScriptName);
            //windows
            String cmd = String.format("python exp.py %s %s",getpid(),jsScriptName);
//            String cmd = "python E:\\myWSL\\pytool\\fridahook\\windows\\exp.py " + getpid();
            System.out.println(cmd);
//            Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(cmd).getInputStream(), "gbk"));
            String line = null;
            while (true) {
                if (!((line = reader.readLine()) != null)) break;
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}