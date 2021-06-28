package rock.dong.services;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Service;

@Service
public class TaskForMyThreadpool {

    public void myTask(){
        System.out.println("theadpool test");
    }

}
