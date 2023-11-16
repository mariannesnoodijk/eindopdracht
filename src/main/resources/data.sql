INSERT INTO roles(rolename)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- USERS DATABASE
INSERT INTO users(username, password)
VALUES ('admin', '$2a$12$SMY80YVA2Y10RxbZWQKiHetM2B/WjH1GqItMYJircqt/Rqs1mvWWm'),
       ('testuser', '$2a$12$aSfpc94Jc.yBUeiC20Hqk.mSddJ.X5bTiXsJQkopj0JOgPD6h3Dc6');

-- ASSIGNING USER TO ROLE
INSERT INTO users_roles(roles_rolename, users_username)
    VALUES ('ROLE_ADMIN', 'admin'),
           ('ROLE_USER', 'testuser');

-- ACCOUNTS DATABASE
INSERT INTO accounts(account_id, firstname, lastname, email, user_username)
VALUES (100, 'marianne', 'snoodijk', 'mariannesnoodijk@test.com', 'testuser');

-- PROPERTIES DATABASE
INSERT INTO properties(property_id, address, price, description)
VALUES (100, 'Singel 10, Amsterdam', 725000.00, 'Charming apartment with modern amenities, centrally located in vibrant Amsterdam neighborhood. Open floor plan, ample natural light, ideal for city living.'),
       (101, 'Kerkstraat 125, Amsterdam', 475000.00, 'Spacious family home with garden. Quiet neighborhood, close to schools and shops. Recent renovations, contemporary design, perfect for family life in Amsterdam.'),
       (102, 'Van Woustraat 69, Amsterdam', 799000.00, 'Luxury penthouse with spectacular city views. High-end finishes, private terrace, within walking distance of exclusive shops and restaurants in Amsterdam.'),
       (103, 'Vondelweg 100, Amsterdam', 1450000.00, 'Trendy studio with panoramic city views. Fully furnished and equipped with high-end appliances. Near public transportation and recreational facilities in Amsterdam.'),
       (104, 'Koninginneweg 253, Amsterdam', 999000, 'Cozy canal-side cottage in the heart of Amsterdam. Authentic details, fireplace, perfect for those who appreciate tranquility and city charm.'),
       (105, 'Kalverstraat 210, Amsterdam', 1200000.00, 'Stylish loft in the historic Jordaan district. Exposed beams, contemporary decor, and city views. Close to cultural hotspots and trendy cafes, ideal for urban living.'),
       (106, 'Leidsestraat 56, Amsterdam', 450000.00, 'Quaint townhouse along Amsterdams iconic canals. Classic architecture, updated interiors, and a charming garden terrace. Proximity to museums and transport links.'),
       (107, 'PC Hooftstraat 169, Amsterdam', 1725000.00, 'Modern apartment in the lively De Pijp neighborhood. Sleek design, high ceilings, and balcony. Surrounded by eclectic shops, eateries, and the vibrant street market.'),
       (108, 'Noordermarkt 100, Amsterdam', 1450000.00, 'Riverside duplex with stunning Amstel River views. Elegant interior, spacious layout, and balcony. Located near parks, cycle paths, and convenient public transportation.'),
       (109, 'Maasstraat 353, Amsterdam', 989000, 'Compact studio in the heart of Amsterdams Old Town. Efficient use of space, contemporary furnishings, and proximity to historic landmarks and bustling markets.');

-- VIEWINGS DATABASE
INSERT INTO viewings(date, time, account_id, viewing_id, email, fullname, phonenumber)
VALUES ('2023-12-12', '11:30', 100, 50, 'mariannesnoodijk@test.com', 'marianne snoodijk', '0611122333'),
       ('2024-06-10', '14:00', 100, 51, 'mariannesnoodijk@test.com', 'marianne snoodijk', '0611122333');