package leo.wan.common;

import leo.wan.dao.RoleMapper;
import leo.wan.dao.SupplierAnswerMapper;
import leo.wan.model.Role;
import leo.wan.model.SupplierAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class SupplierAnswerService2 {
    @Autowired
    private SupplierAnswerMapper supplierAnswerMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 设单独的置事务的传播机制，是的该方法不在外层事务里面，而是独立的事务
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void add() {
        Role role = new Role();
        role.setIntTotal(1);
        role.setRolelevel("23");
        role.setDecemilToal(new BigDecimal(3));
        roleMapper.insert(role);
    }
}
