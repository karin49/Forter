package blocks;

import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.BinaryOperator;

public class BinaryFold<T>
        extends SubmissionPublisher<T>
        implements Flow.Processor<List<T>, T> {

    private Flow.Subscription subscription;
    private BinaryOperator<T> function;
    private T startValue;

    public BinaryFold(BinaryOperator<T> function, T startValue) {
        this.function = function;
        this.startValue = startValue;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(List<T> items) {
        T sum = items.stream().reduce(startValue, function);
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
