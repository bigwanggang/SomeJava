package com.gustavo.jmockit;

public class ClassMocked {
    private String name = "name_init";

    public String getName() {
        return name;
    }

    private static String className = "Class3Mocked_init";

    public static String getClassName() {
        return className;
    }
}
