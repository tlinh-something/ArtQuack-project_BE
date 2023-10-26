package com.swp.ArtQuack.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Constants {

	//Review cutoffs
		public static final int REVIEW_MIN = 1;
		public static final int REVIEW_MAX = 5;
		
		//VALIDATION
		public static final int USERNAME_MIN = 6;
		public static final int PASSWORD_MIN = 6;
		
		//Date
		public static final Date START_DATE = new Date(05/05/2023);
		public static final Date currentDate() {
			return new Date();
		}
		
		public static final Date subtractDaysFromDate(Date date, int days) {
			LocalDate currenDate = LocalDate.now();
			currenDate.minusDays(days);
			return Date.from(currenDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		}
		
		
		//Date format
		public static final Date strToDate(String dateStr) {
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		     try {
		         Date date = dateFormat.parse(dateStr);
		         return date;
		     } catch (ParseException e) {
		         System.out.println("Error parsing date: " + e.getMessage());
		     }
		     return null;
	     }
		
		public static final Date subtractDay(Date date, int offset) {
			LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
			ldt.minusDays(offset);
			return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		}
}
