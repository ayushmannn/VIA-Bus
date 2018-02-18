public class Time implements java.io.Serializable {
	private static final long serialVersionUID = 6529685098267757690L;
	private int hour, minute, second;

	public Time(int h, int m, int s) {
		this.hour = h;
		this.minute = m;
		this.second = s;

		if (h < 0)
			this.hour = 0;
		else if (h > 23)
			this.hour = 23;

		if (m < 0)
			this.minute = 0;
		else if (m > 59)
			this.minute = 59;

		if (s < 0)
			this.second = 0;
		else if (s > 59)
			this.second = 59;
	}

	public Time(int s) {
		if (s / 3600 == 0)
			hour = 0;
		else {
			hour = s / 3600;
			s = s - (hour * 3600);
		}
		if (s / 60 == 0)
			minute = 0;
		else {
			minute = s / 60;
			if (minute == 60) {
				minute = 0;
				hour++;
			}
			s = s - (minute * 60);
		}
		if ((hour == 0) && (minute == 0))
			second = s;
		else
			second = s % 60;
		if (second == 60) {
			second = 0;
			minute++;
		}
	}

	public int getTimeInSeconds() {
		int total = hour * 3600 + minute * 60 + second;
		return total;
	}

	@Override
	public String toString() {
		String all = null;

		int length = String.valueOf(hour).length();
		for (int i = 0; i < 2 - length; i++)
			all = "0";

		if (all != null)
			all = all + hour + ":";
		else
			all = hour + ":";

		length = String.valueOf(minute).length();
		for (int i = 0; i < 2 - length; i++)
			all = all + "0";
		all = all + minute;
		/*
		 * length = String.valueOf(second).length(); for (int i = 0; i < 2 -
		 * length; i++) all = all + "0"; all = all + second;
		 */
		return all;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}
	
	public Time copy() {
		Time timeCopy = new Time (this.hour, this.minute, this.second);
		return timeCopy;
	}

}
