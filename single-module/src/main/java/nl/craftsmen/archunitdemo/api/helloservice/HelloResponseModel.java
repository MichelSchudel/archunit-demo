package nl.craftsmen.archunitdemo.api.helloservice;

public class HelloResponseModel {

    private String message;

    public HelloResponseModel(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
