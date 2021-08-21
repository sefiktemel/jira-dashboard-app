package com.sefiktemel.jiradashboard.model.api;

import lombok.Data;

@Data
public class IssueDto {
    private Long id;
    private String path;
    private String name;
    private String description;
}
