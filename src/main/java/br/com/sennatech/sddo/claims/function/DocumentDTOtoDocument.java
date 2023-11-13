package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.DocumentDTO;
import br.com.sennatech.sddo.claims.domain.entity.Document;
import br.com.sennatech.sddo.claims.service.ClaimService;
import br.com.sennatech.sddo.claims.util.TransformationUtil;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DocumentDTOtoDocument implements Function<DocumentDTO, Document> {

    private final ClaimService claimService;

    @Override
    public Document apply(DocumentDTO documentDTO) {
        var document = Document.builder()
        .file(documentDTO.getFile())
        .name(documentDTO.getName())
        .id(documentDTO.getId())
                .build();

        document.getClaim()
                .add(claimService.retrieveFromId(TransformationUtil.claimIdToLong(documentDTO.getClaimId())));
                
        return document;
    }
}
