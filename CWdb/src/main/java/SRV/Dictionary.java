package SRV;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Artem on 31.10.2016.
 */
public class Dictionary {
    static Map<Integer, Integer> dictionary = new HashMap<Integer, Integer>();

    static void bbb(int key){
        if (dictionary.isEmpty()) {
            dictionary.put(key, 1);
        }
        else {
           // for (int i = 1; i <= dictionary.size(); i++) {
                if(dictionary.get(key) != null){
                    int temp = dictionary.get(key);
                    dictionary.put(key, (temp + 1));
                }
                else {
                    dictionary.put(key, 1);
                }
            //}
        }

    }

    static void qwe(int count) {
        Integer[] arr = new Integer[dictionary.size() + 1];
        System.out.println("Словарь: " + dictionary);
        arr[0] = 1000; // просто поставил большое значение, т.к. дальше сортирую по убыванию, и чтобы "0" элемент не учитывать
        for (int i = 1; i <= dictionary.size(); i++)
        {
            arr[i] = dictionary.get(i);
        }
        Arrays.sort(arr, Collections.<Integer>reverseOrder());
        System.out.print(arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3] + " " +arr[4] + " ");
    }

}
