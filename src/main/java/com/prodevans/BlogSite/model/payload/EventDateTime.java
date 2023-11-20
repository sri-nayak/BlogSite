package com.prodevans.BlogSite.model.payload;

import lombok.*;

import java.sql.Date;
import java.sql.Time;


@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class EventDateTime {
    private Date date;
    private String time;
    private String Topic;
}
