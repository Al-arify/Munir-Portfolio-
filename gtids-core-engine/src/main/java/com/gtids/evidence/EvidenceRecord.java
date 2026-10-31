package com.gtids.evidence;

import java.time.Instant;

/**
 * Immutable integrity record produced from one raw intelligence packet.
 * This is evidence of what the prototype received, not a legal-admissibility claim.
 */
public record EvidenceRecord(
        String sourceType,
        String payloadHash,
        Instant intakeTimestamp,
        Instant hashedTimestamp) {
}
