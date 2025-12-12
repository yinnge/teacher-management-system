package com.teacher.web.controller.achievement;

import com.teacher.common.result.Result;
import com.teacher.domain.dto.AchievementCreateRequest;
import com.teacher.domain.dto.AchievementUpdateRequest;
import com.teacher.domain.entity.Achievement;
import com.teacher.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService service;

    // ✅ 新增：获取列表
    @GetMapping
    public Result<List<Achievement>> list() {
        return Result.ok(service.list());
    }

    // ✅ 新增：根据ID获取
    @GetMapping("/{id}")
    public Result<Achievement> getById(@PathVariable Long id) {
        return Result.ok(service.getById(id));
    }

    @PostMapping
    public Result<String> create(@RequestBody AchievementCreateRequest req) {
        service.create(req);
        return Result.ok("创建成功");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Long id,
                                 @RequestBody AchievementUpdateRequest req) {
        service.updateAchievement(id, req);
        return Result.ok("更新成功");
    }

    // ✅ 新增：删除
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        service.removeById(id);
        return Result.ok("删除成功");
    }

    // AI 相似搜索
    @GetMapping("/similar")
    public Result<List<Achievement>> similar(@RequestParam String query) {
        return Result.ok(service.searchSimilar(query));
    }
}