DELETE FROM book;
ALTER TABLE book AUTO_INCREMENT = 1001;

DELETE FROM category;
ALTER TABLE category AUTO_INCREMENT = 1001;

INSERT INTO `category` (`name`) VALUES ('Featured'),('BestSellers'),('Collectible-Books'),('Biographies'),('Gifts'),('science-math');

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Holly', 'Stephen King', '', 2392, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Retro Crochet Style', 'Savannah Price', '', 1770, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Josiah Manifesto', 'Jonathan Cahn', '', 2199, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Gambler', 'Walters Billy', '', 2716, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Build the Life You Want', 'Oprah Winfrey', '', 2199, 0, FALSE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Shadow Dance', 'Christine Feehan', '', 989, 0, TRUE, FALSE, 1001);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Youre Finally Here!', 'Maelanie Watt', '', 2392, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Blue Zones Secrets for Living Longer', 'Dan Buettner', '', 2622, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Cooks Book', 'Bri McKoy', '', 2805, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Fourth Turning Is Here', 'Neil Howe', '', 2593, 0, FALSE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Tom Lake', 'Ann Patchett', '', 2392, 0, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Hard Is Not the Same Thing as Bad', 'Abbie Halberstadt', '', 1595, 0, TRUE, FALSE, 1002);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Elements of Style', 'Strunk, William Jr', '', 6800, 0, FALSE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Miss Nelson Is Missing', 'Allard, Harry G', '', 8400, 0, FALSE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Firestarter', 'Stephen King', '', 5600, 0, FALSE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Satanic Verses', 'Rushdie, Salman', '', 2300, 0, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Pilgrim at Tinker Creek', 'Annie Dillard', '', 4500, 0, FALSE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Autobiography of Benjamin Franklin', 'Benjamin Franklin', '', 3500, 0, TRUE, FALSE, 1003);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The 48 Laws of Power', 'Robert Greene', '', 1927, 0, FALSE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Team of Rivals', 'Doris Kearns Goodwin', '', 599, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Black Elk Speaks', 'John G. Neihardt', '', 569, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('John Adams', 'David McCullough', '', 429, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Travels with Charley', 'John Steinbeck', '', 579, 0, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Truman', 'David McCullough', '', 479, 0, TRUE, FALSE, 1004);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Nash', 'Jay Crownover', '', 1532, 0, FALSE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Better When Hes Brave', 'Jay Crownover', '', 1329, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('The Madonna Secret', 'Sophie Strand', '', 1892, 0, FALSE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Heaven Officials Blessing', 'Mò Xing Tóng Xiù', '', 1595, 0, FALSE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Secrets in the Dark', 'Heather Graham', '', 954, 0, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Palazzo', 'Danielle Steel', '', 2243, 0, FALSE, FALSE, 1005);

INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Creative Coding in Python', 'Sheena Vaidyanathan', '', 1921, 0, FALSE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Build Your Own Website', 'Kim Gee', '', 699, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Coding Projects in Python', 'D.K. Publishing', '', 769, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Girl Code', 'Andrea Gonzales', '', 479, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Coding for Minecrafters', 'Lynn Beighley', '', 999, 0, TRUE, FALSE, 1006);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id) VALUES ('Teach Your Kids to Code', 'Bryson Payne', '', 589, 0, TRUE, FALSE, 1006);

...
