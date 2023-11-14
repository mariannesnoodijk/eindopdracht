INSERT INTO roles(rolename)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- USERS DATABASE
INSERT INTO users(username, password)
VALUES ('username', 'password');
-- TODO: bcrypt password opzoeken en copy paste ^

-- ACCOUNTS DATABASE
INSERT INTO accounts(account_id, firstname, lastname, email, user_username)
VALUES (100, 'pietje', 'puk', 'pietjepuk@test.com', 'username');
-- username in accounts MOET wel al gedefinieerd zijn.

-- PROPERTIES DATABASE
INSERT INTO properties(property_id, address, price, description)
VALUES (100, 'Singel 10', 725000.00, 'Aliquam eu lorem libero. In pharetra blandit ligula, non volutpat quam hendrerit non. Nulla et diam lorem. Etiam ut tortor luctus magna tempor hendrerit quis.'),
       (101, 'Kerkstraat 125', 450000.00, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin imperdiet pretium metus, vitae elementum ante consectetur sit amet. Nulla quis bibendum nibh. Proin vitae erat.'),
       (102, 'Singel 10', 725000.00, 'Aliquam eu lorem libero. In pharetra blandit ligula, non volutpat quam hendrerit non. Nulla et diam lorem. Etiam ut tortor luctus magna tempor hendrerit quis.'),
       (103, 'Kerkstraat 125', 450000.00, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin imperdiet pretium metus, vitae elementum ante consectetur sit amet. Nulla quis bibendum nibh. Proin vitae erat.');

-- VIEWINGS DATABASE
