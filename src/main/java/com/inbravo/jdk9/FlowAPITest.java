package com.inbravo.jdk9;

import java.util.Arrays;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;

/**
 *
 * Example of Flow API (Reactive Streams)
 * Source https://www.javaworld.com/article/3198904/learn-java/java-9s-other-new-enhancements-part-6-concurrency.html
 * 
 * @author amit.dixit
 *
 *
 */
public final class FlowAPITest {

  public static void main(final String... args) {

    /* Create a submission publisher */
    final SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

    /* Create a subscriber and register it with the publisher */
    final MySubscriber<String> subscriber = new MySubscriber<>();
    publisher.subscribe(subscriber);

    /* Publish several data items and then close the publisher */
    System.out.println("Publishing data items...");
    final String[] items = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};

    /* Iterate and submit through publisher */
    Arrays.asList(items).stream().forEach(i -> publisher.submit(i));

    /* Close the publisher */
    publisher.close();

    try {
      synchronized ("A") {
        "A".wait();
      }
    } catch (InterruptedException ie) {
    }
  }
}


/**
 * Subscriber class
 * 
 * @author amit.dixit
 *
 * @param <T>
 */
class MySubscriber<T> implements Subscriber<T> {

  private Subscription subscription;

  @Override
  public final void onSubscribe(final Subscription subscription) {
    this.subscription = subscription;
    subscription.request(1);
  }

  @Override
  public final void onNext(final T item) {

    System.out.println("Received: " + item);
    subscription.request(1);
  }

  @Override
  public final void onError(final Throwable t) {

    t.printStackTrace();
    
    synchronized ("A") {
      "A".notifyAll();
    }
  }

  @Override
  public void onComplete() {

    System.out.println("Done");
    
    synchronized ("A") {
      "A".notifyAll();
    }
  }
}
