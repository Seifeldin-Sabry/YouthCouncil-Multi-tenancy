INSERT INTO FORMS(TITLE)
VALUES ('hehe xd'),
       ('nice one'),
       ('this goes in');

INSERT INTO QUESTIONS(is_required, question_text, question_type, question_order, form_id)
VALUES (TRUE, 'aint no way bar', 'TEXT_QUESTION', 1, 1),
       (FALSE, 'faahhkk', 'RADIO_QUESTION', 2, 1),
       (TRUE, 'amahan', 'MULTIPLE_CHOICE_QUESTION', 3, 1);
INSERT INTO RADIO_QUESTION_CHOICES(radio_question_question_id, choices)
VALUES (2, 'hehexd'),
       (2, 'adasfsda'),
       (2, 'hehexdddd');

INSERT INTO MULTIPLE_CHOICE_QUESTION_CHOICES(MULTIPLE_CHOICE_question_question_id, choices)
VALUES (3, 'heddhexd'),
       (3, 'adasdddfsda'),
       (3, 'hehadsfsadd');

INSERT INTO municipalities (name)
VALUES ('Antwerp');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2000, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2018, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2020, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2030, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2040, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2050, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2060, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2100, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2140, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2170, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2180, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2600, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2610, 1);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2660, 1);
INSERT INTO municipalities (name)
VALUES ('Zwijndrecht');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2070, 2);
INSERT INTO municipalities (name)
VALUES ('Wijnegem');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2110, 3);
INSERT INTO municipalities (name)
VALUES ('Borsbeek');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2150, 4);
INSERT INTO municipalities (name)
VALUES ('Wommelgem');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2160, 5);
INSERT INTO municipalities (name)
VALUES ('Herentals');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2200, 6);
INSERT INTO municipalities (name)
VALUES ('Heist-op-den-Berg');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2220, 7);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2221, 7);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2222, 7);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2223, 7);
INSERT INTO municipalities (name)
VALUES ('Herselt');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2230, 8);
INSERT INTO municipalities (name)
VALUES ('Hulshout');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2235, 9);
INSERT INTO municipalities (name)
VALUES ('Zandhoven');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2240, 10);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2242, 10);
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2243, 10);
INSERT INTO municipalities (name)
VALUES ('Olen');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2250, 11);
INSERT INTO municipalities (name)
VALUES ('Westerlo');
INSERT INTO postcodes (postcode, municipality_id)
VALUES (2260, 12);