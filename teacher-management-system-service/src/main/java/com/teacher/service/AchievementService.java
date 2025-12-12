package com.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.teacher.domain.dto.AchievementCreateRequest;
import com.teacher.domain.dto.AchievementUpdateRequest;
import com.teacher.domain.entity.Achievement;

import java.util.List;

public interface AchievementService extends IService<Achievement> {
    void create(AchievementCreateRequest req);

    void updateAchievement(Long id, AchievementUpdateRequest req);

    List<Achievement> searchSimilar(String query);
}
