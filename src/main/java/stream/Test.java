package stream;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * @author lizihao
 * @date 2021/7/9 15:50
 */
public class Test {
    public static void main(String[] args) {
        BinaryOperator<Integer> accumulator = Integer::sum;
        Integer count = accumulator.apply(accumulator.apply(0, 1), 2);
        System.out.println(count);
    }

    public static boolean isDigit(Character c) {
        return c >= '0' && c <= '9';
    }
}

class Track {
    private String name;
    private int length;

    public Track() {
    }

    public Track(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
