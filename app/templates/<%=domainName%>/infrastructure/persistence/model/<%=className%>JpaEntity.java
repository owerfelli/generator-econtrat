package <%- classPackage %>.<%- snakeName %>.infrastructure.persistence.model;

import <%- classPackage %>.common.persistence.AbstractEntity;
        <%_ for (relationship of relationships) { _%>
import <%- classPackage %>.<%- relationship.lowerOtherEntityName %>.infrastructure.persistence.model.<%- relationship.otherEntityName %>JpaEntity;
        <%_ } _%>

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "<%- className %>JpaEntity")
@Table(name = "<%- snakeName %>", schema = "schema")
public class <%- className %>JpaEntity extends AbstractEntity {

<%_ for (field of fields) { _%>
    @Column(name = "<%- field.dbName %>")
    private <%- field.fieldType %> <%- field.fieldName %>;

<%_ } _%>
<%_ for (relationship of relationships) { _%>
<%_ if (relationship.relationshipType =='many-to-one') { _%>
    @JoinColumn(name = "<%- relationship.dbName %>")
    @ManyToOne
<%_ } _%>
    private <%- relationship.otherEntityName %>JpaEntity <%- relationship.relationshipName %>;

<%_ } _%>
    @Builder
    public <%- className %>JpaEntity(Long id<%_ for (field of fields) { _%>, <%- field.fieldType %> <%- field.fieldName %><%_ } _%><%_ for (relationship of relationships) { _%>JpaEntity, <%- relationship.otherEntityName %>JpaEntity <%- relationship.relationshipName %><%_ } _%>) {
        this.setId(id);
<%_ for (field of fields) { _%>
        this.<%- field.fieldName %> = <%- field.fieldName %>;
<%_ } _%>
<%_ for (relationship of relationships) { _%>
        this.<%- relationship.relationshipName %> = <%- relationship.relationshipName %>;
<%_ } _%>
    }
}
