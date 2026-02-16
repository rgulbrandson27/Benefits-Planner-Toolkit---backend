-- Create Employee - Patricia Planner (TTW benefits counselor)
INSERT INTO employees (id, first_name, last_name, email, program_type) VALUES
    (27, 'Patricia', 'Planner', 'patricia.planner@ssabenefitshelp.org', 'TTW');

-- Create two Beneficiaries
INSERT INTO clients (id, client_id_number, first_name, last_name, primary_worker_id, onset_date, application_date, entitlement_date, medicare_start_date) VALUES
    (1,'AAA111', 'Betty', 'Beneficiary1', 27, '2023-10-19', '2024-11-10', '2023-11-01', '2025-11-01'),
    (2, 'BBB222', 'Bernard', 'Beneficiary2', 27, '2008-06-22', '2008-07-30', '2008-12-01', '2010-12-01');

-- Betty - SCENARIO 1
INSERT INTO scenarios (client_id, employee_id, scenario_start_year, description, status, created_at, updated_at) VALUES
    (1, 27, 2025, 'Resumed working with former employer in June 2025, working 20 hours/week at $16.00/hour for monthly average earnings of $1,686.00.', 'ACTIVE', NOW(), NOW());

INSERT INTO monthly_work_status (scenario_id, year, month, earnings_amount, earnings_category, twp_month_number, epe_month_number, grace_month_number, medicare_status, created_at, updated_at) VALUES
    (1, 2025, 6, 1686.00, 'ABOVE_TWP', 1, null, null, null, NOW(), NOW()),
    (1, 2025, 7, 1686.00, 'ABOVE_TWP', 2, null, null, null, NOW(), NOW()),
    (1, 2025, 8, 1686.00, 'ABOVE_TWP', 3, null, null, null, NOW(), NOW()),
    (1, 2025, 9, 1686.00, 'ABOVE_TWP', 4, null, null, null, NOW(), NOW()),
    (1, 2025, 10, 1686.00, 'ABOVE_TWP', 5, null, null, null, NOW(), NOW()),
    (1, 2025, 11, 1686.00, 'ABOVE_TWP', 6, null, null, null, NOW(), NOW()),
    (1, 2025, 12, 1686.00, 'ABOVE_TWP', 7, null, null, null, NOW(), NOW()),
    (1, 2026, 1, 1686.00, 'ABOVE_TWP', 8, null, null, null, NOW(), NOW()),
    (1, 2026, 2, 1686.00, 'ABOVE_TWP', 9, null, null, null, NOW(), NOW());

-- Betty - SCENARIO 2
INSERT INTO scenarios (client_id, employee_id, scenario_start_year, description, status, created_at, updated_at) VALUES
    (1, 27, 2025, 'Returned to work part-time February 2025 (20 hrs/week at $16/hr), increased to full-time June 2025 (32 hrs/week at $18/hr).', 'ACTIVE', NOW(), NOW());

-- TWP months - Part-time (Feb-May 2025)
INSERT INTO monthly_work_status (scenario_id, year, month, earnings_amount, earnings_category, twp_month_number, epe_month_number, grace_month_number, benefit_status, medicare_status, created_at, updated_at) VALUES
    (2, 2025, 2, 1385.60, 'ABOVE_TWP', 1, null, null, null, null, NOW(), NOW()),
    (2, 2025, 3, 1385.60, 'ABOVE_TWP', 2, null, null, null, null, NOW(), NOW()),
    (2, 2025, 4, 1385.60, 'ABOVE_TWP', 3, null, null, null, null, NOW(), NOW()),
    (2, 2025, 5, 1385.60, 'ABOVE_TWP', 4, null, null, null, null, NOW(), NOW()),

-- TWP months - Full-time (Jun-Oct 2025)
    (2, 2025, 6, 2494.08, 'ABOVE_SGA', 5, null, null, null, null, NOW(), NOW()),
    (2, 2025, 7, 2494.08, 'ABOVE_SGA', 6, null, null, null, null, NOW(), NOW()),
    (2, 2025, 8, 2494.08, 'ABOVE_SGA', 7, null, null, null, null, NOW(), NOW()),
    (2, 2025, 9, 2494.08, 'ABOVE_SGA', 8, null, null, null, null, NOW(), NOW()),
    (2, 2025, 10, 2494.08, 'ABOVE_SGA', 9, null, null, null, null, NOW(), NOW()),

-- EPE starts, Grace period (Nov 2025-Jan 2026) - Benefits still paying
    (2, 2025, 11, 2494.08, 'ABOVE_SGA', null, 1, 1, null, null, NOW(), NOW()),
    (2, 2025, 12, 2494.08, 'ABOVE_SGA', null, 2, 2, null, null, NOW(), NOW()),
    (2, 2026, 1, 2494.08, 'ABOVE_SGA', null, 3, 3, null, null, NOW(), NOW()),

-- Benefits suspended after grace (Feb 2026)
    (2, 2026, 2, 2494.08, 'ABOVE_SGA', null, 4, null, 'BENEFIT_SUSPENDED', null, NOW(), NOW()),

-- EPE ends (Oct 2028) - still suspended
    (2, 2028, 10, 2494.08, 'ABOVE_SGA', null, 36, null, 'BENEFIT_SUSPENDED', null, NOW(), NOW()),

-- Benefits terminated after EPE (Nov 2028)
    (2, 2028, 11, 2494.08, 'ABOVE_SGA', null, null, null, 'BENEFIT_TERMINATED', null, NOW(), NOW());



-- Bernard - Scenario 1
-- INSERT INTO scenarios (client_id, employee_id, description, status, work_incentives_data, earnings_data, created_at, updated_at) VALUES
--     (2, 27, 'Remote customer service - 30 hours/week, completed TWP last year', 'ACTIVE',
--      '{"twpMonthsUsed": 9, "epeStatus": "IN_EPE", "benefitAmount": 1680.50, "sgaThreshold": 1550.00, "epeMonthsElapsed": 18}',
--      '{"monthlyGross": 1825.00, "irweDeductions": 200.00, "countableIncome": 1625.00, "projectedAnnual": 21900.00, "exceedsSGA": true, "note": "Currently above SGA - benefits suspended but EPE protection active"}',
--      NOW(), NOW());

-- Bernard - Scenario 2
-- INSERT INTO scenarios (client_id, employee_id, description, status, work_incentives_data, earnings_data, created_at, updated_at) VALUES
--     (2, 27, 'Contingency plan - reduce to 20 hours if symptoms increase', 'DRAFT',
--      '{"twpMonthsUsed": 9, "epeStatus": "IN_EPE", "benefitAmount": 1680.50, "sgaThreshold": 1550.00, "epeMonthsElapsed": 18, "riskLevel": "LOW"}',
--      '{"monthlyGross": 1200.00, "irweDeductions": 200.00, "countableIncome": 1000.00, "projectedAnnual": 14400.00, "note": "Below SGA - benefits would reinstate under EPE"}',
--      NOW(), NOW());                                                                                                                                                                                          (4, NOW(), 1000.00, 1000.00, 1550.00, false, 'SAFE_TO_WORK', 'First job attempt - will start TWP but benefits fully protected', NOW());