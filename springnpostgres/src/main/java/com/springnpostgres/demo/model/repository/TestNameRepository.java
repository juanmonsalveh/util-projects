package com.springnpostgres.demo.model.repository;

import com.springnpostgres.demo.model.entity.TestName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface TestNameRepository extends CrudRepository<TestName,String> {

}
