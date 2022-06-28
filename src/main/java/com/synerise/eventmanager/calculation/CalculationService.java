package com.synerise.eventmanager.calculation;

import java.util.Map;

public interface CalculationService {

    Integer getUniqueUserCountByEvent(String eventId, Integer minutesInPast);

    Map<String, Integer> getEventToUserCountMap(Integer minutesInPast);
}
