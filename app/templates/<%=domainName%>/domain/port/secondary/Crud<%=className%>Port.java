package <%- classPackage %>.<%- snakeName %>.domain.port.secondary;

import <%- classPackage %>.<%- snakeName %>.domain.port.primary.in.<%- className %>;

import java.util.List;
import java.util.Optional;


public interface Crud<%- className %>Port {

    <%- className %> save<%- className %>(<%- className %> <%- attributeName %>);

    void delete<%- className %>(Long <%- attributeName %>Id);

    List<<%- className %>> findAll<%- className %>();

    Optional<<%- className %>> find<%- className %>ById(Long <%- attributeName %>Id);

    <%- className %> update<%- className %>(<%- className %> <%- attributeName %>);

    boolean exists<%- className %>(Long <%- attributeName %>Id);

}
