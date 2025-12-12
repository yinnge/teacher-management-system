package com.teacher.web.controller.ai;

import com.teacher.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
@ConditionalOnProperty(name = "spring.ai.enabled", havingValue = "true", matchIfMissing = true)
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat")
    public String chat(@RequestParam String q) {
        return chatService.chat(q);
    }
}

