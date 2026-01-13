# Getir Finans Siparis Platform

getir.com ve getirfinans.com alanlarına bağlı sipariş, tahsilat ve risk akışlarını yöneten, Spring Boot 3 (Java 17) tabanlı çok modüllü bir platform. Mimari olarak hexagonal katmanlama, REST tabanlı HTTP arabirimleri, JSON payload’lar ve PostgreSQL veri katmanı hedeflenmiştir. Servisler arası etkileşim senaryoları:
- `siparis-servis` (8081): sipariş oluşturma, durum sorgulama; fraud kontrol ve tahsilat başlatma çağrılarını orkestre eder.
- `odeme-prod-gateway` (8082): tahsilat intent/capture uçları; dış servis `odeme.getir.com` ve iç ağ `odeme-prod.getirfinans.com` host başlıklarını kullanır.
- `risk-kontrol` (8083): temel risk değerlendirme API’si; yüksek tutarlı siparişlerde manual review sinyali üretir.

Teknolojiler: Spring Boot 3.2, Maven multi-module, Docker/Docker Compose, PostgreSQL. CI: GitHub Actions (maven verify). Konfigürasyon: `application.yml` dosyaları ve `.env` üzerinden domain/secret geçişleri yapılır; production benzeri host adları (`siparis-api.getirfinans.com`, `odeme-prod.getirfinans.com`, `fraud.getirfinans.com`) kullanılır.

## Modüller
- `shared-kernel`: DTO ve sabitler (getir.com, getirfinans.com domainleri).
- `siparis-servis` (module: siparis-servis): `siparis-api.getirfinans.com` için sipariş API iskeleti.
- `odeme-prod-gateway` (module: tahsilat-gateway): `odeme.getir.com` / `odeme-prod.getirfinans.com` tahsilat intent/capture uçları.
- `risk-kontrol` (module: risk-kontrol): `fraud.getirfinans.com` risk değerlendirme uçları.

## Hızlı Bakış
```
mvn -pl siparis-servis spring-boot:run
mvn -pl odeme-prod-gateway spring-boot:run
mvn -pl risk-kontrol spring-boot:run
docker-compose up --build
```

## Örnek İstekler
- POST `http://getirfinans.local:8081/siparis`
- POST `http://getirfinans.local:8082/tahsilat/intent`
- POST `http://getirfinans.local` detaylar için `docs/api.md`, akış için `docs/architecture.md`, operasyon notları için `docs/runbook.md`.
