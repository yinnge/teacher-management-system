package com.teacher.web.controller.Dashboard;

import com.teacher.common.result.Result;
import com.teacher.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;

    @GetMapping
    public Result<?> overview() {
        return Result.ok(service.overview());
    }
}
