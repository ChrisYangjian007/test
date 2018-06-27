package Core.Job;

//import org.apache.log4j.Logger;
import org.quartz.*;

import java.util.Date;

/**
 * Created by Administrator on 2017-08-11.
 */
public class QuartzManager {
    /// log4对象
//    private static Logger logger = Logger.getLogger(QuartzManager.class);
    private static SchedulerFactory gSchedulerFactory;
    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

    private static QuartzManager _item;
    public static QuartzManager Instance() {
        if (_item == null) {
            _item = new QuartzManager();
        }
        return _item;
    }

    public void setgSchedulerFactory(SchedulerFactory gSchedulerFactory) {
        QuartzManager.gSchedulerFactory = gSchedulerFactory;
    }

    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     * @param jobName
     * @param time
     */
    @SuppressWarnings("rawtypes")
    public Boolean addOrModifyJob(String jobName, String className, String time) throws ClassNotFoundException {
        boolean flag = false;
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            //修改当前执行器中相应的定时任务时间
            if (!sched.checkExists(new JobKey(jobName, JOB_GROUP_NAME))) {
                Date runTime = DateBuilder.evenSecondDateAfterNow();
                Class classT = Class.forName(className);
                //用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
                JobDetail jobDetail = JobBuilder.newJob(classT).withIdentity(jobName, JOB_GROUP_NAME).build();
//              jobDetail.getJobDataMap().put("testId", id);        //设置存储参数(不需要可删除)

                //创建一个新的TriggerBuilder来规范一个触发器
                //给触发器一个名字和组名
                Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME)
                        .startAt(runTime)//设置触发开始的时间
                        .withSchedule(CronScheduleBuilder.cronSchedule(time))
                        .build();//产生触发器
                sched.scheduleJob(jobDetail, trigger);
                System.out.println(jobDetail.getKey() + " 运行在: " + runTime);
                sched.start();
                flag=true;
            } else {
                CronTrigger trigger = (CronTrigger) sched.getTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));
                if (trigger == null) {
                    return false;
                } else {
                    String oldCronExp = trigger.getCronExpression();
                    if (!oldCronExp.equalsIgnoreCase(time)) {
                        // 触发器
                        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                        // 触发器名,触发器组
                        triggerBuilder.withIdentity(jobName, TRIGGER_GROUP_NAME);
                        triggerBuilder.startNow();
                        // 触发器时间设定
                        triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(time));
                        // 创建Trigger对象
                        trigger = (CronTrigger) triggerBuilder.build();
                        // 修改一个任务的触发时间
                        sched.rescheduleJob(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME), trigger);
                        flag=true;
                    }
                }
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public boolean removeJob(String jobName) throws SchedulerException {
        boolean flag = false;
        Scheduler sched = gSchedulerFactory.getScheduler();
        try {
            if (sched.checkExists(JobKey.jobKey(jobName, JOB_GROUP_NAME))) {
                sched.pauseTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));// 停止触发器
                sched.unscheduleJob(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));// 移除触发器
                sched.deleteJob(JobKey.jobKey(jobName, JOB_GROUP_NAME));// 删除任务
                flag = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public boolean pauseJob(String jobName) throws SchedulerException {
        boolean flag = false;
        Scheduler sched = gSchedulerFactory.getScheduler();
        try {
            if (sched.checkExists(JobKey.jobKey(jobName, JOB_GROUP_NAME))) {
                sched.pauseTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));// 停止触发器
                flag=true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public boolean closeAll() throws SchedulerException {
        boolean flag = false;
        Scheduler sched = gSchedulerFactory.getScheduler();
        try {
            if (!sched.isShutdown()) {
                sched.pauseAll();
                sched.shutdown();
                flag = true;
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public boolean resumeJob(String jobName) throws SchedulerException {
        boolean flag = false;
        Scheduler sched = gSchedulerFactory.getScheduler();
        try {
            if (sched.checkExists(JobKey.jobKey(jobName, JOB_GROUP_NAME))) {
                sched.resumeTrigger(TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME));// 重启触发器
                flag=true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

    public boolean startAll() throws SchedulerException {
        boolean flag = false;
        Integer count = 0;
        Scheduler sched = gSchedulerFactory.getScheduler();
        try {
            if (!sched.isStarted()) {
                sched.start();
                flag = true;
            } else {
//                logger.error(" The Error Count is :" + count);
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
}
