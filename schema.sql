INSERT INTO smdsm.users (
    id, first_name, last_name, gender, birthday)
VALUES
    (1, 'Ivan', 'Valadez', 'male', '1986-02-09'),
    (2, 'Rosa', 'Gonzales', 'female', '1990-04-12'),
    (3, 'Carlos', 'Gamboa', 'male', '1994-06-03'),
    (4, 'Jose', 'Amante', 'male', '1998-12-01'),
    (5, 'Monserrat', 'Rodriguez', 'female', '1996-01-02'),
    (6, 'Alberto', 'Almeida', 'male', '2000-02-04'),
    (7, 'Lorena', 'Espinoza', 'female', '2008-03-06'),
    (8, 'Hugo', 'Castañeda', 'male', '1999-05-08'),
    (9, 'Walter', 'Corona', 'male', '2012-06-10'),
    (10, 'Francisco', 'Perez', 'male', '2002-07-23')
;

INSERT INTO smdsm.diseases (
    id, name)
VALUES
    (1, 'diabetes'),
    (2, 'cardiovascular')
;

INSERT INTO smdsm.risk_factors (
    id, name)
VALUES
    (1, 'central_obesity'),
    (2, 'triglycerides'),
    (3, 'hdl'),
    (4, 'blood_pressure'),
    (5, 'plasma_glucose')
;

INSERT INTO smdsm.user_risk_factor (
    id_user, id_risk_factor, rate, active)
VALUES
    -- Ivan Si
    (1, 1, 90, 1),
    (1, 2, 150, 1),
    (1, 3, 40, 0),
    (1, 4, 130, 1),
    (1, 5, 100, 1),
    -- Rosa Si
    (2, 1, 80, 1),
    (2, 2, 150, 1),
    (2, 3, 50, 1),
    (2, 4, 130, 1),
    (2, 5, 100, 1),
    -- Carlos No
    (3, 1, 92, 1),
    (3, 2, 152, 1),
    (3, 3, 35, 0),
    (3, 4, 100, 0),
    (3, 5, 101, 0),
    -- Jose Si
    (4, 1, 95, 1),
    (4, 2, 158, 1),
    (4, 3, 42, 1),
    (4, 4, 128, 0),
    (4, 5, 102, 0),
    -- Montserrat Si
    (5, 1, 81, 1),
    (5, 2, 150, 1),
    (5, 3, 51, 1),
    (5, 4, 132, 1),
    (5, 5, 98, 1),
    -- Alberto No
    (6, 1, 90, 1),
    (6, 2, 148, 0),
    (6, 3, 40, 0),
    (6, 4, 126, 0),
    (6, 5, 101, 0),
    -- Lorena Si
    (7, 1, 82, 1),
    (7, 2, 150, 1),
    (7, 3, 40, 0),
    (7, 4, 128, 0),
    (7, 5, 100, 1),
    -- Hugo Si
    (8, 1, 95, 1),
    (8, 2, 148, 0),
    (8, 3, 40, 0),
    (8, 4, 138, 1),
    (8, 5, 105, 1),
    -- Walter No
    (9, 1, 80, 0),
    (9, 2, 140, 0),
    (9, 3, 40, 0),
    (9, 4, 100, 0),
    (9, 5, 90, 1),
    -- Francisco Si
    (10, 1, 91, 1),
    (10, 2, 150, 1),
    (10, 3, 40, 0),
    (10, 4, 127, 0),
    (10, 5, 98, 1);

INSERT INTO smdsm.user_disease (
    id_user, id_disease, active)
VALUES
    -- Ivan Si
    (1, 1, 1),
    (1, 2, 1),
    -- Rosa Si
    (2, 1, 1),
    (2, 2, 0),
    -- Carlos No
    (3, 1, 0),
    (3, 2, 0),
    -- Jose Si
    (4, 1, 0),
    (4, 2, 1),
    -- Montserrat Si
    (5, 1, 1),
    (5, 2, 1),
    -- Alberto No
    (6, 1, 0),
    (6, 2, 0),
    -- Lorena Si
    (7, 1, 1),
    (7, 2, 0),
    -- Hugo Si
    (8, 1, 1),
    (8, 2, 0),
    -- Walter No
    (9, 1, 0),
    (9, 2, 0),
    -- Francisco Si
    (10, 1, 0),
    (10, 2, 1);
