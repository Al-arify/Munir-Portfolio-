package com.gtids.ingestion;

import java.time.Instant;

public record RawIntelPacket(String sourceType, String rawPayload, Instant intakeTimestamp) {
}
