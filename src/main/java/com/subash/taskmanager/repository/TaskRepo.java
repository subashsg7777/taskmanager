package com.subash.taskmanager.repository;

import com.subash.taskmanager.entity.Task;
import com.subash.taskmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    Task findByIdAndUser(Long id, User user);

    List<Task> findByUserIdAndDeleted(Long user, boolean b);

    public enum TaskStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }

    List<Task> findByUserId(Long user);


}
