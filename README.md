# agent_jadxjar
android反编译行号不对，jadx反编译有行号信息

PatchTransformer修改jadx反编译输出java文件的内容顺序，把变量放到最后，方便手动/脚本使行号对齐

pom.xml 配置 tools_slim.jar

tools.jar 缩小 ->tools_slim.jar

除了使用到的class和META-INF，其他全部删除

```
com.sun.tools.attach.*
com.sun.tools.attach.spi.*
sun.jvmstat.*
sun.tools.attach.*
```