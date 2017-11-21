/*
 * MIT License
 * 
 * The Java Shell (Read-Eval-Print Loop - jshell) on JEP-222
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

import jdk.jshell.JShell;
import static java.lang.System.out;

public final class JShellTest {
    
    public static void main(final String... args) {

        /* Create a new java shell instance */
        final JShell shell = JShell.builder().build();
        
        /* Create a new method 'hello()' on java shell using method 'eval()' */
        shell.eval("void one() { System.out.println(\"one VJUG\"); }");
        shell.eval("void two() { System.out.println(\"two VJUG\"); }");
        shell.eval("void three() { System.out.println(\"three VJUG\"); }");
        
        /* Print signature & name of all methods, created on java shell */
        shell.methods().forEach(v -> out.println("Method: " + v.signature() + " " + v.name()));
    }
}
