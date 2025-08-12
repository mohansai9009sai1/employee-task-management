
INSERT INTO EMPLOYEE (id, name, email, department) VALUES (1, 'sai', 'sai@gmail.com', 'Developer');
INSERT INTO EMPLOYEE (id, name, email, department) VALUES (2, 'mohanSai', 'mohansai@gmail.com', 'Tester');
INSERT INTO EMPLOYEE (id, name, email, department) VALUES (3, 'saiMohan', 'saimohan@gmail.com', 'DevOps');

INSERT INTO TASK (id, title, description, status, assigned_to_id) VALUES (1, 'implementation', 'details about task', 'PENDING', 1);
INSERT INTO TASK (id, title, description, status, assigned_to_id) VALUES (2, 'DB changes', 'ER model', 'IN_PROGRESS', 1);
INSERT INTO TASK (id, title, description, status, assigned_to_id) VALUES (3, 'Automation', 'Prepare test cases', 'PENDING', 2);
INSERT INTO TASK (id, title, description, status, assigned_to_id) VALUES (4, 'CICD', 'Create a pipeline', 'COMPLETED', 3);
INSERT INTO TASK (id, title, description, status, assigned_to_id) VALUES (5, 'Refactor', 'Cleanup code', 'PENDING', 1);
INSERT INTO TASK (id, title, description, status, assigned_to_id) VALUES (6, 'UAT Environment', 'Test in UAT environment', 'IN_PROGRESS', 2);
