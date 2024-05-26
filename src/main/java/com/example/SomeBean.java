package com.example;

class SomeBean{
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private OtherInterface field2;
    public void foo(){
        field1.doSomething();
        field2.doOther();
    }
}