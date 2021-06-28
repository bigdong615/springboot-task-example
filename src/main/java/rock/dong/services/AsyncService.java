package rock.dong.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service

public class AsyncService {
    @Async
    public void hello(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("系统已经启动");


    }
}

