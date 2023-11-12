INSERT INTO roles(rolename)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- USERS DATABASE
INSERT INTO users(username, password)
VALUES ('username', 'password');
-- TODO: bcrypt password opzoeken en copy paste ^

-- ACCOUNTS DATABASE
INSERT INTO accounts(firstname, lastname, phonenumber, emailaddress)
VALUES ('marianne', 'test', '0611122333', 'marianne@test.com'),
       ('koen', 'banaan', '0611122333', 'koen@banaan.com');


-- PROPERTIES DATABASE
INSERT INTO properties(address, price, description)
VALUES ('Singel 10', 725000.00, 'Aliquam eu lorem libero. In pharetra blandit ligula, non volutpat quam hendrerit non. Nulla et diam lorem. Etiam ut tortor luctus magna tempor hendrerit quis.'),
       ('Kerkstraat 125', 450000.00, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin imperdiet pretium metus, vitae elementum ante consectetur sit amet. Nulla quis bibendum nibh. Proin vitae erat.'),
       ('Singel 10', 725000.00, 'Aliquam eu lorem libero. In pharetra blandit ligula, non volutpat quam hendrerit non. Nulla et diam lorem. Etiam ut tortor luctus magna tempor hendrerit quis.'),
       ('Kerkstraat 125', 450000.00, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin imperdiet pretium metus, vitae elementum ante consectetur sit amet. Nulla quis bibendum nibh. Proin vitae erat.');

-- VIEWINGS DATABASE
