package com.springnpostgres.demo.entrypoint;

import com.springnpostgres.demo.core.service.TestNameService;
import com.springnpostgres.demo.model.entity.TestName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class TestNameController {

    @Autowired
    private TestNameService service;

    @GetMapping("/names")
    public ResponseEntity<List<TestName>> getAllTestNames(){
        return new ResponseEntity(service.getAllTestNames(), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TestName> getTestName(@PathVariable String name) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity(service.getTestName(name), HttpStatus.OK);
    }

    @PostMapping("/name")
    public ResponseEntity<TestName> createTestName(
            @RequestBody TestName name
    ) {
        return new ResponseEntity(service.createTestName(name), HttpStatus.OK);
    }

    @PostMapping("/name/update")
    public ResponseEntity<TestName> updateTestName(
            @RequestBody TestName name
    ) {
        return new ResponseEntity(service.updateTestName(name), HttpStatus.OK);
    }

    @DeleteMapping("/name")
    public ResponseEntity deleteTestName(
            @RequestBody TestName name
    ) {
        service.deleteTestName(name);
        return new ResponseEntity(HttpStatus.OK);
    }

}
