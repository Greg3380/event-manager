package com.synerise.eventmanager.mapper;

import com.synerise.eventmanager.registration.Event;
import com.synerise.eventmanager.registration.RegisterEventRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Event map(RegisterEventRequest request);
}
