package <%- classPackage %>.<%- snakeName %>.domain.port.exception;

public class <%- className %>NotFoundException extends RuntimeException {
    public <%- className %>NotFoundException(Long <%- attributeName %>Id) {
        super("<%- className %> " + <%- attributeName %>Id + " not found");
    }
}
