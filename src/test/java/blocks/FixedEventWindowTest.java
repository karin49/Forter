package blocks;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import static org.junit.Assert.assertEquals;

public class FixedEventWindowTest {

    @Test
    public void onSubscribeTest() throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        FixedEventWindow<Integer> fixedEventWindow
                = new FixedEventWindow<>(3);
        PrinterSubscriber<List<Integer>> printerSubscriber = new PrinterSubscriber<>();
        List<Integer> items = List.of(1, 2, -5, 3, 4, 5, 6);

        publisher.subscribe(fixedEventWindow);
        fixedEventWindow.subscribe(printerSubscriber);
        items.forEach(publisher::submit);
        publisher.close();
        Thread.sleep(2000);
        assertEquals(2, printerSubscriber.getConsumedElements().size());

    }
}