package com.teacher.service.impl;

import com.teacher.service.EmbeddingService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class EmbeddingServiceImpl implements EmbeddingService {

    private final OpenAiEmbeddingModel embeddingModel;

    @Override
    public float[] generateEmbedding(String text) {


        return embeddingModel.embed(text);

    }
}

