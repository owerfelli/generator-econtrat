package <%- classPackage %>.<%- snakeName %>.domain.port.primary;

import <%- classPackage %>.<%- snakeName %>.domain.port.primary.in.<%- className %>;

import java.util.List;
import java.util.Optional;

public interface Management<%- className %>Service {
<%_ if (resources.indexOf('create')> -1) { _%>
    <%- className %> create<%- className %>(<%- className %> <%- attributeName %>);
<%_ } _%>
<%_ if (resources.indexOf('delete')> -1) { _%>
    void delete<%- className %>(Long <%- attributeName %>Id);
<%_ } _%>
<%_ if (resources.indexOf('findAll')> -1) { _%>
    List<<%- className %>> searchAll<%- className %>s();
<%_ } _%>
<%_ if (resources.indexOf('findOne')> -1) { _%>
    Optional<<%- className %>> searchOne<%- className %>(Long <%- attributeName %>Id);
<%_ } _%>
<%_ if (resources.indexOf('update')> -1) { _%>
    void update<%- className %>(<%- className %> <%- attributeName %>);
<%_ } _%>
}
