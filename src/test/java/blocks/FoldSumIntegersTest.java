package blocks;

import blocks.integers.FoldSumIntegers;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import static org.junit.Assert.assertEquals;

public class FoldSumIntegersTest {

    @Test
    public void onSubscribeTest() throws InterruptedException {
        SubmissionPublisher<List<Integer>> publisher = new SubmissionPublisher<>();
        BinaryFold foldSumIntegers = new FoldSumIntegers();
        PrinterSubscriber<Integer> printerSubscriber = new PrinterSubscriber<>();
        List<Integer> items = List.of(1, 2, 3, 4);

        publisher.subscribe(foldSumIntegers);
        foldSumIntegers.subscribe(printerSubscriber);
        publisher.submit(items);
        publisher.close();
        Thread.sleep(2000);
        assertEquals(1, printerSubscriber.getConsumedElements().size());
        assertEquals(10, (int) printerSubscriber.getConsumedElements().get(0));
    }
}