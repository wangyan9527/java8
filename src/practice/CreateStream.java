package practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateStream {

    public static void main(String[] args) throws FileNotFoundException {
//        createStreamFromCollection().forEach(System.out::println);
//        System.out.println("========");
//        createStreamFromValues().forEach(System.out::println);
//        System.out.println("========");
//        createStreamFromArrays().forEach(System.out::println);

        /*
        File file = new File("/Users/wangyan/test.txt");
        OutputStream is = new FileOutputStream(file);
        createStreamFromFile().forEach(p-> {
            try {
                is.write((p+"\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/

        //createStreamFromIterator().limit(10).forEach(System.out::println);

        //createStreamFromGenerate().limit(10).forEach(System.out::println);

        createStreamFromObj().limit(10).forEach(System.out::println);


    }

    private static Stream<String> createStreamFromCollection() {
        List<String> list = Arrays.asList("hello", "stream", "create", "collection");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues() {
        return Stream.of("create", "stream", "by", "values");
    }

    private static Stream<String> createStreamFromArrays() {
        return Arrays.stream(new String[]{"create", "stream", "by", "arrays"});
    }

    private static Stream<String> createStreamFromFile() {
        Path path = Paths.get("/Users/wangyan/IdeaProjects/JAVA8/src/practice/CreateStream.java");
        try {
            Stream<String> stream = Files.lines(path);
            return stream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过迭代器获取stream对象(会一直迭代)
     *
     * @return
     */
    private static Stream<Integer> createStreamFromIterator() {
        return Stream.iterate(0, a -> a + 2);
    }

    /**
     * 通过随机数获取stream对象
     *
     * @return
     */
    private static Stream<Double> createStreamFromGenerate() {
        return Stream.generate(Math::random);
    }

    private static Stream<Obj> createStreamFromObj() {
        return Stream.generate(new ObjSupplier());
    }

    static class ObjSupplier implements Supplier<Obj> {

        private Integer index = 0;

        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index, "name-->" + index);
        }
    }

    static class Obj {
        private Integer id;
        private String name;

        public Obj(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "obj{" +
                    "id=" + id +
                    "," + name  +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

}
