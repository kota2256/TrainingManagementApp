INSERT INTO roles (code, "value")
VALUES
(1, 'ADMIN'),
(2, 'GENERAL');


INSERT INTO users (id, role_code, name, password, email)
VALUES
(1, 1, '管理者太郎', 'password', 'admin@mail.com'),
(2, 2, '一般二郎', 'password', 'user@mail.com');
--usersテーブル定義の変更、user_idカラムの変更、自動採番3から
ALTER TABLE users ALTER COLUMN id RESTART WITH 3;


INSERT INTO physical_details (user_id, created_at, weight, recorded_date)
VALUES
(1, '2026-02-06 22:49:01', 89.5, '2026-02-06'),
(2, '2026-02-06 22:50:05', 48.5, '2026-02-06');



