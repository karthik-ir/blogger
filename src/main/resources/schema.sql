
create table if not exists blog(
id long PRIMARY KEY  not null,
title varchar(100) not null,
datetime date not null
);

create table if not exists paragraph(
id long PRIMARY KEY  not null,
data varchar(100) not null,
blog_id long,
foreign key(blog_id) references blog(id)
);

create table if not exists comment(
id long PRIMARY KEY  not null,
data varchar(100) not null,
paragraph_id long,
foreign key(paragraph_id) references paragraph(id)
);