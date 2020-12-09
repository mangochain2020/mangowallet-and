package com.token.mangowallet.net.interceptor;

import android.os.SystemClock;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

public class LogInterceptor2 implements Interceptor {

    public static String TAG = LogInterceptor.class.getSimpleName();

    private static HashMap<String,String> headerIgnoreMap = new HashMap<>();

    static {
        headerIgnoreMap.put("Host", "");
        headerIgnoreMap.put("Connection", "");
        headerIgnoreMap.put("Accept-Encoding", "");
    }

    protected void log(String message) {
        LogUtils.d(TAG, message);
    }

    private boolean isPlainText(MediaType mediaType) {
        if (null != mediaType) {
            String mediaTypeString = (null != mediaType ? mediaType.toString() : null);
            if (!ObjectUtils.isEmpty(mediaTypeString)) {
                mediaTypeString = mediaTypeString.toLowerCase();
                if (mediaTypeString.contains("text") || mediaTypeString.contains("application/json")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long startTime = SystemClock.elapsedRealtime();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = SystemClock.elapsedRealtime();
        long duration = endTime - startTime;

        //url
        String url = request.url().toString();
        log("----------Request Start----------");
        log("" + request.method() + " " + url);

        //headers
        Headers headers = request.headers();
        if (null != headers) {
            for (int i = 0, count = headers.size(); i < count; i++) {
                if (!headerIgnoreMap.containsKey(headers.name(i))) {
                    log(headers.name(i) + ": " + headers.value(i));
                }
            }
        }

        //param
        RequestBody requestBody = request.body();
        String paramString = readRequestParamString(requestBody);
        if (!ObjectUtils.isEmpty(paramString)) {
            log("Params:" + paramString);
        }

        //response
        ResponseBody responseBody = response.body();
        String responseString = "";
        if (null != responseBody) {
            if (isPlainText(responseBody.contentType())) {
                responseString = readContent(response);
            } else {
                responseString = "other-type="+ responseBody.contentType();
            }
        }

        log("Response Body:" + responseString);
        log("Time:" + duration + " ms");
        log("----------Request End----------");
        return response;
    }

    private String readRequestParamString(RequestBody requestBody) {
        String paramString;
        if (requestBody instanceof MultipartBody) {//判断是否有文件
            StringBuilder sb = new StringBuilder();
            MultipartBody body = (MultipartBody) requestBody;
            List<MultipartBody.Part> parts = body.parts();
            RequestBody partBody;
            for (int i = 0, size = parts.size(); i < size; i++) {
                partBody = parts.get(i).body();
                if (null != partBody) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    if (isPlainText(partBody.contentType())) {
                        sb.append(readContent(partBody));
                    } else {
                        sb.append("other-param-type=").append(partBody.contentType());
                    }
                }
            }
            paramString = sb.toString();
        } else {
            paramString = readContent(requestBody);
        }
        return paramString;
    }

    private String readContent(Response response){
        if (response == null ) {
            return "";
        }

        try {
            return response.peekBody(Long.MAX_VALUE).string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String readContent(RequestBody body){
        if (body == null ) {
            return "";
        }
        Buffer buffer = new Buffer();
        try {
            //小于2m
            if (body.contentLength() <= 2* 1024*1024) {
                body.writeTo(buffer);
            } else {
                return "content is more than 2M";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.readUtf8();
    }
}
