package SRV;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Artem on 31.10.2016.
 */
public class Dictionary {
    static Map<Integer, Integer> dictionary = new HashMap<Integer, Integer>();
    static ArrayList<Integer> keyList = new ArrayList<>();
    static ArrayList<Integer> indexList = new ArrayList<>();
    static ArrayList<Integer> canCook = new ArrayList<>();
    static ArrayList<Integer> noCanCook = new ArrayList<>();

    static void AddDictionary(int key){
        if (dictionary.isEmpty()) {
            dictionary.put(key, 1);
            keyList.add(key);
        }
        else {
           // for (int i = 1; i <= dictionary.size(); i++) {
                if(dictionary.get(key) != null){
                    int temp = dictionary.get(key);
                    dictionary.put(key, (temp + 1));
                }
                else {
                    dictionary.put(key, 1);
                    keyList.add(key);
                }
            //}
        }

    }

    static void addList () {
        for(int i = 1; i <= dictionary.size(); i++)
        {
            indexList.add(dictionary.get(keyList.get(i-1)));
        }
    }

    static void DistributionId(ArrayList key, ArrayList value, ArrayList valueTable)
    {
        for (int i = 0; i < value.size(); i++)
        {
            int sub = (int)valueTable.get(i) - (int)value.get(i);
            if (sub == 0)
                canCook.add(keyList.get(i));
            if ( sub <= 3 && sub > 0) {
                noCanCook.add(keyList.get(i));
            }
        }
    }

}
