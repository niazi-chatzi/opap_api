package com.niazi.opoap.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "winning_numbers")
public class WinningNumber {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column private Integer number;
    @Column private Integer timesShown;

    public int compareToReverse(WinningNumber toCompare) {
        if(this.timesShown == toCompare.getTimesShown()) return 0;
        return this.timesShown > toCompare.getTimesShown()? -1 : 1;
    }
}
