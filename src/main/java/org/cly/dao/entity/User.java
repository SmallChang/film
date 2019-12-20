package org.cly.dao.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
/**
 * <p>
 * 
 * </p>
 *
 * @author changliyao
 * @since 2019-11-18
 */
@Builder
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;

    private String userName;

    private String userPwd;

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
