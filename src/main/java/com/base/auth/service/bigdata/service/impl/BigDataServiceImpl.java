package com.base.auth.service.bigdata.service.impl;

import com.base.auth.common.Response;
import com.base.auth.service.bigdata.service.BigDataService;
import com.base.auth.to.QuestionReq;
import com.base.auth.to.QuestionRes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import com.zhipu.oapi.service.v4.model.ChatMessageRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;


/**
 * @author liuzheng
 * @date 2024年07月15日 17:41
 * @Description TODO
 */
@Service
@Slf4j
public class BigDataServiceImpl implements BigDataService {

    @Resource
    RestTemplate restTemplate;

    private static final String BASE_URL = "https://chatglm.cn/chatglm/assistant-api/v1/";
    private static final String GET_TOKEN_URL = BASE_URL + "get_token";
    private static final String STREAM_URL = BASE_URL + "stream_sync";

    private static final String AASSISTANT_ID = "669b61c9210692db7b5de92b";

    @Override
    public Response question(QuestionReq questionReq) {
        String accessToken = getAccessToken("ec576e63bcd521ce", "9072462c9d7f8d662556c7ca3a44398f");
        return Response.ok(sendMessage(AASSISTANT_ID, accessToken, questionReq.getPrompt(), null, null, null));
    }

    private Response getToken() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, String> params = new HashMap<>();
            params.put("api_key", "ec576e63bcd521ce");
            params.put("api_secret", "9072462c9d7f8d662556c7ca3a44398f");

            // Convert the parameters to JSON
            String jsonParams = objectMapper.writeValueAsString(params);

