

package com.example.sharebitesdemo.controller;

        import com.alibaba.fastjson.JSONObject;
        import com.example.sharebitesdemo.ShareBitesDemoApplication;
        import com.example.sharebitesdemo.service.CommnetsService;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.test.context.junit4.SpringRunner;

        import static org.junit.Assert.*;

@SpringBootTest(classes = ShareBitesDemoApplication.class)
@RunWith(SpringRunner.class)
public class CommentsControllerTest {
    @Autowired
    private CommnetsService commnetsService;
    @Test
    public void getComments() {
        String id="xxxx";
        JSONObject[] js = commnetsService.getComments(id);
        System.out.println("hello world");
    }
}