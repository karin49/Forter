package sink;

import blocks.Printer;
import org.junit.Test;
import blocks.PrinterSubscriber;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import static org.junit.Assert.assertEquals;

public class PrinterTest {

    @Test
    public void onSubscribeTest() throws InterruptedException {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        Printer<Integer> printer = new Printer<>();
        publisher.subscribe(printer);
        PrinterSubscriber<Integer> printerSubscriber = new PrinterSubscriber<>();
        printer.subscribe(printerSubscriber);
        List<Integer> items = List.of(10, 11 , 12);

        assertEquals(publisher.getNumberOfSubscribers(), 1);
        items.forEach(publisher::submit);
        publisher.close();
        Thread.sleep(1000);
        assertEquals(items.size(), printerSubscriber.getConsumedElements().size());

    }
}