package source;

import java.util.Scanner;
import java.util.concurrent.SubmissionPublisher;

public class StdinSource extends SubmissionPublisher<Integer> {
    public void read() {
        Scanner console = new Scanner(System.in);

        while (console.hasNextLine()) {
            String val = console.next();
            try {
                Integer i = Integer.valueOf(val);
                submit(i);
            } catch (NumberFormatException ne) {
                System.out.println("Numbers only");
            }
        }
    }
}
