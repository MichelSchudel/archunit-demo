package nl.craftsmen.archunitdemo.client;

import nl.craftsmen.archunitdemo.core.HelloClient;
import org.springframework.stereotype.Component;

@Component
public class HelloClientImpl implements HelloClient {

    public String echo() {
        return "hello";
    }
}
