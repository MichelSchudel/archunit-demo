package nl.craftsmen.archunitdemo.core;

import nl.craftsmen.archunitdemo.api.helloservice.HelloController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    private HelloClient helloClient;

    public String echo() {
        return helloClient.echo();
    }

}
