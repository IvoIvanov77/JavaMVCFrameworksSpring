package org.softuini.exodia.service;

import org.modelmapper.ModelMapper;
import org.softuini.exodia.domain.entities.Document;
import org.softuini.exodia.domain.models.service.DocumentServiceModel;
import org.softuini.exodia.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;

    private final ModelMapper modelMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String scheduleDocument(DocumentServiceModel serviceModel){
        Document documentToSchedule = this.modelMapper.map(serviceModel, Document.class);
        Document scheduledDocument = this.documentRepository.saveAndFlush(documentToSchedule);
        return scheduledDocument.getId();
    }

    @Override
    public DocumentServiceModel getById(String id){
        Document document = this.documentRepository.getOne(id);
        if(document == null){
            return null;
        }
        return this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> listAllDocuments(){
        return this.documentRepository.findAll()
                .stream()
                .map(document -> this.modelMapper.map(document, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void  printDocument(String id){
        this.documentRepository.deleteById(id);
    }
}


