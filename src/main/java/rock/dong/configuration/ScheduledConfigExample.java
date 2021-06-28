package rock.dong.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import rock.dong.services.AsyncService;

@Configuration
public class ScheduledConfigExample implements SchedulingConfigurer {

    @Autowired
    private TaskScheduler myThreadPoolTaskScheduler;
    private AsyncService asyncService;
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
        scheduledTaskRegistrar.setTaskScheduler(myThreadPoolTaskScheduler);
        //可以实现动态调整定时任务的执行频率
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> asyncService.hello(),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库动态获取执行周期
                    String cron = "0/2 * * * * ? ";
                    //2.2 合法性校验.
                    //                    if (StringUtils.isEmpty(cron)) {
                    //                        // Omitted Code ..
                    //                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
