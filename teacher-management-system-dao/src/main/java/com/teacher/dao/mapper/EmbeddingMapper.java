package com.teacher.dao.mapper;

import com.teacher.domain.entity.Achievement;
import com.teacher.domain.entity.Embedding;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmbeddingMapper {

    @Insert("""
        INSERT INTO t_embedding (source_type, source_id, embedding, content)
        VALUES (#{sourceType}, #{sourceId}, #{vector}, #{content})
    """)
    void insertEmbedding(Embedding embedding);

    @Update("""
        UPDATE t_embedding
        SET embedding = #{vector}, content = #{content}
        WHERE source_type = #{sourceType} AND source_id = #{sourceId}
    """)
    void updateEmbeddingBySource(String sourceType, Long sourceId, float[] vector, String content);

    @Select("""
        SELECT a.*
        FROM t_embedding e
        JOIN t_achievement a ON a.id = e.source_id
        ORDER BY e.embedding <-> #{vector}
        LIMIT 5
    """)
    List<Achievement> searchSimilar(float[] queryVec);
}

