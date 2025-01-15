desc blog;
desc category;
desc post;
desc user;

insert into user values ("아이디","이름","비밀번호");
delete from user;
select * from user;
insert into blog values ("님의 블로그","사진주소","아이디");
select * from blog;
delete from user where name="d";
insert into user values (null, "일상", "기본 생성 카테고리", "ee");
select * from category;
insert into category values (null, "sdf", "FFf", "kkk");
update blog set title = "님 블로그" where blog_id = "kkk";
delete from user where name = 'd';
delete from blog where title="님의 블로그";
update blog set title = "dd블로그" where blog_id="dd";

select count(*) from post where category_id=1;

select * from post;

DROP TABLE IF EXISTS `jblog`.`post` ;

CREATE TABLE IF NOT EXISTS `jblog`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `contents` TEXT NOT NULL,
  `reg_date` DATETIME NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_post_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `jblog`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;