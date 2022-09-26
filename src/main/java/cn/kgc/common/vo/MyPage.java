package cn.kgc.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyPage implements Serializable {
    private Integer pageNo;
    private Integer pageSize;
}
