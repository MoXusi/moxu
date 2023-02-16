package com.awx.moxu;

import com.awx.moxu.utils.JwtUtils;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
@Component
public class JWT {

    private final long time = 1000*60*60*24;

    private String signature="admin";

    @Test
    public void jwt(){

    }

    @Test
    public void parse(){

    }

}
