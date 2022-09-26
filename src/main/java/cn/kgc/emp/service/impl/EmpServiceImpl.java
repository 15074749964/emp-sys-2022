package cn.kgc.emp.service.impl;

import cn.kgc.emp.entity.Emp;
import cn.kgc.emp.mapper.EmpMapper;
import cn.kgc.emp.service.IEmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kgc
 * @since 2022-07-19
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {

}
