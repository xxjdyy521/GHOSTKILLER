package com.my.ghost.common.utils.quartz;

import java.util.Set;

import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.CronScheduleBuilder;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * quartz简单工具类
 * @author l
 *
 */
public class QuartzBasicUtils {
	
	/**
	 * 构建JobDetail
	 * 
	 * @param jobName
	 * @param jobGroup
	 * @param job
	 * @return
	 */
	public static JobDetail getJobDetail(String jobName,String jobGroup,Job job) {
		return JobBuilder.newJob(job.getClass()).withIdentity(jobName, jobGroup).build();
	}
	
	
	/**
	 * 获取cron表达式形式触发器
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 * @param cronExpression
	 * @return
	 */
	public static Trigger getCronTrigger(String triggerName,String triggerGroup,String cronExpression) {
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(triggerName, triggerGroup)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();
		return trigger;
	}
	
	/**
	 * 获取循环形式触发器----(秒)
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 * @param seconds
	 * @return
	 */
	public static Trigger getSimpleTrigger(String triggerName,String triggerGroup,Integer seconds) {
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(triggerName, triggerGroup)
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(seconds))
				.build();
		return trigger;
	}
	
	/**
	 *  SimpleTrigger升级版
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 * @param timeInterval ---时间间隔
	 * @param intervalUnit ---enum类型  MILLISECOND, SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR 
	 * @return
	 */
	public static Trigger getCalendarIntervalTrigger(String triggerName,String triggerGroup,int timeInterval,IntervalUnit intervalUnit) {
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(triggerName, triggerGroup)
				.withSchedule(CalendarIntervalScheduleBuilder
						.calendarIntervalSchedule()
						.withInterval(timeInterval, intervalUnit))
				.build();
		return trigger;
	}
	
	
	/**
	 * 每天开始的时间和结束的时间
	 * 
	 * @param triggerName
	 * @param triggerGroup
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static Trigger getDailyTimeIntervalTrigger(String triggerName,String triggerGroup,TimeOfDay startTime,TimeOfDay endTime) {
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity(triggerName, triggerGroup)
				.withSchedule(DailyTimeIntervalScheduleBuilder
						.dailyTimeIntervalSchedule()
						.startingDailyAt(startTime)
						.endingDailyAt(endTime))
				.build();
		return trigger;
	}
	
	/**
	 * 执行job 一个JobDetail对应一个trigger
	 * 
	 * @param jobDetail
	 * @param trigger
	 * @return
	 */
	public static Boolean jobStart(JobDetail jobDetail,Trigger trigger) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start();
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 执行job 一个JobDetail对应多个trigger
	 * 
	 * @param jobDetail
	 * @param triggerSet
	 * @param replace
	 * @return
	 */
	public static Boolean jobStartWithTriggers(JobDetail jobDetail,Set<? extends Trigger> triggerSet,Boolean replace) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.scheduleJob(jobDetail, triggerSet, false);
			scheduler.start();
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}
}
