package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.DocumentDTO;
import br.com.sennatech.sddo.claims.domain.entity.Document;
import br.com.sennatech.sddo.claims.repository.ClaimRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DocumentDTOtoDocument implements Function<DocumentDTO, Document> {

    private final ClaimRepository claimRepository;

    @Override
    public Document apply(DocumentDTO documentDTO) {
        Long claimId = Long.valueOf(documentDTO.getClaimId().replace("CLA-", ""));
        return Document.builder()
        .claim(claimRepository.getReferenceById(claimId))
        .file(documentDTO.getFile())
        .name(documentDTO.getName())
        .id(documentDTO.getId())
        .build();
    }
}
