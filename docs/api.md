# API Yüzeyi

## Sipariş Servis (`siparis-api.getirfinans.com`)
- `POST /siparis` — yeni sipariş oluşturur.
- `GET /siparis/{id}` — sipariş detayını getirir.
- `GET /siparis/{id}/status` — sipariş durumunu döner.

## Tahsilat Prod Gateway (`odeme.getir.com` / `odeme-prod.getirfinans.com`)
- `POST /tahsilat/intent` — tahsilat intent oluşturur.
- `GET /tahsilat/{id}` — intent'i getirir.
- `POST /tahsilat/{id}/capture?tutar=100` — tutarı capture eder.

## Fraud Detector (`fraud.getirfinans.com`)
- `POST /fraud/evaluate` — sipariş için risk skoru üretir.

### Örnek Payloads
```json
{
  "siparisId": "ord-1001",
  "musteriId": "cust-55",
  "kalemler": ["getir-market", "istanbul-fulfillment"],
  "toplamTutar": 150.25,
  "paraBirimi": "TRY",
  "kaynakDomain": "getirfinans.com",
  "callbackUrl": "https://siparis-api.getirfinans.com/callback"
}
```

```json
{
  "tahsilatId": "ths-2001",
  "siparisId": "ord-1001",
  "tutar": 150.25,
  "paraBirimi": "TRY",
  "saglayiciDomain": "odeme.getir.com",
  "donusUrl": "https://getir.com/tahsilat/return"
}
```

