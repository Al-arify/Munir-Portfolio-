# GTIDS Core Engine — Evidence and Intake Prototype

A small Java prototype for separating multi-channel intake from evidence hashing. It is intended to make the GTIDS research direction testable without implying a deployed intelligence platform or legal-admissibility guarantee.

## Project Status

- **Status:** Prototype
- **Development phase:** Active implementation
- **Current focus:** Intake modeling, SHA-256 integrity records, concurrency tests, and CI
- **Explicit boundary:** This code does not collect from dark-web services, cryptocurrency networks, or real threat feeds. Test inputs are synthetic and the prototype does not establish a legal chain of custody.

## Separation of concerns

```text
ingestion  ->  evidence  ->  future security/output integrations
```

- `ingestion.RawIntelPacket` models a source label, raw payload, and intake timestamp.
- `ingestion.IngestionHub` validates and routes packets to an `EvidenceHandler`.
- `evidence.Sha256Hasher` has one responsibility: deterministic SHA-256 hashing.
- `evidence.Sha256EvidenceHandler` creates immutable integrity records without retaining raw payloads.
- `security/` and `output/` are reserved for future, separately tested components; no cryptography or AI integration is claimed there yet.

## Run tests

With Java 17+ and Gradle installed:

```bash
gradle test
```

The tests cover deterministic hashing, packet-to-record creation, blank-source validation, and concurrent intake across synthetic source channels.

## Repository layout

```text
src/main/java/com/gtids/  Production prototype code
src/test/java/com/gtids/  JUnit 5 tests
docs/architecture/         Architecture notes
docs/api/                   Intake API notes
.github/workflows/          CI and CodeQL workflows
```

## Roadmap

1. Add parser interfaces that accept sanitized, offline fixtures.
2. Add a versioned evidence-record schema and explicit retention policy.
3. Add security-module threat modeling before considering encryption components.
4. Add output adapters only after analyst review and data-leakage tests are defined.
