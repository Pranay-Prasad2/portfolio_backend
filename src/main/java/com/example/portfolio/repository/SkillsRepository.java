package com.example.portfolio.repository;

import com.example.portfolio.entity.Skills;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillsRepository extends MongoRepository<Skills, ObjectId> {
}
