package <%- classPackage %>.<%- snakeName %>.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@Schema(description = "<%- className %> bean description")
public class <%- className %>Dto {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

<%_ for (field of fields) { _%>
    private <%- field.fieldType %> <%- field.fieldName %>;

<%_ } _%>
}
