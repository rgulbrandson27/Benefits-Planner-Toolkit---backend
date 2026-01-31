-- Create Employee - Patricia Planner (TTW benefits counselor)
INSERT INTO employees (id, first_name, last_name, email, program_type) VALUES
    (27, 'Patricia', 'Planner', 'patricia.planner@benefitshelp.org', 'TTW');

-- Create two Beneficiary Clients
INSERT INTO clients (id, client_id_number, first_name, last_name, primary_worker_id, onset_date, application_date, entitlement_date, medicare_start_date) VALUES
    (1, 'AAA111', 'Betty', 'Beneficiary1', 27, '2018-10-19', '2019-11-10', '2018-11-01', '2020-11-01'),
    (2, 'BBB222', 'Bernard', 'Beneficiary2', 27, '2008-06-22', '2008-06-30', '2008-12-01', '2010-12-01');

-- Betty - Scenario 1
INSERT INTO scenarios (client_id, employee_id, description, status, work_incentives_data, earnings_data, created_at, updated_at) VALUES
    (1, 27, 'Resuming worth with former employer, working 20 hours/week earning $16/hour.', 'ACTIVE',
     '{"twpMonthsUsed": 0, "epeStatus": "NOT_STARTED", "benefitAmount": 1425.00, "sgaThreshold": 1550.00}',
     '{"monthlyGross": 600.00, "irweDeductions": 0, "countableIncome": 600.00, "projectedAnnual": 7200.00}',
     NOW(), NOW());

-- Betty - Scenario 2
INSERT INTO scenarios (client_id, employee_id, description, status, work_incentives_data, earnings_data, created_at, updated_at) VALUES
    (1, 27, 'If hours increase to 25/week - exploring sustainability', 'DRAFT',
     '{"twpMonthsUsed": 0, "epeStatus": "NOT_STARTED", "benefitAmount": 1425.00, "sgaThreshold": 1550.00, "riskLevel": "LOW"}',
     '{"monthlyGross": 1250.00, "irweDeductions": 50.00, "countableIncome": 1200.00, "projectedAnnual": 15000.00}',
     NOW(), NOW());

-- Bernard - Scenario 1
INSERT INTO scenarios (client_id, employee_id, description, status, work_incentives_data, earnings_data, created_at, updated_at) VALUES
    (2, 27, 'Remote customer service - 30 hours/week, completed TWP last year', 'ACTIVE',
     '{"twpMonthsUsed": 9, "epeStatus": "IN_EPE", "benefitAmount": 1680.50, "sgaThreshold": 1550.00, "epeMonthsElapsed": 18}',
     '{"monthlyGross": 1825.00, "irweDeductions": 200.00, "countableIncome": 1625.00, "projectedAnnual": 21900.00, "exceedsSGA": true, "note": "Currently above SGA - benefits suspended but EPE protection active"}',
     NOW(), NOW());

-- Bernard - Scenario 2
INSERT INTO scenarios (client_id, employee_id, description, status, work_incentives_data, earnings_data, created_at, updated_at) VALUES
    (2, 27, 'Contingency plan - reduce to 20 hours if symptoms increase', 'DRAFT',
     '{"twpMonthsUsed": 9, "epeStatus": "IN_EPE", "benefitAmount": 1680.50, "sgaThreshold": 1550.00, "epeMonthsElapsed": 18, "riskLevel": "LOW"}',
     '{"monthlyGross": 1200.00, "irweDeductions": 200.00, "countableIncome": 1000.00, "projectedAnnual": 14400.00, "note": "Below SGA - benefits would reinstate under EPE"}',
     NOW(), NOW());                                                                                                                                                                                          (4, NOW(), 1000.00, 1000.00, 1550.00, false, 'SAFE_TO_WORK', 'First job attempt - will start TWP but benefits fully protected', NOW());