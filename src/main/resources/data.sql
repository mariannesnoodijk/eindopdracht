INSERT INTO roles(rolename)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

-- USERS DATABASE
INSERT INTO users(username, password)
VALUES ('mariannesnoodijk', '$2a$12$4WbEn4EAbV7JXxOEleyzMeiJXLVX8IgRTU1LGC2MfCGfwXL6RO4Lq');

-- ACCOUNTS DATABASE
INSERT INTO accounts(account_id, firstname, lastname, email, user_username)
VALUES (100, 'marianne', 'snoodijk', 'mariannesnoodijk@test.com', 'mariannesnoodijk');
-- username in accounts MOET wel al gedefinieerd zijn.

-- PROPERTIES DATABASE
INSERT INTO properties(property_id, address, price, description)
VALUES (100, 'Singel 10', 725000.00, 'Charming apartment with modern amenities, centrally located in vibrant Amsterdam neighborhood. Open floor plan, ample natural light, ideal for city living.'),
       (101, 'Kerkstraat 125', 450000.00, 'Spacious family home with garden. Quiet neighborhood, close to schools and shops. Recent renovations, contemporary design, perfect for family life in Amsterdam.'),
       (102, 'Van Woustraat 69', 725000.00, 'Luxury penthouse with spectacular city views. High-end finishes, private terrace, within walking distance of exclusive shops and restaurants in Amsterdam.'),
       (103, 'Vondelweg 100', 1450000.00, 'Trendy studio with panoramic city views. Fully furnished and equipped with high-end appliances. Near public transportation and recreational facilities in Amsterdam.'),
       (104, 'Koninginneweg 253', 999000, 'Cozy canal-side cottage in the heart of Amsterdam. Authentic details, fireplace, perfect for those who appreciate tranquility and city charm.'),
       (105, 'Kalverstraat 210', 725000.00, 'Stylish loft in the historic Jordaan district. Exposed beams, contemporary decor, and city views. Close to cultural hotspots and trendy cafes, ideal for urban living.'),
       (106, 'Leidsestraat 56', 450000.00, 'Quaint townhouse along Amsterdams iconic canals. Classic architecture, updated interiors, and a charming garden terrace. Proximity to museums and transport links.'),
       (107, 'PC Hooftstraat 69', 725000.00, 'Modern apartment in the lively De Pijp neighborhood. Sleek design, high ceilings, and balcony. Surrounded by eclectic shops, eateries, and the vibrant street market.'),
       (108, 'Noordermarkt 100', 1450000.00, 'Riverside duplex with stunning Amstel River views. Elegant interior, spacious layout, and balcony. Located near parks, cycle paths, and convenient public transportation.'),
       (109, 'Maasstraat 353', 999000, 'Compact studio in the heart of Amsterdams Old Town. Efficient use of space, contemporary furnishings, and proximity to historic landmarks and bustling markets.');

-- VIEWINGS DATABASE
-- INSERT INTO viewings(velden uit viewing entity!!!)
-- VALUES ()