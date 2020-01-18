package blocks;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class FixedEventWindow <T>
        extends SubmissionPublisher<List<T>>
        implements Flow.Processor<T, List<T>> {

    private final int aggregateNumber;
    private Flow.Subscription subscription;
    private List<T> consumedElements;

    public FixedEventWindow(int aggregateNumber) {
        super();
        this.aggregateNumber = aggregateNumber;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        consumedElements = new LinkedList<>();
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        consumedElements.add(item);
        if (isFull()) {
            submit(consumedElements);
            consumedElements = new LinkedList<>();
        }
        subscription.request(1);
    }

    private boolean isFull() {
        return consumedElements.size() == aggregateNumber;
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}