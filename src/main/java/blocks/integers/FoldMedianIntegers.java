package blocks.integers;

import blocks.ArrayFold;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FoldMedianIntegers extends ArrayFold<Integer> {
    private static Function<List<Integer>, Integer> median = (arr) -> {
        int size = arr.size();
        List<Integer> sortedList = arr.stream().sorted().collect(Collectors.toList());
        return (sortedList.get(size/2) + sortedList.get((size-1)/2)) / 2;
    };

    public FoldMedianIntegers() {
        super(median);
    }

}
