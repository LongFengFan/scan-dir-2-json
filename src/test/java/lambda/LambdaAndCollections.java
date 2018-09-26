package lambda;

import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by LongFF on 2018/9/26
 * Collection中的新方法
 */
public class LambdaAndCollections {

    /*
     * forEach()
     * */


    /*
     * removeIf()
     * */

    @Test
    public void removeIf() {

//        java7 迭代器迭代
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
//            if(it.next().length()>=3) // 删除长度大于3的元素
            it.next();
            it.remove();
        }
        System.out.println(list);

//        java8 lambda
        ArrayList<String> arrays = new ArrayList<>(Arrays.asList("I", "love", "you", "too"));
        arrays.removeIf(s -> s.length() > 3);
        System.out.println(arrays);
    }

    /*
     * replaceAll()
     * */

    @Test
    public void testRemoveAll() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("i", "love", "you", "too"));
        list.replaceAll(s -> {
            if (s.length() >= 3) {
                return s.toUpperCase();
            }
            return s;
        });
        System.out.println(list);
    }

    /*
     *  sort()
     * */
    @Test
    public void testSort() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "lov", "you", "too0"));
//        list.sort((str1, str2) -> str1.length() - str2.length());
        list.sort(Comparator.comparingInt(String::length));
        System.out.println(list);
    }

    /*
     *  spliterator()
     *  Spliterator既可以像Iterator那样逐个迭代，也可以批量迭代。批量迭代可以降低迭代的开销。
        Spliterator是可拆分的，一个Spliterator可以通过调用Spliterator<T> trySplit()方法来尝试分成两个。一个是this，另一个是新返回的那个，这两个迭代器代表的元素没有重叠。
        可通过（多次）调用Spliterator.trySplit()方法来分解负载，以便多线程处理。
     * */

    @Test
    public void testSpliterator(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("I", "lov", "you", "too0"));
        Spliterator<String> spliterator = list.spliterator();
//        spliterator.forEachRemaining(s -> System.out.println(s));

        Spliterator<String> stringSpliterator = spliterator.trySplit();
        spliterator.forEachRemaining(s -> System.out.println(s));
        stringSpliterator.forEachRemaining(s -> System.out.println(s));

    }















}


