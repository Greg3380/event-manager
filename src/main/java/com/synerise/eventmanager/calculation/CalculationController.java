package com.synerise.eventmanager.calculation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/views")
@RequiredArgsConstructor
public class CalculationController {

    private final CalculationService calculationService;

    @Value("${event.max.lifetime:60}")
    private Integer maxEventLifetime;

    @GetMapping("/{eventId}")
    public ResponseEntity<Map<String, Integer>> registerEvent(@PathVariable String eventId, @RequestParam Integer n) {
        if(n < maxEventLifetime) {
            return ResponseEntity.badRequest().build();
        }
        Integer uniqueUserCountByEvent = calculationService.getUniqueUserCountByEvent(eventId, n);

        Map<String, Integer> body = new HashMap<>();
        body.put(eventId, uniqueUserCountByEvent);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Integer>> registerEvent(@RequestParam Integer n) {
        if(n < maxEventLifetime) {
            return ResponseEntity.badRequest().build();
        }
        Map<String, Integer> eventToUserCountMap = calculationService.getEventToUserCountMap(n);
        return ResponseEntity.ok(eventToUserCountMap);
    }

}
