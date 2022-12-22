package com.ren130302.utils;

import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.joda.time.DateTime;
import org.joda.time.Period;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

@Value(staticConstructor = "of")
public class DateUtils {

	public static void main(String[] args) {
		String d = "2022-12-22T03:13:38Z";
		final String timeAgo = of(d).add(Unit.YEARS, "year", "years").add(Unit.MONTHS, "month", "months").add(Unit.WEEKS, "week", "weeks").add(Unit.DAYS, "day", "days").add(Unit.HOURS, "hour", "hours").add(Unit.MINUTES, "minute", "minutes").add(Unit.SECONDS, "second", "seconds").print();
		System.out.println(timeAgo.isBlank() ? "today" : timeAgo);
	}

	private final Period period;
	private final Map<Unit, Function<Integer, String>> list = new HashMap<>();

	public static DateUtils of(DateTime dateTime) {
		return DateUtils.of(new Period(dateTime, DateTime.now()));
	}

	public static DateUtils of(String dateTime) {
		return DateUtils.of(DateTime.parse(dateTime));
	}

	private DateUtils add(Unit chronoUnit, Function<Integer, String> func) {
		this.list.put(chronoUnit, func);
		return this;
	}

	public DateUtils add(Unit chronoUnit, String text) {
		return this.add(chronoUnit, t -> text(t, text));
	}

	public DateUtils add(Unit chronoUnit, String singularText, String pluralText) {
		return this.add(chronoUnit, t -> text(t, singularText, pluralText));
	}

	public String print() {
		for (Unit unit : this.list.keySet().stream().sorted().toList()) {
			final int time = unit.value().apply(this.period);
			final String msg = this.list.get(unit).apply(time);

			if (0 >= time) {
				continue;
			}

			return msg;
		}

		return "";
	}

	static String printTimeAgo(final Period period) {
		final String time = 0 < period.getYears() ? text(period.getYears(), "year", "years")
				: 0 < period.getMonths() ? text(period.getMonths(), "month", "months")
				: 0 < period.getWeeks() ? text(period.getWeeks(), "week", "weeks")
				: 0 < period.getDays() ? text(period.getDays(), "day", "days")
				: 0 < period.getHours() ? text(period.getHours(), "hour", "hours")
				: 0 < period.getMinutes() ? text(period.getMinutes(), "minute", "minutes")
				: 0 < period.getSeconds() ? text(period.getSeconds(), "second", "seconds")
				: "moments";

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
	private static String text(int time, String text) {
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
	private static String text(int time, String singularText, String pluralText) {
		final String text = time == 1 ? singularText : pluralText;

		return String.format("%d %s", time, text);
	}

	@RequiredArgsConstructor
	@Getter
	@Accessors(fluent = true)
	public enum Unit {
		YEARS(ChronoUnit.YEARS, p -> p.getYears()),
		MONTHS(ChronoUnit.MONTHS, p -> p.getMonths()),
		WEEKS(ChronoUnit.WEEKS, p -> p.getWeeks()),
		DAYS(ChronoUnit.DAYS, p -> p.getDays()),
		HOURS(ChronoUnit.HOURS, p -> p.getHours()),
		MINUTES(ChronoUnit.MINUTES, p -> p.getMinutes()),
		SECONDS(ChronoUnit.SECONDS, p -> p.getSeconds());

		private final ChronoUnit chronoUnit;
		private final Function<Period, Integer> value;

		public static boolean greaterThanZero(int time) {
			return 0 < time;
		}
	}
}