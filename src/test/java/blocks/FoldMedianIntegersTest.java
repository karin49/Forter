package blocks;

import blocks.integers.FoldMedianIntegers;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import static org.junit.Assert.assertEquals;

public class FoldMedianIntegersTest {
    @Test
    public void onSubscribeTest() throws InterruptedException {
        SubmissionPublisher<List<Integer>> publisher = new SubmissionPublisher<>();
        ArrayFold foldMedianIntegers = new FoldMedianIntegers();
        PrinterSubscriber<Integer> printerSubscriber = new PrinterSubscriber<>();
        List<Integer> items = List.of(1, 2, 3, 4, 5);

        publisher.subscribe(foldMedianIntegers);
        foldMedianIntegers.subscribe(printerSubscriber);
        publisher.submit(items);
        publisher.close();
        Thread.sleep(2000);
        assertEquals(1, printerSubscriber.getConsumedElements().size());
        assertEquals(3, (int) printerSubscriber.getConsumedElements().get(0));
    }
}