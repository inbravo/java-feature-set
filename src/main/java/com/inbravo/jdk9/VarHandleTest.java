/*
 * MIT License
 * 
 * Class 'java.lang.invoke.VarHandle' example based on JEP-193
 *
 * Copyright (c) 2017 Onwards Amit Dixit (github.com/inbravo)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.inbravo.jdk9;

import java.lang.invoke.VarHandle;
import java.lang.invoke.MethodHandles;

/**
 * 'java.lang.invoke.VarHandle' class example based on JEP-193
 * 
 * @author amit.dixit
 */
public class VarHandleTest {

  public static void main(final String... args) {
    try {
      final MethodHandles.Lookup lookup = MethodHandles.lookup();
      final VarHandle varHandle = lookup.in(Foo.class).findVarHandle(Foo.class, "i", int.class);

      System.out.println(varHandle);
    } catch (final ReflectiveOperationException e) {
      throw new Error(e);
    }
  }
}


/* A non static nested class = Inner Class 'Foo' */
class Foo {

  public final int i = 10;
}
