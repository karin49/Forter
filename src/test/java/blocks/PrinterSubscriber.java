package blocks;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;

public class PrinterSubscriber<T> implements Flow.Subscriber<T> {
    private Flow.Subscription subscription;
    private List<T> consumedElements = new LinkedList<>();

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        System.out.println("> " + item);
        consumedElements.add(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public List<T> getConsumedElements() {
        return consumedElements;
    }
}
