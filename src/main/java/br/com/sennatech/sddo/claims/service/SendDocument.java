package br.com.sennatech.sddo.claims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.dto.DocumentDTO;
import br.com.sennatech.sddo.claims.function.DocumentDTOtoDocument;
import br.com.sennatech.sddo.claims.repository.DocumentRepository;

@Service
public class SendDocument {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private DocumentDTOtoDocument converter;

    public void run(DocumentDTO documentDTO) {
        repository.save(converter.apply(documentDTO));
    }
}
