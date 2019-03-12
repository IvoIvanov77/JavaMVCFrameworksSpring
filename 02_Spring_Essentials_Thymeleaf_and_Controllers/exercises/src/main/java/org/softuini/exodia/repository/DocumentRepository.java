package org.softuini.exodia.repository;

import org.softuini.exodia.domain.entities.Document;
import org.softuini.exodia.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
}
