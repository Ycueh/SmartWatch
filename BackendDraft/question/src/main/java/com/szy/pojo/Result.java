package com.szy.pojo;

//response result

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code; //response code, 1:successful ; 0:fail
    private String msg; //rresponse inforrmation;  describe String
    private Object data; //return data

    //add delete update   successful response
    public static Result success(){
        return new Result(1, "success", null);
    }

    //select     successful response
    public static Result success(Object data){
        return new Result(1, "success", data);
    }

    //failed response
    public static Result error(String msg){
        return new Result(0, msg, null);
    }
}
