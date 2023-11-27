package com.scy.db.mapper;

import com.scy.db.model.MoleTaskDO;
import com.scy.db.model.MoleTaskDOExample;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MoleTaskDOMapper {
    @SelectProvider(type = MoleTaskDOSqlProvider.class, method = "countByExample")
    long countByExample(MoleTaskDOExample example);

    @DeleteProvider(type = MoleTaskDOSqlProvider.class, method = "deleteByExample")
    int deleteByExample(MoleTaskDOExample example);

    @Delete({
            "delete from mole_task",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @InsertProvider(type = MoleTaskDOSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(MoleTaskDO row);

    @SelectProvider(type = MoleTaskDOSqlProvider.class, method = "selectByExampleWithBLOBs")
    List<MoleTaskDO> selectByExampleWithBLOBs(MoleTaskDOExample example);

    @SelectProvider(type = MoleTaskDOSqlProvider.class, method = "selectByExample")
    List<MoleTaskDO> selectByExample(MoleTaskDOExample example);

    @Select({
            "select",
            "id, next_exe_time, exe_interval_sec, exe_count, max_exe_count, exe_status, shared_id, ",
            "param_type_json, target_class_name, method_name, bean_name, env, created_at, ",
            "updated_at, error_message, params_json",
            "from mole_task",
            "where id = #{id,jdbcType=BIGINT}"
    })
    MoleTaskDO selectByPrimaryKey(Long id);

    @UpdateProvider(type = MoleTaskDOSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("row") MoleTaskDO row, @Param("example") MoleTaskDOExample example);

    @UpdateProvider(type = MoleTaskDOSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MoleTaskDO row);
}