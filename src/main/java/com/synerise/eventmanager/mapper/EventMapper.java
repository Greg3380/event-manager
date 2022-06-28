package com.synerise.eventmanager.mapper;

import com.synerise.eventmanager.registration.model.RegisterEventRequest;
import com.synerise.eventmanager.registration.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    Event map(RegisterEventRequest request);
}
