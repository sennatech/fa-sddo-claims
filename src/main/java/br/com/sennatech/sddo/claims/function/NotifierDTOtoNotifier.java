package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.NotifierDTO;
import br.com.sennatech.sddo.claims.domain.entity.Notifier;

@Component
public class NotifierDTOtoNotifier implements Function<NotifierDTO, Notifier> {
    @Autowired
    private ModelMapper mapper;

    @Override
    public Notifier apply(NotifierDTO notifierDTO) {
        return mapper.map(notifierDTO, Notifier.class);
    }
}
