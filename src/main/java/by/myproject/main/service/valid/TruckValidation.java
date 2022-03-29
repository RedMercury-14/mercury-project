package by.myproject.main.service.valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import by.myproject.main.entity.Truck;

public class TruckValidation {
	private Truck truck;

	public TruckValidation(Truck truck) {
		super();
		this.truck = truck;
		process();
	}

	public void process() throws NotDateFormatException, NotDataFieldException{
		String numCar = truck.getNumCar();
		String numTr = truck.getNumTr();
		String carModel = truck.getCarModel();
		String trModel = truck.getTrModel();
		String typeTr = truck.getTrModel();
		String dateCar = truck.getDateCar().trim();
		String dateTr = truck.getDateTr().trim();

		if(numCar.isEmpty()|| numTr.isEmpty() ||carModel.isEmpty() || trModel.isEmpty() || typeTr.isEmpty() || dateCar.isEmpty() || dateTr.isEmpty()) {
			throw new NotDataFieldException("NotDataFieldException");
		}else if (!isValidationDate(dateCar) || !isValidationDate(dateTr)) {
			throw new NotDateFormatException("NotDateFormatException");
		} 

	}

	private boolean isValidationDate(String date) {
		if(date.length() !=10) {
			return false;
		}
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

		if (result != null && mount <= 12) {
			return true;
		} else {
			return false;
		}
	}

}
