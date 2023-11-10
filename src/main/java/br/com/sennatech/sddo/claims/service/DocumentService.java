package br.com.sennatech.sddo.claims.service;

import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.dto.DocumentDTO;
import br.com.sennatech.sddo.claims.function.DocumentDTOtoDocument;
import br.com.sennatech.sddo.claims.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository repository;
    private final DocumentDTOtoDocument converter;

    public void create(DocumentDTO documentDTO) {
        repository.save(converter.apply(documentDTO));
    }
}
