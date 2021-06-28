package rock.dong.configuration;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import rock.dong.services.AsyncService;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class TaskSchedulerExample implements SchedulingConfigurer {
    @Autowired

    private TaskScheduler myThreadPoolTaskScheduler;
    private AsyncService asyncService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //简单粗暴的方式直接指定
        //scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(5));
        //也可以自定义的线程池，方便线程的使用与维护，这里不多说了
        final var cronTrigger = new CronTrigger("0 0 12 * * ?");
        scheduledTaskRegistrar.setTaskScheduler(myThreadPoolTaskScheduler);
        scheduledTaskRegistrar.addTriggerTask(() -> System.out.println("cccccccccccccccc--->" + Thread.currentThread().getId())
                ,cronTrigger);



    }


    @Bean(name = "myThreadPoolTaskScheduler")
    public TaskScheduler getMyThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.setThreadNamePrefix("Haina-Scheduled-");
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        taskScheduler.setAwaitTerminationSeconds(60);
        return taskScheduler;
    }
}
