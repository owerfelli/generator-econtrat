package <%- classPackage %>.<%- snakeName %>.application.adapters;

import <%- classPackage %>.common.annotation.WebAdapter;
import <%- classPackage %>.<%- snakeName %>.application.<%- className %>WebMapper;
import <%- classPackage %>.<%- snakeName %>.application.dto.<%- className %>Dto;
import <%- classPackage %>.<%- snakeName %>.domain.port.primary.Management<%- className %>Service;
import <%- classPackage %>.<%- snakeName %>.domain.port.primary.in.<%- className %>;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@WebAdapter
@RequiredArgsConstructor
public class <%- className %>Adapter {

    private final Management<%- className %>Service management<%- className %>Service;
    private final <%- className %>WebMapper <%- attributeName %>WebMapper;

    public List<<%- className %>Dto> searchAll<%- className %>() {
        List<<%- className %>> <%- attributeName %>s = management<%- className %>Service.searchAll<%- className %>s();
        return Optional.ofNullable(<%- attributeName %>s)
                .stream()
                .flatMap(Collection::stream)
                .map(<%- attributeName %>WebMapper::toDto)
                .collect(Collectors.toList());
    }

    public <%- className %>Dto create<%- className %>(<%- className %>Dto <%- attributeName %>Dto) {
        <%- className %> <%- attributeName %> = <%- attributeName %>WebMapper.toDomainEntity(<%- attributeName %>Dto);
        return <%- attributeName %>WebMapper.toDto(management<%- className %>Service.create<%- className %>(<%- attributeName %>));

    }

    public Optional<<%- className %>Dto> searchOne<%- className %>(long id<%- className %>) {
        return management<%- className %>Service.searchOne<%- className %>(id<%- className %>)
                .map(<%- attributeName %>WebMapper::toDto);
    }

    public void delete<%- className %>(long id) {
        management<%- className %>Service.delete<%- className %>(id);
    }

    public void update<%- className %>(long id<%- className %>, <%- className %>Dto <%- attributeName %>Dto) {
        <%- attributeName %>Dto.setId(id<%- className %>);
        management<%- className %>Service.update<%- className %>(<%- attributeName %>WebMapper.toDomainEntity(<%- attributeName %>Dto));
    }
}
