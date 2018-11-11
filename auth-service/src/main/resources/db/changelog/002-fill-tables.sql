-- Insert default data

INSERT INTO role(name) VALUES ('ROLE_ADMIN');
INSERT INTO role(name) VALUES ('ROLE_USER');

-- User login/pas = admin/admin
INSERT INTO account(login, email, password, active)
  VALUES ('admin', 'admin@admin.com', '$2a$11$Q/gDzPwNGojss/v1hsPG' ||
   '.exnkGiJBjy3f3niBoTeCSgyOTDeaMi0u', true);

-- User login/pas = user/admin
INSERT INTO account(login, email, password, active)
  VALUES ('user', 'user@user.com', '$2a$11$Q/gDzPwNGojss/v1hsPG' ||
   '.exnkGiJBjy3f3niBoTeCSgyOTDeaMi0u', true);

INSERT INTO account_role(account_id, role_id) VALUES (1, 1);
INSERT INTO account_role(account_id, role_id) VALUES (1, 2);
