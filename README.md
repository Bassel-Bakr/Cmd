Cmd
=

- Cmd is an open-source library licensed under Apache License, Version 2.0

- Using this library you will be able to execute bash/shell and even Java commands with root access easily and get the output in different formats.


Usage:
=

- Check root access:

```java
Cmd.SU.root();
```

- No root:

```java
Cmd.SH.ex(T cmd, U... args);
```

cmd is our command
args can be:
	cmd formating args => must be right after cmd
	bool redirectErrorStream => default: true
	File path => current path
	ILineListener => listens to new lines
	IResultListener => fired at the very end

- With root access:

```java
Cmd.SU.ex(T cmd, U... args);

JavaRoot java = JavaRoot.newInstance(String packageName, String className, boolean isSystemApp);
java.execute(String... args);
java.executeInBackground(String... args);
```

All previous methods aside of the last one:
```java
java.executeInBackground(String... args);
```
return an instance of type ShellResult that has the following methods:
```java
PrintWriter setWriter     //INTERACTIVE(O) sets process's PrintWriter
void setWriter            //INTERACTIVE(O) returns process's PrintWriter
String getString()        //INTERACTIVE(X) returns output in String format
String[] getArray()       //INTERACTIVE(X) returns output in String Array format
List<String> getList()    //INTERACTIVE(X) returns output in String List format
int getExitValue()        //INTERACTIVE(X) returns process exit value
```


- Converting:

```java
String string = ...;
String[] array = ...;
List<String> list = ...;

String s1 = Convert.array2string(array);
String s2 = Convert.list2string(list);

String[] a1 = Convert.list2array(list);
String[] a2 = Convert.string2array(string);

List<String> l1 = Convert.array2list(array);
List<String> l2 = Convert.string2list(string);
```
