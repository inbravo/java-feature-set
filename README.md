[![Sputnik](https://sputnik.ci/conf/badge)](https://sputnik.ci/app#/builds/inbravo/java-src)
### Some useful Java examples

#### Language features 
-  [A static nested class is called 'Static Nested Class'. A NON static nested class is called 'Inner Class'] [NestedClassTest.md]
-  [Program to analyze, how shift operation based calculation is faster than mathematical operation based] [NativeCalc.md]
-  [Simulator for OutOfMemoryError] [OutOfMemoryErrorSim.md]
-  [Singleton using Enum] [EnumSingletonTest.md]
-  [How RMI works] [PowerServiceImpl.md]

#### JVM features 
-  [How JVM method-inline optimization works] [NoClassInstance.md]
-  [Another example of JVM method-inline optimization] [MethodInlineTest.md]
-  [Failed Hoisting example] [FailedHoistingTest.md]
-  [Successful Hoisting example] [SuccessfulHoistingTest.md]
-  [Another successful Hoisting example] [AnotherSuccessfullHoistingTest.md]
-  [How JVM finds the HotSpot and converts them into native instruction using JIT compiler] [FibonacciNumber.md]
-  [How JVM finds the HotSpot and converts them into native instruction using JIT compiler] [MethodInlineTest.md]
-  [Object size at runtime] [ObjectSizeTest.md]
-  [Primitive value size at runtime] [PrimitiveVarsSizeTest.md]
-  [Unsafe objects (Heap bypassing)] [UnsafeObject.md]
-  [Example of System.identityHashCode] [IdentityHashCode.md]

#### JDK 7 features
-  [Usage of 'MethodHandles'] [MethodHandleTest.md]
-  [Performance comparison between MethodHandle, Reflection, LambdaMetafactory and Direct method call] [TestMethodPerf.md]

#### JDK 8 features
-  [New diamond operator(<>)] [DiamondOperatorTest.md]
-  [Interface with 'default' method] [InterfaceWithMainTest.md]
-  [Interface with 'static' method] [InterfaceWithStaticMethod.md]
-  [Class with 'default' method] [ClassWithDefaultMethodUsage.md]
-  [Functional interfaces] [FunctionalInterfaceTest.md]
-  [Runnable is a functional interface now (java.lang.FunctionalInterface)] [ThreadSleepTest.md]
-  [Predicate as functional interfaces] [PredicateTest.md]
-  [Using Collections.sort on Lambdas] [ComparatorTest.md]
-  [Using List.forEach on Lambdas] [ForEachTest.md]
-  [List Streaming] [ListStreamTest.md]

#### Collection
-  [Usage of 'CopyOnWriteArrayList'] [CowalTest.md]
-  [Performance testing of 'ConcurrantMap'] [ConcurrantMapTest.md]
-  [Usage of 'WeakHashMap'] [WeakHashMapTest.md]


#### Concurrency
-  [Classical example of failed concurrency] [FailedConcurrency.md]
-  [Classical example of successful concurrency] [SuccessfullConcurrency.md]
-  [Number of processor cores on a computer] [AvailableProcessorTest.md]
-  [A blocking array implemented using 'ReentrantLock'] [BlockingArray.md]
-  [Usage of 'CountDownLatch'] [CountDownLatch.md]
-  [Usage of 'CyclicBarrier'] [CyclicBarrierTest.md]
-  [Usage of 'Exchanger'] [ExchangerTest.md]
-  [Usage of 'Semaphore'] [SemaphoreTest.md]
-  [Usage of 'Timer' and 'TimerTask'] [TimerTest.md]


#### Data structures & algorithms
-  [Stack][Stack.md]
-  [Reverse a word using a stack][ReverseTheWord.md]
-  [Queue][Queue.md]
-  [Circular Queue][CircularQueue.md]
-  [Linked Queue][LinkedQueue.md]
-  [Priority Queue][PriorityQueue.md]
-  [Linear Search][LinearSearch.md]
-  [Binary Search][BinarySearch.md]
-  [Bubble Sort][BubbleSort.md]
-  [Insertion Sort][InsertionSort.md]
-  [Selection Sort][SelectionSort.md]
-  [Binary Tree][BinaryTree.md]
-  [Armstrong Number][ArmstrongNumber.md]
-  [Floyds Triangle][FloydsTriangle.md]
-  [Example on Hex Numbers][HexNumberTest.md]
-  [Example on Prime Numbers][PrimeNumbers.md]
-  [Example on Random Numbers][RandomNumbers.md]
-  [How to test a number is odd by using '%'] [OddityTest.md]
-  [Check if a string is anagram] [Anagram.md]
-  [Check if a string contains DuplicateChars] [DuplicateChars.md]
-  [Get frequency of words in a string] [WordFrequency.md]
-  [Get repeating and non repeating chars from a string] [FirstNonRepeatingChar.md]
-  [Find the Levenshtein distance between two strings] [LevenshteinDistanceTest.md]


[Anagram.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/string/Anagram.java
[IdentityHashCode.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/string/IdentityHashCode.java
[DuplicateChars.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/string/DuplicateChars.java
[WordFrequency.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/string/WordFrequency.java
[FirstNonRepeatingChar.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/string/FirstNonRepeatingChar.java
[LevenshteinDistanceTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/string/LevenshteinDistanceTest.java
[NoClassInstance.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/puzzle/NoClassInstance.java
[PowerServiceImpl.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/rmi/PowerServiceImpl.java
[FailedHoistingTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/runtime/jit/FailedHoistingTest.java
[SuccessfulHoistingTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/runtime/jit/SuccessfulHoistingTest.java
[AnotherSuccessfullHoistingTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/runtime/jit/AnotherSuccessfullHoistingTest.java
[MethodInlineTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/runtime/jit/MethodInlineTest.java
[UnsafeObject.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/runtime/jit/UnsafeObject.java
[FibonacciNumber.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/runtime/jit/FibonacciNumber.java
[NestedClassTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/lang/NestedClassTest.java
[ObjectSizeTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/memory/ObjectSizeTest.java
[PrimitiveVarsSizeTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/memory/PrimitiveVarsSizeTest.java
[OutOfMemoryErrorSim.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/memory/OutOfMemoryErrorSim.java
[CowalTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/collection/CowalTest.java
[AvailableProcessorTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/concurrency/AvailableProcessorTest.java
[BlockingArray.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/concurrency/BlockingArray.java
[CountDownLatch.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/concurrency/CountDownLatch.java
[CyclicBarrierTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/concurrency/CyclicBarrierTest.java
[ExchangerTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/concurrency/ExchangerTest.java
[SemaphoreTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/concurrency/SemaphoreTest.java
[FailedConcurrency.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/concurrency/FailedConcurrency.java
[SuccessfullConcurrency.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/concurrency/SuccessfullConcurrency.java
[TimerTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/concurrency/TimerTest.java
[Stack.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds.stack/Stack.java
[ReverseTheWord.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/stack/ReverseTheWord.java
[ReverseTheWord.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/stack/ReverseTheWord.java
[Queue.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/queue/Queue.java
[CircularQueue.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/queue/CircularQueue.java
[LinkedQueue.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/queue/LinkedQueue.java
[PriorityQueue.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/queue/PriorityQueue.java
[BinarySearch.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/search/BinarySearch.java
[LinearSearch.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/search/LinearSearch.java
[BubbleSort.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/search/BubbleSort.java
[InsertionSort.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/search/InsertionSort.java
[SelectionSort.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/search/SelectionSort.java
[BinaryTree.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ds/tree/BinaryTree.java
[MethodHandleTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk7/MethodHandleTest.java
[DiamondOperatorTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/DiamondOperatorTest.java
[InterfaceWithMainTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/InterfaceWithMainTest.java
[InterfaceWithStaticMethod.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/funcinterf/InterfaceWithStaticMethod.java
[ClassWithDefaultMethodUsage.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/funcinterf/ClassWithDefaultMethodUsage.java
[FunctionalInterfaceTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/funcinterf/FunctionalInterfaceTest.java
[PredicateTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/functions/PredicateTest.java
[ComparatorTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/lambda/ComparatorTest.java
[ForEachTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/lambda/ForEachTest.java
[ThreadSleepTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/lambda/ThreadSleepTest.java
[TestMethodPerf.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/lambda/perf/TestMethodPerf.java
[TestMethodPerf.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/lambda/perf/TestMethodPerf.java
[ListStreamTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/jdk8/streams/ListStreamTest.java
[ArmstrongNumber.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/number/ArmstrongNumber.java
[FloydsTriangle.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/number/FloydsTriangle.java
[HexNumberTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/number/HexNumberTest.java
[PrimeNumbers.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/number/PrimeNumbers.java
[RandomNumbers.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/number/RandomNumbers.java
[OddityTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/puzzle/OddityTest.java
[EnumSingletonTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/pattern/EnumSingletonTest.java
[NativeCalc.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/perf/NativeCalc.java
[ConcurrantMapTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ref/ConcurrantMapTest.java
[WeakHashMapTest.md]: https://github.com/inbravo/java-src/blob/master/src/com/inbravo/ref/WeakHashMapTest.java

