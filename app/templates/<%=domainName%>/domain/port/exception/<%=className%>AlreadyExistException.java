package <%- classPackage %>.<%- snakeName %>.domain.port.exception;

public class <%- className %>AlreadyExistException extends RuntimeException {
    public <%- className %>AlreadyExistException(Long <%- attributeName %>Id) {
        super("<%- className %> " + <%- attributeName %>Id + " already exist");
    }
}
