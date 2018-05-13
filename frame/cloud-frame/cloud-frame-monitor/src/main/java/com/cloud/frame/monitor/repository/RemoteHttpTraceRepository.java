package com.cloud.frame.monitor.repository;

import com.cloud.frame.monitor.util.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;

import java.util.Collections;
import java.util.List;

/**
 * Created by wd on 2018/5/13.
 */
public class RemoteHttpTraceRepository implements HttpTraceRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteHttpTraceRepository.class);

    @Override
    public List<HttpTrace> findAll() {
        return Collections.emptyList();
    }

    @Override
    public void add(HttpTrace trace) {
        //send log to remote server or log center
        LOGGER.info(GsonUtil.getInstance().toJson(trace));
    }
}
