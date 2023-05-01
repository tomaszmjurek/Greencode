package com.ing.atmservice;

import com.ing.atmservice.model.Atm;
import com.ing.atmservice.model.Task;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Singleton
@Slf4j
public class AtmService {

    private final Map<Integer, List<Atm>> tasksOrdered = new HashMap<>(); // todo test processing time with global and local var

    public List<Atm> calculateOrder(List<Task> serviceTasks) {
        log.info("Calculating {} service tasks", serviceTasks.size());
        var timestamp = System.currentTimeMillis();

        for (var task : serviceTasks) {
            int region = task.getRegion();
            var atm = new Atm(task.getAtmId(), region, task.getRequestType());
            List<Atm> regionAtms;
            if /* Processing new region */ (!tasksOrdered.containsKey(region)) {
                regionAtms = new ArrayList<>();
                regionAtms.add(atm); // sort? compare time
                tasksOrdered.put(region, regionAtms);
            } /* Region already in the system */ else {
                regionAtms = tasksOrdered.get(region);
                Optional<Atm> any = regionAtms.stream().filter(a -> a.getAtmId() == atm.getAtmId()).findAny();
                if /* Atm already in the system */ (any.isPresent()) {
                    if /* New atm has more severe request type */ (atm.isHigherOrder(any.get())) {
                        regionAtms.remove(any.get()); // not removing?
                        regionAtms.add(atm);
                        tasksOrdered.put(region, regionAtms);
                    }
                } else /* Processing new atm */ {
                    regionAtms.add(atm);
                    tasksOrdered.put(region, regionAtms);
                }
            }
        }

        tasksOrdered.entrySet().forEach(entry -> {
            List<Atm> atms = entry.getValue();
            Collections.sort(atms);
            entry.setValue(atms);
        });

        var response =  tasksOrdered.values().stream().flatMap(Collection::stream).toList();
        log.info("Report generated in: {}ms", System.currentTimeMillis() - timestamp);
        return response;
    }
}
