package practice1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SpliteratorInAction {

    private static String test = "The Cauchari-Olaroz project will see the chemical extracted from brine deposits - so-called 'brine mining.'" +
            " \n" +
            "Lithium is a key component1 of modern batteries, which are in increasing demand worldwide thanks to the growing number of smartphones and tablets being sold." +
            " \n" +
            "Not only that, but the recent rapid sales growth of electric vehicles has also seen demand for batteries soar." +
            " \n" +
            "It's not just the need for battery components2 that's rising - there's also growing global demand for places to build them." +
            " \n" +
            "With that in mind, one of the world's leading battery firms, Contemporary Amperex Technology of China, recently choose Germany to be the location of its first European factory." +
            " \n" +
            "Previously3, it had been reported that the firm was considering three countries to host the facility." +
            " \n" +
            "The company already has plans in its home market to drastically increase its production capacity of batteries in order to meet demand from manufacturers." +
            " \n" +
            "It is expected that the German factory will serve the needs of local manufacturers.";

    public static void main(String[] args) {

//        IntStream intStream = IntStream.rangeClosed(0, 10);
//        Spliterator.OfInt spliterator = intStream.spliterator();
//        Consumer<Integer> consumer = System.out::println;
//        spliterator.forEachRemaining(consumer);

        MySpliteratorTest mySpliteratorTest = new MySpliteratorTest(test);
        Optional.ofNullable(mySpliteratorTest.parallelStream().count()).ifPresent(System.out::println);

        mySpliteratorTest.parallelStream().forEach(System.out::println);

        ArrayList<String> list = new ArrayList<>();
        System.out.println(list);


    }

    static class MySpliteratorTest {
        private final String[] data;

        MySpliteratorTest(String test) {
            Objects.requireNonNull(test, "test is null");
            this.data = test.split("\n");
        }

        public Stream<String> stream() {
            return StreamSupport.stream(new MySpliterator(), false);//非并行
        }

        public Stream<String> parallelStream() {
            return StreamSupport.stream(new MySpliterator(), true);//并行
        }

        private class MySpliterator implements Spliterator<String> {

            private int start, end;

            public MySpliterator() {
                this.start = 0;
                this.end = MySpliteratorTest.this.data.length - 1;
            }

            public MySpliterator(int start, int end) {
                this.start = start;
                this.end = end;
            }

            /**
             * 消费流里面的元素,如果没有元素则返回false
             *
             * @param action
             * @return
             */
            @Override
            public boolean tryAdvance(Consumer<? super String> action) {
                if (start < end) {
                    action.accept(MySpliteratorTest.this.data[start++]);
                    return true;
                }
                return false;
            }

            /**
             * 并行时会执行该方法
             * 对任务进行分割,返回一个新的Spliterator迭代器
             *
             * @return
             */
            @Override
            public Spliterator<String> trySplit() {
                int mid = (end - start) / 2;
                if (mid <= 1) {
                    return null;
                }
                int left = start;
                int right = start + mid;
                start = right + 1;
                return new MySpliterator(left, right);
            }

            /**
             * 用于估算还需要多少元素需要遍历
             *
             * @return
             */
            @Override
            public long estimateSize() {
                return end - start;
            }

            /**
             * 当迭代器拥有SIZED特征时，返回剩余元素个数；否则返回-1
             *
             * @return
             */
            @Override
            public long getExactSizeIfKnown() {
                return estimateSize();
            }

            /**
             * 返回当前对象有哪些特征值
             *
             * @return
             */
            @Override
            public int characteristics() {
                return IMMUTABLE | SIZED | SUBSIZED;
            }
        }
    }

}
