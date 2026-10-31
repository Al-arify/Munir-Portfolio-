package com.gtids.ingestion;

import com.gtids.evidence.EvidenceHandler;
import com.gtids.evidence.EvidenceRecord;
import com.gtids.evidence.Sha256EvidenceHandler;
import com.gtids.evidence.Sha256Hasher;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Receives raw packets from independent channels and hands each packet to an evidence handler.
 * The prototype stores integrity records only; raw payload retention is deliberately out of scope.
 */
public final class IngestionHub {
    private final EvidenceHandler evidenceHandler;
    private final List<EvidenceRecord> evidenceRecords = new CopyOnWriteArrayList<>();

    public IngestionHub() {
        this(new Sha256EvidenceHandler(new Sha256Hasher()));
    }

    public IngestionHub(EvidenceHandler evidenceHandler) {
        this.evidenceHandler = Objects.requireNonNull(evidenceHandler, "evidenceHandler");
    }

    public EvidenceRecord ingestFeed(String sourceType, String rawPayload) {
        if (sourceType == null || sourceType.isBlank()) {
            throw new IllegalArgumentException("sourceType must not be blank");
        }
        Objects.requireNonNull(rawPayload, "rawPayload");
        RawIntelPacket packet = new RawIntelPacket(sourceType.trim(), rawPayload, Instant.now());
        EvidenceRecord record = evidenceHandler.handle(packet);
        evidenceRecords.add(record);
        return record;
    }

    public List<EvidenceRecord> evidenceRecords() {
        return List.copyOf(evidenceRecords);
    }
}
