package net.lab1024.sa.common.common.domain;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Check if the data is null
 *
 */
@Data
public class ValidateData<T> {

    @NotNull(message = "Data can not be null")
    private T data;
}