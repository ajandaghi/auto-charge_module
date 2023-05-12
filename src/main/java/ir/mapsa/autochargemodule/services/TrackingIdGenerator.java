package ir.mapsa.autochargemodule.services;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrackingIdGenerator {
    public static String generateID() {
        return UUID.randomUUID().toString();
    }
}
