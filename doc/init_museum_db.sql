

/* データベース作成。「if not exists」はmysqlのみ。*/
create database if not exists cal_db character set utf8;

/* 作成したデータベースに接続。 */
use cal_db;



/* 表を一旦削除する。 */
drop table if exists museum_data;

/*
 * 画像テーブル作成
 *
 * mysql> show columns from museum_data;
 * +-----------+--------------+------+-----+---------+----------------+
 * | Field     | Type         | Null | Key | Default | Extra          |
 * +-----------+--------------+------+-----+---------+----------------+
 * | id        | int(11)      | NO   | PRI | NULL    | auto_increment |
 * | author    | varchar(256) | YES  |     | NULL    |                |
 * | imagename | varchar(256) | YES  | UNI | NULL    |                |
 * | imagedata | blob         | YES  |     | NULL    |                |
 * | upd_date  | datetime     | YES  |     | NULL    |                |
 * +-----------+--------------+------+-----+---------+----------------+
 * 5 rows in set (0.00 sec)
 */
create table museum_data (
	id			int auto_increment primary key,
	author		varchar(256), 
	imagename	varchar(256) unique key,
	imagedata	blob,
	upd_date	datetime
);



/* 表を一旦削除する。 */
drop table if exists museum_comment;

/*
 * コメントテーブル作成
 *
 *mysql> show columns from museum_comment;
 *+-------------+--------------+------+-----+---------+----------------+
 *| Field       | Type         | Null | Key | Default | Extra          |
 *+-------------+--------------+------+-----+---------+----------------+
 *| id          | int(11)      | NO   | PRI | NULL    | auto_increment |
 *| pic_id      | int(11)      | NO   |     | NULL    |                |
 *| comment     | varchar(256) | YES  |     | NULL    |                |
 *| commentator | varchar(256) | YES  |     | NULL    |                |
 *| upd_date    | datetime     | YES  |     | NULL    |                |
 *+-------------+--------------+------+-----+---------+----------------+
 *5 rows in set (0.00 sec)
 */
create table museum_comment (
	id			int auto_increment primary key,
	pic_id		int not null,
	comment		varchar(256),
	commentator	varchar(256),
	upd_date 	datetime
);



/* 表を一旦削除する。 */
drop table if exists museum_tags;

/*
 * タグテーブル作成
 *
 * mysql> show columns from museum_tags;
 * +----------+--------------+------+-----+---------+----------------+
 * | Field    | Type         | Null | Key | Default | Extra          |
 * +----------+--------------+------+-----+---------+----------------+
 * | id       | int(11)      | NO   | PRI | NULL    | auto_increment |
 * | tag      | varchar(256) | NO   | UNI | NULL    |                |
 * +----------+--------------+------+-----+---------+----------------+
 * 3 rows in set (0.00 sec)
 */
create table museum_tags (
	id	int auto_increment primary key,
	tag	varchar(256) not null unique key
);



/* 表を一旦削除する。 */
drop table if exists museum_pictotag;

/*
 * 画像toタグテーブル作成
 *
 * mysql> show columns from museum_pictotag;
 * +----------+----------+------+-----+---------+----------------+
 * | Field    | Type     | Null | Key | Default | Extra          |
 * +----------+----------+------+-----+---------+----------------+
 * | id       | int(11)  | NO   | PRI | NULL    | auto_increment |
 * | pic_id   | int(11)  | NO   |     | NULL    |                |
 * | tag_id   | int(11)  | NO   |     | NULL    |                |
 * +----------+----------+------+-----+---------+----------------+
 * 4 rows in set (0.02 sec)
 */
create table museum_pictotag (
	id		int auto_increment primary key,
	pic_id	int not null,
	tag_id	int not null
);


