package de.ToxicGaming.Cloud.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeManager {
	public static DateFormat format = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");
	public String getEndeAnzeige(long end) {
		Date date = new Date(end);
		return format.format(date);
	}
}
