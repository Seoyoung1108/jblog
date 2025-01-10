desc blog;
desc category;
desc post;
desc user;

insert into user values ("아이디","이름","비밀번호");
delete from user;
select * from user;
insert into blog values ("님의 블로그","사진주소","아이디");
select * from blog;
delete from user where id="aa";
insert into user values (null, "일상", "기본 생성 카테고리", "ee");
select * from category;