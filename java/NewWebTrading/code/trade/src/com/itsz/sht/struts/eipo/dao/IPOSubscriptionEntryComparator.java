package com.itsz.sht.struts.eipo.dao;

import java.util.Comparator;

public class IPOSubscriptionEntryComparator implements Comparator<EIPOSubscriptionEntry> {

    public boolean equals(Object o) {
        boolean result = false;
        return result;
    }

	public int compare(EIPOSubscriptionEntry o1, EIPOSubscriptionEntry o2) {
		int result = -1;
		if (o1.getSubscriptionId() != null && o2.getSubscriptionId() != null) {
			result = o1.getSubscriptionId().compareTo(o2.getSubscriptionId());
		}
        return result;
	}
}
