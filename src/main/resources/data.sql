-- CREATE CLIENT
INSERT INTO client VALUES (1L, 'Toujeni', 'Ghazi');


-- CREATE BANK_ACCOUNT
INSERT INTO bank_account VALUES (1L, 1000, NOW(), 1);

-- CREATE OPERATION
INSERT INTO activity VALUES(1L, 500, NOW(), 'DEPOSIT', 1);
INSERT INTO activity VALUES(2L, 100, NOW(), 'WITHDRAWAL', 1);

