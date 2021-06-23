package <%- classPackage %>.<%- snakeName %>.infrastructure.persistence;

import <%- classPackage %>.<%- snakeName %>.infrastructure.persistence.model.<%- className %>JpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface <%- className %>Repository extends JpaRepository<<%- className %>JpaEntity, Long> {
}
