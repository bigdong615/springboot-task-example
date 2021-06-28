package rock.dong.services;

import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduledService {
    @Scheduled(cron = "0 0 12 * * ?")
    public void hello(){
        System.out.println("定时执行任务");
    }





}
