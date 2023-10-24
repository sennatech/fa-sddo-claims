package br.com.sennatech.sddo.claims.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.sennatech.sddo.claims.function.ClaimToClaimDTO;
import br.com.sennatech.sddo.claims.domain.dto.ClaimDTO;
import br.com.sennatech.sddo.claims.domain.entity.Notifier;
import br.com.sennatech.sddo.claims.repository.ClaimRepository;
import br.com.sennatech.sddo.claims.repository.NotifierRepository;
import jakarta.persistence.EntityNotFoundException;
import br.com.sennatech.sddo.claims.domain.enums.Status;

@Service
public class ListClaims {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private NotifierRepository notifierRepository;

    @Autowired
    private ClaimToClaimDTO converter;

    public List<ClaimDTO> run(Map<String, String> queryParameters) throws IllegalArgumentException {
        List<ClaimDTO> listOfAllClaims = new ArrayList<>();
        String stringStatus = queryParameters.get("status");
        String insuredDocument = queryParameters.get("insuredDocument");
        String notifierDocument = queryParameters.get("notifierDocument");
        if (notifierDocument != null) {
            listOfAllClaims.addAll(getClaimDTOsFromNotifierDocument(notifierDocument));
        }
        if (insuredDocument != null && notifierDocument == null) {
            listOfAllClaims.addAll(getClaimDTOsFromInsuredDocument(insuredDocument));
        }
        if (stringStatus != null) {
            return listOfAllClaims.stream().filter(claim -> claim.getStatus().equals(Status.fromString(stringStatus))).collect(Collectors.toList());
        }
        return listOfAllClaims;
    }

    /**
     * @param insuredDocument
     * @return List of ClaimDTOs from the repository filtered by the issued insuredDocument value in param
     */
    private List<ClaimDTO> getClaimDTOsFromInsuredDocument(String insuredDocument) {
        return claimRepository.findByInsuredDocument(insuredDocument).stream().map(claim -> converter.apply(claim)).collect(Collectors.toList());
    }

    /**
     * @param notifierDocument
     * @return List of ClaimDTOs from the repository filtered by the notifier entity found with the issued notifierDocument value in param
     */
    private List<ClaimDTO> getClaimDTOsFromNotifierDocument(String notifierDocument) {
        Notifier notifier = notifierRepository.findById(notifierDocument).orElseThrow(() -> new EntityNotFoundException("Notifier not found"));
        return claimRepository.findByNotifier(notifier).stream().map(claim -> converter.apply(claim)).collect(Collectors.toList());
    }
}
