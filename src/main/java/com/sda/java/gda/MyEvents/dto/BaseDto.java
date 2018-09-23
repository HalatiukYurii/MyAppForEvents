package com.sda.java.gda.MyEvents.dto;

import lombok.Setter;

import java.util.UUID;

public abstract class BaseDto {
    @lombok.Getter
    @Setter
    protected UUID id;

}
