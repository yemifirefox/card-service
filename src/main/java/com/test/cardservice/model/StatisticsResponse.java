package com.test.cardservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StatisticsResponse {
    private boolean success;
    private long start;
    private long limit;
    private long size;
    private StatPayload payload;
}