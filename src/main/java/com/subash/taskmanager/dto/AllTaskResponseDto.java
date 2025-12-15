        package com.subash.taskmanager.dto;

        import com.subash.taskmanager.entity.User;
        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.Size;
        import lombok.Data;

        @Data
        public class AllTaskResponseDto {

            public enum TaskStatus {
                PENDING,
                IN_PROGRESS,
                COMPLETED
            }


            private Long id;
            private String title;
            private String description;
            private TaskStatus status;
            private Boolean deleted;
            private Long user;
        }
