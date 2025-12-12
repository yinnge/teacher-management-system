-- 启用 pgvector 扩展
CREATE EXTENSION IF NOT EXISTS vector;

-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS t_user CASCADE;

CREATE TABLE t_user (
                        id BIGSERIAL PRIMARY KEY,
                        username VARCHAR(50) UNIQUE NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        role VARCHAR(30) NOT NULL,
                        create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ----------------------------
-- 教师表
-- ----------------------------
DROP TABLE IF EXISTS t_teacher CASCADE;

CREATE TABLE t_teacher (
                           id BIGSERIAL PRIMARY KEY,
                           user_id BIGINT NOT NULL REFERENCES t_user(id) ON DELETE CASCADE,
                           username VARCHAR(100),
                           name VARCHAR(100),
                           department VARCHAR(100)
);

-- ----------------------------
-- 课程表
-- ----------------------------
DROP TABLE IF EXISTS t_course CASCADE;

CREATE TABLE t_course (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(200) NOT NULL,
                          teacher_id BIGINT REFERENCES t_teacher(id) ON DELETE SET NULL,
                          description TEXT
);

-- ----------------------------
-- 科研成果表
-- ----------------------------
DROP TABLE IF EXISTS t_achievement CASCADE;

CREATE TABLE t_achievement (
                               id BIGSERIAL PRIMARY KEY,
                               title VARCHAR(255),
                               description TEXT,
                               teacher_id BIGINT REFERENCES t_teacher(id) ON DELETE SET NULL,
                               vector vector(1536)
);

-- ----------------------------
-- 向量表
-- ----------------------------
DROP TABLE IF EXISTS t_embedding CASCADE;

CREATE TABLE t_embedding (
                             id BIGSERIAL PRIMARY KEY,
                             source_type VARCHAR(50),
                             source_id BIGINT,
                             embedding vector(1536),
                             content TEXT
);

-- ----------------------------
-- 初始化默认管理员
-- ----------------------------
INSERT INTO t_user (username, password, role, create_time)
VALUES ('admin', 'admin', 'ADMIN', CURRENT_TIMESTAMP);
