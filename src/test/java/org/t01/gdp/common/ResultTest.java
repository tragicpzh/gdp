package org.t01.gdp.common;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class ResultTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void success() {
        Result.JSONResultMap success = Result.success();
        assertTrue((boolean)success.get("success"));
        assertEquals(200,success.get("code"));
    }

    @Test
    public void testSuccess() {
        int data = 123;
        Result.JSONResultMap success = Result.success((Object) data);
        assertEquals(data,success.get("data"));
    }

    @Test
    public void testSuccess1() {
        int data = 123;
        String message = "message";
        Result.JSONResultMap success = Result.success((Object) data, message);
        assertEquals(data,success.get("data"));
        assertEquals(message,success.get("message"));
    }

    @Test
    public void testSuccess2() {
        int data = 123;
        String message = "message";
        int statusCode = 321;
        Result.JSONResultMap success = Result.success((Object) data, message, statusCode);
        assertEquals(data,success.get("data"));
        assertEquals(message,success.get("message"));
        assertEquals(statusCode,success.get("code"));
    }

    @Test
    public void testSuccess3() {
        String message = "message";
        int statusCode = 321;
        Result.JSONResultMap success = Result.success(message, statusCode);
        assertEquals(message,success.get("message"));
        assertEquals(statusCode,success.get("code"));
    }

    @Test
    public void testSuccess4() {
        int statusCode = 321;
        Result.JSONResultMap success = Result.success(statusCode);
        assertEquals(statusCode,success.get("code"));
    }

    @Test
    public void testSuccess5() {
        String message = "message";
        Result.JSONResultMap success = Result.success(message);
        assertEquals(message,success.get("message"));
    }

    @Test
    public void error() {
        int statusCode = 400;
        Result.JSONResultMap error = Result.error(statusCode);
        assertFalse((boolean)error.get("success"));
        assertEquals(statusCode,error.get("code"));
        assertEquals("HTTP 错误 400.0 - 访问被拒绝：错误的请求",error.get("message"));
    }

    @Test
    public void testError() {
        Result.JSONResultMap error = Result.error();
        assertEquals(500,error.get("code"));
        assertEquals("HTTP 错误 500.0 - 服务器出错：内部服务器出错",error.get("message"));
    }

    @Test
    public void testError1() {
        String message = "message";
        Result.JSONResultMap error = Result.error(message);
        assertEquals(500,error.get("code"));
        assertEquals(message,error.get("message"));
    }

    @Test
    public void testError2() {
        int statusCode = 233;
        String message = "message";
        Result.JSONResultMap error = Result.error(message,statusCode);
        assertEquals(statusCode,error.get("code"));
        assertEquals(message,error.get("message"));
    }

    @Test
    public void fail() {
        Result.JSONResultMap fail = Result.fail();
        assertFalse((boolean)fail.get("success"));
        assertEquals(200,fail.get("code"));
    }

    @Test
    public void testFail() {
        String message = "message";
        int statusCode = 233;
        Result.JSONResultMap fail = Result.fail(message, statusCode);
        assertEquals(statusCode,fail.get("code"));
        assertEquals(message,fail.get("message"));
    }

    @Test
    public void testFail1() {
        int statusCode = 405;
        Result.JSONResultMap fail = Result.fail(statusCode);
        assertEquals(statusCode,fail.get("code"));
        assertEquals("用来访问本页面的 HTTP 谓词不被允许（方法不被允许）",fail.get("message"));
    }

    @Test
    public void testFail2() {
        String message = "message";
        Result.JSONResultMap fail = Result.fail(message);
        assertEquals(200,fail.get("code"));
        assertEquals(message,fail.get("message"));
    }
}