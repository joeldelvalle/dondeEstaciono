package coop.tecso.donde.estaciono.utils;

import java.util.Calendar;

/**
 * 
 * @author joel.delvalle
 * 
 */
public class DESTime {

	public static Calendar getToday() {
		return Calendar.getInstance();
	}

	public static Long calculateDifferenceBetweenTwoCalendars(Calendar start, Calendar end) {

		long daysInMilis = end.getTimeInMillis() - start.getTimeInMillis();

		return Math.round(daysInMilis / 86400000D); // 1000 * 60 * 60 * 24
	}

}
