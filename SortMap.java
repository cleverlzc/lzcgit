package interview.basic;

import java.util.*;
import java.util.stream.Collectors;

public class SortMap {
    public static void main(String[] args) {
        //keyUpSort();
        //keyDownSort();
        //valueUpSort();
        valueUpSortOfJdk8();
    }


    public static void keyUpSort()
    {
        //default, TreeMap sorted by key in ascending order
        Map<String, Integer> map = new TreeMap<String, Integer>();
        map.put("acb1", 5);
        map.put("bac1", 3);
        map.put("bca1", 20);
        map.put("cab1", 80);
        map.put("cba1", 1);
        map.put("abc1", 10);
        map.put("abc2", 12);

        for (Map.Entry<String, Integer> entry:map.entrySet())
        {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static void keyDownSort()
    {
        Comparator<String> keyComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        };

        Map<String, Integer> map = new TreeMap<String, Integer>(keyComparator);
        map.put("acb1", 5);
        map.put("bac1", 3);
        map.put("bca1", 20);
        map.put("cab1", 80);
        map.put("cba1", 1);
        map.put("abc1", 10);
        map.put("abc2", 12);

        for (Map.Entry<String, Integer> entry:map.entrySet())
        {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }


    public static void valueUpSort()
    {
        //default, TreeMap sorted by key in ascending order
        Map<String, Integer> map = new TreeMap<String, Integer>();
        map.put("acb1", 5);
        map.put("bac1", 3);
        map.put("bca1", 20);
        map.put("cab1", 80);
        map.put("cba1", 1);
        map.put("abc1", 10);
        map.put("abc2", 12);

        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        };

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, valueComparator);

        for (Map.Entry<String, Integer> entry : list)
        {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public static void valueUpSortOfJdk8()
    {
        //default, TreeMap sorted by key in ascending order
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("acb1", 5);
        map.put("bac1", 3);
        map.put("bca1", 20);
        map.put("cab1", 80);
        map.put("cba1", 1);
        map.put("abc1", 10);
        map.put("abc2", 12);


        List<Map.Entry<String, Integer>> list = map.entrySet().stream()
                .sorted((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))
                .collect(Collectors.toList());

        for (Map.Entry<String, Integer> entry : list)
        {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
