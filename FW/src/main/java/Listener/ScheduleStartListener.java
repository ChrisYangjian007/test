package Listener;

import Core.Job.QuartzManager;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by Administrator on 2017-08-11.
 */
public class ScheduleStartListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            QuartzManager.Instance().closeAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void contextInitialized(ServletContextEvent sce) {
        try {
            recovery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void recovery() {
        Scheduler scheduler = null;
//        WeakReference<DBExecutor> m_WeakReference = new WeakReference<DBExecutor>(new DBExecutor(4));
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            QuartzManager.Instance().setgSchedulerFactory(schedulerFactory);
            scheduler = schedulerFactory.getScheduler();//可以通过SchedulerFactory创建一个Scheduler实例
            List<String> triggerGroups = scheduler.getTriggerGroupNames();//获取调度器中所有的触发器组
            System.out.println("调度器中所有的触发器组 size():" + triggerGroups.size());

            if (triggerGroups != null && triggerGroups.size() != 0)//重新恢复在triggerGroups组中所有的触发器
            {
                for (int i = 0; i < triggerGroups.size(); i++) {
                    TriggerKey triggerKey = TriggerKey.triggerKey(triggerGroups.get(i), triggerGroups.get(i));
                    System.out.println("triggerKey:" + triggerKey);

                    Trigger tg = scheduler.getTrigger(triggerKey);//获取trigger
                    System.out.println(triggerKey + " -> 执行时间 :" + tg.getNextFireTime());

                    scheduler.rescheduleJob(triggerKey, tg);//按新的trigger重新设置job执行
                }
            }
            scheduler.start();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
