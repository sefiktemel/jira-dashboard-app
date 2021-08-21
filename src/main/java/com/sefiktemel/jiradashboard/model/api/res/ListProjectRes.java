package com.sefiktemel.jiradashboard.model.api.res;


import com.sefiktemel.jiradashboard.model.api.ProjectDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListProjectRes {
    private List<ProjectDto> projects = new ArrayList<>();
}
