package com.teacher.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.teacher.domain.entity.Achievement;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AchievementMapper extends BaseMapper<Achievement> {

    // 插入 - 传实体对象 + vector 字符串
    @Insert("""
        INSERT INTO t_achievement (title, description, teacher_id, vector)
        VALUES (#{entity.title}, #{entity.description}, #{entity.teacherId}, CAST(#{vectorStr} AS vector))
    """)
    int insertWithVector(@Param("entity") Achievement entity, @Param("vectorStr") String vectorStr);

    // 更新时转换 vector
    @Update("""
        UPDATE t_achievement
        SET title = #{entity.title},
            description = #{entity.description},
            vector = CAST(#{vectorStr} AS vector)
        WHERE id = #{entity.id}
    """)
    int updateWithVector(@Param("entity") Achievement entity, @Param("vectorStr") String vectorStr);

    // 相似度搜索
    @Select("""
        SELECT id, title, description, teacher_id
        FROM t_achievement
        ORDER BY vector <-> CAST(#{vec} AS vector)
        LIMIT 10
    """)
    List<Achievement> searchSimilar(@Param("vec") String vec);
}
