package com.teacher.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teacher.dao.mapper.AchievementMapper;
import com.teacher.domain.dto.AchievementCreateRequest;
import com.teacher.domain.dto.AchievementUpdateRequest;
import com.teacher.domain.entity.Achievement;
import com.teacher.service.AchievementService;
import com.teacher.service.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl
        extends ServiceImpl<AchievementMapper, Achievement>
        implements AchievementService {

    private final EmbeddingService embeddingService;
    private final AchievementMapper mapper;

    @Override
    public void create(AchievementCreateRequest req) {
        // 1. 生成向量
        float[] vector = embeddingService.generateEmbedding(
                req.getTitle() + " " + req.getDescription()
        );
        String vectorStr = arrayToVectorString(vector);

        // 2. 创建实体
        Achievement entity = new Achievement();
        entity.setTitle(req.getTitle());
        entity.setDescription(req.getDescription());
        entity.setTeacherId(req.getTeacherId());

        // 3. 插入
        mapper.insertWithVector(entity, vectorStr);
    }

    @Override
    public void updateAchievement(Long id, AchievementUpdateRequest req) {
        Achievement entity = this.getById(id);
        if (entity == null) {
            throw new RuntimeException("成果不存在");
        }

        // 1. 生成新向量
        float[] vector = embeddingService.generateEmbedding(
                req.getTitle() + " " + req.getDescription()
        );
        String vectorStr = arrayToVectorString(vector);

        // 2. 更新实体
        entity.setTitle(req.getTitle());
        entity.setDescription(req.getDescription());

        // 3. 更新
        mapper.updateWithVector(entity, vectorStr);
    }

    @Override
    public List<Achievement> searchSimilar(String query) {
        float[] qVec = embeddingService.generateEmbedding(query);
        String vectorStr = arrayToVectorString(qVec);
        return mapper.searchSimilar(vectorStr);
    }

    private String arrayToVectorString(float[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
