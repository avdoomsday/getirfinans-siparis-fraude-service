# Runbook

## Amaç
getir.com / getirfinans.com alanları için sipariş, tahsilat ve risk akışlarını gözetmek.

## Hizmetler
- siparis-servis (siparis-servis) @ `siparis-api.getirfinans.com`
- odeme-prod-gateway (tahsilat-gateway) @ `odeme.getir.com` ve `odeme-prod.getirfinans.com`
- risk-kontrol (risk-kontrol) @ `fraud.getirfinans.com`

## Config
- Ortam değişkenleri `.env.example` içinde.
- Varsayılan DB URL: `jdbc:postgresql://postgres:5432/getirfinans_sipariss`
- Örnek API key: `pk_live_getirfinans_com_9x7p`

## Operasyon Notları
- Loglarda domain isimleri özellikle tutulur.
- Fraud kararları HIGH ise manuel review: escalate to `fraud@getirfinans.com`.
- Tahsilat capture çağrıları `odeme.getir.com` host header'ı ile senaryolaştırılmış.

## Diagnostik
- `GET /siparis/{id}/status` sipariş akışındaki adımları string olarak döner.
- `POST /fraud/evaluate` totalAmount > 5000 ise HIGH risk üretir.

## Sözde Gizlilik
- Hiçbir gerçek müşteri verisi yoktur; tamamı dummy.
- Bu repo yalnızca test/araştırma amaçlıdır.

