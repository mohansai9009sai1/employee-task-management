
INSERT INTO EMPLOYEE (name, email, department) VALUES ('sai', 'sai@gmail.com', 'Developer');
INSERT INTO EMPLOYEE (name, email, department) VALUES ('mohanSai', 'mohansai@gmail.com', 'Tester');
INSERT INTO EMPLOYEE ( name, email, department) VALUES ('saiMohan', 'saimohan@gmail.com', 'DevOps');

INSERT INTO TASK ( title, description, status, assigned_to_id) VALUES ('implementation', 'details about task', 'PENDING', 1);
INSERT INTO TASK (title, description, status, assigned_to_id) VALUES ('DB changes', 'ER model', 'IN_PROGRESS', 1);
INSERT INTO TASK ( title, description, status, assigned_to_id) VALUES ('Automation', 'Prepare test cases', 'PENDING', 2);
INSERT INTO TASK ( title, description, status, assigned_to_id) VALUES ('CICD', 'Create a pipeline', 'COMPLETED', 3);
INSERT INTO TASK ( title, description, status, assigned_to_id) VALUES ( 'Refactor', 'Cleanup code', 'PENDING', 1);
INSERT INTO TASK ( title, description, status, assigned_to_id) VALUES ( 'UAT Environment', 'Test in UAT environment', 'IN_PROGRESS', 2);
