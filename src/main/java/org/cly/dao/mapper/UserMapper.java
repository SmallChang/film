package org.cly.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.cly.dao.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author changliyao
 * @since 2019-11-18
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> getUsers(IPage<User> page);
}
