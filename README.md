Cmd
=

- Cmd is an open-source library licensed under Apache License, Version 2.0

- Using this library you will be able to execute bash/shell and even Java commands with root access easily and get the output in different formats.


Usage:
=

- Check root access:

```java
Cmd.root();
```

- No root:

```java
Cmd.SH.ex(String command, String... args);

Cmd.SH.ex(String[] commands);

Cmd.SH.ex(List<String> commands);
```

- With root access:

```java
Cmd.SU.ex(String command, String... args);

Cmd.SU.ex(String[] commands);

Cmd.SU.ex(List<String> commands);

JavaRoot java = JavaRoot.newInstance(String packageName, String className, boolean isSystemApp);
java.execute(String... args);
java.executeInBackground(String... args);
```

All previous methods aside of the last one:
```java
java.executeInBackground(String... args);
```
return an instance of type Output that has the following methods:
```java
boolean success()			//returns true if process exit value = 0 else false
String getString()			//returns output in String format
String[] getArray()			//returns output in String Array format
List<String> getList()		//returns output in String List format
int getExitValue()			//returns process exit value
String toString()			//returns process status and output in String format
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
