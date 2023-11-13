package br.com.sennatech.sddo.claims.function;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.sennatech.sddo.claims.domain.dto.NotificationAddressDTO;
import br.com.sennatech.sddo.claims.domain.entity.NotificationAddress;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationAddressDTOtoNotificationAdress
        implements Function<NotificationAddressDTO, NotificationAddress> {

    private final ModelMapper mapper;

    @Override
    public NotificationAddress apply(NotificationAddressDTO notificationAddressDTO) {
        return mapper.map(notificationAddressDTO, NotificationAddress.class);
    }
}