            // Set up headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create the request entity
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonParams, headers);

            // Send the request
            ResponseEntity<String> response = restTemplate.exchange(
                    "https://chatglm.cn/chatglm/assistant-api/v1/get_token",
                    HttpMethod.POST,
                    requestEntity,
                    String.class);

            System.out.println(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public String getAccessToken(String apiKey, String apiSecret) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(Map.of("api_key", apiKey, "api_secret", apiSecret), headers);
        ResponseEntity<Map> response = restTemplate.exchange(GET_TOKEN_URL, HttpMethod.POST, request, new ParameterizedTypeReference<>() {
        });

        String jsonString = response.getBody().toString();
        // 使用逗号和空格作为分隔符分割字符串
        String[] parts = jsonString.split(", ");

        // 遍历所有部分，寻找 access_token 的值
        for (String part : parts) {
            if (part.startsWith("result={access_token=")) {
                // 提取 access_token 后面的值
                String accessToken = part.substring("result={access_token=".length());
                return accessToken;
            }
        }
        return "";
    }

    public String sendMessage(String assistantId, String accessToken, String prompt, String conversationId, List<String> fileList, Map<String, Object> metaData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);

        Map<String, Object> data = Map.of(
                "assistant_id", assistantId,
                "prompt", prompt
        );

        if (conversationId != null) {
            data.put("conversation_id", conversationId);
        }
        if (fileList != null) {
            data.put("file_list", fileList);
        }
        if (metaData != null) {
            data.put("meta_data", metaData);
        }

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.exchange(STREAM_URL, HttpMethod.POST, request, String.class);

        int statusCode = response.getStatusCodeValue();
        if (statusCode == 200) {
            return handleResponse(response.getBody().toString());
        } else {
            System.out.println("Request failed");
            System.out.println(statusCode);
        }
        return "";
    }

    private String handleResponse(String resultSubstring) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(resultSubstring);
            JsonNode resultNode = rootNode.get("result");
            JsonNode outputNode = resultNode.get("output").get(0);
            JsonNode contentNode = outputNode.get("content").get(0);
            JsonNode textNode = contentNode.get("text");

            String text = textNode.asText();
            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
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

    public static void main(String[] args) {
        String input = "{\"message\":\"success\",\"result\":{\"conversation_id\":\"669bda177e149a76b285e49f\",\"history_id\":\"669bda177e149a76b285e4a0\",\"output\":[{\"content\":[{\"text\":\"应用与服务类企业共有8家。\",\"type\":\"text\"}],\"created_at\":\"2024-07-20 23:39:04\",\"id\":\"669bda177e149a76b285e4a0\",\"logic_id\":\"63b4d4a4-6bdf-4586-a690-3ec24948fc50\",\"meta_data\":{\"input_risk_details\":{\"biz_type\":\"56d997d4a7b5efde4641b1a935bdab00\",\"from_cache\":true,\"netease_text\":{\"code\":200,\"msg\":\"ok\",\"result\":{\"antispam\":{\"censorTime\":1721487440666,\"censorType\":0,\"dataId\":\"f029ccb8823b41e5a2d4c77036e03146\",\"hitSource\":-1,\"isRelatedHit\":false,\"label\":0,\"labels\":[],\"resultType\":1,\"suggestion\":0,\"taskId\":\"3omm8g0iq84eu37pbhxc0q0h0010a8z2\"},\"language\":{\"dataId\":\"f029ccb8823b41e5a2d4c77036e03146\",\"details\":[{\"type\":\"zh\"}],\"taskId\":\"3omm8g0iq84eu37pbhxc0q0h0010a8z2\"}}},\"requestId\":\"f029ccb8823b41e5a2d4c77036e03146\"},\"input_risk_level\":\"PASS\",\"risk_details\":{\"biz_type\":\"050fbf9dc648604b500357422f01199b\",\"netease_text\":{\"code\":200,\"msg\":\"ok\",\"result\":{\"antispam\":{\"censorTime\":1721489948190,\"censorType\":0,\"dataId\":\"6c05e39c51ae4e00b52279fa95696f0b\",\"hitSource\":-1,\"isRelatedHit\":false,\"label\":0,\"labels\":[],\"resultType\":1,\"suggestion\":0,\"taskId\":\"bib2mrupgpmq0xt1bsqlvh0h0010a8z3\"},\"language\":{\"dataId\":\"6c05e39c51ae4e00b52279fa95696f0b\",\"details\":[{\"type\":\"zh\"}],\"taskId\":\"bib2mrupgpmq0xt1bsqlvh0h0010a8z3\"}}},\"requestId\":\"6c05e39c51ae4e00b52279fa95696f0b\"},\"risk_level\":\"PASS\",\"toolCallRecipient\":\"\",\"usage\":{\"completion_tokens\":10,\"prompt_tokens\":4118,\"total_tokens\":4128}},\"model\":\"chatglm-all-tools\",\"recipient\":\"all\",\"role\":\"assistant\",\"status\":\"finish\"}],\"status\":\"finish\"},\"status\":0}";

        ObjectMapper mapper = new ObjectMapper();
        try {
            QuestionRes questionRes = mapper.readValue(input, QuestionRes.class);
            log.info(questionRes.toString());
            JsonNode rootNode = mapper.readTree(input);
            JsonNode resultNode = rootNode.get("result");
            JsonNode outputNode = resultNode.get("output").get(0);
            JsonNode contentNode = outputNode.get("content").get(0);
            JsonNode textNode = contentNode.get("text");

            String text = textNode.asText();
            System.out.println("Text: " + text);
        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        String jsonString = "{message=success, result={access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJmcmVzaCI6ZmFsc2UsImlhdCI6MTcyMTQ4Njc4NiwianRpIjoiNzFiNDg0ZDAtNjM5NS00MTBkLTliZGEtMmNhYWMxMWYxNjFiIiwidHlwZSI6ImFjY2VzcyIsInN1YiI6IkFQSV82NWUxNTRmNGNiZmZlMjUxMjQxMTkwYjFfYjdkNDJhOTIiLCJuYmYiOjE3MjE0ODY3ODYsImV4cCI6MTcyMjM1MDc4NiwidWlkIjoiNjY5YjZjZmRiOWRmYWU3MmQzNWRhZDdjIiwidXBsYXRmb3JtIjoiIiwiYXBpX3JvbGUiOiJkZXZlbG9wZXIiLCJyb2xlcyI6WyJhdXRoZWRfdXNlciJdfQ.klOS9ZgA-FkkBxZ9TKKscT0MZJy11IVy0z1_R3bGrtY, expires_in=864000, token_expires=1722350786}, status=0}";
//                try {
//                    // 使用逗号和空格作为分隔符分割字符串
//                    String[] parts = jsonString.split(", ");
//
//                    // 遍历所有部分，寻找 access_token 的值
//                    for (String part : parts) {
//                        if (part.startsWith("result={access_token=")) {
//                            // 提取 access_token 后面的值
//                            String accessToken = part.substring("result={access_token=".length());
//                            System.out.println("Access Token: " + accessToken);
//                            break; // 找到后退出循环
//                        }
//                    }
//                }catch (Exception e){
//                    log.error("error",e);
//                }
    }
}
