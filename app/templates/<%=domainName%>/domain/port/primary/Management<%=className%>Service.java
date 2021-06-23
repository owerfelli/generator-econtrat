package <%- classPackage %>.<%- snakeName %>.domain.port.primary;

import <%- classPackage %>.<%- snakeName %>.domain.port.primary.in.<%- className %>;

import java.util.List;
import java.util.Optional;

public interface Management<%- className %>Service {

    <%- className %> create<%- className %>(<%- className %> <%- attributeName %>);

    void delete<%- className %>(Long <%- attributeName %>Id);

    List<<%- className %>> searchAll<%- className %>s();

    Optional<<%- className %>> searchOne<%- className %>(Long <%- attributeName %>Id);

    void update<%- className %>(<%- className %> <%- attributeName %>);
}
