package br.com.sennatech.sddo.claims.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.sennatech.sddo.claims.domain.dto.NotifierDTO;
import br.com.sennatech.sddo.claims.domain.entity.Notifier;
import br.com.sennatech.sddo.claims.repository.NotifierRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotifierService {
    
    private final NotifierRepository notifierRepository;
    private final ModelMapper mapper;

    public Notifier retrieveOrCreateNotifier(String documentNumber, NotifierDTO notifierDTO) {
        Optional<Notifier> existingNotifier = notifierRepository.findById(documentNumber);
        if (existingNotifier.isPresent()) {
            return existingNotifier.get();
        } else {
            Notifier newNotifier = convertDTOToEntity(notifierDTO);
            return notifierRepository.save(newNotifier);
        }
    }

    public Notifier retrieveFromDocumentNumber(String documentNumber) {
        return notifierRepository.findById(documentNumber).orElseThrow(() -> new EntityNotFoundException("Notifier not found"));
    }

    private Notifier convertDTOToEntity(NotifierDTO notifierDTO) {
        return mapper.map(notifierDTO, Notifier.class);
    }
}
