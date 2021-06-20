package sample.camel;

import org.mongodb.morphia.query.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NghiaTM on 8/8/2016.
 */
public class PartnerManager {

    List<Partner> partnerList = new ArrayList<Partner>();
    Map<String, Partner> partnerMap = new HashMap<String, Partner>();

    private static PartnerManager partnerManager;
    private static final Object lock = new Object();

    public static PartnerManager getInstance() {
        PartnerManager manager = partnerManager;
        if (manager == null) {
            synchronized (lock) {
                manager = partnerManager;
                if (manager == null) {
                    manager = new PartnerManager();
                    partnerManager = manager;
                }
            }
        }

        return manager;
    }

    public int getSize() {
        return partnerList.size();
    }

    public Partner getPartner(String id) {
        if (!partnerMap.containsKey(id)) {
            return null;
        }
        return partnerMap.get(id);
    }

    public boolean isPartner(String pId) {
        boolean result = true;

        Partner partner = partnerMap.get(pId);
        if (partner == null) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public boolean delete(String pId) throws IOException {

        int index = -1;
        for (int i = 0; i < partnerList.size(); i++) {
            if (partnerList.get(i).get_id().toString().equals(pId)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            partnerList.remove(index);
            partnerMap.remove(pId);
            return true;
        } else {
            return false;
        }

    }

    public List<Partner> getPartnerList() {
        return partnerList;
    }

    public void setPartnerList(List<Partner> partnerList) {
        this.partnerList = partnerList;
    }

    public Map<String, Partner> getPartnerMap() {
        return partnerMap;
    }

    public void setPartnerMap(Map<String, Partner> partnerMap) {
        this.partnerMap = partnerMap;
    }
}
