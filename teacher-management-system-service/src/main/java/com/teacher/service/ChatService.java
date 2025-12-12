package com.teacher.service;

import reactor.core.publisher.Flux;

public interface ChatService {
    String chat(String message);
//    Flux<String> chatstream(String message);
}
