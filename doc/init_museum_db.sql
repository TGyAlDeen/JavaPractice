

/* �f�[�^�x�[�X�쐬�B�uif not exists�v��mysql�̂݁B*/
create database if not exists cal_db character set utf8;

/* �쐬�����f�[�^�x�[�X�ɐڑ��B */
use cal_db;



/* �\����U�폜����B */
drop table if exists museum_data;

/*
 * �摜�e�[�u���쐬
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



/* �\����U�폜����B */
drop table if exists museum_comment;

/*
 * �R�����g�e�[�u���쐬
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



/* �\����U�폜����B */
drop table if exists museum_tags;

/*
 * �^�O�e�[�u���쐬
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



/* �\����U�폜����B */
drop table if exists museum_pictotag;

/*
 * �摜to�^�O�e�[�u���쐬
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


