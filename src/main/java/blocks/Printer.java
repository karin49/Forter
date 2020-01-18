package blocks;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class Printer<T>  extends SubmissionPublisher<T>
        implements Flow.Subscriber<T> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        print(item);
        submit(item);
        subscription.request(1);
    }

    private void print(T item) {
        System.out.println(getPREFIX() + item);
    }

    protected String getPREFIX() {
        return "";
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
