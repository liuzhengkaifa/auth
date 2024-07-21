package com.base.auth.to;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QuestionRes {

    /**
     * message : success
     * result : {"conversation_id":"669bda177e149a76b285e49f","history_id":"669bda177e149a76b285e4a0","output":[{"content":[{"text":"应用与服务类企业共有8家。","type":"text"}],"created_at":"2024-07-20 23:39:04","id":"669bda177e149a76b285e4a0","logic_id":"63b4d4a4-6bdf-4586-a690-3ec24948fc50","meta_data":{"input_risk_details":{"biz_type":"56d997d4a7b5efde4641b1a935bdab00","from_cache":true,"netease_text":{"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721487440666,"censorType":0,"dataId":"f029ccb8823b41e5a2d4c77036e03146","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"},"language":{"dataId":"f029ccb8823b41e5a2d4c77036e03146","details":[{"type":"zh"}],"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"}}},"requestId":"f029ccb8823b41e5a2d4c77036e03146"},"input_risk_level":"PASS","risk_details":{"biz_type":"050fbf9dc648604b500357422f01199b","netease_text":{"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721489948190,"censorType":0,"dataId":"6c05e39c51ae4e00b52279fa95696f0b","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"},"language":{"dataId":"6c05e39c51ae4e00b52279fa95696f0b","details":[{"type":"zh"}],"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"}}},"requestId":"6c05e39c51ae4e00b52279fa95696f0b"},"risk_level":"PASS","toolCallRecipient":"","usage":{"completion_tokens":10,"prompt_tokens":4118,"total_tokens":4128}},"model":"chatglm-all-tools","recipient":"all","role":"assistant","status":"finish"}],"status":"finish"}
     * status : 0
     */

    private String message;
    private ResultBeanXX result;
    private int status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBeanXX getResult() {
        return result;
    }

    public void setResult(ResultBeanXX result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ResultBeanXX {
        /**
         * conversation_id : 669bda177e149a76b285e49f
         * history_id : 669bda177e149a76b285e4a0
         * output : [{"content":[{"text":"应用与服务类企业共有8家。","type":"text"}],"created_at":"2024-07-20 23:39:04","id":"669bda177e149a76b285e4a0","logic_id":"63b4d4a4-6bdf-4586-a690-3ec24948fc50","meta_data":{"input_risk_details":{"biz_type":"56d997d4a7b5efde4641b1a935bdab00","from_cache":true,"netease_text":{"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721487440666,"censorType":0,"dataId":"f029ccb8823b41e5a2d4c77036e03146","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"},"language":{"dataId":"f029ccb8823b41e5a2d4c77036e03146","details":[{"type":"zh"}],"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"}}},"requestId":"f029ccb8823b41e5a2d4c77036e03146"},"input_risk_level":"PASS","risk_details":{"biz_type":"050fbf9dc648604b500357422f01199b","netease_text":{"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721489948190,"censorType":0,"dataId":"6c05e39c51ae4e00b52279fa95696f0b","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"},"language":{"dataId":"6c05e39c51ae4e00b52279fa95696f0b","details":[{"type":"zh"}],"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"}}},"requestId":"6c05e39c51ae4e00b52279fa95696f0b"},"risk_level":"PASS","toolCallRecipient":"","usage":{"completion_tokens":10,"prompt_tokens":4118,"total_tokens":4128}},"model":"chatglm-all-tools","recipient":"all","role":"assistant","status":"finish"}]
         * status : finish
         */

        private String conversation_id;
        private String history_id;
        private String status;
        private List<OutputBean> output;

        public String getConversation_id() {
            return conversation_id;
        }

        public void setConversation_id(String conversation_id) {
            this.conversation_id = conversation_id;
        }

        public String getHistory_id() {
            return history_id;
        }

        public void setHistory_id(String history_id) {
            this.history_id = history_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<OutputBean> getOutput() {
            return output;
        }

        public void setOutput(List<OutputBean> output) {
            this.output = output;
        }

        public static class OutputBean {
            /**
             * content : [{"text":"应用与服务类企业共有8家。","type":"text"}]
             * created_at : 2024-07-20 23:39:04
             * id : 669bda177e149a76b285e4a0
             * logic_id : 63b4d4a4-6bdf-4586-a690-3ec24948fc50
             * meta_data : {"input_risk_details":{"biz_type":"56d997d4a7b5efde4641b1a935bdab00","from_cache":true,"netease_text":{"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721487440666,"censorType":0,"dataId":"f029ccb8823b41e5a2d4c77036e03146","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"},"language":{"dataId":"f029ccb8823b41e5a2d4c77036e03146","details":[{"type":"zh"}],"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"}}},"requestId":"f029ccb8823b41e5a2d4c77036e03146"},"input_risk_level":"PASS","risk_details":{"biz_type":"050fbf9dc648604b500357422f01199b","netease_text":{"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721489948190,"censorType":0,"dataId":"6c05e39c51ae4e00b52279fa95696f0b","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"},"language":{"dataId":"6c05e39c51ae4e00b52279fa95696f0b","details":[{"type":"zh"}],"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"}}},"requestId":"6c05e39c51ae4e00b52279fa95696f0b"},"risk_level":"PASS","toolCallRecipient":"","usage":{"completion_tokens":10,"prompt_tokens":4118,"total_tokens":4128}}
             * model : chatglm-all-tools
             * recipient : all
             * role : assistant
             * status : finish
             */

            private String created_at;
            private String id;
            private String logic_id;
            private MetaDataBean meta_data;
            private String model;
            private String recipient;
            private String role;
            private String status;
            private List<ContentBean> content;

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLogic_id() {
                return logic_id;
            }

            public void setLogic_id(String logic_id) {
                this.logic_id = logic_id;
            }

            public MetaDataBean getMeta_data() {
                return meta_data;
            }

            public void setMeta_data(MetaDataBean meta_data) {
                this.meta_data = meta_data;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getRecipient() {
                return recipient;
            }

            public void setRecipient(String recipient) {
                this.recipient = recipient;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<ContentBean> getContent() {
                return content;
            }

            public void setContent(List<ContentBean> content) {
                this.content = content;
            }

            public static class MetaDataBean {
                /**
                 * input_risk_details : {"biz_type":"56d997d4a7b5efde4641b1a935bdab00","from_cache":true,"netease_text":{"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721487440666,"censorType":0,"dataId":"f029ccb8823b41e5a2d4c77036e03146","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"},"language":{"dataId":"f029ccb8823b41e5a2d4c77036e03146","details":[{"type":"zh"}],"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"}}},"requestId":"f029ccb8823b41e5a2d4c77036e03146"}
                 * input_risk_level : PASS
                 * risk_details : {"biz_type":"050fbf9dc648604b500357422f01199b","netease_text":{"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721489948190,"censorType":0,"dataId":"6c05e39c51ae4e00b52279fa95696f0b","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"},"language":{"dataId":"6c05e39c51ae4e00b52279fa95696f0b","details":[{"type":"zh"}],"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"}}},"requestId":"6c05e39c51ae4e00b52279fa95696f0b"}
                 * risk_level : PASS
                 * toolCallRecipient :
                 * usage : {"completion_tokens":10,"prompt_tokens":4118,"total_tokens":4128}
                 */

                private InputRiskDetailsBean input_risk_details;
                private String input_risk_level;
                private RiskDetailsBean risk_details;
                private String risk_level;
                private String toolCallRecipient;
                private UsageBean usage;

                public InputRiskDetailsBean getInput_risk_details() {
                    return input_risk_details;
                }

                public void setInput_risk_details(InputRiskDetailsBean input_risk_details) {
                    this.input_risk_details = input_risk_details;
                }

                public String getInput_risk_level() {
                    return input_risk_level;
                }

                public void setInput_risk_level(String input_risk_level) {
                    this.input_risk_level = input_risk_level;
                }

                public RiskDetailsBean getRisk_details() {
                    return risk_details;
                }

                public void setRisk_details(RiskDetailsBean risk_details) {
                    this.risk_details = risk_details;
                }

                public String getRisk_level() {
                    return risk_level;
                }

                public void setRisk_level(String risk_level) {
                    this.risk_level = risk_level;
                }

                public String getToolCallRecipient() {
                    return toolCallRecipient;
                }

                public void setToolCallRecipient(String toolCallRecipient) {
                    this.toolCallRecipient = toolCallRecipient;
                }

                public UsageBean getUsage() {
                    return usage;
                }

                public void setUsage(UsageBean usage) {
                    this.usage = usage;
                }

                public static class InputRiskDetailsBean {
                    /**
                     * biz_type : 56d997d4a7b5efde4641b1a935bdab00
                     * from_cache : true
                     * netease_text : {"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721487440666,"censorType":0,"dataId":"f029ccb8823b41e5a2d4c77036e03146","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"},"language":{"dataId":"f029ccb8823b41e5a2d4c77036e03146","details":[{"type":"zh"}],"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"}}}
                     * requestId : f029ccb8823b41e5a2d4c77036e03146
                     */

                    private String biz_type;
                    private boolean from_cache;
                    private NeteaseTextBean netease_text;
                    private String requestId;

                    public String getBiz_type() {
                        return biz_type;
                    }

                    public void setBiz_type(String biz_type) {
                        this.biz_type = biz_type;
                    }

                    public boolean isFrom_cache() {
                        return from_cache;
                    }

                    public void setFrom_cache(boolean from_cache) {
                        this.from_cache = from_cache;
                    }

                    public NeteaseTextBean getNetease_text() {
                        return netease_text;
                    }

                    public void setNetease_text(NeteaseTextBean netease_text) {
                        this.netease_text = netease_text;
                    }

                    public String getRequestId() {
                        return requestId;
                    }

                    public void setRequestId(String requestId) {
                        this.requestId = requestId;
                    }

                    public static class NeteaseTextBean {
                        /**
                         * code : 200
                         * msg : ok
                         * result : {"antispam":{"censorTime":1721487440666,"censorType":0,"dataId":"f029ccb8823b41e5a2d4c77036e03146","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"},"language":{"dataId":"f029ccb8823b41e5a2d4c77036e03146","details":[{"type":"zh"}],"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"}}
                         */

                        private int code;
                        private String msg;
                        private ResultBean result;

                        public int getCode() {
                            return code;
                        }

                        public void setCode(int code) {
                            this.code = code;
                        }

                        public String getMsg() {
                            return msg;
                        }

                        public void setMsg(String msg) {
                            this.msg = msg;
                        }

                        public ResultBean getResult() {
                            return result;
                        }

                        public void setResult(ResultBean result) {
                            this.result = result;
                        }

                        public static class ResultBean {
                            /**
                             * antispam : {"censorTime":1721487440666,"censorType":0,"dataId":"f029ccb8823b41e5a2d4c77036e03146","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"}
                             * language : {"dataId":"f029ccb8823b41e5a2d4c77036e03146","details":[{"type":"zh"}],"taskId":"3omm8g0iq84eu37pbhxc0q0h0010a8z2"}
                             */

                            private AntispamBean antispam;
                            private LanguageBean language;

                            public AntispamBean getAntispam() {
                                return antispam;
                            }

                            public void setAntispam(AntispamBean antispam) {
                                this.antispam = antispam;
                            }

                            public LanguageBean getLanguage() {
                                return language;
                            }

                            public void setLanguage(LanguageBean language) {
                                this.language = language;
                            }

                            public static class AntispamBean {
                                /**
                                 * censorTime : 1721487440666
                                 * censorType : 0
                                 * dataId : f029ccb8823b41e5a2d4c77036e03146
                                 * hitSource : -1
                                 * isRelatedHit : false
                                 * label : 0
                                 * labels : []
                                 * resultType : 1
                                 * suggestion : 0
                                 * taskId : 3omm8g0iq84eu37pbhxc0q0h0010a8z2
                                 */

                                private long censorTime;
                                private int censorType;
                                private String dataId;
                                private int hitSource;
                                private boolean isRelatedHit;
                                private int label;
                                private int resultType;
                                private int suggestion;
                                private String taskId;
                                private List<?> labels;

                                public long getCensorTime() {
                                    return censorTime;
                                }

                                public void setCensorTime(long censorTime) {
                                    this.censorTime = censorTime;
                                }

                                public int getCensorType() {
                                    return censorType;
                                }

                                public void setCensorType(int censorType) {
                                    this.censorType = censorType;
                                }

                                public String getDataId() {
                                    return dataId;
                                }

                                public void setDataId(String dataId) {
                                    this.dataId = dataId;
                                }

                                public int getHitSource() {
                                    return hitSource;
                                }

                                public void setHitSource(int hitSource) {
                                    this.hitSource = hitSource;
                                }

                                public boolean isIsRelatedHit() {
                                    return isRelatedHit;
                                }

                                public void setIsRelatedHit(boolean isRelatedHit) {
                                    this.isRelatedHit = isRelatedHit;
                                }

                                public int getLabel() {
                                    return label;
                                }

                                public void setLabel(int label) {
                                    this.label = label;
                                }

                                public int getResultType() {
                                    return resultType;
                                }

                                public void setResultType(int resultType) {
                                    this.resultType = resultType;
                                }

                                public int getSuggestion() {
                                    return suggestion;
                                }

                                public void setSuggestion(int suggestion) {
                                    this.suggestion = suggestion;
                                }

                                public String getTaskId() {
                                    return taskId;
                                }

                                public void setTaskId(String taskId) {
                                    this.taskId = taskId;
                                }

                                public List<?> getLabels() {
                                    return labels;
                                }

                                public void setLabels(List<?> labels) {
                                    this.labels = labels;
                                }
                            }

                            public static class LanguageBean {
                                /**
                                 * dataId : f029ccb8823b41e5a2d4c77036e03146
                                 * details : [{"type":"zh"}]
                                 * taskId : 3omm8g0iq84eu37pbhxc0q0h0010a8z2
                                 */

                                private String dataId;
                                private String taskId;
                                private List<DetailsBean> details;

                                public String getDataId() {
                                    return dataId;
                                }

                                public void setDataId(String dataId) {
                                    this.dataId = dataId;
                                }

                                public String getTaskId() {
                                    return taskId;
                                }

                                public void setTaskId(String taskId) {
                                    this.taskId = taskId;
                                }

                                public List<DetailsBean> getDetails() {
                                    return details;
                                }

                                public void setDetails(List<DetailsBean> details) {
                                    this.details = details;
                                }

                                public static class DetailsBean {
                                    /**
                                     * type : zh
                                     */

                                    private String type;

                                    public String getType() {
                                        return type;
                                    }

                                    public void setType(String type) {
                                        this.type = type;
                                    }
                                }
                            }
                        }
                    }
                }

                public static class RiskDetailsBean {
                    /**
                     * biz_type : 050fbf9dc648604b500357422f01199b
                     * netease_text : {"code":200,"msg":"ok","result":{"antispam":{"censorTime":1721489948190,"censorType":0,"dataId":"6c05e39c51ae4e00b52279fa95696f0b","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"},"language":{"dataId":"6c05e39c51ae4e00b52279fa95696f0b","details":[{"type":"zh"}],"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"}}}
                     * requestId : 6c05e39c51ae4e00b52279fa95696f0b
                     */

                    private String biz_type;
                    private NeteaseTextBeanX netease_text;
                    private String requestId;

                    public String getBiz_type() {
                        return biz_type;
                    }

                    public void setBiz_type(String biz_type) {
                        this.biz_type = biz_type;
                    }

                    public NeteaseTextBeanX getNetease_text() {
                        return netease_text;
                    }

                    public void setNetease_text(NeteaseTextBeanX netease_text) {
                        this.netease_text = netease_text;
                    }

                    public String getRequestId() {
                        return requestId;
                    }

                    public void setRequestId(String requestId) {
                        this.requestId = requestId;
                    }

                    public static class NeteaseTextBeanX {
                        /**
                         * code : 200
                         * msg : ok
                         * result : {"antispam":{"censorTime":1721489948190,"censorType":0,"dataId":"6c05e39c51ae4e00b52279fa95696f0b","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"},"language":{"dataId":"6c05e39c51ae4e00b52279fa95696f0b","details":[{"type":"zh"}],"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"}}
                         */

                        private int code;
                        private String msg;
                        private ResultBeanX result;

                        public int getCode() {
                            return code;
                        }

                        public void setCode(int code) {
                            this.code = code;
                        }

                        public String getMsg() {
                            return msg;
                        }

                        public void setMsg(String msg) {
                            this.msg = msg;
                        }

                        public ResultBeanX getResult() {
                            return result;
                        }

                        public void setResult(ResultBeanX result) {
                            this.result = result;
                        }

                        public static class ResultBeanX {
                            /**
                             * antispam : {"censorTime":1721489948190,"censorType":0,"dataId":"6c05e39c51ae4e00b52279fa95696f0b","hitSource":-1,"isRelatedHit":false,"label":0,"labels":[],"resultType":1,"suggestion":0,"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"}
                             * language : {"dataId":"6c05e39c51ae4e00b52279fa95696f0b","details":[{"type":"zh"}],"taskId":"bib2mrupgpmq0xt1bsqlvh0h0010a8z3"}
                             */

                            private AntispamBeanX antispam;
                            private LanguageBeanX language;

                            public AntispamBeanX getAntispam() {
                                return antispam;
                            }

                            public void setAntispam(AntispamBeanX antispam) {
                                this.antispam = antispam;
                            }

                            public LanguageBeanX getLanguage() {
                                return language;
                            }

                            public void setLanguage(LanguageBeanX language) {
                                this.language = language;
                            }

                            public static class AntispamBeanX {
                                /**
                                 * censorTime : 1721489948190
                                 * censorType : 0
                                 * dataId : 6c05e39c51ae4e00b52279fa95696f0b
                                 * hitSource : -1
                                 * isRelatedHit : false
                                 * label : 0
                                 * labels : []
                                 * resultType : 1
                                 * suggestion : 0
                                 * taskId : bib2mrupgpmq0xt1bsqlvh0h0010a8z3
                                 */

                                private long censorTime;
                                private int censorType;
                                private String dataId;
                                private int hitSource;
                                private boolean isRelatedHit;
                                private int label;
                                private int resultType;
                                private int suggestion;
                                private String taskId;
                                private List<?> labels;

                                public long getCensorTime() {
                                    return censorTime;
                                }

                                public void setCensorTime(long censorTime) {
                                    this.censorTime = censorTime;
                                }

                                public int getCensorType() {
                                    return censorType;
                                }

                                public void setCensorType(int censorType) {
                                    this.censorType = censorType;
                                }

                                public String getDataId() {
                                    return dataId;
                                }

                                public void setDataId(String dataId) {
                                    this.dataId = dataId;
                                }

                                public int getHitSource() {
                                    return hitSource;
                                }

                                public void setHitSource(int hitSource) {
                                    this.hitSource = hitSource;
                                }

                                public boolean isIsRelatedHit() {
                                    return isRelatedHit;
                                }

                                public void setIsRelatedHit(boolean isRelatedHit) {
                                    this.isRelatedHit = isRelatedHit;
                                }

                                public int getLabel() {
                                    return label;
                                }

                                public void setLabel(int label) {
                                    this.label = label;
                                }

                                public int getResultType() {
                                    return resultType;
                                }

                                public void setResultType(int resultType) {
                                    this.resultType = resultType;
                                }

                                public int getSuggestion() {
                                    return suggestion;
                                }

                                public void setSuggestion(int suggestion) {
                                    this.suggestion = suggestion;
                                }

                                public String getTaskId() {
                                    return taskId;
                                }

                                public void setTaskId(String taskId) {
                                    this.taskId = taskId;
                                }

                                public List<?> getLabels() {
                                    return labels;
                                }

                                public void setLabels(List<?> labels) {
                                    this.labels = labels;
                                }
                            }

                            public static class LanguageBeanX {
                                /**
                                 * dataId : 6c05e39c51ae4e00b52279fa95696f0b
                                 * details : [{"type":"zh"}]
                                 * taskId : bib2mrupgpmq0xt1bsqlvh0h0010a8z3
                                 */

                                private String dataId;
                                private String taskId;
                                private List<DetailsBeanX> details;

                                public String getDataId() {
                                    return dataId;
                                }

                                public void setDataId(String dataId) {
                                    this.dataId = dataId;
                                }

                                public String getTaskId() {
                                    return taskId;
                                }

                                public void setTaskId(String taskId) {
                                    this.taskId = taskId;
                                }

                                public List<DetailsBeanX> getDetails() {
                                    return details;
                                }

                                public void setDetails(List<DetailsBeanX> details) {
                                    this.details = details;
                                }

                                public static class DetailsBeanX {
                                    /**
                                     * type : zh
                                     */

                                    private String type;

                                    public String getType() {
                                        return type;
                                    }

                                    public void setType(String type) {
                                        this.type = type;
                                    }
                                }
                            }
                        }
                    }
                }

                public static class UsageBean {
                    /**
                     * completion_tokens : 10
                     * prompt_tokens : 4118
                     * total_tokens : 4128
                     */

                    private int completion_tokens;
                    private int prompt_tokens;
                    private int total_tokens;

                    public int getCompletion_tokens() {
                        return completion_tokens;
                    }

                    public void setCompletion_tokens(int completion_tokens) {
                        this.completion_tokens = completion_tokens;
                    }

                    public int getPrompt_tokens() {
                        return prompt_tokens;
                    }

                    public void setPrompt_tokens(int prompt_tokens) {
                        this.prompt_tokens = prompt_tokens;
                    }

                    public int getTotal_tokens() {
                        return total_tokens;
                    }

                    public void setTotal_tokens(int total_tokens) {
                        this.total_tokens = total_tokens;
                    }
                }
            }

            public static class ContentBean {
                /**
                 * text : 应用与服务类企业共有8家。
                 * type : text
                 */

                private String text;
                private String type;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}
