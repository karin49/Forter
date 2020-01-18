package blocks;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import static org.junit.Assert.assertEquals;

public class FilterBlockTest {

    @Test
    public void onSubscribeTest() throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        FilterBlock<Integer> filterBlock
                = new FilterBlock<>(i -> i > 0);
        PrinterSubscriber<Integer> printerSubscriber = new PrinterSubscriber<>();
        List<Integer> items = List.of(1, 2, -5, 3, 4, 5, 6);
        List<Integer> expectedResult = List.of(1, 2, 3, 4, 5, 6);

        publisher.subscribe(filterBlock);
        filterBlock.subscribe(printerSubscriber);
        items.forEach(publisher::submit);
        publisher.close();
        Thread.sleep(1000);
        assertEquals(expectedResult.size(), printerSubscriber.getConsumedElements().size());

    }
}