package SRV;

import java.util.HashMap;
import java.util.Map;

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
    static Map<Integer, Integer> aaa(){
        return dictionary;
    }
}
