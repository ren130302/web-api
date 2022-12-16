package com.ren130302.utils;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class DateUtils {
	public static void main(String[] args) {
		String d = "2022-11-22T23:13:38Z";
		String timeAgo = calcTimeAgo(d);
		System.out.println(timeAgo);
	}

	static String calcTimeAgo(String value) {
		final Period period = new Period(DateTime.parse(value), DateTime.now());

		final String time = 0 < period.getYears() ? msg(period.getYears(), "year", "years") :
				0 < period.getMonths() ? msg(period.getMonths(), "month", "months") :
				0 < period.getWeeks() ? msg(period.getWeeks(), "week", "weeks") :
				0 < period.getDays() ? msg(period.getDays(), "day", "days") :
				0 < period.getHours() ? msg(period.getYears(), "hour", "hours") :
				0 < period.getMinutes() ? msg(period.getYears(), "minute", "minutes") :
				0 < period.getSeconds() ? msg(period.getYears(), "second", "seconds") :
				"moments";
		return String.format("%s %s", time, "ago");
	}

	/**
	 * @param time
	 * @param singularText
	 *            text to print if field value is one
	 * @param pluralText
	 *            text to print if field value is not one
	 * @return
	 */
	static String msg(int time, String text) {
		return String.format("%d %s", time, text);
	}

	/**
	 * @param time
	 * @param singularText
	 *            text to print if field value is one
	 * @param pluralText
	 *            text to print if field value is not one
	 * @return
	 */
	static String msg(int time, String singularText, String pluralText) {
		final String text = time == 1 ? singularText : pluralText;

		return String.format("%d %s", time, text);
	}
}