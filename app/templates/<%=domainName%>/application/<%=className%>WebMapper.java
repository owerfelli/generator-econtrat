<%_
        let hasRelationship = relationships.length !== 0;
        _%>
package <%- classPackage %>.<%- snakeName %>.application;

import <%- classPackage %>.<%- snakeName %>.application.dto.<%- className %>Dto;
import <%- classPackage %>.<%- snakeName %>.domain.port.primary.in.<%- className %>;
        <%_ for (relationship of relationships) { _%>
import <%- classPackage %>.<%- relationship.lowerOtherEntityName %>.application.<%- relationship.otherEntityName %>WebMapper;
        <%_ } _%>

import org.springframework.stereotype.Component;
<%_ if (hasRelationship) { _%>
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
<%_ } _%>
@Component
public class <%- className %>WebMapper {

    <%_ for (relationship of relationships) { _%>
    private final <%- relationship.otherEntityName %>WebMapper <%- relationship.lowerOtherEntityName %>WebMapper;
    <%_ } _%>

    public <%- className %> toDomainEntity(<%- className %>Dto <%- attributeName %>Dto){
        return <%- className %>.builder()
                .id(<%- attributeName %>Dto.getId())
<%_ for (field of fields) { _%>
                .<%- field.fieldName %>(<%- attributeName %>Dto.get<%- field.capitalFieldName %>())
<%_ } _%>
<%_ for (relationship of relationships) { _%>
                .<%- relationship.relationshipName %>(<%- relationship.lowerOtherEntityName %>WebMapper.toDomainEntity(<%- attributeName %>Dto.get<%- relationship.capitalRelationshipName %>()))
<%_ } _%>
                .build();
    }

    public <%- className %>Dto toDto(<%- className %> <%- attributeName %>){
        return <%- className %>Dto.builder()
                .id(<%- attributeName %>.getId())
<%_ for (field of fields) { _%>
                .<%- field.fieldName %>(<%- attributeName %>.get<%- field.capitalFieldName %>())
<%_ } _%>
<%_ for (relationship of relationships) { _%>
                .<%- relationship.relationshipName %>(<%- relationship.lowerOtherEntityName %>WebMapper.toDto(<%- attributeName %>.get<%- relationship.capitalRelationshipName %>()))
<%_ } _%>
                .build();
    }
}
