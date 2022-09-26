package cn.kgc.common.vo;

import cn.kgc.common.constants.ResultConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        return new Result<>(ResultConstant.RESULT_SUCCESS,"success",null);
    }

    public static <T> Result<T> success(String message){
        return new Result<>(ResultConstant.RESULT_SUCCESS,message,null);
    }

    public static <T> Result<T> success(String message,T data){
        return new Result<>(ResultConstant.RESULT_SUCCESS,message,data);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(ResultConstant.RESULT_SUCCESS,"success",data);
    }

    public static <T> Result<T> fail(){
        return new Result<>(ResultConstant.RESULT_FAIL,"fail",null);
    }

    public static <T> Result<T> fail(Integer code){
        return new Result<>(code,"fail",null);
    }

    public static <T> Result<T> fail(Integer code,String message){
        return new Result<>(code,message,null);
    }

    public static <T> Result<T> fail(String message){
        return new Result<>(ResultConstant.RESULT_FAIL,message,null);
    }
}
