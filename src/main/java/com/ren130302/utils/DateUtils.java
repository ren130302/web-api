package com.ren130302.utils;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Period;

import lombok.Value;

@Value(staticConstructor = "of")
public class DateUtils {

	public static final int YEAR = 1;
	public static final int MONTH = 1;
	public static final int WEEK = 1;
	public static final int DAY = 1;
	public static final int HOUR = 1;
	public static final int MINUTE = 1;
	public static final int SECOND = 1;
	public static final int ETC = 1;

	private final Period period;
	private final List<String> list = new ArrayList<>();

	public static DateUtils of(DateTime dateTime) {
		return DateUtils.of(new Period(dateTime, DateTime.now()));
	}

	public static DateUtils of(String dateTime) {
		return DateUtils.of(DateTime.parse(dateTime));
	}

	public static void main(String[] args) {
		String d = "2022-11-22T23:13:38Z";
		String timeAgo = of(d).printTimeAgo();
		System.out.println(timeAgo);
	}

	public String printTimeAgo() {
		return printTimeAgo(this.getPeriod());
	}

	static String printTimeAgo(final Period period) {
		final String time = 0 < period.getYears() ? text(period.getYears(), "year", "years") :
				0 < period.getMonths() ? text(period.getMonths(), "month", "months") :
				0 < period.getWeeks() ? text(period.getWeeks(), "week", "weeks") :
				0 < period.getDays() ? text(period.getDays(), "day", "days") :
				0 < period.getHours() ? text(period.getHours(), "hour", "hours") :
				0 < period.getMinutes() ? text(period.getMinutes(), "minute", "minutes") :
				0 < period.getSeconds() ? text(period.getSeconds(), "second", "seconds") :
				"moments";

		return String.format("%s %s", time, "ago");
	}

	public static String checkTime(int time, String text) {
		return 0 < time ? DateUtils.text(time, text) : "";
	}

	public static String checkTime(int time, String singularText, String pluralText) {
		return 0 < time ? DateUtils.text(time, singularText, pluralText) : "";
	}

	/**
	 * @param time
	 * @param singularText
	 *            text to print if field value is one
	 * @param pluralText
	 *            text to print if field value is not one
	 * @return
	 */
	public static String text(int time, String text) {
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
	public static String text(int time, String singularText, String pluralText) {
		final String text = time == 1 ? singularText : pluralText;

		return String.format("%d %s", time, text);
	}
}