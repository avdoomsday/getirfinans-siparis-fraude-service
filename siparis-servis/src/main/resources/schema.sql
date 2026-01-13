CREATE TABLE IF NOT EXISTS siparis (
    id VARCHAR(64) PRIMARY KEY,
    musteri_id VARCHAR(64) NOT NULL,
    toplam_tutar NUMERIC(12,2) NOT NULL,
    para_birimi VARCHAR(8) NOT NULL,
    durum VARCHAR(32) NOT NULL,
    kaynak_domain VARCHAR(128),
    callback_url VARCHAR(256)
);

