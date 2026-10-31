# Ingestion API

`IngestionHub.ingestFeed(String sourceType, String rawPayload)` accepts one synthetic packet, timestamps it, passes it to an `EvidenceHandler`, and returns an immutable `EvidenceRecord`.

## Accepted source labels

The prototype intentionally treats source labels as data. Examples used in tests are `Dark Web`, `Crypto Chains`, `OSINT/Pastebins`, and `Cyber Feeds`; none of these labels activate a network connector.

## Security notes

- Do not pass credentials, private reports, personal data, or raw dumps into public tests.
- The default handler stores a SHA-256 digest and metadata, not the raw payload.
- Hashing supports integrity comparison; it does not prove authenticity, provenance, or legal admissibility.
