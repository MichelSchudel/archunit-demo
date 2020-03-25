package nl.craftsmen.archunitdemo.api.helloservice;

import nl.craftsmen.archunitdemo.core.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/echo")
    public HelloResponseModel echo() {
        return new HelloResponseModel();
    }
}
