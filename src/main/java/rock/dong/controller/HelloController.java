package rock.dong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rock.dong.configuration.TaskSchedulerExample;
import rock.dong.services.AsyncService;

@RestController
public class HelloController {

    @Autowired
    AsyncService asyncService;

    @RequestMapping("/hello")
    public String hello()  {
        asyncService.hello();
        return "ok";
    }
}
