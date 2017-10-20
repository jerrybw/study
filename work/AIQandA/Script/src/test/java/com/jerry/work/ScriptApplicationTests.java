package com.jerry.work;

import com.jerry.work.mapper.primary.ResultMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScriptApplicationTests {

    @Autowired
    private ResultMapper resultMapper;

    @Test
    public void contextLoads() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", "100000379");
        map.put("method", "GetExerciseTemporaryScheme1Result");
    }

}
