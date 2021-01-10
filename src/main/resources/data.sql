--Main ToDo list
INSERT INTO
	TBL_TODO_LISTS (name)
VALUES
  	('school'),
  	('Job'),
  	('grocery');

--sub items in main ToDo list
INSERT INTO
	TBL_TODO_LIST_ITEM (item_name ,item_status ,item_due_date ,list_id )
VALUES
  	('pickup', 'pending', '2021-01-23 01:00:00', 1),
  	('enroll', 'pending', '2021-01-23 01:00:00', 1),
  	('submit', 'pending', '2021-02-21 01:00:00', 2),
    ('milk', 'pending', '2021-01-11 11:00:00', 3);

--tag names for list items
INSERT INTO
	TBL_TAGS (tag_name)
VALUES
  	('School tag'),
  	('Application tag'),
  	('Shopping tag');

--mapping of tag with list items
INSERT INTO
	TBL_TAG_ITEMS (tag_id, item_id)
VALUES
  	(1,1),
  	(1,2),
  	(2,2),
  	(3,3);
