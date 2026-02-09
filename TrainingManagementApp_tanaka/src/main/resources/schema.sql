CREATE TABLE IF NOT EXISTS roles(
	code	INT UNIQUE NOT NULL,		--参照ID	1:管理者, 2:一般ユーザ
	"value"	VARCHAR(255) NOT NULL,		--権限	管理者:ADMIN,  一般ユーザ:GENERAL
	CHECK (code <= 999)
);

CREATE TABLE IF NOT EXISTS users(
	id					INT AUTO_INCREMENT,
	role_code	INT,
	name			VARCHAR(255) NOT NULL,
	password	VARCHAR(60) NOT NULL,
	email			VARCHAR(255) NOT NULL UNIQUE,
	PRIMARY KEY(id), --プライマリーキー
	FOREIGN KEY(role_code)	REFERENCES roles(code),	--外部キー
	CHECK (id <= 999),
	CHECK (role_code <= 999)
);

CREATE TABLE IF NOT EXISTS physical_details(
user_id				INT NOT NULL,
created_at			TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP,	--年月日時分秒（小数部なし）、値を指定しなければ現在時刻が自動挿入
weight					DECIMAL(4, 1) NOT NULL,	--DECIMAL(5,2)：整数部分5桁まで、小数部分2桁
recorded_date	DATE NOT NULL,
PRIMARY KEY(user_id, created_at), --プライマリーキー
FOREIGN KEY(user_id) REFERENCES users(id),		--外部キー
CHECK (user_id <= 999)
);

