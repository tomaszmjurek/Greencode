package com.ing.atmservice;

import com.ing.atmservice.model.Atm;
import com.ing.atmservice.model.Order;
import com.ing.atmservice.model.Task;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Singleton
@Slf4j
public class AtmService {

    public Order calculateOrder(List<Task> serviceTasks) {
        log.info("Calculating {} service tasks", serviceTasks.size());
        var timestamp = System.currentTimeMillis();
        Map<Integer, List<Atm>> tasksOrdered = new HashMap<>();

        for (var task : serviceTasks) {
            int region = task.getRegion();
            var atm = new Atm(task.getAtmId(), region, task.getRequestType());
            List<Atm> regionAtms;
            if /* Processing new region */ (!tasksOrdered.containsKey(region)) {
                regionAtms = new ArrayList<>();
                regionAtms.add(atm);
                tasksOrdered.put(region, regionAtms);
            } /* Region already in the system */ else {
                regionAtms = tasksOrdered.get(region);
                Optional<Atm> existingAtm = regionAtms.stream().filter(a -> a.getAtmId() == atm.getAtmId()).findAny();
                if /* Atm already in the system */ (existingAtm.isPresent()) {
                    if /* New atm has more severe request type */ (atm.isPreceding(existingAtm.get())) {
                        regionAtms.remove(existingAtm.get());
                        regionAtms.add(atm);
                        tasksOrdered.put(region, regionAtms);
                    }
                } else /* Processing new atm */ {
                    regionAtms.add(atm);
                    tasksOrdered.put(region, regionAtms);
                }
            }
        }

        sortTasksByPriority(tasksOrdered);

        var order = new Order(tasksOrdered.values().stream().flatMap(Collection::stream).toList());
        log.info("Report generated in: {}ms", System.currentTimeMillis() - timestamp);
        return order;
    }

    private void sortTasksByPriority(Map<Integer, List<Atm>> tasksOrdered) {
        tasksOrdered.entrySet().forEach(entry -> {
            List<Atm> atms = entry.getValue();
            Collections.sort(atms);
            entry.setValue(atms);
        });
    }
}
