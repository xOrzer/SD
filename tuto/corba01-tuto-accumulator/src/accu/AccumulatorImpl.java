package accu;

import accumulator.Accumulator;
import accumulator.AccumulatorPOA;

public class AccumulatorImpl extends AccumulatorPOA
{
    private int total;

    public AccumulatorImpl() {
        total = 0;
    }

    @Override
    public void add(int number) {
        total += number;
        System.out.println("Total : " + total);
    }
}
