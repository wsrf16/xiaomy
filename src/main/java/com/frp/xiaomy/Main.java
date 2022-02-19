package com.frp.xiaomy;

import com.aio.portable.swiss.design.singleton.SingletonProvider;
import com.aio.portable.swiss.sugar.type.CollectionSugar;
import com.aio.portable.swiss.suite.bean.BeanSugar;
import com.aio.portable.swiss.suite.bean.serializer.json.JacksonSugar;
import com.frp.xiaomy.bean.DataDTO;
import com.frp.xiaomy.bean.Result;
import com.frp.xiaomy.common.Config;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    static ThreadPoolExecutor cachedThreadPool = SingletonProvider.instance(new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>()));

    public static void main(String[] args) {
        String token = parseToken(args);
//        token = "z8le30z7";

        Config config = SingletonProvider.instance(Config.class);
        config.setToken(token);
        login(config);
    }

    private static String parseToken(String[] args) {
        String token;
        if (!CollectionSugar.isEmpty(args))
            token = args[0];
        else {
            System.out.println("请输入隧道令牌：");
            token = new Scanner(System.in).next();
        }
        System.out.println("当前使用的有效令牌是：" + token);
        return token;
    }


    private static void login(Config config) {
        ResponseEntity<Result> forEntity = new RestTemplate().getForEntity(Config.server_ip + "/port/get/token?portToken=" + config.getToken(), Result.class);


        Result body = forEntity.getBody();
        if (forEntity.getStatusCode() == HttpStatus.OK && body.getCode() == 0) {
            DataDTO data = JacksonSugar.deepCopy(body.getData(), DataDTO.class);
            BeanSugar.Properties.copyAllProperties(data, config);
            if (config.isFirst == 0) {
                new TcpTunnel(cachedThreadPool).start();
            }
        } else {
            System.out.println(body.getData());
        }
    }
}
