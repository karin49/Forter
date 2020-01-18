package blocks.integers;

import blocks.BinaryFold;

public class FoldSumIntegers extends BinaryFold<Integer> {

    public FoldSumIntegers() {
        super(Integer::sum, 0);
    }
}
