package com.itsz.sht.struts.eipo.dao;

import java.util.Comparator;

public class EIPOMasterEntryComparator implements Comparator<EIPOMasterEntry> {

    public boolean equals(Object o) {
        boolean result = false;
        return result;
    }

	public int compare(EIPOMasterEntry o1, EIPOMasterEntry o2) {
		int result = -1;
		if (o1.getIpoId() != null && o2.getIpoId() != null) {
			Long t1 = Long.valueOf(o1.getIpoId());
			Long t2 = Long.valueOf(o2.getIpoId());
			result = t1.compareTo(t2);
		}
        return result;
	}
}
