package com.springnpostgres.demo.core.service;

import com.springnpostgres.demo.model.entity.TestName;
import com.springnpostgres.demo.model.repository.TestNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestNameServiceImpl implements TestNameServiceI {

    @Autowired
    private TestNameRepository testNameRepo;

    public List<TestName> getAllTestNames(){
        List<TestName> testNames = new ArrayList<TestName>();
        testNameRepo.findAll().forEach(testNames::add);
        return testNames;
    }

    public TestName getTestName(String name) throws ChangeSetPersister.NotFoundException {
        return testNameRepo.findById(name).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public TestName createTestName(TestName name) {
        return testNameRepo.save(name);
    }

    public TestName updateTestName(TestName name) {
        return testNameRepo.save(name);
    }

    public void deleteTestName(TestName name) {
        testNameRepo.delete(name);
    }

}
