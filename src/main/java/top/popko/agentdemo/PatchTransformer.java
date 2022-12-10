package top.popko.agentdemo;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;

public class PatchTransformer implements ClassFileTransformer {
    public PatchTransformer() {
    }

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//        String clazzName = "top/popko/agentdemo/Run1";
//        String methodName = "out";
//        String methodBody = "{return \"[patch]cmd:\"+$1;}";
        String clazzName = "jadx/core/codegen/ClassGen";
        String methodName = "addClassBody";
        String methodBody = "{        $1.add('{');\n"
                + "        if ($2 && this.cls.checkCommentsLevel(jadx.api.CommentsLevel.INFO)) {\n"
                + "            $1.add(\" // from class: \" + this.cls.getClassInfo().getFullName());\n"
                + "        }\n"
                + "        this.setBodyGenStarted(true);\n"
                + "        this.clsDeclOffset = $1.getLength();\n"
                + "        $1.incIndent();\n"
                + "        this.addInnerClsAndMethods($1);\n"
                + "        this.addFields($1);\n"
                + "        $1.decIndent();\n"
                + "        $1.startLine('}');\n"
                + "        $1.attachAnnotation(jadx.api.metadata.annotations.NodeEnd.VALUE);"
                + "}";
        if (!className.equals(clazzName)) {
            return null;
        } else {
            String clsName = className.replace("/", ".");
            CtClass cls = null;

            try {
                cls = ClassPool.getDefault().get(clsName);
//                CtMethod mth = cls.getDeclaredMethod(methodName);
                CtMethod mth = cls.getDeclaredMethod(methodName,new CtClass[]{ClassPool.getDefault().get("jadx.api.ICodeWriter"),ClassPool.getDefault().get("boolean")});
                // 方法体内容代码 $1代表第一个参数，$2代表第二个参数
                mth.setBody(methodBody);
                System.out.println("patch success++");
                return cls.toBytecode();
            } catch (CannotCompileException | IOException | NotFoundException var9) {
                var9.printStackTrace();
                return null;
            }
        }
    }
}
