import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;

public class ClassTester {
    private static List<String> classPathList;

    static {
        classPathList = new LinkedList<>();
    }

    public static void addClass(String classPath) {
        classPathList.add(classPath);
    }

    public static void executeTests() {
        List<ClassNotFoundException> notFoundClassesList = new LinkedList<>();
        int successTests = 0;
        int failTests = 0;
        for (String classPath : classPathList) {
            try {
                File file = new File(new File(classPath).getParent());
                URL url = file.toURI().toURL();
                URL[] urlArray = new URL[]{url};
                ClassLoader cl = new URLClassLoader(urlArray);


                String classFileName = new File(classPath).getName();
                classFileName = classFileName.substring(0, classFileName.indexOf('.'));
                Class<?> clazz = Class.forName("testclasses." + classFileName, false, cl);
                for (Method method : clazz.getMethods()) {
                    if (method.isAnnotationPresent(Test.class)) {
                        if (Modifier.isStatic(method.getModifiers())) {
                            if (method.getParameterCount() == 0) {
                                method.invoke(null);
                                System.out.println(clazz + method.getName());
                                successTests++;
                            }
                        }
                    }
                }

            } catch (ClassNotFoundException notFoundException) {
                notFoundClassesList.add(notFoundException);
            } catch (Exception exception) {
                exception.printStackTrace();
                failTests++;
            }
        }

        for (var classNotFound : notFoundClassesList) {
            classNotFound.printStackTrace();
        }

        System.out.println("Succeeded " + successTests + " tests.");
        System.out.println("Failed  " + failTests + " tests.");
    }
}