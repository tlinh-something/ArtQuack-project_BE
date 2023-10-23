package Utilities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Constants {
	
		//Review 
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
}
