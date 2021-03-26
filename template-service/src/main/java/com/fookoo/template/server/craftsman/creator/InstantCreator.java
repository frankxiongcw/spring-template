package com.fookoo.template.server.craftsman.creator;

import java.time.Instant;

public class InstantCreator implements Creator<Instant> {

    @Override
    public Instant create(Object source) {
        Instant srcObject = (Instant) source;
        return Instant.from(srcObject);
    }
}
