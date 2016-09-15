package com.inbravo.lang;

/**
 * 
 * @author amit.dixit
 *
 */
public final class NestedClass {

  public static final void main(final String... args) {

    final OuterClass.StaticNestedClass staticNestedClassInstance = new OuterClass.StaticNestedClass();
    staticNestedClassInstance.printOuterClassFields();

    // OuterClass.InnerClass innerClassInstance = new OuterClass.InnerClass(); <- Compiling Error

    final OuterClass outerClassInstance = new OuterClass();
    OuterClass.InnerClass innerClassInstance = outerClassInstance.new InnerClass();
    innerClassInstance.printOuterClassFields();
  }
}


/**
 * 
 * Outer class
 *
 */
final class OuterClass {

  private int a = 0;
  int b = 1;
  protected int c = 2;
  public int d = 3;
  private static int e = 4;

  private static final void invokeStaticMethod() {
    System.out.println("\t Static Method of OuterClass is invoked");
  }

  private final void invokeInstanceMethod() {
    System.out.println("\t Instance Method of OuterClass is invoked");
  }

  /* If a nested class is static, it's called static nested class */
  static final class StaticNestedClass {

    public final void printOuterClassFields() {

      System.out.println("Static Nested Class");

      // System.out.println("\t a is " + a); <- Can't access instance fields
      // invokeInstanceMethod(); <- Can't access instance method
      System.out.println("\t Cannot access instance fields of OuterClass from StaticNestedClass");
      System.out.println("\t Static field e is " + e);
      invokeStaticMethod();
    }
  }

  /* If a nested class is NOT static, it's called inner class */
  final class InnerClass {

    public final void printOuterClassFields() {

      System.out.println("Inner Class");
      System.out.println("\t Instance Field a is " + a);
      System.out.println("\t Instance Field b is " + b);
      System.out.println("\t Instance Field c is " + c);
      System.out.println("\t Instance Field d is " + d);
      System.out.println("\t Static field e is " + e);

      invokeStaticMethod();
      invokeInstanceMethod();
    }
  }
}
