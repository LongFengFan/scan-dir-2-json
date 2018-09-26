package lambda;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LongFF on 2018/9/26
 */
public class Demo {
    public static void main(String[] args) {
        Comparator<Date> comparator = new Comparator<Date>() {

            @Override
            public int compare(Date o1, Date o2) {
                if (o1.after(o2))
                    return 1;
                return -1;
            }
        };

        Date date = new Date();
        System.out.println(date.toLocaleString());
        Calendar instance = Calendar.getInstance();
        instance.set(2012,8,9,18,23,50);
        Date time = instance.getTime();
        System.out.println(instance.getTime().toLocaleString());
//        hh是十二小时制，HH是二十四小时制
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
        int compare = comparator.compare(date, time);

        List<Date> dates = Arrays.asList(date, time);
        System.out.println("date 开始");
       dates.forEach(date1 -> System.out.println(date1.toString()));
       Collections.sort(dates,(o1, o2) -> {
           if(o1.after(o2))
               return 1;
           return -1;
       });
        dates.forEach(date1 -> System.out.println(date1.toString()));

    }
}
