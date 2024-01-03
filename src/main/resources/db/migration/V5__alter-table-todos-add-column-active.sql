alter table todos add is_active tinyint;
update todos set is_active = 1;
