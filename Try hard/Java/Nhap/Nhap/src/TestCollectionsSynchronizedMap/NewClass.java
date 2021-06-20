package TestCollectionsSynchronizedMap;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class NewClass {

    public static void main(String[] args) {
        // create map
        Map<String, String> map = new HashMap<String, String>();

        // populate the map
        map.put("1", "TP");
        map.put("2", "IS");
        map.put("3", "BEST");

        // create a synchronized map
        Map<String, String> synmap = Collections.synchronizedMap(map);

        System.out.println("Synchronized map is :" + synmap);

        Map<String, Date> previousCharge = Collections.synchronizedMap(new LinkedHashMap<String, Date>() {
            @Override
            protected boolean removeEldestEntry(final Map.Entry eldest) {
                return size() > 1000;
            }
        });
        
        System.out.println(previousCharge.get("dsdsd"));
    }

}
