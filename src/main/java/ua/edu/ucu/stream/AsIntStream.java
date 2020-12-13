package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.util.ArrayList;
import java.util.Collections;

public class AsIntStream implements IntStream {
    private ArrayList<Integer> arrList;

    private AsIntStream(ArrayList<Integer> values) {
        arrList = new ArrayList<>(values);
    }

    private void checkEmpty() {
        if (arrList.isEmpty()) {
            throw new IllegalArgumentException("Empty values!");
        }
    }

    public static IntStream of(int... values) {
        ArrayList<Integer> arrList = new ArrayList<>();
        for (int val : values) {
            arrList.add(val);
        }
        return new AsIntStream(arrList);
    }

    @Override
    public Double average() {
        checkEmpty();
        return sum() / (double) count();
    }

    private Integer maxMin(boolean max) {
        checkEmpty();
        if (max) {
            return Collections.max(arrList);
        } else {
            return Collections.min(arrList);
        }
    }

    @Override
    public Integer max() {
        return maxMin(true);
    }

    @Override
    public Integer min() {
        return maxMin(false);
    }

    @Override
    public long count() {
        checkEmpty();
        return arrList.size();
    }

    @Override
    public Integer sum() {
        checkEmpty();
        int sum = 0;
        for (int val : arrList) {
            sum += val;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int val : arrList) {
            if (predicate.test(val)) {
                result.add(val);
            }
        }
        return new AsIntStream(result);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int val : arrList) {
            action.accept(val);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int val : arrList) {
            result.add(mapper.apply(val));
        }
        return new AsIntStream(result);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] flatMapped;
        for (int val : arrList) {
            flatMapped = func.applyAsIntStream(val).toArray();
            for (int flat : flatMapped) {
                result.add(flat);
            }
        }
        return new AsIntStream(result);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int result = identity;
        for (int val : arrList) {
            result = op.apply(result, val);
        }
        return result;
    }

    @Override
    public int[] toArray() {
        int[] result = new int[arrList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = arrList.get(i);
        }
        return result;
    }

}
