package DataTableTest.MainPac;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Pattern;

class ClassMembers {
    private String nameClass;

    ClassMembers(String nameClass) {
        this.nameClass = nameClass;
    }

    public Class getSomeClass() {
        Class someClass = null;
        try {
            someClass = Class.forName(nameClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return someClass;
    }

    public Constructor getNotDefConstructor() {
        Constructor cons = null;
        Constructor[] constructors = getSomeClass().getConstructors();
        for (Constructor constructor : constructors) {
            if (constructors.length > 1) {
                if (constructor.getParameterCount() == 0) {
                    continue;
                }
                cons = constructor;
                break;
            } else {
                System.out.print("Not default constructor is required.");
                System.exit(1);
            }
        }
        return cons;
    }

    public Method[] getSomeMethods() {
        Method[] allMethods = getSomeClass().getMethods();
        Field[] allFields = getSomeClass().getDeclaredFields();
        Pattern onlyGet = Pattern.compile("get\\w+[^iIdD]([^Class])");
        Pattern notId = Pattern.compile("\\w*[^iIdD]");
        Method[] methods = Arrays.stream(allMethods).filter(n -> onlyGet.matcher(n.getName()).matches()).toArray(Method[]::new);
        String[] methodsName = Arrays.stream(methods).map(n -> n.getName().substring(3)).toArray(String[]::new);
        String[] fieldsName = Arrays.stream(allFields).filter(n -> notId.matcher(n.getName()).matches()).map(Field::getName).toArray(String[]::new);
        //Making right order of methods
        Method t;
        if (methodsName.length == fieldsName.length) {
            for (int i = 0; i < methods.length; i++) {
                if (!(methodsName[i].equalsIgnoreCase(fieldsName[i]))) {
                    do {
                        if (i != methods.length - 1) {
                            t = methods[i];
                            methods[i] = methods[i + 1];
                            methods[i + 1] = t;
                        } else {
                            break;
                        }
                    } while (methodsName[i].equalsIgnoreCase(fieldsName[i]));
                } else {
                    continue;
                }
            }
        } else {
            System.out.print("Make sure you create POJO class properly");
            System.exit(1);
        }
        return methods;
    }
}


