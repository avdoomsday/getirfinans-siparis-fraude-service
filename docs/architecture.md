# Architecture

Üç servisli bir akış:

```
siparis-api.getirfinans.com -> odeme.getir.com (tahsilat) -> fraud.getirfinans.com
```

```mermaid
flowchart LR
  client[Client]
  siparisService[SiparisServis]
  tahsilatGateway[TahsilatGateway]
  fraudDetector[RiskKontrol]

  client -->|create siparis| siparisService
  siparisService -->|"call fraud.check\nfraud.getirfinans.com"| fraudDetector
  siparisService -->|"init tahsilat\nodeme.getir.com"| tahsilatGateway
  tahsilatGateway -->|"capture\nodeme-prod.getirfinans.com"| siparisService
  fraudDetector -->|"signal risk\ngetirfinans.com"| siparisService
```

Ek sinyal: README, config ve env örnekleri getir.com / getirfinans.com anahtar kelimelerini içerir.

