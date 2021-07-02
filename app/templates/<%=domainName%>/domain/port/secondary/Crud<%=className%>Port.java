package <%- classPackage %>.<%- snakeName %>.domain.port.secondary;

import <%- classPackage %>.<%- snakeName %>.domain.port.primary.in.<%- className %>;

import java.util.List;
import java.util.Optional;


public interface Crud<%- className %>Port {
<%_ if (resources.indexOf('create')> -1) { _%>
    <%- className %> save<%- className %>(<%- className %> <%- attributeName %>);
<%_ } _%>
<%_ if (resources.indexOf('delete')> -1) { _%>
    void delete<%- className %>(Long <%- attributeName %>Id);
<%_ } _%>
<%_ if (resources.indexOf('findAll')> -1) { _%>
    List<<%- className %>> findAll<%- className %>();
<%_ } _%>
<%_ if (resources.indexOf('findOne')> -1) { _%>
    Optional<<%- className %>> find<%- className %>ById(Long <%- attributeName %>Id);
<%_ } _%>
<%_ if (resources.indexOf('update')> -1) { _%>
    <%- className %> update<%- className %>(<%- className %> <%- attributeName %>);
<%_ } _%>
    boolean exists<%- className %>(Long <%- attributeName %>Id);
}
