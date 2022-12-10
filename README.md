pom.xml 配置 tools_slim.jar

tools.jar 缩小 ->tools_slim.jar

除了使用到的class和META-INF，其他全部删除

```
com.sun.tools.attach.*
com.sun.tools.attach.spi.*
sun.jvmstat.*
sun.tools.attach.*
```