package com.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;

public class Injector {
    private Properties properties;

    public Injector() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("javalaba5/src/main/java/com/example/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T inject(T obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String className = properties.getProperty(interfaceName);
                if (className != null) {
                try {
                    Class<?> clazz = Class.forName(className);
                    Object service = clazz.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(obj, service);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }
        }
        return obj;
    }
}
