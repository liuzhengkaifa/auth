package com.base.auth.service.bigdata.service.impl;

import com.base.auth.common.Response;
import com.base.auth.service.bigdata.service.BigDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.ChatCompletionRequest;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import com.zhipu.oapi.service.v4.model.ChatMessageRole;
import com.zhipu.oapi.service.v4.model.ModelApiResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author liuzheng
 * @date 2024年07月15日 17:41
 * @Description TODO
 */
@Service
public class BigDataServiceImpl implements BigDataService {

    @Override
    public Response test() {

        ClientV4 client = new ClientV4.Builder("56a971d8f3f9283b59cec806b50257dc.hYnIJGM6qFAUqpLm").build();
        testInvoke(client);

        return null;
    }
    /**
     * 同步调用
     */
    private static void testInvoke(ClientV4 client) {
        // 创建 ObjectMapper 实例
        ObjectMapper mapper = new ObjectMapper();

        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), "上海有几个区，分别是哪些区");
        messages.add(chatMessage);
        String requestId = String.format(UUID.randomUUID().toString(), System.currentTimeMillis());

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)
                .stream(Boolean.FALSE)
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .requestId(requestId)
                .build();
        ModelApiResponse invokeModelApiResp = client.invokeModelApi(chatCompletionRequest);
        try {
//            System.out.println(invokeModelApiResp);
            System.out.println("model output:" + mapper.writeValueAsString(invokeModelApiResp));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
