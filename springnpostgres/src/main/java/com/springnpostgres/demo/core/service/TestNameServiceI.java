package com.springnpostgres.demo.core.service;

import com.springnpostgres.demo.model.entity.TestName;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TestNameServiceI {
    List<TestName> getAllTestNames();
    TestName getTestName(String name) throws ChangeSetPersister.NotFoundException;
    TestName createTestName(TestName name);
    TestName updateTestName(TestName name);
    void deleteTestName(TestName name);
}
