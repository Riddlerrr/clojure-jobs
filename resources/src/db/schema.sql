create table if not exists vacancies (
  id            integer primary key autoincrement,
  title         varchar(32),
  description   text
);
