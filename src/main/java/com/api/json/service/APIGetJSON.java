package com.api.json.service;


import com.alibaba.fastjson.JSONObject;




import java.util.Map;


import static com.api.json.util.HttpClient.sendPortRequset;

public class APIGetJSON {
    public static void main(String[] args) {
        String url = "http://localhost:8080/v2/api-docs";
        //开始发送HTTP请求
        String res = sendPortRequset(url,null);
        System.out.println(res);
        SwaggerPath[] apiPaths= APIJSONPathAnalysis(res);

        System.out.println(apiPaths.length);
        for (int i= 0;i<apiPaths.length;i++){
            System.out.println(apiPaths[i].getPath());
        }
    }
    //对swagger json 进行解析
    public static SwaggerPath[] APIJSONPathAnalysis(String res){
        // 对返回数据开始解析
        JSONObject resp = JSONObject.parseObject(res);
        // 解析请求路径
        JSONObject paths = resp.getJSONObject("paths");
        System.out.println(" paths 格式 "+paths);
        //将json对象转化成为map对象
        Map pathsMap = paths;

        // 传过来的json动态生成返回结果集
        SwaggerPath[] apiPaths = new SwaggerPath[pathsMap.keySet().size()];
        System.out.println("开始对请求路径进行解析");
        //对数组进行推动便利
        int index =0;
        for(Object key :pathsMap.keySet()){

            // 将key的类型进行转换成为String类型
            String stringPath = key.toString();
            // 获取到对应的path对象
            JSONObject jsonPath = paths.getJSONObject(stringPath);
            System.out.println(jsonPath);

            // 解析头部信息
            JSONObject jsonHead = jsonPath.getJSONObject("head");
            SwaggerMessage headMessage = APIMessageAnalysis(jsonHead);

            // 解析patch信息
            JSONObject jsonPatch = jsonPath.getJSONObject("patch");
            SwaggerMessage patchMessage = APIMessageAnalysis(jsonPatch);

            //解析POST信息
            JSONObject jsonPost = jsonPath.getJSONObject("post");
            SwaggerMessage postMessage = APIMessageAnalysis(jsonPost);

            //解析GET信息
            JSONObject jsonGet = jsonPath.getJSONObject("get");
            SwaggerMessage getMessage = APIMessageAnalysis(jsonGet);

            // 解析option信息
            JSONObject jsonOption = jsonPath.getJSONObject("options");
            SwaggerMessage optionsMessage = APIMessageAnalysis(jsonOption);

            //解析delete信息
            JSONObject jsonDelete = jsonPath.getJSONObject("delete");
            SwaggerMessage deleteMessage = APIMessageAnalysis(jsonDelete);

            //解析put信息
            JSONObject jsonPut = jsonPath.getJSONObject("put");
            SwaggerMessage putMessage = APIMessageAnalysis(jsonPut);

            SwaggerPath apiPath = new SwaggerPath(stringPath,headMessage,patchMessage,postMessage,getMessage,optionsMessage,deleteMessage,putMessage);

            apiPaths[index] = apiPath;
            index++;
        }
        return apiPaths;

    }
    //对API请求信息进行解析
    public static SwaggerMessage APIMessageAnalysis(JSONObject jsonObject){
        SwaggerMessage apiMessage = new SwaggerMessage();
        //将json对象转换成map
        Map meaasgeMap = jsonObject;

        if (meaasgeMap.get("consumes") != null){
            apiMessage.setConsumes(meaasgeMap.get("consumes").toString());
        }

        if (meaasgeMap.get("description") != null){
            apiMessage.setDescription(meaasgeMap.get("description").toString());
        }

        if (meaasgeMap.get("parameters") != null){
            apiMessage.setParameters(meaasgeMap.get("parameters").toString());
        }

        if (meaasgeMap.get("produces")!=null){
            apiMessage.setProduces(meaasgeMap.get("produces").toString());
        }

        if (meaasgeMap.get("responses") != null){
            apiMessage.setResponses(meaasgeMap.get("responses").toString());
        }

        if (meaasgeMap.get("operationId")!=null){
            apiMessage.setOperationId(meaasgeMap.get("operationId").toString());
        }

        if (meaasgeMap.get("summary")!=null){
            apiMessage.setSummary(meaasgeMap.get("summary").toString());
        }

        if (meaasgeMap.get("tags")!=null){
            apiMessage.setTags(meaasgeMap.get("tags").toString());
        }

        // 将对应字段构造出APImessage对象
        return apiMessage;
    }

    public static class   SwaggerPath{
        private String path;
        private SwaggerMessage head;
        private SwaggerMessage patch;
        private SwaggerMessage post;
        private SwaggerMessage get;
        private SwaggerMessage options;
        private SwaggerMessage delete;
        private SwaggerMessage put;

        public SwaggerPath() {
        }

        public SwaggerPath(String path, SwaggerMessage head, SwaggerMessage patch, SwaggerMessage post, SwaggerMessage get, SwaggerMessage options, SwaggerMessage delete, SwaggerMessage put) {
            this.path = path;
            this.head = head;
            this.patch = patch;
            this.post = post;
            this.get = get;
            this.options = options;
            this.delete = delete;
            this.put = put;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public SwaggerMessage getHead() {
            return head;
        }

        public void setHead(SwaggerMessage head) {
            this.head = head;
        }

        public SwaggerMessage getPatch() {
            return patch;
        }

        public void setPatch(SwaggerMessage patch) {
            this.patch = patch;
        }

        public SwaggerMessage getPost() {
            return post;
        }

        public void setPost(SwaggerMessage post) {
            this.post = post;
        }

        public SwaggerMessage getGet() {
            return get;
        }

        public void setGet(SwaggerMessage get) {
            this.get = get;
        }

        public SwaggerMessage getOptions() {
            return options;
        }

        public void setOptions(SwaggerMessage options) {
            this.options = options;
        }

        public SwaggerMessage getDelete() {
            return delete;
        }

        public void setDelete(SwaggerMessage delete) {
            this.delete = delete;
        }

        public SwaggerMessage getPut() {
            return put;
        }

        public void setPut(SwaggerMessage put) {
            this.put = put;
        }
    }

    public static class SwaggerMessage{
        private String summary;           //总结
        private String produces;          //过程
        private String description;       //描述
        private String operationId;       //操作id
        private String responses;         //返回参数
        private String parameters;        //请求参数
        private String tags;              //标签
        private String consumes;          //格式

        public SwaggerMessage() {
        }

        public SwaggerMessage(String summary, String produces, String description, String operationId, String responses, String parameters, String tags, String consumes) {
            this.summary = summary;
            this.produces = produces;
            this.description = description;
            this.operationId = operationId;
            this.responses = responses;
            this.parameters = parameters;
            this.tags = tags;
            this.consumes = consumes;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getProduces() {
            return produces;
        }

        public void setProduces(String produces) {
            this.produces = produces;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getOperationId() {
            return operationId;
        }

        public void setOperationId(String operationId) {
            this.operationId = operationId;
        }

        public String getResponses() {
            return responses;
        }

        public void setResponses(String responses) {
            this.responses = responses;
        }

        public String getParameters() {
            return parameters;
        }

        public void setParameters(String parameters) {
            this.parameters = parameters;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getConsumes() {
            return consumes;
        }

        public void setConsumes(String consumes) {
            this.consumes = consumes;
        }
    }


}
