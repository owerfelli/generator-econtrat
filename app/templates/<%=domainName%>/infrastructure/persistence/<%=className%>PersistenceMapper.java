<%_
let hasRelationship = relationships.length !== 0;
_%>
package <%- classPackage %>.<%- snakeName %>.infrastructure.persistence;

import <%- classPackage %>.<%- snakeName %>.domain.port.primary.in.<%- className %>;
import <%- classPackage %>.<%- snakeName %>.infrastructure.persistence.model.<%- className %>JpaEntity;
<%_ for (relationship of relationships) { _%>
import <%- classPackage %>.<%- relationship.lowerOtherEntityName %>.infrastructure.persistence.<%- relationship.otherEntityName %>PersistenceMapper;
<%_ } _%>

import org.springframework.stereotype.Component;
<%_ if (hasRelationship) { _%>
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
<%_ } _%>
@Component
public class <%- className %>PersistenceMapper {
<%_ for (relationship of relationships) { _%>
    private final <%- relationship.otherEntityName %>PersistenceMapper <%- relationship.lowerOtherEntityName %>PersistenceMapper;
<%_ } _%>

    <%- className %>JpaEntity toJpaEntity(<%- className %> <%- attributeName %>) {
        return <%- className %>JpaEntity.builder()
                .id(<%- attributeName %>.getId())
<%_ for (field of fields) { _%>
                .<%- field.fieldName %>(<%- attributeName %>.get<%- field.capitalFieldName %>())
<%_ } _%>
<%_ for (relationship of relationships) { _%>
                .<%- relationship.relationshipName %>(<%- relationship.lowerOtherEntityName %>PersistenceMapper.toJpaEntity(<%- attributeName %>.get<%- relationship.capitalRelationshipName %>())
<%_ } _%>
                .build();
    }

    <%- className %> toDomainEntity(<%- className %>JpaEntity <%- attributeName %>JpaEntity) {
        return <%- className %>.builder()
                .id(<%- attributeName %>JpaEntity.getId())
<%_ for (field of fields) { _%>
                .<%- field.fieldName %>(<%- attributeName %>JpaEntity.get<%- field.capitalFieldName %>())
<%_ } _%>
<%_ for (relationship of relationships) { _%>
                .<%- relationship.relationshipName %>(<%- relationship.lowerOtherEntityName %>PersistenceMapper.toDomainEntity(<%- attributeName %>JpaEntity.get<%- relationship.capitalRelationshipName %>()))
<%_ } _%>
                .build();
    }
}
