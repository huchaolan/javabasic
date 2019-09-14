package generics.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.junit.Test;

/**
 * Java8日期时间学习
 * @author hucha
 *
 */
public class TimeInJava8Demo {

	/**
	 * 在 Java 8 中获取今天的日期,只有日期，没有时分秒
	 */
	@Test
	public void getCurrentDate() {
		LocalDate now = LocalDate.now();
		System.out.println(now);
	}
	
	/**
	 * 获取单独年月日
	 * now.getYear()
	 * now.getMonthValue() 月份从1开始
	 * now.getDayOfMonth()
	 */
	@Test
	public void getYMD() {
		LocalDate now = LocalDate.now();
		System.out.println(String.format("year:%s,month:%s,day:%s",
				now.getYear(),now.getMonthValue(),now.getDayOfMonth()));
	}
	
	/**
	 * 在 Java 8 中处理特定日期
	 */
	@Test
	public void setSpecialDate() {
		LocalDate aday = LocalDate.of(2019, 9, 14);
		System.out.println(aday);
	}
	
	/**
	 * 在 Java 8 中日期是否相等
	 */
	@Test
	public void equalsDate() {
		LocalDate aday = LocalDate.of(2019, 9, 14);
		LocalDate now = LocalDate.now();
		System.out.println(aday.equals(now));
	}
	
	/**
	 * 检查月日周期
	 */
	@Test
	public void monthDay() {
		LocalDate aday = LocalDate.of(2019, 9, 14);
		LocalDate now = LocalDate.now();
		
		MonthDay aMonDay= MonthDay.of(aday.getMonthValue(),aday.getDayOfMonth());
		MonthDay nowMonDay = MonthDay.from(now);
		System.out.println(aMonDay.equals(nowMonDay));
	}
	
	/**
	 * 获取当前时间
	 * 输出15:55:21.114
	 */
	@Test
	public void nowTime() {
		LocalTime nowTime = LocalTime.now();
		System.out.println(nowTime);
	}
	
	/**
	 * 增加小时
	 * 使用plusHours增加小时，注意LocalTime是不变的对象，每次运算完了要重新赋值
	 */
	@Test
	public void addHour() {
		LocalTime nowTime = LocalTime.now();
		LocalTime afterOneHour = nowTime.plusHours(1);
		System.out.println(nowTime);
		System.out.println(afterOneHour);
	}
	
	/**
	 * 添加各种单位时间
	 * ChronoUnit有各种单位，年月日世纪等等
	 */
	@Test
	public void addChronoUnit(){
		LocalDate nowDate = LocalDate.now();
		LocalDate afterOneWeek = nowDate.plus(1, ChronoUnit.WEEKS);
		System.out.println(nowDate);
		System.out.println(afterOneWeek);
	}
	
	/**
	 * 时间获取日期的比较
	 * Date.isAfter(otherDate) true表示Date大于或等于otherDate
	 * Date.isBefore(otherDate) true表示Date小于或等于otherDate
	 */
	@Test
	public void compareTime() {
		LocalDate nowDate = LocalDate.now();
		LocalDate afterOneDay = nowDate.plus(1,ChronoUnit.DAYS);
		LocalDate beforeOneDay = nowDate.minus(1,ChronoUnit.DAYS);
		System.out.println(nowDate.isAfter(afterOneDay));
		System.out.println(nowDate.isAfter(beforeOneDay));
		System.out.println(nowDate.isBefore(afterOneDay));
		System.out.println(nowDate.isBefore(beforeOneDay));
	}
	
	/**
	 * 时区
	 * ZonedDateTime使用默认时区展示
	 * 2019-09-14T17:34:27.917+08:00[Asia/Shanghai]
	   2019-09-14T17:34:27.917-07:00[America/Los_Angeles]
	   只有时区不一致
	 */
	@Test
	public void timezone() {
		ZoneId china = ZoneId.of("Asia/Shanghai");
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime dateAndTimeInChina  = ZonedDateTime.of(now, china );
		System.out.println(dateAndTimeInChina);
		ZoneId Los_Angeles = ZoneId.of("America/Los_Angeles");
		ZonedDateTime dateAndTimeInLos_Angeles = ZonedDateTime.of(now, Los_Angeles );
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm:ss", Locale.US);
		System.out.println(dateAndTimeInLos_Angeles.format(dtf));
	}
}
