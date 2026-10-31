package com.gtids.ingestion;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class IngestionHubTest {
    @Test
    void createsAnIntegrityRecordForAnIncomingFeed() {
        IngestionHub hub = new IngestionHub();

        var record = hub.ingestFeed("OSINT/Pastebin", "sanitized sample payload");

        assertEquals("OSINT/Pastebin", record.sourceType());
        assertEquals(64, record.payloadHash().length());
        assertNotNull(record.intakeTimestamp());
        assertEquals(1, hub.evidenceRecords().size());
    }

    @Test
    void acceptsConcurrentSourceChannels() throws InterruptedException {
        IngestionHub hub = new IngestionHub();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<String> sources = List.of("Dark Web", "Crypto Chains", "OSINT/Pastebins", "Cyber Feeds");

        for (int index = 0; index < 40; index++) {
            String source = sources.get(index % sources.size());
            pool.submit(() -> hub.ingestFeed(source, "synthetic-" + source));
        }
        pool.shutdown();

        assertTrue(pool.awaitTermination(5, TimeUnit.SECONDS));
        assertEquals(40, hub.evidenceRecords().size());
    }

    @Test
    void rejectsBlankSourceTypes() {
        IngestionHub hub = new IngestionHub();

        assertThrows(IllegalArgumentException.class, () -> hub.ingestFeed(" ", "payload"));
    }
}
