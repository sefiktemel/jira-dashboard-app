package com.sefiktemel.jiradashboard.model.api.res;


import com.sefiktemel.jiradashboard.model.api.IssueDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListIssueRes {
    private List<IssueDto> issues = new ArrayList<>();
}
