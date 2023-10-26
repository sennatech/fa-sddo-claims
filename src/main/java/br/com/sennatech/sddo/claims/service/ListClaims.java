package br.com.sennatech.sddo.claims.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.sennatech.sddo.claims.function.ClaimToClaimListDTO;
import br.com.sennatech.sddo.claims.domain.dto.ClaimListDTO;
import br.com.sennatech.sddo.claims.domain.entity.Claim;
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
    private ClaimToClaimListDTO converter;

    public List<ClaimListDTO> run(Map<String, String> queryParameters) throws IllegalArgumentException {
        List<Claim> listOfAllClaims = new ArrayList<>();
        String stringStatus = queryParameters.get("status");
        String insuredDocument = queryParameters.get("insuredDocument");
        String notifierDocument = queryParameters.get("notifierDocument");
        if (notifierDocument != null) {
            listOfAllClaims.addAll(getClaimDTOsFromNotifierDocument(notifierDocument));
        }
        if (insuredDocument != null && notifierDocument == null) {
            listOfAllClaims.addAll(getClaimDTOsFromInsuredDocument(insuredDocument));
        }
        if (insuredDocument == null && notifierDocument == null) {
            listOfAllClaims.addAll(claimRepository.findAll());
        }
        if (stringStatus != null) {
            return listOfAllClaims.stream().filter(claim -> claim.getStatus().equals(Status.fromString(stringStatus))).map(claim -> converter.apply(claim)).collect(Collectors.toList());
        }
        return listOfAllClaims.stream().map(claim -> converter.apply(claim)).collect(Collectors.toList());
    }

    /**
     * @param insuredDocument
     * @return List of ClaimDTOs from the repository filtered by the issued insuredDocument value in param
     */
    private List<Claim> getClaimDTOsFromInsuredDocument(String insuredDocument) {
        return claimRepository.findByInsuredDocument(insuredDocument);
    }

    /**
     * @param notifierDocument
     * @return List of ClaimDTOs from the repository filtered by the notifier entity found with the issued notifierDocument value in param
     */
    private List<Claim> getClaimDTOsFromNotifierDocument(String notifierDocument) {
        Notifier notifier = notifierRepository.findById(notifierDocument).orElseThrow(() -> new EntityNotFoundException("Notifier not found"));
        return claimRepository.findByNotifier(notifier);
    }
}
