package <%- classPackage %>.<%- snakeName %>.infrastructure.persistence;

import <%- classPackage %>.common.annotation.PersistenceAdapter;
import <%- classPackage %>.<%- snakeName %>.domain.port.primary.in.<%- className %>;
import <%- classPackage %>.<%- snakeName %>.domain.port.secondary.Crud<%- className %>Port;
import <%- classPackage %>.<%- snakeName %>.infrastructure.persistence.model.<%- className %>JpaEntity;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@Transactional
@RequiredArgsConstructor
public class Crud<%- className %>Adapter implements Crud<%- className %>Port {

    private final <%- className %>Repository <%- attributeName %>Repository;
    private final <%- className %>PersistenceMapper <%- attributeName %>PersistenceMapper;
<%_ if (resources.indexOf('create')> -1) { _%>
    @Override
    public <%- className %> save<%- className %>(<%- className %> <%- attributeName %>) {
        <%- className %>JpaEntity <%- attributeName %>JpaEntity = <%- attributeName %>PersistenceMapper.toJpaEntity(<%- attributeName %>);
<%_ for (relationship of relationships) { _%>
        <%- attributeName %>JpaEntity.set<%- relationship.capitalRelationshipName %>(<%- relationship.relationshipName %>Repository.getOne(<%- attributeName %>.get<%- relationship.capitalRelationshipName %>().getId()));
<%_ } _%>
        <%- attributeName %>Repository.save(<%- attributeName %>JpaEntity);
        return <%- attributeName %>PersistenceMapper.toDomainEntity(<%- attributeName %>JpaEntity);
    }
<%_ } _%>
<%_ if (resources.indexOf('delete')> -1) { _%>
    @Override
    public void delete<%- className %>(Long <%- attributeName %>Id) {
        <%- attributeName %>Repository.deleteById(<%- attributeName %>Id);
    }
<%_ } _%>
<%_ if (resources.indexOf('findAll')> -1) { _%>
    @Override
    public List<<%- className %>> findAll<%- className %>() {
        List<<%- className %>JpaEntity> <%- attributeName %>JpaEntities = <%- attributeName %>Repository.findAll();
        return <%- attributeName %>JpaEntities.stream()
                .map(<%- attributeName %>PersistenceMapper::toDomainEntity)
                .collect(Collectors.toList());
    }
<%_ } _%>
<%_ if (resources.indexOf('findOne')> -1) { _%>
    @Override
    public Optional<<%- className %>> find<%- className %>ById(Long <%- attributeName %>Id) {
        return <%- attributeName %>Repository.findById(<%- attributeName %>Id).map(<%- attributeName %>PersistenceMapper::toDomainEntity);
    }
<%_ } _%>
<%_ if (resources.indexOf('update')> -1) { _%>
    @Override
    public <%- className %> update<%- className %>(<%- className %> <%- attributeName %>) {
        return this.save<%- className %>(<%- attributeName %>);
    }
<%_ } _%>
    @Override
    public boolean exists<%- className %>(Long <%- attributeName %>Id) {
        return <%- attributeName %>Repository.existsById(<%- attributeName %>Id);
    }
}
