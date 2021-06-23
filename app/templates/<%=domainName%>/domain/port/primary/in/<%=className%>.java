package <%- classPackage %>.<%- snakeName %>.domain.port.primary.in;

        <%_ for (relationship of relationships) { _%>
import <%- classPackage %>.<%- relationship.lowerOtherEntityName %>.domain.port.primary.in.<%- relationship.otherEntityName %>;
        <%_ } _%>
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class <%- className %> {

    private Long id;

<%_ for (field of fields) { _%>
    private <%- field.fieldType %> <%- field.fieldName %>;

<%_ } _%>
<%_ for (relationship of relationships) { _%>
    private <%- relationship.otherEntityName %> <%- relationship.relationshipName %>;

<%_ } _%>
}
