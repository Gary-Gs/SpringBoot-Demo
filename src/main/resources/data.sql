INSERT INTO `USER` (USER_ID, FULL_NAME) VALUES
(1, 'alice'),
(2, 'bob');

INSERT INTO `CATEGORY` (CATEGORY_ID, CATEGORY_NAME) VALUES
(1, 'food'),
(2, 'drinks');

INSERT INTO `PRODUCT` (PRODUCT_ID, PRODUCT_NAME, DESCRIPTION, PRICE) VALUES
(1, 'apple', 'green apple', 1.5),
(2, 'coke', 'coke light', 1.2);

INSERT INTO `PRODUCT_CATEGORY` (PRODUCT_ID, CATEGORY_ID) VALUES
(1, 1),
(2, 2);
