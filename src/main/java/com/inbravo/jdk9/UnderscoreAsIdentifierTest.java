/*
 * MIT License
 * 
 * Underscore (_) as an Identifier Name based on JEP-213
 * Using underscore ("_") as an identifier, should be turned into an error in Java SE 9
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

/**
 * Oracle has removed underscore (“_”) usage as identifier completely in Java SE 9 release
 * 
 * @author amit.dixit
 */
public final class UnderscoreAsIdentifierTest {
    
    public static void main(final String... args) {
 
        final String stringWithoutUnderscore = "stringWithoutUnderscore";
        
        /* Line below will give error */
        /* final String _ = "stringWithUnderscore"; */
        /* System.out.println("stringWithUnderscore : " + _); */
        System.out.println("stringWithoutUnderscore : " + stringWithoutUnderscore);        
    }
}