package blocks;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class ArrayFold <T>
        extends SubmissionPublisher<T>
        implements Flow.Processor<List<T>, T> {

    private Flow.Subscription subscription;
    private Function<List<T>, T> function;

    public ArrayFold(Function<List<T>, T> function) {
        this.function = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(List<T> items) {
        T sum = function.apply(items);
        submit(sum);
        subscription.request(1);
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
