# Architecture overview

```text
synthetic feed input
        |
        v
IngestionHub -- validates source/payload and timestamps packet
        |
        v
EvidenceHandler -- produces SHA-256 integrity record
        |
        v
immutable EvidenceRecord list (prototype-local)
```

The current design uses `CopyOnWriteArrayList` for a simple concurrency-safe prototype store. It is not a durable database, tamper-evident ledger, timestamp authority, or chain-of-custody system. Those concerns require threat modeling, access control, durable audit storage, clock strategy, retention rules, and independent review before any stronger claim could be made.
