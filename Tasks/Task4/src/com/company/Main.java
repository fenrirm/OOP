package com.company;

import java.util.Arrays;

public class Main {

    public static void describeClass(String className) {
        try {
            System.out.println("\n***************  " + className + "  ***************");
            Class<?> clazz = Class.forName(className);
            System.out.println("Fields: " + Arrays.toString(clazz.getDeclaredFields()));
            System.out.println("Methods: " + Arrays.toString(clazz.getDeclaredMethods()));
            System.out.println("Implements: " + ": " + Arrays.toString(clazz.getAnnotatedInterfaces()));

            Class<?> base = clazz.getSuperclass();
            if(base != null){
                System.out.println("Extends: " + base.getName());
                if(!base.getName().equals("java.lang.Object")) {
                    describeClass(base.getName());
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        describeClass("com.company.classes.Interface");
//        describeClass("com.company.classes.Base");
        describeClass("com.company.classes.ComplexClass");
    }
}
