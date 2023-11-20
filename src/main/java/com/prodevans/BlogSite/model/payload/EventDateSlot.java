package com.prodevans.BlogSite.model.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventDateSlot {
   private Date date;
   private Boolean slot1;
   private Boolean slot2;
}
