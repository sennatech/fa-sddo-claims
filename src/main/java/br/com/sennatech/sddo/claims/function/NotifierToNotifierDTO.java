package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.NotifierDTO;
import br.com.sennatech.sddo.claims.domain.entity.Notifier;

@Component
public class NotifierToNotifierDTO implements Function<Notifier, NotifierDTO> {
    @Autowired
    private ModelMapper mapper;

    @Override
    public NotifierDTO apply(Notifier notifier) {
        return mapper.map(notifier, NotifierDTO.class);
    }
}
