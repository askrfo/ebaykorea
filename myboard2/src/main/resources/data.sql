insert into member(id, name, email, passwd, regdate)
   values(1, 'kim', 'urstory@gmail.com', '{bcrypt}$2a$10$G5Qu/l/fe1jQy7yv6aFR1ehNAsUz3lhvtv80ZlJ2i54ansGO2UNWi', now() );

insert into member_role(id, name, member_id) values (1, 'USER', 1);

insert into board(id, title, content, regdate, member_id)
   values(1, 'hello 111', 'hello 111 content', now(), 1);

insert into board(id, title, content, regdate, member_id)
   values(2, 'hello 222', 'hello 222 content', now(), 1);

insert into board(id, title, content, regdate, member_id)
   values(3, 'hello 333', 'hello 333 content', now(), 1);

insert into board(id, title, content, regdate, member_id)
   values(4, 'hello 444', 'hello 444 content', now(), 1);

insert into board(id, title, content, regdate, member_id)
   values(5, 'hello 555', 'hello 555 content', now(), 1);
