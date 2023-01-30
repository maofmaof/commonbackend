package com.commonbackend.commonbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SupportIssue {

    int supportTaskId;
    int customerId;
    String priority;
    String comment;
    String status;

}
