package com.synerise.eventmanager.calculation;

import com.synerise.eventmanager.registration.model.RegisterEventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/views")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @GetMapping("/{eventId}")
    public ResponseEntity<Integer> registerEvent(@PathVariable String eventId, @RequestParam Integer n) {
        Integer uniqueUserCountByEvent = calculationService.getUniqueUserCountByEvent(eventId, n);
        return ResponseEntity.ok(uniqueUserCountByEvent);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Integer>> registerEvent(@RequestParam Integer n) {
        Map<String, Integer> eventToUserCountMap = calculationService.getEventToUserCountMap(n);
        return ResponseEntity.ok(eventToUserCountMap);
    }

}
