Cmd
=

- Cmd is an open-source library licensed under Apache License, Version 2.0

- Using this library you will be able to execute bash/shell commands easily and get the output in plain text.

- You can also convert the output easily using the same library.

Usage:
=

- Check root access:

```java
Cmd.root();
```

- No root:

```java
Cmd.SH.ex(String command);

Cmd.SH.ex(String[] commands);

Cmd.SH.ex(List<String> commands);


Cmd.SH.ex(String command, boolean STDERR);

Cmd.SH.ex(String[] commands, boolean STDERR);

Cmd.SH.ex(List<String> commands, boolean STDERR);
```

- With root access:

```java
Cmd.SU.ex(String command);

Cmd.SU.ex(String[] commands);

Cmd.SU.ex(List<String> commands);


Cmd.SU.ex(String command, boolean STDERR);

Cmd.SU.ex(String[] commands, boolean STDERR);

Cmd.SU.ex(List<String> commands, boolean STDERR);
```

- Converting output (String by default):

```java
String output = Cmd.SU.ex(String command);

String[] outputArray = Convert.string2array(output);

List<String> outputArray = Convert.string2list(output);
```
