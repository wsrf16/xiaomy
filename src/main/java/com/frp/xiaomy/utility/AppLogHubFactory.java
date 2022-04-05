package com.frp.xiaomy.utility;

import com.aio.portable.swiss.suite.bean.serializer.SerializerAdapterFactory;
import com.aio.portable.swiss.suite.log.facade.LogHub;
import com.aio.portable.swiss.suite.log.factory.LogHubFactory;
import com.aio.portable.swiss.suite.log.solution.slf4j.Slf4JLogProperties;

public class AppLogHubFactory extends LogHubFactory {

    public static final LogHub staticBuild1() {
        Slf4JLogProperties.singletonInstance().setEnabled(false);
        LogHub log = LogHubFactory.staticBuild();
        log.setSerializerAdapter(SerializerAdapterFactory.buildSilentShortJackson());
        log.setLooseSerializerAdapter(SerializerAdapterFactory.buildSilentShortJackson());
        return log;
    }

//    @Override
//    public LogHub build(String className) {
//        Slf4JLogProperties.singletonInstance().setEnabled(false);
//        Slf4JLog slf4JLog = new Slf4JLog();
//        ArrayList<LogSingle> objects = new ArrayList<>();
//        objects.add(slf4JLog);
//        LogHub log = LogHub.build(objects);
//        return log;
//    }

//    @Override
//    public LogHub build(String className) {
////        return LogHub.build(new ConsoleLog(className));
//        LogHub build = super.build(className);
//        build.getLogList().forEach(c -> {
//            if (c instanceof Slf4JLog) {
//                ((Slf4JLog) c).getProperties().setEnabled(false);
//            }
//        });
//        return build;
//    }
}
