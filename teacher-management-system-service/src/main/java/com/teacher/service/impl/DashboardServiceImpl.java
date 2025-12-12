package com.teacher.service.impl;

import com.teacher.service.AchievementService;
import com.teacher.service.CourseService;
import com.teacher.service.DashboardService;
import com.teacher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.teacher.domain.entity.Achievement;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserService userService;
    private final CourseService courseService;
    private final AchievementService achievementService;

    @Override
    public Map<String, Object> overview() {

        Map<String, Object> map = new HashMap<>();
        map.put("userCount", userService.count());
        map.put("courseCount", courseService.count());
        map.put("achievementCount", achievementService.count());

        map.put("recentAchievements",
                achievementService.lambdaQuery()
                        .orderByDesc(Achievement::getId)
                        .last("limit 5")
                        .list()
        );

        return map;
    }
}

