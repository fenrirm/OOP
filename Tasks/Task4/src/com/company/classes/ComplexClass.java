package com.company.classes;

public class ComplexClass extends Base implements Interface {
    private final String a;

    public ComplexClass(int x, String a) {
        super(x);
        this.a = a;
    }

    protected void printX() {
        System.out.println(x);
    }

    @Override
    public void sayHello() {
        System.out.println("Hello Complex");
    }
}

