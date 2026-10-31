package com.gtids.evidence;

import com.gtids.ingestion.RawIntelPacket;

@FunctionalInterface
public interface EvidenceHandler {
    EvidenceRecord handle(RawIntelPacket packet);
}
