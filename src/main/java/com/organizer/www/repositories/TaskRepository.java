package com.organizer.www.repositories;

import com.organizer.www.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findTaskById(Long id);
    List<Task> findAllByOrderByCreatedAt();
}
