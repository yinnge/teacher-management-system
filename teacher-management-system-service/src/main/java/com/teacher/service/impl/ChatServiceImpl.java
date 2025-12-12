package com.teacher.service.impl;


import com.teacher.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final OpenAiChatModel chatModel;

    // ✅ 系统提示词 - 定义 AI 角色
    private static final String SYSTEM_PROMPT = """
        你是「教师管理系统」的智能助手小智，专门为高校教师和管理员提供帮助。
        
        你的职责包括：
        1. 📚 解答教学相关问题（课程设计、教学方法、学生管理等）
        2. 📝 协助撰写教学文档（教案、课程大纲、教学总结等）
        3. 🔬 提供科研支持（论文写作建议、研究方向探讨、学术规范等）
        4. 💻 解答系统使用问题（如何添加课程、管理成果等）
        5. 🎯 提供职业发展建议（职称评定、教学能力提升等）
        
        回复要求：
        - 语气亲切专业，像一位经验丰富的教育顾问
        - 回答简洁明了，重点突出
        - 适当使用 emoji 让对话更生动
        - 如果问题超出教育领域，礼貌地引导回主题
        
        请用中文回复。
        """;

    @Override
    public String chat(String msg) {
        // ✅ 创建带有系统提示的消息列表
        List<Message> messages = List.of(
                new SystemMessage(SYSTEM_PROMPT),
                new UserMessage(msg)
        );

        Prompt prompt = new Prompt(messages);
        ChatResponse response = chatModel.call(prompt);

        return response.getResult().getOutput().getContent();
    }


}

