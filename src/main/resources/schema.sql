DROP TABLE IF EXISTS tools;
DROP TABLE IF EXISTS tool_charges;

CREATE TABLE tools (
  code VARCHAR(250) PRIMARY KEY,
  tool_type INT,
  brand VARCHAR(250)
);

CREATE TABLE tool_charges (
  tool_type INT PRIMARY KEY,
  daily DOUBLE PRECISION,
  weekday_charge BOOLEAN,
  weekend BOOLEAN,
  holiday BOOLEAN
);