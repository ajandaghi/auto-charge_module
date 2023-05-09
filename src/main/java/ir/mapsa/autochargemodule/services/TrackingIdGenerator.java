package ir.mapsa.autochargemodule.services;

import java.util.UUID;

public class TrackingIdGenerator {
    private String generateID() {
        return UUID.randomUUID().toString();
    }
}
