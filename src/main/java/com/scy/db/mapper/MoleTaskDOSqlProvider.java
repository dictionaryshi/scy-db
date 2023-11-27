package com.scy.db.mapper;

import com.scy.db.model.MoleTaskDO;
import com.scy.db.model.MoleTaskDOExample;
import com.scy.db.model.MoleTaskDOExample.Criteria;
import com.scy.db.model.MoleTaskDOExample.Criterion;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class MoleTaskDOSqlProvider {
    public String countByExample(MoleTaskDOExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("mole_task");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(MoleTaskDOExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("mole_task");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(MoleTaskDO row) {
        SQL sql = new SQL();
        sql.INSERT_INTO("mole_task");

        if (row.getNextExeTime() != null) {
            sql.VALUES("next_exe_time", "#{nextExeTime,jdbcType=TIMESTAMP}");
        }

        if (row.getExeIntervalSec() != null) {
            sql.VALUES("exe_interval_sec", "#{exeIntervalSec,jdbcType=INTEGER}");
        }

        if (row.getExeCount() != null) {
            sql.VALUES("exe_count", "#{exeCount,jdbcType=INTEGER}");
        }

        if (row.getMaxExeCount() != null) {
            sql.VALUES("max_exe_count", "#{maxExeCount,jdbcType=INTEGER}");
        }

        if (row.getExeStatus() != null) {
            sql.VALUES("exe_status", "#{exeStatus,jdbcType=INTEGER}");
        }

        if (row.getSharedId() != null) {
            sql.VALUES("shared_id", "#{sharedId,jdbcType=BIGINT}");
        }

        if (row.getParamTypeJson() != null) {
            sql.VALUES("param_type_json", "#{paramTypeJson,jdbcType=VARCHAR}");
        }

        if (row.getTargetClassName() != null) {
            sql.VALUES("target_class_name", "#{targetClassName,jdbcType=VARCHAR}");
        }

        if (row.getMethodName() != null) {
            sql.VALUES("method_name", "#{methodName,jdbcType=VARCHAR}");
        }

        if (row.getBeanName() != null) {
            sql.VALUES("bean_name", "#{beanName,jdbcType=VARCHAR}");
        }

        if (row.getEnv() != null) {
            sql.VALUES("env", "#{env,jdbcType=VARCHAR}");
        }

        if (row.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getErrorMessage() != null) {
            sql.VALUES("error_message", "#{errorMessage,jdbcType=LONGVARCHAR}");
        }

        if (row.getParamsJson() != null) {
            sql.VALUES("params_json", "#{paramsJson,jdbcType=LONGVARCHAR}");
        }

        return sql.toString();
    }

    public String selectByExampleWithBLOBs(MoleTaskDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("next_exe_time");
        sql.SELECT("exe_interval_sec");
        sql.SELECT("exe_count");
        sql.SELECT("max_exe_count");
        sql.SELECT("exe_status");
        sql.SELECT("shared_id");
        sql.SELECT("param_type_json");
        sql.SELECT("target_class_name");
        sql.SELECT("method_name");
        sql.SELECT("bean_name");
        sql.SELECT("env");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.SELECT("error_message");
        sql.SELECT("params_json");
        sql.FROM("mole_task");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        if (example != null && example.getLimit() != null) {
            if (example.getOffset() == null) {
                return sql.toString() + " limit " + example.getLimit();
            } else {
                return sql.toString() + " limit " + example.getOffset() + ", " + example.getLimit();
            }
        }

        return sql.toString();
    }

    public String selectByExample(MoleTaskDOExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("next_exe_time");
        sql.SELECT("exe_interval_sec");
        sql.SELECT("exe_count");
        sql.SELECT("max_exe_count");
        sql.SELECT("exe_status");
        sql.SELECT("shared_id");
        sql.SELECT("param_type_json");
        sql.SELECT("target_class_name");
        sql.SELECT("method_name");
        sql.SELECT("bean_name");
        sql.SELECT("env");
        sql.SELECT("created_at");
        sql.SELECT("updated_at");
        sql.FROM("mole_task");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        if (example != null && example.getLimit() != null) {
            if (example.getOffset() == null) {
                return sql.toString() + " limit " + example.getLimit();
            } else {
                return sql.toString() + " limit " + example.getOffset() + ", " + example.getLimit();
            }
        }

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        MoleTaskDO row = (MoleTaskDO) parameter.get("row");
        MoleTaskDOExample example = (MoleTaskDOExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("mole_task");

        if (row.getId() != null) {
            sql.SET("id = #{row.id,jdbcType=BIGINT}");
        }

        if (row.getNextExeTime() != null) {
            sql.SET("next_exe_time = #{row.nextExeTime,jdbcType=TIMESTAMP}");
        }

        if (row.getExeIntervalSec() != null) {
            sql.SET("exe_interval_sec = #{row.exeIntervalSec,jdbcType=INTEGER}");
        }

        if (row.getExeCount() != null) {
            sql.SET("exe_count = #{row.exeCount,jdbcType=INTEGER}");
        }

        if (row.getMaxExeCount() != null) {
            sql.SET("max_exe_count = #{row.maxExeCount,jdbcType=INTEGER}");
        }

        if (row.getExeStatus() != null) {
            sql.SET("exe_status = #{row.exeStatus,jdbcType=INTEGER}");
        }

        if (row.getSharedId() != null) {
            sql.SET("shared_id = #{row.sharedId,jdbcType=BIGINT}");
        }

        if (row.getParamTypeJson() != null) {
            sql.SET("param_type_json = #{row.paramTypeJson,jdbcType=VARCHAR}");
        }

        if (row.getTargetClassName() != null) {
            sql.SET("target_class_name = #{row.targetClassName,jdbcType=VARCHAR}");
        }

        if (row.getMethodName() != null) {
            sql.SET("method_name = #{row.methodName,jdbcType=VARCHAR}");
        }

        if (row.getBeanName() != null) {
            sql.SET("bean_name = #{row.beanName,jdbcType=VARCHAR}");
        }

        if (row.getEnv() != null) {
            sql.SET("env = #{row.env,jdbcType=VARCHAR}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{row.createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{row.updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getErrorMessage() != null) {
            sql.SET("error_message = #{row.errorMessage,jdbcType=LONGVARCHAR}");
        }

        if (row.getParamsJson() != null) {
            sql.SET("params_json = #{row.paramsJson,jdbcType=LONGVARCHAR}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(MoleTaskDO row) {
        SQL sql = new SQL();
        sql.UPDATE("mole_task");

        if (row.getNextExeTime() != null) {
            sql.SET("next_exe_time = #{nextExeTime,jdbcType=TIMESTAMP}");
        }

        if (row.getExeIntervalSec() != null) {
            sql.SET("exe_interval_sec = #{exeIntervalSec,jdbcType=INTEGER}");
        }

        if (row.getExeCount() != null) {
            sql.SET("exe_count = #{exeCount,jdbcType=INTEGER}");
        }

        if (row.getMaxExeCount() != null) {
            sql.SET("max_exe_count = #{maxExeCount,jdbcType=INTEGER}");
        }

        if (row.getExeStatus() != null) {
            sql.SET("exe_status = #{exeStatus,jdbcType=INTEGER}");
        }

        if (row.getSharedId() != null) {
            sql.SET("shared_id = #{sharedId,jdbcType=BIGINT}");
        }

        if (row.getParamTypeJson() != null) {
            sql.SET("param_type_json = #{paramTypeJson,jdbcType=VARCHAR}");
        }

        if (row.getTargetClassName() != null) {
            sql.SET("target_class_name = #{targetClassName,jdbcType=VARCHAR}");
        }

        if (row.getMethodName() != null) {
            sql.SET("method_name = #{methodName,jdbcType=VARCHAR}");
        }

        if (row.getBeanName() != null) {
            sql.SET("bean_name = #{beanName,jdbcType=VARCHAR}");
        }

        if (row.getEnv() != null) {
            sql.SET("env = #{env,jdbcType=VARCHAR}");
        }

        if (row.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }

        if (row.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }

        if (row.getErrorMessage() != null) {
            sql.SET("error_message = #{errorMessage,jdbcType=LONGVARCHAR}");
        }

        if (row.getParamsJson() != null) {
            sql.SET("params_json = #{paramsJson,jdbcType=LONGVARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, MoleTaskDOExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}