# PALab12

## Reflection
Create an application to analyze and test java classes.
The application will receive as input java classes and it will display their prototypes and perform the tests specified by the @Test annotation.

The main specifications of the application are:

## Compulsory
- The input will be a .class file, located anywhere in the file system.
- Load the specified class in memory, identifying dynamically its package.
- Using reflection, extract as many information about the class (at least its methods).
- Using reflection, invoke the static methods, with no arguments, annotated with @Test.

## Optional
- The input may be a folder (containing .class files) or a .jar. You must explore it recursively.
- Create the complete prototype, in the same manner as javap tool.
- Identify all public classes annotated with @Test and invoke the methods annotated with @Test, whether static or not.

If a method requires primitive (at least int) or String arguments, generate mock values for them.
- Print a statistics regarding the tests.

## Bonus
- Consider the case when the input files are .java files and compile the source code before analyzing them. (use Java Compiler, for example).
- Using additional annotations, implement non-functional tests over the methods in order to test their reliability and efficiency.
- Use a bytecode manipulation and analysis framework, such as ASM, BCEL or Javassist in order to extract the bytecode of the class, perform bytecode instrumentation (inject code in some method) and generate dynamically a class.
