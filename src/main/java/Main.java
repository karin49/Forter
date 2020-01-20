import blocks.FilterBlock;
import blocks.FixedEventWindow;
import blocks.InputPrinter;
import blocks.Printer;
import blocks.integers.FoldMedianIntegers;
import blocks.integers.FoldSumIntegers;
import source.StdinSource;

public class Main {

    public static void main(String[] args) throws Exception {
        FilterBlock<Integer> filterBlock = new FilterBlock<>(i -> i> 0);
        FixedEventWindow<Integer> window2 = new FixedEventWindow<>(2);
        FoldSumIntegers foldSum = new FoldSumIntegers();
        FixedEventWindow<Integer> window3 = new FixedEventWindow<>(3);
        FoldMedianIntegers foldMedian = new FoldMedianIntegers();
        Printer<Integer> printer = new Printer<>();
        InputPrinter inputPrinter = new InputPrinter();
        inputPrinter.subscribe(filterBlock);
        filterBlock.subscribe(window2);
        window2.subscribe(foldSum);
        foldSum.subscribe(window3);
        window3.subscribe(foldMedian);
        foldMedian.subscribe(printer);

        StdinSource source = new StdinSource();
        source.subscribe(inputPrinter);
        source.read();
    }

}
