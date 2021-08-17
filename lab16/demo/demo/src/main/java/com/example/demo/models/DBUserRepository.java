package com.example.demo.models;

import org.springframework.data.repository.CrudRepository;

public interface DBUserRepository extends CrudRepository<DBUser,Integer> {


        public DBUser findByUsername(String username);

}
