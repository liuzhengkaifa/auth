package com.base.auth.service.bigdata.service.impl;

import com.base.auth.common.Response;
import com.base.auth.service.bigdata.service.BigDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import com.zhipu.oapi.service.v4.model.ChatMessageRole;
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

        ClientV4 client = new ClientV4.Builder("74d23715908af2d66476770cacdd08ef.03GBInqs7GIAU6CA").build();
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
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), "应用与服务类企业有几家");
        messages.add(chatMessage);
        String requestId = String.format(UUID.randomUUID().toString(), System.currentTimeMillis());

    }
}
