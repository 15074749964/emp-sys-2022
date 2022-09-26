package cn.kgc.emp.vo;

import cn.kgc.common.vo.MyPage;
import lombok.Data;

import java.util.Date;

@Data
public class EmpQuery extends MyPage {
    private String name;
    private Date birthday;
}
