/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

class Foo {
    public final int i = 10;
}
