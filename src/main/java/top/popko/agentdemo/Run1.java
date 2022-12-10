package top.popko.agentdemo;

class xxx{
    public void xxx(){
        System.out.println("xxx");
    }
}
class ICodeWriter{

    public void add(char c) {
        System.out.println("[]add:");
        System.out.println(c);
    }
}
public class Run1 {
    public String cls;
    public String out(String cmd){
        System.out.println("cmd:"+cmd);
        return "[return]cmd:"+cmd;
    }
    public void addFields(ICodeWriter flag){
        System.out.println("[2]addFields");
        System.out.println(flag);
    }
    public void setBodyGenStarted(boolean flag){
        System.out.println("[1]setBodyGenStarted");
    }
    public void addClassBody(ICodeWriter clsCode, boolean printClassName) {
        clsCode.add('{');
        System.out.println(printClassName);
        System.out.println(this.cls);
        this.setBodyGenStarted(true);
        this.addFields(clsCode);
        System.out.println(new xxx());
    }
    public static void main(String[] args) throws Exception {
        Run1 run1 = new Run1();
        run1.cls = "ccc";
//        System.out.println(run1.out("qwe"));
        run1.addClassBody(new ICodeWriter(),false);
    }
}
