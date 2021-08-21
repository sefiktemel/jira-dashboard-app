package com.sefiktemel.jiradashboard.model.api;

import lombok.Data;

@Data
public class ProjectDto {
    private Long id;
    private String name;
    private String key;
    private String path;
}
