INSERT INTO tools (code, tool_type, brand) VALUES
('CHNS', 0, 'Stihl'),
('LADW', 1, 'Werner'),
('JAKD', 2, 'DeWalt'),
('JAKR', 2, 'Ridgid');

INSERT INTO tool_charges (tool_type, daily, weekday_charge, weekend, holiday) VALUES
(0, 1.49, TRUE, FALSE, TRUE),
(1, 1.99, TRUE, TRUE, FALSE),
(2, 2.99, TRUE, FALSE, FALSE);