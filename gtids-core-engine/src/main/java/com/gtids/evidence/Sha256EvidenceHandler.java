package com.gtids.evidence;

import com.gtids.ingestion.RawIntelPacket;

import java.time.Instant;
import java.util.Objects;

/** Creates an integrity record without retaining the raw payload. */
public final class Sha256EvidenceHandler implements EvidenceHandler {
    private final Sha256Hasher hasher;

    public Sha256EvidenceHandler(Sha256Hasher hasher) {
        this.hasher = Objects.requireNonNull(hasher, "hasher");
    }

    @Override
    public EvidenceRecord handle(RawIntelPacket packet) {
        Objects.requireNonNull(packet, "packet");
        return new EvidenceRecord(
                packet.sourceType(),
                hasher.hash(packet.rawPayload()),
                packet.intakeTimestamp(),
                Instant.now());
    }
}
