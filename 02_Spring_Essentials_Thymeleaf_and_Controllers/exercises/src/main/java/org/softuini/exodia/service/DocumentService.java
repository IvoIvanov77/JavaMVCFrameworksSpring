package org.softuini.exodia.service;

import org.softuini.exodia.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    String scheduleDocument(DocumentServiceModel serviceModel);

    DocumentServiceModel getById(String id);

    List<DocumentServiceModel> listAllDocuments();

    void  printDocument(String id);
}
