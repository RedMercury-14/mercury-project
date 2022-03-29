package by.myproject.main.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class DateValidator {
	private static final Logger log = Logger.getLogger(DateValidator.class);

	public String dateParser(String date) {
		StringBuilder builder = new StringBuilder();
		String result = null;
		String reformattedStr = null;
		try {
			Pattern pattern = Pattern.compile("[ ,.!?/-]");
			String[] words = pattern.split(date);
			for (String word : words) {
				result = builder.append(word).toString();
			}
			SimpleDateFormat fromUser = new SimpleDateFormat("ddMMyyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

			reformattedStr = myFormat.format(fromUser.parse(result));
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("ошибка проеобразования даты", e);
		}
		return reformattedStr;
	}

	public boolean isValidationDate(String date) {
		String result = null;
		List<SimpleDateFormat> dateFormats = new ArrayList<SimpleDateFormat>();
		dateFormats.add(new SimpleDateFormat("dd.mm.yyyy"));
		dateFormats.add(new SimpleDateFormat("dd-mm-yyyy"));
		dateFormats.add(new SimpleDateFormat("dd,mm,yyyy"));
		dateFormats.add(new SimpleDateFormat("dd,mm,yyyy"));
		dateFormats.add(new SimpleDateFormat("dd:mm:yyyy"));
		dateFormats.add(new SimpleDateFormat("dd/mm/yyyy"));
		dateFormats.add(new SimpleDateFormat("dd mm yyyy"));

		for (SimpleDateFormat format : dateFormats) {
			try {
				format.setLenient(false);
				result = format.parse(date).toString();
			} catch (ParseException e) {				
			}
			if (result != null) {
				break;
			}
		}
		Pattern pattern = Pattern.compile("[ ,.!?/-]");
		String[] words = pattern.split(date);
		int mount = Integer.parseInt(words[1]);		
		
		if (result != null && mount<=12) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isSQLValidationDate (String date){
		String result = null;
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");		
			try {
				myFormat.setLenient(false);
				result = myFormat.parse(date).toString();
			} catch (ParseException e) {
			}
		
		Pattern pattern = Pattern.compile("[ ,.!?/-]");
		String[] words = pattern.split(date);
		int mount = Integer.parseInt(words[1]);		
		
		if (result != null && mount<=12) {
			return true;
		} else {
			return false;
		}
	}

}
