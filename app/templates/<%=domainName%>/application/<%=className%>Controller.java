package <%- classPackage %>.<%- snakeName %>.application;

import <%- classPackage %>.<%- snakeName %>.application.adapters.<%- className %>Adapter;
import <%- classPackage %>.<%- snakeName %>.application.dto.<%- className %>Dto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/<%- attributeName %>")
@RequiredArgsConstructor
public class <%- className %>Controller {

    private final <%- className %>Adapter <%- attributeName %>Adapter;
<%_ if (resources.indexOf('findAll')> -1) { _%>
    @Operation(summary = "View a list of available <%- attributeName %>s")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved <%- attributeName %>s",
                    content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = List.class)) })
    })
    @GetMapping
    public ResponseEntity<List<<%- className %>Dto>> searchAll<%- className %>() {
        return ResponseEntity.ok(<%- attributeName %>Adapter.searchAll<%- className %>());
    }
<%_ } _%>
<%_ if (resources.indexOf('create')> -1) { _%>
    @Operation(summary = "Add a <%- attributeName %>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added <%- attributeName %>"),
            @ApiResponse(responseCode = "401", description = "The resource already exist")
    })

    @PostMapping
    public ResponseEntity create<%- className %>(@Valid @RequestBody <%- className %>Dto <%- attributeName %>Dto) {
        <%- className %>Dto <%- attributeName %>Saved = <%- attributeName %>Adapter.create<%- className %>(<%- attributeName %>Dto);
        // location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(<%- attributeName %>Saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
<%_ } _%>
<%_ if (resources.indexOf('findOne')> -1) { _%>
    @Operation(summary = "Search <%- attributeName %> by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved <%- attributeName %>",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EntityModel.class)) }),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<EntityModel<<%- className %>Dto>> searchOne<%- className %>(@PathVariable("id") long id<%- className %>) {
        Optional<<%- className %>Dto> <%- attributeName %> = <%- attributeName %>Adapter.searchOne<%- className %>(id<%- className %>);
        if(<%- attributeName %>.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        // Hateoas
        EntityModel<<%- className %>Dto> resource = EntityModel.of(<%- attributeName %>.get());
        WebMvcLinkBuilder linkToBuilder = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
                .searchAll<%- className %>());
        resource.add(linkToBuilder.withRel("all-<%- attributeName %>s"));
        return ResponseEntity.ok(resource);
    }
<%_ } _%>
<%_ if (resources.indexOf('delete')> -1) { _%>
    @Operation(summary = "Delete <%- attributeName %>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted <%- attributeName %>"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete<%- className %>(@PathVariable("id") long id) {
        <%- attributeName %>Adapter.delete<%- className %>(id);
        return ResponseEntity.noContent().build();
    }
<%_ } _%>
<%_ if (resources.indexOf('update')> -1) { _%>
    @Operation(summary = "Update <%- attributeName %>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully updated <%- attributeName %>"),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
    })
    @PutMapping("{id}")
    public ResponseEntity<Void> update<%- className %>(@PathVariable("id") long id<%- className %>, @RequestBody <%- className %>Dto <%- attributeName %>) {
        <%- attributeName %>Adapter.update<%- className %>(id<%- className %>, <%- attributeName %>);
        return ResponseEntity.noContent().build();
    }
<%_ } _%>
}
