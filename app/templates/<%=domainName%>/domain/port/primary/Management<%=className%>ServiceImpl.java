package <%- classPackage %>.<%- snakeName %>.domain.port.primary;


import <%- classPackage %>.common.annotation.UseCase;
import <%- classPackage %>.<%- snakeName %>.domain.port.exception.<%- className %>AlreadyExistException;
import <%- classPackage %>.<%- snakeName %>.domain.port.exception.<%- className %>NotFoundException;
import <%- classPackage %>.<%- snakeName %>.domain.port.primary.in.<%- className %>;
import <%- classPackage %>.<%- snakeName %>.domain.port.secondary.Crud<%- className %>Port;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class Management<%- className %>ServiceImpl implements Management<%- className %>Service {

    private final Crud<%- className %>Port crud<%- className %>Port;

<%_ if (resources.indexOf('findAll')> -1) { _%>
    @Override
    public List<<%- className %>> searchAll<%- className %>s() {
        return crud<%- className %>Port.findAll<%- className %>();
    }
<%_ } _%>
<%_ if (resources.indexOf('findOne')> -1) { _%>
    @Override
    public Optional<<%- className %>> searchOne<%- className %>(Long <%- attributeName %>Id) {
        return crud<%- className %>Port.find<%- className %>ById(<%- attributeName %>Id);
    }
<%_ } _%>
<%_ if (resources.indexOf('create')> -1) { _%>
    @Override
    public <%- className %> create<%- className %>(<%- className %> <%- attributeName %>) {
        if (<%- attributeName %>.getId() != null && crud<%- className %>Port.exists<%- className %>(<%- attributeName %>.getId())) {
            throw new <%- className %>AlreadyExistException(<%- attributeName %>.getId());
        }
        return crud<%- className %>Port.save<%- className %>(<%- attributeName %>);
    }
<%_ } _%>
<%_ if (resources.indexOf('delete')> -1) { _%>
    @Override
    public void delete<%- className %>(Long <%- attributeName %>Id) {
        boolean exists = crud<%- className %>Port.exists<%- className %>(<%- attributeName %>Id);
        if (!exists) {
            throw new <%- className %>NotFoundException(<%- attributeName %>Id);
        }
        crud<%- className %>Port.delete<%- className %>(<%- attributeName %>Id);
    }
<%_ } _%>
<%_ if (resources.indexOf('update')> -1) { _%>
    @Override
    public void update<%- className %>(<%- className %> <%- attributeName %>) {
        boolean exists = crud<%- className %>Port.exists<%- className %>(<%- attributeName %>.getId());
        if (!exists) {
            throw new <%- className %>NotFoundException(<%- attributeName %>.getId());
        }
        crud<%- className %>Port.update<%- className %>(<%- attributeName %>);
    }
<%_ } _%>
}
