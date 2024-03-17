# Java Agent Template 

# 一、这是什么？

一个Java Agent的模板仓库，用于快速开始Java Agent类应用的开发。

# 二、如何使用？

## Step1： “Use Template”

以当前仓库为模板创建自己的仓库。

## Step2： 修改逻辑

`com.github.java.sec.agent.template.AgentMain`是整个程序的主入口，在此处修改添加自己的`ClassFileTransformer`。

`com.github.java.sec.agent.template.tramsformer.FooTransformer`是用来对类做转换处理的`ClassFileTransformer`，您可以在此扩展自己的逻辑或者创建自己的`ClassFileTransformer`。

其它部分基本与一般Java应用差不多，不再详述。

## Step3：打包

在`resources/META-INF/MANIFEST.MF`中存放着一些打包运行配置，根据自己的情况修改：

```
Manifest-Version: 1.0
Premain-Class: com.github.java.sec.agent.template.AgentMain
Agent-Class: com.github.java.sec.agent.template.AgentMain
Main-Class: com.github.java.sec.agent.template.AgentMain
Can-Redefine-Classes: true
Can-Retransform-Classes: true

```

然后打包即可：

```
mvn package
```

## Step4：运行

支持三种运行方式：

- 以`-javaagent`方式运行
- 以`attach`方式注入
- 以`java -jar`方式运行 

```bash
java -javaagent:java-agent-template-1.0-SNAPSHOT.jar -jar java-agent-template-1.0-SNAPSHOT.jar
```









































